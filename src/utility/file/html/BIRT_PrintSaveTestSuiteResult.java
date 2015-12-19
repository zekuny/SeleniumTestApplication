package utility.file.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import logger.BIRT_Logger;
import persistence.BIRT_Persistence_TestSuite;
import utility.file.FileUtility;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;

import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;

public class BIRT_PrintSaveTestSuiteResult 
{
	

	public void printNSaveTestSuiteResult(int strTestSuiteID, String currentTestSuiteTimeStampFolder, Object[] testCaseResults, Calendar clTestSuiteStartTime, int iProjectID) throws BIRT_Exception
	{
		File fStatsFile = new File(currentTestSuiteTimeStampFolder + FileUtility.FILE_PATH_SEPARATOR + "Result.txt");
		try
		{
			fStatsFile.createNewFile();
			FileWriter fwStatsFile = new FileWriter(fStatsFile.getAbsoluteFile());
			BufferedWriter bwStatsFile = new BufferedWriter(fwStatsFile);

			int iNoOfTestCases = testCaseResults.length;

			bwStatsFile.write("TestSuite ID : " + strTestSuiteID);
			bwStatsFile.write("\n");
			bwStatsFile.write("No Of TestCases : " + iNoOfTestCases);
			bwStatsFile.write("\n");
			bwStatsFile.write("Start Time : " + clTestSuiteStartTime.get(Calendar.YEAR) + "/" + (clTestSuiteStartTime.get(Calendar.MONTH) + 1) + "/" + clTestSuiteStartTime.get(Calendar.DATE) + " "
					+ clTestSuiteStartTime.get(Calendar.HOUR_OF_DAY) + ":" + clTestSuiteStartTime.get(Calendar.MINUTE) + ":" + clTestSuiteStartTime.get(Calendar.MILLISECOND));
			bwStatsFile.write("\n");

			/*
			 * Object[0] - Boolean - TestCase Status
			 * Object[1] - String  - TestCase Name
			 * Object[2] - Integer  - No. Of Source Records
			 * Object[3] - Integer  - No. Of Target Records
			 * Object[4] - Integer  - No. Of Records Passed
			 * Object[5] - Integer  - No. of Records Failed
			 * Object[6] - String  - Report Path.
			 * Object[7] - String  - Remark.
			 */

			bwStatsFile.write("TestCase Name \t");
			bwStatsFile.write("Status \t");
			bwStatsFile.write("No. Of Source Records \t");
			bwStatsFile.write("No. Of Target Records \t");
			bwStatsFile.write("No. Of Records Passed \t");
			bwStatsFile.write("No. of Records Failed \t");
			bwStatsFile.write("Report Path \t");
			bwStatsFile.write("Remark \t");
			bwStatsFile.write("\n");

			int iTotalTestCasePass = 0;

			for (int iCtr = 0; iCtr < iNoOfTestCases; iCtr++)
			{
				Object[] singleTestCaseStat = (Object[]) testCaseResults[iCtr];

				bwStatsFile.write((String) singleTestCaseStat[1] + "\t");

				if ((Boolean) singleTestCaseStat[0])
				{
					iTotalTestCasePass++;
					bwStatsFile.write("Success \t");

				}
				else
				{
					bwStatsFile.write("Failure \t");
				}

				bwStatsFile.write(((Integer) singleTestCaseStat[2]).intValue() + "\t");
				bwStatsFile.write(((Integer) singleTestCaseStat[3]).intValue() + "\t");
				bwStatsFile.write(((Integer) singleTestCaseStat[4]).intValue() + "\t");
				bwStatsFile.write(((Integer) singleTestCaseStat[5]).intValue() + "\t");
				bwStatsFile.write((String) singleTestCaseStat[6] + "\t");
				bwStatsFile.write((String) singleTestCaseStat[7] + "\t");
				bwStatsFile.write("\n");
			}
			bwStatsFile.write("Total TestCase Pass - " + iTotalTestCasePass + "\n");
			bwStatsFile.write("Total TestCase Fail - " + (iNoOfTestCases - iTotalTestCasePass) + "\n");
			bwStatsFile.write("Total TestCase Pass Percentage - " + ((iTotalTestCasePass) / (float) iNoOfTestCases) * 100 + "%\n");

			bwStatsFile.flush();
			bwStatsFile.close();

			int[] TestStats = new int[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_TEST_STATS_TOTAL];
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS] = iTotalTestCasePass;
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_FAIL] = (iNoOfTestCases - iTotalTestCasePass);
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASES] = iNoOfTestCases;
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS_PERCENTAGE] = ((iNoOfTestCases - iTotalTestCasePass) / iNoOfTestCases);

			BIRT_Persistence.getObjBIPersistence_TestSuite().saveTestSuiteResult(iProjectID, strTestSuiteID, TestStats, clTestSuiteStartTime);

		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			e.printStackTrace();
			throw new BIRT_Temp_Exception("Unable to save TestSuite Report File");
		}

	}

	public void printNSaveTestSuiteResultHTML(int strTestSuiteID, String currentTestSuiteTimeStampFolder, Object[] testCaseResults, Calendar clTestSuiteStartTime, int iProjectID)
			throws BIRT_Exception
	{

		String TABLE_START = "<TABLE cellpadding=\"5\" >";
		String TABLE_END = "</TABLE>";

		String THEAD_START = "<THEAD>";
		String THEAD_END = "</THEAD>";

		String TR_HEADER_START = "<TR style=\" font-family:Verdana; font-size: x-small; background: Ivory; color: black \">";
		String TR_END = "</TR>";
		String TR_START = " <TR> ";

		String TR_BODY_SUCCESS_PART1 = "<TR style=\" font-family:Verdana; font-size: x-small;  background: LightGreen ; color: black \"";
		String TR_BODY_FAILURE_PART1 = "<TR style=\" font-family:Verdana; font-size: x-small;  background: MistyRose ; color: black \"";
		String TR_BODY_PART2 = ">";

		String TD_START = "<TD style=\"  font-family:Verdana; font-size: x-small; \" >";
		String TD_CENTER_START = "<TD style=\"  font-family:Verdana; font-size: x-small; \" > ";
		String TD_NONE_START = "<TD style=\"  font-family:Verdana; font-size: x-small; \">  ";
		String TD_END = "</TD>";

		String TH_START = "<TH>";
		String TH_END = "</TH>";

		String TBODY_START = "<TBODY>";
		String TBODY_END = "</TBODY>";

		String TABLE_BORDER_START = "<DIV style = \"border: 1px solid #000000; \">";
		String TABLE_BORDER_END = "</DIV>";

		String A_RESULT_START = "<A HREF=\"";
		String A_RESULT_END = "\" >Result </A>";

		@SuppressWarnings("unused")
		String STYLE_START = "<STYLE title=currentStyle type=text/css>";
		@SuppressWarnings("unused")
		String STYLE_END = "</STYLE>";
		@SuppressWarnings("unused")
		String SCRIPT_START = "<SCRIPT language=javascript type=text/javascript ";
		@SuppressWarnings("unused")
		String SCRIPT_END = "</SCRIPT>";

		File fStatsFile = new File(currentTestSuiteTimeStampFolder + FileUtility.FILE_PATH_SEPARATOR + "Result.html");
		try
		{
			fStatsFile.createNewFile();
			FileWriter fwStatsFile = new FileWriter(fStatsFile.getAbsoluteFile());
			BufferedWriter bwStatsFile = new BufferedWriter(fwStatsFile);

			int iNoOfTestCases = testCaseResults.length;

			bwStatsFile.write("<HTML>");
			bwStatsFile.write("<HEAD>");
			bwStatsFile.write("<TITLE>");
			bwStatsFile.write("TestSuite - " + strTestSuiteID + " Test Report");
			bwStatsFile.write("</TITLE>");
			bwStatsFile.write("</HEAD>");
			bwStatsFile.write("<BODY>");
			/*
			 * <style type="text/css">
			 * body {background-color:yellow;}
			 * p {color:blue;}
			 * </style>
			 */

			/*
			 * <script>
			 * function myFunction()
			 * {
			 * 		document.getElementById("demo").innerHTML="Hello JavaScript!";
			 * }
			 * </script>
			 */

			bwStatsFile.write(TABLE_BORDER_START);

			bwStatsFile.write(TABLE_START);
			{
				bwStatsFile.write(THEAD_START);
				{
					bwStatsFile.write(TR_HEADER_START);
					{
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("");
						}
						bwStatsFile.write(TH_END);
					}
					bwStatsFile.write(TR_END);

				}
				bwStatsFile.write(THEAD_END);

				bwStatsFile.write(TBODY_START);
				{
					bwStatsFile.write(TR_START);
					{
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write("TestSuite ID");
						}
						bwStatsFile.write(TD_END);
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write(strTestSuiteID + "");
						}
						bwStatsFile.write(TD_END);
					}
					bwStatsFile.write(TR_END);
					bwStatsFile.write(TR_START);
					{
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write("No Of TestCases");
						}
						bwStatsFile.write(TD_END);
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write(iNoOfTestCases + "");
						}
						bwStatsFile.write(TD_END);
					}
					bwStatsFile.write(TR_END);
					bwStatsFile.write(TR_START);
					{
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write("Start Time");
						}
						bwStatsFile.write(TD_END);
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write(clTestSuiteStartTime.get(Calendar.YEAR) + "/" + (clTestSuiteStartTime.get(Calendar.MONTH) + 1) + "/" + clTestSuiteStartTime.get(Calendar.DATE) + " "
									+ clTestSuiteStartTime.get(Calendar.HOUR_OF_DAY) + ":" + clTestSuiteStartTime.get(Calendar.MINUTE) + ":" + clTestSuiteStartTime.get(Calendar.MILLISECOND));
						}
						bwStatsFile.write(TD_END);
					}
					bwStatsFile.write(TR_END);

				}
				bwStatsFile.write(TBODY_END);

			}
			bwStatsFile.write(TABLE_END);

			bwStatsFile.write(TABLE_BORDER_END);

			/*
				 * Object[0] - Boolean - TestCase Status
				 * Object[1] - String  - TestCase Name
				 * Object[2] - Integer  - No. Of Source Records
				 * Object[3] - Integer  - No. Of Target Records
				 * Object[4] - Integer  - No. Of Records Passed
				 * Object[5] - Integer  - No. of Records Failed
				 * Object[6] - String  - Report Path.
				 * Object[7] - String  - Remark.
				 */

			/*
			 * <table id="table_id">
			 *     <thead>
			 *             <tr>
			 *                         <th>Column 1</th>
			 *                         <th>Column 2</th>
			 *             </tr>
			 *    </thead>
			 *    <tbody>
			 *            <tr>
			 *                        <td>Row 1 Data 1</td>
			 *                        <td>Row 1 Data 2</td>
			 *           </tr>
			 *           <tr>
			 *                       <td>Row 2 Data 1</td>
			 *                       <td>Row 2 Data 2</td>
			 *           </tr>
			 *    </tbody>
			 *</table>
			 */

			int iTotalTestCasePass = 0;

			bwStatsFile.write(TABLE_BORDER_START);

			bwStatsFile.write(TABLE_START);
			{
				bwStatsFile.write(THEAD_START);
				{
					bwStatsFile.write(TR_HEADER_START);
					{
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("TestCase Name ");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("Status");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("No. Of Source Records");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("No. Of Target Records");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("No. Of Records Passed");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("No. of Records Failed");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("Report Path");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("Remark");
						}
						bwStatsFile.write(TH_END);

					}
					bwStatsFile.write(TR_END);

				}
				bwStatsFile.write(THEAD_END);

				bwStatsFile.write(TBODY_START);
				{
					for (int iCtr = 0; iCtr < iNoOfTestCases; iCtr++)
					{
						Object[] singleTestCaseStat = (Object[]) testCaseResults[iCtr];
						Boolean isTestCaseSuccess = (Boolean) singleTestCaseStat[0];

						//TR
						if (isTestCaseSuccess)
							bwStatsFile.write(TR_BODY_SUCCESS_PART1);
						else bwStatsFile.write(TR_BODY_FAILURE_PART1);
						bwStatsFile.write(iCtr + "");
						bwStatsFile.write(TR_BODY_PART2);
						{

							bwStatsFile.write(TD_NONE_START);
							bwStatsFile.write((String) singleTestCaseStat[1]);
							bwStatsFile.write(TD_END);

							bwStatsFile.write(TD_NONE_START);
							if (isTestCaseSuccess)
							{
								iTotalTestCasePass++;
								bwStatsFile.write("Success");
							}
							else
							{
								bwStatsFile.write("Failure");
							}
							bwStatsFile.write(TD_END);

							bwStatsFile.write(TD_CENTER_START);
							bwStatsFile.write(((Integer) singleTestCaseStat[2]).intValue() + "");
							bwStatsFile.write("</td>");

							bwStatsFile.write(TD_CENTER_START);
							bwStatsFile.write(((Integer) singleTestCaseStat[3]).intValue() + "");
							bwStatsFile.write(TD_END);

							bwStatsFile.write(TD_CENTER_START);
							bwStatsFile.write(((Integer) singleTestCaseStat[4]).intValue() + "");
							bwStatsFile.write(TD_END);

							bwStatsFile.write(TD_CENTER_START);
							bwStatsFile.write(((Integer) singleTestCaseStat[5]).intValue() + "");
							bwStatsFile.write(TD_END);

							String strReportPath = (String) singleTestCaseStat[6];

							bwStatsFile.write(TD_NONE_START);
							bwStatsFile.write(A_RESULT_START + strReportPath.replaceAll("/", "\\\\") + A_RESULT_END);
							bwStatsFile.write(TD_END);

							bwStatsFile.write(TD_NONE_START);
							bwStatsFile.write((String) singleTestCaseStat[7]);
							bwStatsFile.write(TD_END);

						}
						bwStatsFile.write(TR_END);

					}

				}
				bwStatsFile.write(TBODY_END);

			}
			bwStatsFile.write(TABLE_END);

			bwStatsFile.write(TABLE_BORDER_END);

			bwStatsFile.write(TABLE_BORDER_START);

			bwStatsFile.write(TABLE_START);
			{
				bwStatsFile.write(THEAD_START);
				{
					bwStatsFile.write(TR_HEADER_START);
					{
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("");
						}
						bwStatsFile.write(TH_END);
						bwStatsFile.write(TH_START);
						{
							bwStatsFile.write("");
						}
						bwStatsFile.write(TH_END);
					}
					bwStatsFile.write(TR_END);

				}
				bwStatsFile.write(THEAD_END);

				bwStatsFile.write(TBODY_START);
				{
					bwStatsFile.write(TR_START);
					{
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write("Total TestCase Pass");
						}
						bwStatsFile.write(TD_END);
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write(iTotalTestCasePass + "");
						}
						bwStatsFile.write(TD_END);
					}
					bwStatsFile.write(TR_END);
					bwStatsFile.write(TR_START);
					{
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write("Total TestCase Fail");
						}
						bwStatsFile.write(TD_END);
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write((iNoOfTestCases - iTotalTestCasePass) + "");
						}
						bwStatsFile.write(TD_END);
					}
					bwStatsFile.write(TR_END);
					bwStatsFile.write(TR_START);
					{
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write("Total TestCase Pass Percentage");
						}
						bwStatsFile.write(TD_END);
						bwStatsFile.write(TD_START);
						{
							bwStatsFile.write("" + ((iTotalTestCasePass) / (float) iNoOfTestCases) * 100);
						}
						bwStatsFile.write(TD_END);
					}
					bwStatsFile.write(TR_END);

				}
				bwStatsFile.write(TBODY_END);

			}
			bwStatsFile.write(TABLE_END);

			bwStatsFile.write(TABLE_BORDER_END);

			bwStatsFile.write("</BODY>");
			bwStatsFile.write("</HTML>");

			bwStatsFile.flush();
			bwStatsFile.close();
			
			BIRT_DataObject.setiTotalTestCases(iNoOfTestCases);
			BIRT_DataObject.setiTestCasePassed(iTotalTestCasePass);
			BIRT_DataObject.setiTestPassPercentage((int) (((iTotalTestCasePass) / (float) iNoOfTestCases) * 100));
			BIRT_DataObject.setiTestCaseFailed(iNoOfTestCases - iTotalTestCasePass);

			int[] TestStats = new int[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_TEST_STATS_TOTAL];
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS] = iTotalTestCasePass;
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_FAIL] = (iNoOfTestCases - iTotalTestCasePass);
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASES] = iNoOfTestCases;
			TestStats[BIRT_Persistence_TestSuite.TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS_PERCENTAGE] = (int) (((iTotalTestCasePass) / (float) iNoOfTestCases) * 100);

			BIRT_Persistence.getObjBIPersistence_TestSuite().saveTestSuiteResult(iProjectID, strTestSuiteID, TestStats, clTestSuiteStartTime);

		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			e.printStackTrace();
			throw new BIRT_Temp_Exception("Unable to save TestSuite Report File");
		}

	}


}
