package com.anugraha.birt.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;

import logger.BIRT_Logger;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import persistence.BIRT_Persistence_TestCase;
import utility.file.BIRT_DownloadFileFilter;
import utility.file.DirectoryUtility;
import utility.file.FileUtility;
import utility.file.excel.ExcelMacroRun;
import utility.file.html.BIRT_PrintSaveTestSuiteResult;
import utility.framework.BRTEnumerator;
import utility.string.StringUtility;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;
import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import framework.dbconnection.driver.BIRT_DBConnectionDriver;
import framework.dbconnection.driver.BIRT_DBConnectionDriverFactory;
import framework.selenium.driver.exec_engine.BIRT_SeleniumDriverFactory;
import framework.selenium.driver.web.BIRT_WebDriver;
import framework.selenium.driver.web.BIRT_WebDriverFactory;

public class BIRT_AppDriver_Parameter
{
	private static final String	TESTCASE_SOURCE_DIR_NAME			= "Source";
	private static final String	TESTCASE_REPORT_DIR_NAME			= "Report";
	private static final String	TESTCASE_REPORT_SOURCE_SHEETNAME	= "Sourcedata";
	private static final String	TESTCASE_REPORT_TARGET_SHEETNAME	= "Targetdata";
	private static final long	FILE_DOWNLOAD_TIMEOUT_IN_MS			= 1200000;
	private static final long	FILE_DOWNLOAD_CHECK_INTERVAL		= 500;

	private static final int	NO_OF_SRC_RECORDS					= 0;
	private static final int	NO_OF_TRGET_RECORDS					= 1;
	private static final int	NO_OF_RECORDS_PASSED				= 2;
	private static final int	NO_OF_RECORDS_FAILED				= 3;
	private static final int	TOTAL_STATS							= 4;

	public boolean runTestSuite(int iProjectID, int iTestSuiteID) throws BIRT_Exception
	{
		// System.out.println("Hello, I am inside runTestSuite...");

		boolean isAllTestCaseSuccess = true;

		// Initialize App Properties
		BIRT_Logger.debug("Initialize App Properties");
		BIRT_AppProperty.initializeProperties();

		Calendar clTestSuiteStart = new GregorianCalendar();

		// Setup Archive Mechanism
		BIRT_Logger.debug("Setup Archive Mechanism");
		String strArchiveDirectory = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_ARCHIVE_DIR);
		if (DirectoryUtility.isValidDirectory(strArchiveDirectory))
		{
			// Create Directory Project
			BIRT_Logger.debug("Create Directory Project");
			String strProjectID = BIRT_DataObject.getIProjectID() + "";
			if (DirectoryUtility.createDirectory(strArchiveDirectory, strProjectID))
			{
				String strProjectDir = strArchiveDirectory + FileUtility.FILE_PATH_SEPARATOR + strProjectID;
				// Create Directory TestSuiteID
				BIRT_Logger.debug("Create Directory TestSuiteID");
				String strTestSuiteID = BIRT_DataObject.getIExecute_TestSuiteID() + "";
				if (DirectoryUtility.createDirectory(strProjectDir, strTestSuiteID))
				{
					String strTestSuiteDir = strProjectDir + FileUtility.FILE_PATH_SEPARATOR + strTestSuiteID;
					// Create CurrentTimeStamp
					BIRT_Logger.debug("Create CurrentTimeStamp");
					Calendar clNow = new GregorianCalendar();
					String currentTimeStamp = clNow.get(Calendar.YEAR) + "-" + (clNow.get(Calendar.MONTH) + 1) + "-" + clNow.get(Calendar.DATE) + "-" + clNow.get(Calendar.HOUR_OF_DAY) + "-"
							+ clNow.get(Calendar.MINUTE) + "-" + clNow.get(Calendar.MILLISECOND);

					if (DirectoryUtility.createDirectory(strTestSuiteDir, currentTimeStamp))
					{
						// Setup CurrentTestSuiteTimeStampFolder in BIRT_DataObject
						BIRT_Logger.debug("Setup CurrentTestSuiteTimeStampFolder in BIRT_DataObject");
						String strCurrentTestSuiteTimeStampFolder = strTestSuiteDir + FileUtility.FILE_PATH_SEPARATOR + currentTimeStamp;
						BIRT_DataObject.setStrCurrentTestSuiteTimeStampFolder(strCurrentTestSuiteTimeStampFolder);

						int iExecuteProjectID = BIRT_DataObject.getIProjectID();

						int iExecuteTestCases[] = BIRT_Persistence.getObjBIPersistence_TestSuite().getTestSuiteTestCases(BIRT_DataObject.getIExecute_TestSuiteID(), iExecuteProjectID);
						int ino_of_TestCases = iExecuteTestCases.length;

						Object[] oTestCaseResults = new Object[ino_of_TestCases];

						//************************************************
						// Zekun_change
						// Read parameters
						String fileName = "addSteps.txt";
						String line = null;
						// Save all steps in the ArrayList 
						ArrayList<Object> addSteps = new ArrayList<Object>();
						// int count = 0;
						try{
							FileReader fileReader = new FileReader(fileName);
							BufferedReader bufferedReader = new BufferedReader(fileReader);
							
							while((line = bufferedReader.readLine()) != null){
								String[] tmp = line.split(" ");
								Object[] objSingleTestStep = new Object[5];
								// TESTSTEP_OBJECT_SEQNO
								objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO] = new Integer(tmp[0]);
								// TESTSTEP_OBJECT_COMMAND
								objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND] = new String(tmp[1]);
								// TESTSTEP_OBJECT_METHOD
								objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD] = new String(tmp[2]);
								// TESTSTEP_OBJECT_TARGET
								objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET] = new String(tmp[3]);
								// TESTSTEP_OBJECT_VALUE
								objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE] = new String(tmp[4]);	
								addSteps.add(objSingleTestStep);
							}
							bufferedReader.close();
						}catch(FileNotFoundException e){
							// System.out.println("Unable to open file '" + fileName + "'"); 
							e.printStackTrace();  
						}catch(IOException e){
							e.printStackTrace();
						}
						
						int count = addSteps.size();
						final int totalAddSteps = 6;
						// count = count / totalAddSteps;
						// separate all steps for each case
						// will change "6" to an actual number
						
						ArrayList<Object[]> parameterList = new ArrayList<Object[]>();
						int n = 0;
						Object[] testSteps = new Object[totalAddSteps];
						for(int i = 0; i < count; i++){
							testSteps[n++] = addSteps.get(i);
							if(n > 0 && n % totalAddSteps == 0){
								n = 0;
								parameterList.add(testSteps);
								testSteps = new Object[totalAddSteps];
							}
						}

						int testCaseID = iExecuteTestCases[0];
						//************************************************	
						// Zekun_change
						for (int iCtr = 0; iCtr < count / totalAddSteps; iCtr++)
						{
							// Run Each of the TestCase
							BIRT_Logger.debug("Run Each of the TestCase");
							/*
							 * Object[] - Return TestCase Status, TestCase Name, Test Case Description, Total No of Records, No Of Records Passed, No Of Records Failed, TestCase Report Path, Remark.
							 * Object[0] - Boolean - TestCase Status
							 * Object[1] - String  - TestCase Name
							 * Object[2] - Integer  - No. Of Source Records
							 * Object[3] - Integer  - No. Of Target Records
							 * Object[4] - Integer  - No. Of Records Passed
							 * Object[5] - Integer  - No. of Records Failed
							 * Object[6] - String  - Report Path.
							 * Object[7] - String  - Remark.
							 */
							
							// Zekun_change
							oTestCaseResults[iCtr] = runTestCase(iExecuteProjectID, testCaseID, parameterList.get(iCtr));

							if (!(Boolean) ((Object[]) oTestCaseResults[iCtr])[0])
								isAllTestCaseSuccess = false;

							//Clean up SideEffects after Test Case Execution
							BIRT_Logger.debug("Clean up SideEffects after Test Case Execution");
							BIRT_DataObject.cleanUpAfterTestCase();
						}

						// After running all the test cases print the statistics to a file in TestSuite folder.
						BIRT_Logger.debug("After running all the test cases print the statistics to a file in TestSuite folder.");
						BIRT_PrintSaveTestSuiteResult objBIRT_PrintSaveTestSuiteResult = new BIRT_PrintSaveTestSuiteResult();
						objBIRT_PrintSaveTestSuiteResult.printNSaveTestSuiteResultHTML(BIRT_DataObject.getIExecute_TestSuiteID(), strCurrentTestSuiteTimeStampFolder, oTestCaseResults, clTestSuiteStart, iProjectID);

						return isAllTestCaseSuccess;
					}
				}
				else
				{
					BIRT_Logger.error("Error in creating TestSuite Directory in Archive Directory. Please try again later.");
					throw new BIRT_Temp_Exception("Error in creating TestSuite Directory in Archive Directory. Please try again later.");
				}
			}
			else
			{
				BIRT_Logger.error("Error in creating Project Directory in Archive Directory. Please try again later.");
				throw new BIRT_Temp_Exception("Error in creating Project Directory in Archive Directory. Please try again later.");
			}

		}
		else
		{
			BIRT_Logger.error("Archive Directory is not valid. Please configure the application through Application Configuration Menu");
			throw new BIRT_Temp_Exception("Archive Directory is not valid. Please configure the application through Application Configuration Menu");
		}

		return false;

	}


	/*
	 * Object[] - Return TestCase Status, TestCase Name, Test Case Description, Total No of Records, No Of Records Passed, No Of Records Failed, TestCase Report Path, Remark.
	 * Object[0] - Boolean - TestCase Status
	 * Object[1] - String  - TestCase Name
	 * Object[2] - Integer  - No. Of Source Records
	 * Object[3] - Integer  - No. Of Target Records
	 * Object[4] - Integer  - No. Of Records Passed
	 * Object[5] - Integer  - No. of Records Failed
	 * Object[6] - String  - Report Path.
	 * Object[7] - String  - Remark.
	 */

	// Jump to...here<---
	// public Object[] runTestCase(int iProjectID, int iTestCaseID, String[] parameters) throws BIRT_Exception
	// Zekun_change
	public Object[] runTestCase(int iProjectID, int iTestCaseID, Object[] parameters) throws BIRT_Exception
	{
		// System.out.println("Hello, I am inside runTestCase...");
		// Check whether TestCase exists
		BIRT_Logger.debug("Check whether TestCase exists");
		if (BIRT_Persistence.getObjBIPersistence_TestCase().existsTestCase(iTestCaseID))
		{
			// Get TestCase Data
			BIRT_Logger.debug("Get TestCase Data");
			/*
			 * Object[] - Contents of TestCase saved.
			 * Object[0] - String - TestCaseName
			 * Object[1] - Integer - TypeOfBrowser
			 * Object[2] - String - BaseURL
			 * Object[3] - Integer - TestScriptID
			 * Object[4] - Integer - QueryID
			 * Object[5] - Integer - DBConnectionID
			 * Object[6] - Integer - DownloadedFileType
			 * Object[7] - String - DownloadedFileName 
			 * Object[8] - Integer - ReportHeaderLine 
			 * Object[9] - Integer - DataStartLine
			 */
			Object[] oTestCaseData = BIRT_Persistence.getObjBIPersistence_TestCase().getTestCaseDetails(iTestCaseID);

			// Initalize the folder with the TestCaseID and required folders
			BIRT_Logger.debug("Initalize the folder with the TestCaseID and required folders");
			String currentTestSuiteExecuteFolder = BIRT_DataObject.getStrCurrentTestSuiteTimeStampFolder();
			BIRT_Logger.debug(currentTestSuiteExecuteFolder);

			String strTestCaseID = iTestCaseID + "";
			BIRT_Logger.debug(strTestCaseID);

			String strTestCaseName = (String) oTestCaseData[0];
			BIRT_Logger.debug(strTestCaseName);

			if (DirectoryUtility.createDirectory(currentTestSuiteExecuteFolder, strTestCaseID))
			{
				//Set current TestCaseID execution folder - strCurrentTestCaseIDFolder
				String strCurrentTestCaseIDFolder = currentTestSuiteExecuteFolder + FileUtility.FILE_PATH_SEPARATOR + strTestCaseID;
				BIRT_DataObject.setStrCurrentTestCaseIDFolder(strCurrentTestCaseIDFolder);
				BIRT_Logger.debug("Set current TestCaseID execution folder - " + strCurrentTestCaseIDFolder);

				if (DirectoryUtility.createDirectory(strCurrentTestCaseIDFolder, TESTCASE_SOURCE_DIR_NAME))
				{
					if (DirectoryUtility.createDirectory(strCurrentTestCaseIDFolder, TESTCASE_REPORT_DIR_NAME))
					{
						// Initialize WebDriver According to the TestCase
						BIRT_Logger.debug("Initialize WebDriver According to the TestCase");
						int iTypeOfBrowser = ((Integer) oTestCaseData[1]).intValue();
						BIRT_WebDriverFactory objWebDriverFactory = new BIRT_WebDriverFactory(iTypeOfBrowser);

						BIRT_WebDriver objWebDriver = objWebDriverFactory.getBIRT_WebDriver();

						//Set BASE URL in WebDriver
						BIRT_Logger.debug("Set BASE URL in WebDriver");
						String BASEURL = (String) oTestCaseData[2];
						objWebDriver.setBaseUrl(BASEURL);

						//Set the currentWebDriver in BIRT_Object
						BIRT_Logger.debug("Set the currentWebDriver in BIRT_Object");
						BIRT_DataObject.setObjWebDriver(objWebDriver);

						//Execute TestScript
						BIRT_Logger.debug("Execute TestScript");
						// Run TestScript to download the report.

						BIRT_Logger.debug("Run TestScript to download the report.");
						int iTestScriptID = ((Integer) oTestCaseData[3]).intValue();

						try
						{
							BIRT_Logger.debug("Running TestScript - " + iTestScriptID);
								
							// Zekun_change
							if (execute_TestScript(iProjectID, iTestScriptID, parameters))
							{

								//BIRT_Persistence.getObjBIPersistence_TestCase();

								// Wait for the file to get down loaded.
								BIRT_Logger.debug(" Wait for the file to get down loaded.");
								
								String strRegDownloadFileName = "^.*"+(String) oTestCaseData[7] +".*"+ "\\." + BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT[((Integer) oTestCaseData[6]).intValue()]+".*";
								BIRT_Logger.debug(" Regular Expression for file wait : "+strRegDownloadFileName);
								
								//Divya added to check the download folder provided by user
								
								try{
								String strDownloadDirectory = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_DOWNLOAD_DIR);
								BIRT_Logger.debug("Download Directory : "+strDownloadDirectory);
								File[] objFF = utility.file.FileFinder.search(new File(strDownloadDirectory),strRegDownloadFileName );
								String strDownloadFileName = objFF[0].getName();
								//String strDownloadFileName = "Test.txt";
								BIRT_Logger.debug("DownloadFileName : "+strDownloadFileName);
								// System.out.println("Im here-1");
								try{
								waitForFileDownload(strDownloadFileName);
								}
								catch(Exception e){
									BIRT_Logger.error("Error in waiting for the file to get downloaded");
								}
							//	File[] objFF = utility.file.FileFinder.search(new File("D:\\Temp\\Working Directory\\Download"),strRegDownloadFileName );
								//String strDownloadFileName = objFF[0].getName();

							//	waitForFileDownload(strDownloadFileName);
								

								// Copy the file after down load and place it into folder
								BIRT_Logger.debug("Copy the file after down load and place it into folder");
								// System.out.println("Im here2");
								copySourceFile(strDownloadFileName);
								
								}
								catch(Exception e){
									System.out.println(" exception!!"+e);
								}
								
								//Setup Report File
								BIRT_Logger.debug("Setup Report File");
								// System.out.println("Im here3");
								//Copy and Rename TemplateFile
								BIRT_Logger.debug("Copy and Rename TemplateFile");
								setupReportFile(iTestCaseID);
								// System.out.println("Im here4");
								// Initialize the DB Connection According to the TestCase
								BIRT_Logger.debug("Initialize the DB Connection According to the TestCase");
								// System.out.println("Im here5");
								// get Connection
								BIRT_Logger.debug(" get Connection");
								int DBConnectionID = ((Integer) oTestCaseData[5]).intValue();
								Connection DbConnection = getDBConnection(DBConnectionID);
								// System.out.println("Im here6");
								//Execute the query using the connection
								BIRT_Logger.debug("Execute the query using the connection");
								// System.out.println("Im here7");
								//Extract Data from DB
								BIRT_Logger.debug("Extract Data from DB");

								int QueryID = ((Integer) oTestCaseData[4]).intValue();
								ResultSet rsTestCaseQueryResult = executeQuery(DbConnection, QueryID);

								int iReportHeaderLine = ((Integer) oTestCaseData[8]).intValue();
								int iDataStartLine = ((Integer) oTestCaseData[9]).intValue();
								
								boolean isHeaderPresent = iReportHeaderLine==0?false:true;
								
								
								if(isHeaderPresent)
								{
								String[] strHeaderFieldsSource = extractHeaderFromSource(iReportHeaderLine,((Integer) oTestCaseData[6]).intValue());
								//Write Header details in source and target sheet
								BIRT_Logger.debug("Write Header details in source and target sheet");

								writeHeader(TESTCASE_REPORT_SOURCE_SHEETNAME, strHeaderFieldsSource);
								writeHeader(TESTCASE_REPORT_TARGET_SHEETNAME, strHeaderFieldsSource);

								}
								else
								{
									
									
									try {
										int No_Of_Columns = BIRT_DataObject.getRsmtdCurrentQueryResult().getColumnCount();
										String[] strHeaderFieldsSource = new String[No_Of_Columns];
										
										for(int i=0;i<No_Of_Columns;i++)
											strHeaderFieldsSource[i]="Column_"+(i+1);
										
										//Write Header details in source and target sheet
										BIRT_Logger.debug("Write Header details in source and target sheet");

										writeHeader(TESTCASE_REPORT_SOURCE_SHEETNAME, strHeaderFieldsSource);
										writeHeader(TESTCASE_REPORT_TARGET_SHEETNAME, strHeaderFieldsSource);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										//Skip writing headers
										BIRT_Logger.error("Error in getting no. of Headers. Hence skipped headers");
									}
									
									
									
									isHeaderPresent=true;
									
								}
								// Insert data from DB to excel
								BIRT_Logger.info(" Insert data from DB to excel");

								try
								{
									copyDBDatatoExcel(rsTestCaseQueryResult,isHeaderPresent);
								}
								catch (Exception ex)
								{
									ex.printStackTrace();
									if(BIRT_AppProperty.PRINT_STACK_TRACE)
									BIRT_Logger.printStackTrace(ex);
									BIRT_Logger.error(ex.getMessage());
									
								}

								// Copy the source data into report file
								BIRT_Logger.info("Copy the source data into report file");
								try
								{
									extractSourcetoReport(iDataStartLine,((Integer) oTestCaseData[6]).intValue(),isHeaderPresent);
								}
								catch (Exception ex)
								{
									ex.printStackTrace();
									if(BIRT_AppProperty.PRINT_STACK_TRACE)
									BIRT_Logger.printStackTrace(ex);
									BIRT_Logger.error(ex.getMessage());
									
								}

								// Compare the excel by Running Macro
								BIRT_Logger.info("Compare the excel by Running Macro");

								// Save and exit.

								ExcelMacroRun.runMacroInWorkbook(BIRT_DataObject.getStrCurrentReportFile());

								// Update TestCase Count
								// Insert Statistics of TestCase count - Total no of Records, No. of
								// Records Passed, No. of Records Failed

								/* int[] - 
								 * int[0] - Integer  - No. Of Source Records
								* int[1] - Integer  - No. Of Target Records
								* int[2] - Integer  - No. Of Records Passed
								* int[3] - Integer  - No. of Records Failed
								*/
								int[] iTestCaseStatistics = getTestCaseStatistics();

								//Return TestCase Result
								BIRT_Logger.debug("Return TestCase Result");

								if (null != objWebDriver)
									objWebDriver.tearDown();

								if (iTestCaseStatistics[0] == iTestCaseStatistics[1] && iTestCaseStatistics[0] == iTestCaseStatistics[2])
									return validTestCaseResult(strTestCaseName, iTestCaseStatistics, "Success", true);
								else return validTestCaseResult(strTestCaseName, iTestCaseStatistics, "Failure", false);

							}
							else
							{
								if (null != objWebDriver)
									objWebDriver.tearDown();
								return invalidTestCaseResult(strTestCaseName, "Error in executing TestScript");
							}

						}
						catch (BIRT_Exception ex)
						{if(BIRT_AppProperty.PRINT_STACK_TRACE)
							BIRT_Logger.printStackTrace(ex);
							BIRT_Logger.error(ex.getMessage());
							if (null != objWebDriver)
								objWebDriver.tearDown();
							return invalidTestCaseResult(strTestCaseName, ex.getDisplayableMessage());
						}

					}
					else
					{
						return invalidTestCaseResult(strTestCaseName, "Error in creating Testcase subfolder Archive Directory. Please try again later.");

					}
				}
				else
				{
					return invalidTestCaseResult(strTestCaseName, "Error in creating Testcase subfolder Archive Directory. Please try again later.");

				}

			}
			else
			{
				return invalidTestCaseResult(strTestCaseName, "Error in creating Testcase Archive Directory. Please try again later.");

			}

		}
		else
		{
			return invalidTestCaseResult(iTestCaseID + "", "Invalid TestCase. Please ensure TestCase is not corrupted.");

		}

	}

	private int[] getTestCaseStatistics() throws BIRT_Exception
	{
		try
		{

			File fCurrentReportFile = new File(BIRT_DataObject.getStrCurrentReportFile());

			//Excel Manipulation
			InputStream ipCurrentReport;
			Workbook wbCurrentReport;
			Sheet sheetCurrentReport;
			Row rowCurrentSheet;
			Cell cellCurrentSheet;

			int[] iTestStats = new int[TOTAL_STATS];

			ipCurrentReport = new FileInputStream(fCurrentReportFile);
			wbCurrentReport = WorkbookFactory.create(ipCurrentReport);
			sheetCurrentReport = wbCurrentReport.getSheet("Report");

			//Getting No. Of Source Records
			rowCurrentSheet = sheetCurrentReport.getRow(10);
			cellCurrentSheet = rowCurrentSheet.getCell(1);
			iTestStats[NO_OF_SRC_RECORDS] = (int) cellCurrentSheet.getNumericCellValue();

			//No of Records Passed
			cellCurrentSheet = rowCurrentSheet.getCell(3);
			iTestStats[NO_OF_RECORDS_PASSED] = (int) cellCurrentSheet.getNumericCellValue();

			//No. Of Target Records
			rowCurrentSheet = sheetCurrentReport.getRow(11);
			cellCurrentSheet = rowCurrentSheet.getCell(1);
			iTestStats[NO_OF_TRGET_RECORDS] = (int) cellCurrentSheet.getNumericCellValue();

			//No of Records Failed
			cellCurrentSheet = rowCurrentSheet.getCell(3);
			iTestStats[NO_OF_RECORDS_FAILED] = (int) cellCurrentSheet.getNumericCellValue();

			return iTestStats;
		}
		catch (FileNotFoundException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to get statistics from report.");
		}
		catch (InvalidFormatException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to get statistics from report.");
		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to get statistics from report.");
		}

	}

	private void writeHeader(String testcaseReportSourceSheetname, String[] strHeaderFields) throws BIRT_Exception
	{
		File fCurrentReportFile = new File(BIRT_DataObject.getStrCurrentReportFile());

		//Excel Manipulation
		InputStream ipCurrentReport;
		Workbook wbCurrentReport;
		Sheet sheetCurrentReport;

		try
		{

			ipCurrentReport = new FileInputStream(fCurrentReportFile);
			wbCurrentReport = WorkbookFactory.create(ipCurrentReport);
			sheetCurrentReport = wbCurrentReport.getSheet(testcaseReportSourceSheetname);

			int iRowCtr = 0;

			Row rowCurrentSheet = sheetCurrentReport.getRow(iRowCtr);
			if (rowCurrentSheet == null)
			{
				rowCurrentSheet = sheetCurrentReport.createRow(iRowCtr);
			}

			for (int iCellCtr = 0; iCellCtr < strHeaderFields.length; iCellCtr++)
			{
				Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
				if (cellCurrentSheet == null)
				{
					cellCurrentSheet = rowCurrentSheet.createCell(iCellCtr);
				}
				if (strHeaderFields[iCellCtr] != null || !strHeaderFields[iCellCtr].equals(""))
				{
					cellCurrentSheet.setCellValue(strHeaderFields[iCellCtr].trim());
				}
			}

			FileOutputStream fosCurrentReport = new FileOutputStream(BIRT_DataObject.getStrCurrentReportFile());
			wbCurrentReport.write(fosCurrentReport);
			ipCurrentReport.close();
			fosCurrentReport.flush();
			fosCurrentReport.close();

		}
		catch (FileNotFoundException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);

			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to Edit Report File.");
		}
		catch (InvalidFormatException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Invalid Header Character/Format");
		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to Edit Report File.");
		}
	}

	private String[] extractHeaderFromSource(int reportHeaderLine, int iFileFormatType) throws BIRT_Exception
	{
		File fCurrentSrcFile = new File(BIRT_DataObject.getStrCurrentSourceFile());

		Scanner sc;
		try
		{
			sc = new Scanner(fCurrentSrcFile);
		}
		catch (FileNotFoundException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to find downloaded report");

		}
		int iLineCounter = 1;

		//Skip to HeaderLine
		while (sc.hasNextLine() && iLineCounter < reportHeaderLine)
		{
			sc.nextLine();
			iLineCounter++;

		}

		if (sc.hasNextLine())
		{
			String strNextLine = sc.nextLine();
			iLineCounter++;
			
			if(iFileFormatType==BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_TXT )
			{
				String strCurrentReportFields[] = strNextLine.split("\t",-1);
				sc.close();
				return strCurrentReportFields;
			}
			else if(iFileFormatType==BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_CSV)
			{
				String strCurrentReportFields[] = strNextLine.split(",", -1);
				sc.close();
				return strCurrentReportFields;
			}
			
			String strCurrentReportFields[] = strNextLine.split("\t", -1);
			sc.close();
			return strCurrentReportFields;
			
		}
		else
		{
			sc.close();
			throw new BIRT_Temp_Exception("Report Header Columns not found in Downloaded Report File");
		}

	}

	private void copyDBDatatoExcel(ResultSet rsTestCaseQueryResult, boolean isHeaderPresent) throws BIRT_Exception
	{

		try
		{
			File fCurrentReportFile = new File(BIRT_DataObject.getStrCurrentReportFile());

			int No_Of_Columns = BIRT_DataObject.getRsmtdCurrentQueryResult().getColumnCount();

			//Excel Manipulation
			InputStream ipCurrentReport;
			Workbook wbCurrentReport;
			Sheet sheetCurrentReport;
			Row rowCurrentReportFile;

			ipCurrentReport = new FileInputStream(fCurrentReportFile);
			wbCurrentReport = WorkbookFactory.create(ipCurrentReport);
			sheetCurrentReport = wbCurrentReport.getSheet("Targetdata");

			int iRowCtr = 1;
			
			
			if(isHeaderPresent)
			{
				//Row - 0 Header already written
				iRowCtr=1;
				
			}
			else
				iRowCtr=0;
				
				

			//if (!rsTestCaseQueryResult.first())
			//throw new BIRT_Temp_Exception("No Rows returned by Target Query. Hence no comparison");

			//rsTestCaseQueryResult.beforeFirst();

			while (rsTestCaseQueryResult.next())
			{
				rowCurrentReportFile = sheetCurrentReport.getRow(iRowCtr);
				if (rowCurrentReportFile == null)
				{
					rowCurrentReportFile = sheetCurrentReport.createRow(iRowCtr);
				}

				for (int iCellCtr = 0; iCellCtr < No_Of_Columns; iCellCtr++)
				{
					Cell cellTrgtRpt = rowCurrentReportFile.getCell(iCellCtr);

					BRTEnumerator.writeAutoMetadataTrgtToCell(rowCurrentReportFile, cellTrgtRpt, rsTestCaseQueryResult, iCellCtr + 1);

				}

				iRowCtr++;
			}

			//rsTestCaseQueryResult.close();

			FileOutputStream fosCurrentTrgtReport = new FileOutputStream(BIRT_DataObject.getStrCurrentReportFile());
			wbCurrentReport.write(fosCurrentTrgtReport);
			fosCurrentTrgtReport.flush();
			fosCurrentTrgtReport.close();
			ipCurrentReport.close();

		}
		catch (FileNotFoundException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Database to Excel");

		}
		catch (InvalidFormatException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Database to Excel");
		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Database to Excel");
		}
		catch (SQLException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Database to Excel");
		}

	}

	private ResultSet executeQuery(Connection dbConnection, int QueryID) throws BIRT_Exception
	{
		try
		{
			//Create Statement
			Statement stmtQuery = dbConnection.createStatement();

			//Get Query
			String strQuery = BIRT_Persistence.getObjBIPersistence_Query().getQueryDetail(QueryID);

			//Remove Linebreaks
			StringUtility.removeLineBreak(strQuery);
			System.err.println(strQuery);

			ResultSet rsQueryResult = stmtQuery.executeQuery(strQuery);
			ResultSetMetaData rsmtdQueryResult = rsQueryResult.getMetaData();

			//Set Result Set Metada for Sharing
			BIRT_DataObject.setRsmtdCurrentQueryResult(rsmtdQueryResult);

			return rsQueryResult;

		}
		catch (SQLException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to connect to Database");
		}

	}

	private Connection getDBConnection(int connectionID) throws BIRT_Exception
	{
		Object[] DBConnectionDetail = BIRT_Persistence.getObjBIPersistence_DBConnection().getDBConnectionDetail(connectionID);

		/* Return Type Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PARAMS_TOTAL]
		* Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_TYPE] - ConnectionType - String
		* Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVER] - DBServer - String
		* Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PORT] - DBPort - Integer
		* Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_USERNAME] - Username - String
		* Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PASSWORD] - Password - String
		* Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_DATABASE] - DatabaseName - String
		* Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVICENAME] - ServiceName - String
		*/

		int iConnectionType = ((Integer) DBConnectionDetail[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_TYPE]).intValue();

		BIRT_DBConnectionDriverFactory objConnectionDriverFactory = new BIRT_DBConnectionDriverFactory(iConnectionType);
		Connection conDB = objConnectionDriverFactory.getDBConnectionDriver().getConnection(DBConnectionDetail);
		if (null != conDB)
			return conDB;
		else throw new BIRT_Temp_Exception("Unable to create DB Connection to fetch Target Data");

	}

	private void extractSourcetoReport(int DataStartLine, int iFileFormatType, boolean isHeaderPresent) throws BIRT_Exception, Exception
	{
		try
		{

			File fCurrentReportFile = new File(BIRT_DataObject.getStrCurrentReportFile());
			File fCurrentSrcFile = new File(BIRT_DataObject.getStrCurrentSourceFile());

			int No_Of_Columns = BIRT_DataObject.getRsmtdCurrentQueryResult().getColumnCount();

			//Excel Manipulation
			InputStream ipCurrentReport;
			Workbook wbCurrentReport;
			Sheet sheetCurrentReport;
			Row rowCurrentSheet;

			ipCurrentReport = new FileInputStream(fCurrentReportFile);
			wbCurrentReport = WorkbookFactory.create(ipCurrentReport);
			sheetCurrentReport = wbCurrentReport.getSheet("Sourcedata");
			
			if(iFileFormatType != BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_XLS) {
                //Row - 0 Header already written
                int iRowCtr = 1;
                Scanner sc = new Scanner(fCurrentSrcFile);
                int iLineCounter = 1;
                while (iLineCounter < DataStartLine && sc.hasNextLine()) {
                       sc.nextLine();
                       iLineCounter++;
                }
                while (sc.hasNextLine()) {
                       rowCurrentSheet = sheetCurrentReport.getRow(iRowCtr);
                       if (rowCurrentSheet == null) {
                              rowCurrentSheet = sheetCurrentReport.createRow(iRowCtr);
                       }
                       String strNextLine = sc.nextLine();
                       System.err.println("File Format Type passed: "+iFileFormatType);
                       if(iFileFormatType==BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_TXT) {
                              String strCurrentReportFields[] = strNextLine.split("\t", -1);
                              // System.out.println(strCurrentReportFields.length + " = " + No_Of_Columns);
                              if(strCurrentReportFields.length==No_Of_Columns)
                                     for (int iCellCtr = 0; iCellCtr < No_Of_Columns; iCellCtr++) {
                                            System.err.println(strCurrentReportFields[iCellCtr]);
                                            Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
                                            BRTEnumerator.writeAutoMetadataSrcToCell(rowCurrentSheet, cellCurrentSheet, strCurrentReportFields[iCellCtr], iCellCtr + 1);
                                     }
                       } else if (iFileFormatType==BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_CSV) {
                              String strCurrentReportFields[] = strNextLine.split(",");
                              System.err.println("Got inside CSV. length of split content: "+strCurrentReportFields.length);
                              if(strCurrentReportFields.length==No_Of_Columns)
                                     for (int iCellCtr = 0; iCellCtr < No_Of_Columns; iCellCtr++) {
                                            System.err.println(strCurrentReportFields[iCellCtr]);
                                            Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
                                            BRTEnumerator.writeAutoMetadataSrcToCell(rowCurrentSheet, cellCurrentSheet, strCurrentReportFields[iCellCtr], iCellCtr + 1);
                                     }
                       }
                       iRowCtr++;
                }
                sc.close();
          } else { // Code to read normal excel starts here
                HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fCurrentReportFile));
                HSSFSheet worksheet = workbook.getSheet(workbook.getSheetName(0)); // Assumption first sheet will always have data
                //HSSFRow row1 = worksheet.getRow(DataStartLine);

                Iterator<Row> rowIter = worksheet.rowIterator();
                //Code to reach data start line
                int i = 0;
                while (rowIter.hasNext() && i < DataStartLine) {
                       rowIter.next();
                       i++;
                }
                int iRowCtr = 1; // First line already taken by header
                 while(rowIter.hasNext()){
                       rowCurrentSheet = sheetCurrentReport.getRow(iRowCtr);
                       if (rowCurrentSheet == null) {
                              rowCurrentSheet = sheetCurrentReport.createRow(iRowCtr);
                       }
                       HSSFRow myRow = (HSSFRow) rowIter.next();
                       Iterator<Cell> cellIter = myRow.cellIterator();
                       for (int iCellCtr = 0; cellIter.hasNext(); iCellCtr++) {
                              Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
                              BRTEnumerator.writeAutoMetadataSrcToCellFromExcel(rowCurrentSheet, cellCurrentSheet, cellIter.next(), iCellCtr + 1);
                       }
                       iRowCtr++;
                }
          }




			
			
			
			
			
			// COde change

			/*//Row - 0 Header already written
			int iRowCtr = 1;
			if(isHeaderPresent)
			{
				//Row - 0 Header already written
				iRowCtr = 1;
			}
			else
				iRowCtr=0;

			Scanner sc = new Scanner(fCurrentSrcFile);

			int iLineCounter = 1;
			while (iLineCounter < DataStartLine && sc.hasNextLine())
			{
				sc.nextLine();
				iLineCounter++;
			}

			while (sc.hasNextLine())
			{
				rowCurrentSheet = sheetCurrentReport.getRow(iRowCtr);
				if (rowCurrentSheet == null)
				{
					rowCurrentSheet = sheetCurrentReport.createRow(iRowCtr);
				}

				String strNextLine = sc.nextLine();
				
				
				System.err.println("File Format Type passed: "+iFileFormatType);
				
				if(iFileFormatType==BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_TXT)
				{
					String strCurrentReportFields[] = strNextLine.split("\t");
					//if(strCurrentReportFields.length==No_Of_Columns)
						for (int iCellCtr = 0; iCellCtr < No_Of_Columns; iCellCtr++)
						{
							System.err.println(strCurrentReportFields[iCellCtr]);
							Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
							BRTEnumerator.writeAutoMetadataSrcToCell(rowCurrentSheet, cellCurrentSheet, strCurrentReportFields[iCellCtr], iCellCtr + 1);
						}

						
				}
				else if (iFileFormatType==BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_CSV)
				{
					String strCurrentReportFields[] = strNextLine.split(",");
					System.err.println("Got inside CSV. length of split content: "+strCurrentReportFields.length);
					//if(strCurrentReportFields.length==No_Of_Columns)
						for (int iCellCtr = 0; iCellCtr < No_Of_Columns; iCellCtr++)
						{
							System.err.println(strCurrentReportFields[iCellCtr]);
							Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
							BRTEnumerator.writeAutoMetadataSrcToCell(rowCurrentSheet, cellCurrentSheet, strCurrentReportFields[iCellCtr], iCellCtr + 1);
						}

				}

				

				iRowCtr++;

			}*/

			FileOutputStream fosCurrentReport = new FileOutputStream(BIRT_DataObject.getStrCurrentReportFile());
			wbCurrentReport.write(fosCurrentReport);
			fosCurrentReport.flush();
			fosCurrentReport.close();
			ipCurrentReport.close();
			//sc.close(); -- Also part of code chnage
		}
		catch (FileNotFoundException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");

		}
		catch (InvalidFormatException e)
		{
			e.printStackTrace();
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}

	}

	private void setupReportFile(int iTestCaseID) throws BIRT_Exception
	{
		String strTemplateFilePath = BIRT_AppProperty.REPORT_TEMPLATE_FILE;
		String strReportFilePath = BIRT_DataObject.getStrCurrentTestCaseIDFolder() + FileUtility.FILE_PATH_SEPARATOR + TESTCASE_REPORT_DIR_NAME + FileUtility.FILE_PATH_SEPARATOR + iTestCaseID + "."
				+ BIRT_AppProperty.REPORT_EXTN;
		File fTemplateFilePath = new File(strTemplateFilePath);
		File fReportFilePath = new File(strReportFilePath);
		// System.out.println("Im here1");

		try
		{
			FileUtils.copyFile(fTemplateFilePath, fReportFilePath);
			BIRT_DataObject.setStrCurrentReportFile(strReportFilePath);
		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to create TestCase Report.");
		}

	}

	private void copySourceFile(String strDownloadFileName) throws BIRT_Exception
	{
		String sourceFilePath = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_DOWNLOAD_DIR) + FileUtility.FILE_PATH_SEPARATOR + strDownloadFileName;
		String destFilePath = BIRT_DataObject.getStrCurrentTestCaseIDFolder() + FileUtility.FILE_PATH_SEPARATOR + TESTCASE_SOURCE_DIR_NAME + FileUtility.FILE_PATH_SEPARATOR + strDownloadFileName;

		File fSourceFile = new File(sourceFilePath);
		File fDestFile = new File(destFilePath);
		try
		{
			FileUtils.moveFile(fSourceFile, fDestFile);
			// System.out.println(fDestFile.toString());
			BIRT_DataObject.setStrCurrentSourceFile(destFilePath);
		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to copy downloaded file for processing.");
		}
	}

	private void waitForFileDownload(String strDownloadFileName) throws BIRT_Exception
	{
		String strDownloadPath = new String(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_DOWNLOAD_DIR));
		File fDownloadPath = new File(strDownloadPath);
		FileFilter fileFilter = new BIRT_DownloadFileFilter(strDownloadFileName);

		boolean continueloop = true;
		Date currentTime = new Date();
		long startTime = currentTime.getTime();
		long endTime = startTime + FILE_DOWNLOAD_TIMEOUT_IN_MS;
		while (continueloop)
		{
			try
			{
				Thread.sleep(FILE_DOWNLOAD_CHECK_INTERVAL);
			}
			catch (InterruptedException e)
			{if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
			}

			File[] DirectoryFiles = fDownloadPath.listFiles(fileFilter);
			if (System.currentTimeMillis() < endTime)
			{
				if (null != DirectoryFiles)
				{
					if (DirectoryFiles.length > 0)
					{
						if (DirectoryFiles[0].canRead())
						{
							if (DirectoryFiles[0].length() > 0)
							{
								continueloop = false;
							}
						}
					}
				}
			}
			else throw new BIRT_Temp_Exception("File Download Timeout.");

		}

	}

	// Zekun_change
	// private boolean execute_TestScript(int projectID, int testScriptID, String[] parameters) throws BIRT_Exception
	private boolean execute_TestScript(int projectID, int testScriptID, Object[] parameters) throws BIRT_Exception
	{
		// System.out.println("Finally...I am here to execute test script...");
		Object[] testSteps = BIRT_Persistence.getObjBIPersistence_TestScript().getTestStepsforTestScript(projectID, testScriptID);
		
		int no_of_TestSteps = testSteps.length;
//		boolean isTestScriptSuccess = true;
//		for (int iCtr = 0; iCtr < no_of_TestSteps; iCtr++){
//			if (execute_TestStep((Object[]) testSteps[iCtr]) == false){
//				isTestScriptSuccess = false;
//			}
//		}
		
		// Zekun_change
		// add additional steps
		Object[] newTestSteps = new Object[no_of_TestSteps + 6];
		int i = 0;
		// "10" is the number of steps before encounting additional steps
		for(; i < 10; i++){
			newTestSteps[i] = testSteps[i];
		}
		for(; i < 16; i++){
			newTestSteps[i] = parameters[i - 10];
		}

		for(; i < no_of_TestSteps + 6; i++){
			newTestSteps[i] = parameters[i - 6];
		}
		
		boolean isTestScriptSuccess = true;
		for (int iCtr = 0; iCtr < no_of_TestSteps + 6; iCtr++){
			if (execute_TestStep((Object[]) newTestSteps[iCtr]) == false){
				isTestScriptSuccess = false;
			}
		}
		return isTestScriptSuccess;
	}

	private boolean execute_TestStep(Object[] testStep) throws BIRT_Exception
	{
		// System.out.println("***execute_TestStep***");
		// System.out.println(BIRT_DataObject.getObjWebDriver());
		// System.out.println(testStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND]);
		// System.out.println((String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD]);
		// System.out.println((String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE]);
		// System.out.println((String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET]);
		// System.out.println("***end_execute_TestStep***");
		
		return BIRT_SeleniumDriverFactory.getBIRT_SeleniumDriver().executeDriverCommand(BIRT_DataObject.getObjWebDriver(), (String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND],
				(String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD], (String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE], (String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET]);

	}

	/*
	 * Object[] - Return TestCase Status, TestCase Name, Test Case Description, Total No of Records, No Of Records Passed, No Of Records Failed, TestCase Report Path, Remark.
	 * Object[0] - Boolean - TestCase Status
	 * Object[1] - String  - TestCase Name
	 * Object[2] - Integer  - No. Of Source Records
	 * Object[3] - Integer  - No. Of Target Records
	 * Object[4] - Integer  - No. Of Records Passed
	 * Object[5] - Integer  - No. of Records Failed
	 * Object[6] - String  - Report Path.
	 * Object[7] - String  - Remark.
	 */

	private Object[] invalidTestCaseResult(String TestCaseName, String remark)
	{
		Object[] oInvalidTestCaseResult = new Object[8];
		oInvalidTestCaseResult[0] = new Boolean(false);
		oInvalidTestCaseResult[1] = new String(TestCaseName);
		oInvalidTestCaseResult[2] = new Integer(-1);
		oInvalidTestCaseResult[3] = new Integer(-1);
		oInvalidTestCaseResult[4] = new Integer(-1);
		oInvalidTestCaseResult[5] = new Integer(-1);
		oInvalidTestCaseResult[6] = new String(BIRT_DataObject.getStrCurrentReportFile());
		oInvalidTestCaseResult[7] = new String(remark);
		return oInvalidTestCaseResult;
	}

	private Object[] validTestCaseResult(String TestCaseName, int[] stats, String remark, boolean isSuccess)
	{
		Object[] oInvalidTestCaseResult = new Object[8];
		oInvalidTestCaseResult[0] = new Boolean(isSuccess);
		oInvalidTestCaseResult[1] = new String(TestCaseName);
		oInvalidTestCaseResult[2] = new Integer(stats[NO_OF_SRC_RECORDS]);
		oInvalidTestCaseResult[3] = new Integer(stats[NO_OF_TRGET_RECORDS]);
		oInvalidTestCaseResult[4] = new Integer(stats[NO_OF_RECORDS_PASSED]);
		oInvalidTestCaseResult[5] = new Integer(stats[NO_OF_RECORDS_FAILED]);
		oInvalidTestCaseResult[6] = new String(BIRT_DataObject.getStrCurrentReportFile());
		oInvalidTestCaseResult[7] = new String(remark);
		return oInvalidTestCaseResult;
	}

}
