package persistence;

import exception.BIRT_Exception;

public interface BIRT_Persistence_TestCase
{

	public final static String[]	SUPPORTED_FILE_FORMAT		=
																{ "txt","xls","csv" };
	public final static int			SUPPORTED_FILE_FORMAT_TXT	= 0;
	public final static int			SUPPORTED_FILE_FORMAT_XLS	= 1;
	public final static int			SUPPORTED_FILE_FORMAT_CSV	= 2;
	
	public boolean saveTestCase(int ProjectID, String TestCaseName, String TestCaseDescription, int TypeOfBrowser, String BaseURL, int TestScriptID, int QueryID, int DBConnectionID,
			int DownloadedFileType, String DownloadedFileName, int ReportHeaderLine, int DataStartLine) throws BIRT_Exception;

	public boolean editTestCase(int ProjectID, int TestCaseID, String TestCaseName, String TestCaseDescription, int TypeOfBrowser, String BaseURL, int TestScriptID, int QueryID, int DBConnectionID,
			int DownloadedFileType, String DownloadedFileName, int ReportHeaderLine, int DataStartLine) throws BIRT_Exception;

	public boolean isTestCaseNameDuplicate(String TestCaseName) throws BIRT_Exception;

	public Object[] getTestCaseDescriptions(int ProjectID) throws BIRT_Exception;

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
	public Object[] getTestCaseDetails(int TestCaseID) throws BIRT_Exception;

	/*
	 * Return Type - String[] - TestCase names of all test cases whose
	 * TestCaseIDs are provided
	 */
	public String[] getTestCaseNames(int ProjectID, int[] TestCaseIDs) throws BIRT_Exception;

	public boolean existsTestCase(int TestCaseID) throws BIRT_Exception;
	
	public String getTestCaseName(int ProjectID, int TestCaseID) throws BIRT_Exception;

	public boolean deleteTestCase(int ProjectID, int TestCaseID) throws BIRT_Exception;

}
