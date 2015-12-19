package persistence;

import java.util.Calendar;

import exception.BIRT_Exception;

public interface BIRT_Persistence_TestSuite
{
	public boolean saveTestSuite(int ProjectID, String TestSuiteName, String TestPath, String TestSuiteDescription, int[] TestCaseIds) throws BIRT_Exception;

	public boolean editTestSuite(int ProjectID, int TestSuiteID, String TestSuiteName, String TestFilePath, String TestSuiteDescription, int[] TestCaseIds) throws BIRT_Exception;

	/*
	 * int [] TestStats
	 * int[0] - NoOfTestCases
	 * int[1] - NoOfTestCasePass
	 * int[2] - NoOfTestCaseFail
	 * int[3] - NoOfPassPercentage
	 *
	 */

	public static final int	TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASES					= 0;
	public static final int	TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS				= 1;
	public static final int	TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_FAIL				= 2;
	public static final int	TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS_PERCENTAGE	= 3;
	public static final int	TEST_SUITE_RSLT_HSTRY_TEST_STATS_TOTAL					= 4;

	public boolean saveTestSuiteResult(int ProjectID, int TestSuiteID, int[] TestStats, Calendar clTimeStamp) throws BIRT_Exception;

	/*
	 * Object[] - Contains SingleTestSuiteResultHistory
	 * SingleTestSuiteResultHistory[0] - TimeStamp - String
	 * SingleTestSuiteResultHistory[1] - NoOfTestCases
	 * SingleTestSuiteResultHistory[2] - NoOfTestCasePass
	 * SingleTestSuiteResultHistory[3] - NoOfTestCaseFail
	 * SingleTestSuiteResultHistory[4] - NoOfPassPercentage
	 */

	public Object[] getTestSuiteResultHistory(int iProjectID, int TestSuiteID) throws BIRT_Exception;

	public boolean isTestSuiteNameDuplicate(String TestSuiteName) throws BIRT_Exception;

	public Object[] getTestSuiteDescriptions(int ProjectID) throws BIRT_Exception;

	/*
	 * Return type int[] - TestCase ID
	 */
	public int[] getTestSuiteTestCases(int TestSuiteID, int ProjectID) throws BIRT_Exception;

	public boolean deleteTestSuite(int ProjectID, int TestSuiteID) throws BIRT_Exception;
	public String getFileName(int TestSuiteID) throws BIRT_Exception;
}
