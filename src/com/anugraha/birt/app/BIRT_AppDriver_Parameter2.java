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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import persistence.BIRT_Persistence_TestCase;
import utility.file.BIRT_DownloadFileFilter;
import utility.file.DirectoryUtility;
import utility.file.FileUtility;
import utility.file.excel.ExcelMacroRun;
import utility.file.html.BIRT_PrintSaveTestSuiteResult;
import utility.framework.BRTEnumerator;
import utility.process.ProcessControl;
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
import framework.selenium.driver.web.firefox.BIRT_FirefoxDriver;

public class BIRT_AppDriver_Parameter2
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

	// ZekunPlay
	public boolean runTestSuite(int iProjectID, int iTestSuiteID, Object[] parameters, String query) throws BIRT_Exception
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

						for (int iCtr = 0; iCtr < ino_of_TestCases; iCtr++)
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
							
							// ZekunPlay
							oTestCaseResults[iCtr] = runTestCase(iExecuteProjectID, iExecuteTestCases[iCtr], parameters, query);

							if (!(Boolean) ((Object[]) oTestCaseResults[iCtr])[0])
								isAllTestCaseSuccess = false;
							
							// System.out.println("Finish run all test case, I am back");
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

	// zekun-start
	// ZekunPlay
	public Object[] runTestCase(int iProjectID, int iTestCaseID, Object[] parameters, String query) throws BIRT_Exception
	{
//	public Object[] runTestCase(int iProjectID, int iTestCaseID) throws BIRT_Exception
//	{
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

			
			// System.out.println("Finish getting details...");
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
						// System.out.println("iTypeOfBrowser " + iTypeOfBrowser);
						BIRT_WebDriverFactory objWebDriverFactory = new BIRT_WebDriverFactory(iTypeOfBrowser);
						// System.out.println(objWebDriverFactory);
						// System.out.println("It's still right here 1...");

						// ZReverse
						BIRT_WebDriver objWebDriver = null;
						//BIRT_FirefoxDriver() objWebDriver = null;
						//BIRT_WebDriver objWebDriver = new FirefoxDriver();
						// BIRT_WebDriver objWebDriver = objWebDriverFactory.getBIRT_WebDriver();
						try{
							objWebDriver = objWebDriverFactory.getBIRT_WebDriver();
							objWebDriver.getWebDriver().navigate().to("http://www.google.com/");
						}catch(Exception e){
							e.printStackTrace();
						}
						// System.out.println("It's still right here 2...");
						
						//Set BASE URL in WebDriver
						BIRT_Logger.debug("Set BASE URL in WebDriver");
						String BASEURL = (String) oTestCaseData[2];
						//objWebDriver.setBaseUrl(BASEURL);
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
							
							// Zekun-start
							if (execute_TestScript(iProjectID, iTestScriptID, parameters))
							//if (execute_TestScript(iProjectID, iTestScriptID))
							{
								System.out.println("successfully executed all steps.");

								//BIRT_Persistence.getObjBIPersistence_TestCase();
								// System.out.println("*****************F.I.L.E*************************");
								// System.out.println(" Wait for the file to get down loaded.");
								// Wait for the file to get down loaded.
								BIRT_Logger.debug(" Wait for the file to get down loaded.");
								
								String strRegDownloadFileName = "^.*"+(String) oTestCaseData[7] +".*"+ "\\." + BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT[((Integer) oTestCaseData[6]).intValue()]+".*";
								System.out.println(" Regular Expression for file wait : "+strRegDownloadFileName);
								
								BIRT_Logger.debug(" Regular Expression for file wait : "+strRegDownloadFileName);
								
								//Divya added to check the download folder provided by user
								
								try{
									String strDownloadDirectory = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_DOWNLOAD_DIR);
									System.out.println("Download Directory : "+strDownloadDirectory);
									BIRT_Logger.debug("Download Directory : "+strDownloadDirectory);
									File[] objFF = utility.file.FileFinder.search(new File(strDownloadDirectory),strRegDownloadFileName );
									System.out.println("111***********: " + objFF.length);
									System.out.println("111: " + objFF[0].getName());
									String strDownloadFileName = objFF[0].getName();
									//String strDownloadFileName = "Test.txt";
									System.out.println("DownloadFileName : "+strDownloadFileName);
									BIRT_Logger.debug("DownloadFileName : "+strDownloadFileName);
									try{
										waitForFileDownload(strDownloadFileName);
									}catch(Exception e){
										e.printStackTrace();
										BIRT_Logger.error("Error in waiting for the file to get downloaded");
									}
									//	File[] objFF = utility.file.FileFinder.search(new File("D:\\Temp\\Working Directory\\Download"),strRegDownloadFileName );
									//String strDownloadFileName = objFF[0].getName();

									//	waitForFileDownload(strDownloadFileName);
								

									// Copy the file after down load and place it into folder
									BIRT_Logger.debug("Copy the file after down load and place it into folder");
									copySourceFile(strDownloadFileName);
									
									// Zekun-yigaorendanda
									//ProcessControl.killExcel();
									//BIRT_DataObject.cleanUpAfterTestSuite();
								}catch(Exception e){
									System.out.println(" exception!!"+e);
									e.printStackTrace();
								}
								
								//Setup Report File
								BIRT_Logger.debug("Setup Report File");
								//Copy and Rename TemplateFile
								BIRT_Logger.debug("Copy and Rename TemplateFile");
								setupReportFile(iTestCaseID);
								
								
								// System.out.println("**************DB Connection**************");
								// Initialize the DB Connection According to the TestCase
								BIRT_Logger.debug("Initialize the DB Connection According to the TestCase");
								// get Connection
								BIRT_Logger.debug(" get Connection");
								int DBConnectionID = ((Integer) oTestCaseData[5]).intValue();
								Connection DbConnection = getDBConnection(DBConnectionID);
								//Execute the query using the connection
								BIRT_Logger.debug("Execute the query using the connection");
								//Extract Data from DB
								BIRT_Logger.debug("Extract Data from DB");

								int QueryID = ((Integer) oTestCaseData[4]).intValue();
								
								// ZekunPlay
								// System.out.println("--------------------------HHHHHHHHHH-----------------------");
								ResultSet rsTestCaseQueryResult = executeQuery(DbConnection, QueryID, query);
								// System.out.println("--------------------------WWWWWWWWWW-----------------------");
								System.out.println("Now the result from teradata is in rsTestCaseQueryResult");
								// System.out.println("---Got the result already---");

								int iReportHeaderLine = ((Integer) oTestCaseData[8]).intValue();
								int iDataStartLine = ((Integer) oTestCaseData[9]).intValue();
								System.out.println("ReportHeaderLine: " + iReportHeaderLine);
								System.out.println("DataStartLine: " + iDataStartLine);
								boolean isHeaderPresent = iReportHeaderLine==0?false:true;
								
								
								if(isHeaderPresent)
								{
								// System.out.println("isHeaderPresent");
								String[] strHeaderFieldsSource = extractHeaderFromSource(iReportHeaderLine,((Integer) oTestCaseData[6]).intValue());
								//Write Header details in source and target sheet
								BIRT_Logger.debug("Write Header details in source and target sheet");

								writeHeader(TESTCASE_REPORT_SOURCE_SHEETNAME, strHeaderFieldsSource);
								writeHeader(TESTCASE_REPORT_TARGET_SHEETNAME, strHeaderFieldsSource);

								}
								else
								{
									
									// System.out.println("Not isHeaderPresent");
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
										// System.out.println("Error in not isHeaderPresent.");
										BIRT_Logger.error("Error in getting no. of Headers. Hence skipped headers");
									}
									
									
									
									isHeaderPresent=true;
									
								}
								// Insert data from DB to excel
								BIRT_Logger.info(" Insert data from DB to excel");

								try
								{
									// System.out.println("---yep---");
									copyDBDatatoExcel(rsTestCaseQueryResult,isHeaderPresent);
									// System.out.println("---nope---");
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
								// System.out.println("Compare the excel by Running Macro");
								// Save and exit.

								System.out.println("Need Windows system to run the scripts.");
								ExcelMacroRun.runMacroInWorkbook(BIRT_DataObject.getStrCurrentReportFile());
								System.out.println("Suppose finish running the scripts.");
								
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
								System.out.println("-------------------" + iTestCaseStatistics.length + "-------------------");
								for(int i = 0; i < iTestCaseStatistics.length; i++){
									System.out.println(iTestCaseStatistics[i]);
								}
								//Return TestCase Result
								BIRT_Logger.debug("Return TestCase Result");

								if (null != objWebDriver)
									  objWebDriver.tearDown();
								      //objWebDriver.quit();

								if (iTestCaseStatistics[0] == iTestCaseStatistics[1] && iTestCaseStatistics[0] == iTestCaseStatistics[2])
									return validTestCaseResult(strTestCaseName, iTestCaseStatistics, "Success", true);
								else return validTestCaseResult(strTestCaseName, iTestCaseStatistics, "Failure", false);

							}
							else
							{
								if (null != objWebDriver)
									objWebDriver.tearDown();
									//objWebDriver.quit();
								return invalidTestCaseResult(strTestCaseName, "Error in executing TestScript");
							}

						}
						catch (BIRT_Exception ex)
						{if(BIRT_AppProperty.PRINT_STACK_TRACE)
							BIRT_Logger.printStackTrace(ex);
							BIRT_Logger.error(ex.getMessage());
							if (null != objWebDriver)
								objWebDriver.tearDown();
								//objWebDriver.quit();
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

			// System.out.println("Copying data to Excel: " + ipCurrentReport + " " + wbCurrentReport + " " + sheetCurrentReport);
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
				// System.out.println("---yep---");
				rowCurrentReportFile = sheetCurrentReport.getRow(iRowCtr);
				// System.out.println("1 " + rowCurrentReportFile);
				if (rowCurrentReportFile == null)
				{
					rowCurrentReportFile = sheetCurrentReport.createRow(iRowCtr);
					// System.out.println("2 " + rowCurrentReportFile);
				}

				for (int iCellCtr = 0; iCellCtr < No_Of_Columns; iCellCtr++)
				{
					Cell cellTrgtRpt = rowCurrentReportFile.getCell(iCellCtr);
					// System.out.println("cellTrgtRpt: " + cellTrgtRpt);
					BRTEnumerator.writeAutoMetadataTrgtToCell(rowCurrentReportFile, cellTrgtRpt, rsTestCaseQueryResult, iCellCtr + 1);
					// System.out.println("Yep: " + cellTrgtRpt);
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

	private ResultSet executeQuery(Connection dbConnection, int QueryID, String query) throws BIRT_Exception
	{
		try
		{
			//Create Statement
			Statement stmtQuery = dbConnection.createStatement();

			//Get Query
			String strQuery = BIRT_Persistence.getObjBIPersistence_Query().getQueryDetail(QueryID);
			// ZekunPlay
			strQuery = query;
			
			//Remove Linebreaks
			StringUtility.removeLineBreak(strQuery);
			System.err.println(strQuery);

			ResultSet rsQueryResult = stmtQuery.executeQuery(strQuery);
			ResultSetMetaData rsmtdQueryResult = rsQueryResult.getMetaData();

			//Set Result Set MetaData for Sharing
			BIRT_DataObject.setRsmtdCurrentQueryResult(rsmtdQueryResult);

			return rsQueryResult;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
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

			System.out.println("BMW 3i...........");
			File fCurrentReportFile = new File(BIRT_DataObject.getStrCurrentReportFile());
			System.out.println("1 " + fCurrentReportFile);
			File fCurrentSrcFile = new File(BIRT_DataObject.getStrCurrentSourceFile());
			System.out.println("2 " + fCurrentSrcFile);
			
			// the number of columns are not matched here
			int No_Of_Columns = BIRT_DataObject.getRsmtdCurrentQueryResult().getColumnCount();	
			
			System.out.println("3 " + No_Of_Columns);
			//Excel Manipulation
			InputStream ipCurrentReport;
			Workbook wbCurrentReport;
			Sheet sheetCurrentReport;
			Row rowCurrentSheet;

			ipCurrentReport = new FileInputStream(fCurrentReportFile);
			System.out.println("4 " + ipCurrentReport);
			wbCurrentReport = WorkbookFactory.create(ipCurrentReport);
			System.out.println("5 " + wbCurrentReport);
			sheetCurrentReport = wbCurrentReport.getSheet("Sourcedata");
			System.out.println("6 " + sheetCurrentReport);
			
			if(iFileFormatType != BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_XLS) {
                //Row - 0 Header already written
				//System.out.println("BMW 4i...........");
                int iRowCtr = 1;
                System.out.println(fCurrentSrcFile);
                Scanner sc = new Scanner(fCurrentSrcFile);
                
                int iLineCounter = 1;
                while (iLineCounter < DataStartLine && sc.hasNextLine()) {
                       sc.nextLine();
                       iLineCounter++;
                }
                int count = 0;
                while (sc.hasNextLine()) {
                       rowCurrentSheet = sheetCurrentReport.getRow(iRowCtr);
                       if (rowCurrentSheet == null) {
                              rowCurrentSheet = sheetCurrentReport.createRow(iRowCtr);
                       }
                       String strNextLine = sc.nextLine();
                       System.err.println("File Format Type passed: "+iFileFormatType);
                       if(iFileFormatType==BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT_TXT) {
                              String strCurrentReportFields[] = strNextLine.split(",", -1);
                              
//                              System.out.println("***start: " + count + "***");
//                              System.out.println(strCurrentReportFields.length);
//                              for(int i = 0; i < strCurrentReportFields.length; i++){
//                            	  System.out.println(i + " " + strCurrentReportFields[i]);
//                              }
//                              System.out.println("***end: " + count + "***");
                              
                              // System.out.println(strCurrentReportFields.length + " = " + No_Of_Columns);
//                              if(strCurrentReportFields.length==No_Of_Columns){
                              if(true){
                            	  if(strCurrentReportFields.length!=No_Of_Columns){
                            		  System.err.println("the length are not matched");
                            	  }
                            	  int total = Math.min(strCurrentReportFields.length, No_Of_Columns);
                            	  //System.out.println("***start: " + count + "***");
                                     for (int iCellCtr = 0; iCellCtr < total; iCellCtr++) {
                                            //System.out.println(strCurrentReportFields[iCellCtr]);
                                            Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
                                            BRTEnumerator.writeAutoMetadataSrcToCell(rowCurrentSheet, cellCurrentSheet, strCurrentReportFields[iCellCtr], iCellCtr + 1);       
                                     }
                                  //System.out.println("***end: " + count + "***");
                              }else{
                            	  // System.err.println("----------Shake it off------------");
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
                       count++;
                }
                sc.close();
          } else { // Code to read normal excel starts here
        	  	// Read current report
        	    // it should be fCurrentSrcFile Zekun
                // HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fCurrentReportFile));
        	  	// HSSFWorkbook workbook = null;
        	  	//XSSFWorkbook workbook = null;
        	  	POIFSFileSystem fs = null;
        	    HSSFWorkbook workbook = null;
        	    HSSFSheet worksheet = null;
        	    HSSFRow row;
        	    HSSFCell cell;
        	  	try{
        	  		// workbook = new HSSFWorkbook(new FileInputStream(fCurrentSrcFile));
        	  		//workbook = new XSSFWorkbook(new FileInputStream(fCurrentSrcFile));
        	  		fs = new POIFSFileSystem(new FileInputStream(fCurrentSrcFile));
            	    workbook = new HSSFWorkbook(fs);
            	    worksheet = workbook.getSheetAt(0);
        	  	}catch(Exception e){
        	  		System.out.println("***HSSFWorkbook***");
        	  		System.out.println("File path: " + fCurrentSrcFile);
        	  		e.printStackTrace();
        	  		System.out.println("***HSSFWorkbook***");
        	  	}
                System.out.println("7 " + fCurrentReportFile);
                System.out.println("7 " + workbook.getFirstVisibleTab());
                // Read current worksheet
                // HSSFSheet worksheet = workbook.getSheet(workbook.getSheetName(0)); // Assumption first sheet will always have data
                //XSSFSheet worksheet = workbook.getSheet(workbook.getSheetName(0));
                System.out.println("8 " + workbook.getSheetName(0));
                System.out.println("8 " + worksheet.getSheetName());
                //HSSFRow row1 = worksheet.getRow(DataStartLine);

                // change current worksheet to row iterator
                Iterator<Row> rowIter = worksheet.rowIterator();
                System.out.println("9 " + rowIter);
                //Code to reach data start line
                int i = 0;
                while (rowIter.hasNext() && i < DataStartLine) {
                       rowIter.next();
                       i++;
                }
                int iRowCtr = 1; // First line already taken by header
                 while(rowIter.hasNext()){
                	 // start from 0
                       rowCurrentSheet = sheetCurrentReport.getRow(iRowCtr);
                       if (rowCurrentSheet == null) {
                              rowCurrentSheet = sheetCurrentReport.createRow(iRowCtr);
                       }
                       HSSFRow myRow = (HSSFRow) rowIter.next();
                       Iterator<Cell> cellIter = myRow.cellIterator();
                       for (int iCellCtr = 0; cellIter.hasNext(); iCellCtr++) {
                    	   	  Cell tmp = cellIter.next();
                              Cell cellCurrentSheet = rowCurrentSheet.getCell(iCellCtr);
                              System.out.println("Error: rowCurrentSheet " + rowCurrentSheet);
                              System.out.println("Error: cellCurrentSheet " + cellCurrentSheet);
                              System.out.println("Error: " + tmp);
                              System.out.println("Error: " + iCellCtr + 1);
                              BRTEnumerator.writeAutoMetadataSrcToCellFromExcel(rowCurrentSheet, cellCurrentSheet, tmp, iCellCtr + 1);
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
			System.out.println("----------------------------------------1");
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");

		}
		catch (InvalidFormatException e)
		{
			e.printStackTrace();
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			System.out.println("----------------------------------------2");
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			System.out.println("----------------------------------------3");
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");
		}
		catch (SQLException e)
		{
			// Zekun-start
			e.printStackTrace();
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			System.out.println("----------------------------------------4");
			throw new BIRT_Temp_Exception("Unable to write Data from Downloaded file to Excel");
			//System.out.println("Unable to write Data from Downloaded file to Excel");
		}
		catch (Exception e) {
			// Zekun-start
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("----------------------------------------5");
			throw e;
		}

	}

	private void setupReportFile(int iTestCaseID) throws BIRT_Exception
	{
		String strTemplateFilePath = BIRT_AppProperty.REPORT_TEMPLATE_FILE;
		String strReportFilePath = BIRT_DataObject.getStrCurrentTestCaseIDFolder() + FileUtility.FILE_PATH_SEPARATOR + TESTCASE_REPORT_DIR_NAME + FileUtility.FILE_PATH_SEPARATOR + iTestCaseID + "."
				+ BIRT_AppProperty.REPORT_EXTN;
		// System.out.println("Hey Jude: " + strReportFilePath);
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

	// *******Zekun-start*******
	// ZekunPlay
	private boolean execute_TestScript(int projectID, int testScriptID, Object[] parameters) throws BIRT_Exception
	{
		System.out.println("Hello, how are you?");
		Object[] testSteps = BIRT_Persistence.getObjBIPersistence_TestScript().getTestStepsforTestScript(projectID, testScriptID);
		System.out.println("Pretty good...");
		int start_index = 14;
		int end_index = 27;
		int no_of_TestSteps = testSteps.length - (end_index - start_index + 1) + parameters.length;
		System.out.println("no_of_TestSteps: " + no_of_TestSteps);
		Object[] newSteps = new Object[no_of_TestSteps];

		int index = 0;
		while(index < start_index){
			newSteps[index] = testSteps[index];
			index++;
		}
		// System.out.println("Yep 1: " + index);
		
		for(int i = 0; i < parameters.length; i++){
			newSteps[index] = parameters[i];
			index++;
		}
		// System.out.println("Yep 2: " + index);
		
		for(int i = end_index + 1; i < testSteps.length; i++){
			newSteps[index] = testSteps[i];
			index++;
		}
		// System.out.println("Yep 3: " + index);
		
		boolean isTestScriptSuccess = true;
		for (int iCtr = 0; iCtr < no_of_TestSteps; iCtr++){
			// System.out.println("So the problem happens here... " + iCtr);
			if (execute_TestStep((Object[]) newSteps[iCtr]) == false)
				// isTestScriptSuccess = false;
				System.out.println("--------------------------------------------------------------------------------This step is wrong");
		}
		return isTestScriptSuccess;

	}
	// *******Zekun-end*******
	
	private boolean execute_TestStep(Object[] testStep) throws BIRT_Exception
	{
//		System.out.println("***execute_TestStep***");
//		System.out.println(BIRT_DataObject.getObjWebDriver());
//		System.out.println(testStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND]);
//		System.out.println((String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD]);
//		System.out.println((String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE]);
//		System.out.println((String) testStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET]);
//		System.out.println("***end_execute_TestStep***");
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
