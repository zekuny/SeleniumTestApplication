package comm;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.openqa.selenium.WebDriver;

import resources.resourcebundle.BIRT_Resource_PropertyNames;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;
import framework.selenium.driver.web.BIRT_WebDriver;

public class BIRT_DataObject
{

	private static ArrayList<Object[]>	alTestCasesSelectedForTestSuite			= new ArrayList<Object[]>();
	private static ArrayList<Object[]>	alTestScript_CreateTestSteps			= new ArrayList<Object[]>();
	private static int					App_Panel_DBConnection_Browse_SelectedDBConnectionID;
	private static String				App_Panel_DBConnection_Browse_SelectedDBConnectionName;
	private static int					App_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID;

	private static String				App_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName;
	private static String				App_Panel_Query_Browse_SelectedQueryDesc;
	private static int					App_Panel_Query_Browse_SelectedQueryID;
	private static String				App_Panel_Query_Browse_SelectedQueryName;
	private static int					App_Panel_Query_BrowsenAdd_SelectedQueryID;
	private static String				App_Panel_Query_BrowsenAdd_SelectedQueryName;
	private static int					App_Panel_TestCase_Browse_SelectedTestCaseID;
	private static String				App_Panel_TestCase_Browse_SelectedTestCaseName;
	private static int					App_Panel_TestCase_BrowsenAdd_SelectedTestCaseID;

	private static String				App_Panel_TestCase_BrowsenAdd_SelectedTestCaseName;
	private static String				App_Panel_TestScript_Browse_SelectedTestScriptDescription;
	private static int					App_Panel_TestScript_Browse_SelectedTestScriptID;
	private static String				App_Panel_TestScript_Browse_SelectedTestScriptName;

	private static int					App_Panel_TestScript_BrowsenAdd_SelectedTestScriptID;
	private static String				App_Panel_TestScript_BrowsenAdd_SelectedTestScriptName;
	private static String				App_Panel_TestSuite_Browse_SelectedTestSuiteDesc;
	private static int					App_Panel_TestSuite_Browse_SelectedTestSuiteID;
	private static String				App_Panel_TestSuite_Browse_SelectedTestSuiteName;
	private static String				App_Panel_TestSuite_Browse_SelectedTestFilePath;
	
	private static String				App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc;
	private static int					App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID;

	private static String				App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName;
	private static String				App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestFilePath;

	public static int					DBCONN_DESC								= 1;
	public static int					DBCONN_NAME								= 0;
	public static int					DBCONN_TABLE_DATA						= 1;
	public static int					DBCONNID								= 0;
	public static final int				DIRECTORIES_ONLY						= JFileChooser.DIRECTORIES_ONLY;

	// FileDescriptions
	public static final String			FD_FIREFOX_EXE_LOC_BRWSE				= "Firefox Executable";
	public static final String			FD_FIREFOX_PROFILE_BRWSE				= "Firefox Profile Directory";
	public static final String			FD_IE_DRIVER_BRWSE						= "IE Driver Executable";
	public static final String			FD_RPT_ARCHIVE_DIR						= "Report Archive Directory";
	public static final String			FD_RPT_DWNLD_DIR						= "Report Download Directory";
	public static final String			FD_TESTSCRIPT_EXPORT_DIR				= "Test Script Export Directory";
	public static final String			FD_TESTSCRIPT_FILE						= "Test Script File";
	public static final String			FDBT_BROWSE								= "Browse";

	// File Dialog Button Text
	public static final String			FDBT_SELECT								= "Select";

	// FileExtensions
	public static final String[]		FE_EXECUTABLES							=
																				{ "exe" };
	public static final String[]		FE_TXT									=
																				{ "txt" };

	// FileSelectionMode
	public static final int				FILES_ONLY								= JFileChooser.FILES_ONLY;
	private static int					iExecute_TestSuiteID					= -1;

	private static int					iProjectID;
	private static int					iTestCaseFailed;
	private static int					iTestCasePassed;
	private static int					iTestPassPercentage;
	private static int					iTotalTestCases;

	public static final boolean			MULTIPLE_FILES							= true;

	public static String				NULL									= "";
	private static Object[]				objDBConnectionTableDetails;
	
	/*
	 * Contents of Object[]objQueryTableDetails objQueryTableDetails[QUERYID] =
	 * private static Integer[] iQueryIDs;
	 * objQueryTableDetails[QUERY_TABLE_DATA] = Object[][]
	 * objQueryDetailsTableData;
	 * 
	 * Object[][] objQueryDetailsTableData - Two Dimensional Array with Data for
	 * each row Row1Col1, Row1Col2.... Row1Coln Row2Col1, Row2Col2 ....Row2Coln
	 * . . . RownCol1, RownCol2 .... RownColn
	 */
	private static Object[]				objQueryTableDetails;
	private static Object[]				objTestCaseTableDetails;
	private static Object[]				objTestScriptTableDetails;

	private static Object[]				objTestSuiteHistoryTableDetails			= null;

	/*
	 * Contents of Test Script Create Test Steps Table ArrayList
	 * alTestScript_CreateTestSteps[] Sequence No1, Command1, Method1, Target1,
	 * Value1 Sequence No2, Command2, Method2, Target2, Value2 Sequence No3,
	 * Command3, Method3, Target3, Value3 Sequence No4, Command4, Method4,
	 * Target4, Value4
	 * 
	 * But when function gives it back.... it has to return as Object[]
	 */

	private static Object[]				objTestSuiteTableDetails;
	private static String				objFileName								= "";
	// Zekun
	private static BIRT_WebDriver		objWebDriver							= null;
	//private static WebDriver            objWebDriver                            = null;
	public static int					QUERY_DESC								= 1;
	public static int					QUERY_NAME								= 0;
	public static int					QUERY_TABLE_DATA						= 1;

	public static int					QUERYID									= 0;
	private static ResultSetMetaData	rsmtdCurrentQueryResult					= null;
	public static final boolean			SINGLE_FILE								= false;
	private static String				strCurrentReportFile					= NULL;

	private static String				strCurrentSourceFile					= NULL;
	private static String				strCurrentTestCaseIDFolder				= NULL;
	private static String				strCurrentTestSuiteTimeStampFolder		= NULL;
	private static String				strProjectName;

	public static int					TESTCASE_DESC							= 1;
	public static int					TESTCASE_NAME							= 0;

	public static int					TESTCASE_TABLE_DATA						= 1;

	public static int					TESTCASEID								= 0;
	public static int					TESTSCRIPT_DESC							= 1;
	public static int					TESTSCRIPT_NAME							= 0;
	public static int					TESTSCRIPT_TABLE_DATA					= 1;
	public static int					TESTSCRIPTID							= 0;
	public static int					TESTSTEP_OBJECT_COMMAND					= 1;

	public static int					TESTSTEP_OBJECT_METHOD					= 2;
	public static int					TESTSTEP_OBJECT_SEQNO					= 0;
	public static int					TESTSTEP_OBJECT_TARGET					= 3;
	public static int					TESTSTEP_OBJECT_TOTALSIZE				= 5;

	public static int					TESTSTEP_OBJECT_VALUE					= 4;
	public static int					TESTSUITE_DESC							= 1;
	public static int					TESTSUITE_ID							= 0;
	public static int					TESTSUITE_NAME							= 0;

	public static int					TESTSUITE_SELECTED_TESTCASE_ID			= 0;

	public static int					TESTSUITE_SELECTED_TESTCASE_NAME		= 1;
	public static int					TESTSUITE_SELECTED_TESTCASE_TOTALSIZE	= 2;
	public static int					TESTSUITE_TABLE_DATA					= 1;
	public static int					TESTSUITEHIST_ARCHIVE_PATH				= 5;
	public static int					TESTSUITEHIST_FAIL_TESTCASES			= 3;

	public static int					TESTSUITEHIST_ID						= 0;
	public static int					TESTSUITEHIST_PASS_PERCENTAGE			= 4;

	public static int					TESTSUITEHIST_PASS_TESTCASES			= 2;
	public static int					TESTSUITEHIST_TABLE_DATA				= 1;

	/*
	 * { "TimeStamp", "Total Test Cases", "Pass", "Fail", "Pass Percentage", "Archive Path" };
	 */
	public static int					TESTSUITEHIST_TIMESTAMP					= 0;
	public static int					TESTSUITEHIST_TOTAL						= 6;
	public static int					TESTSUITEHIST_TOTAL_TESTCASES			= 1;
	public static final boolean			UNEDITABLE								= false;

	public static void addTestScript_CreateTestStep(Object[] objTestStep)
	{
		BIRT_DataObject.alTestScript_CreateTestSteps.add(objTestStep);
	}

	public static void addTestScript_TestSteps(Object[] objTestSteps)
	{
		for (int iCtr = 0; iCtr < objTestSteps.length; iCtr++)
			BIRT_DataObject.addTestScript_CreateTestStep((Object[]) objTestSteps[iCtr]);
	}

	public static void addTestSuite_SelectedTestCase(Object[] objTestCase)
	{

		BIRT_DataObject.alTestCasesSelectedForTestSuite.add(objTestCase);
	}

	public static void cleanUpAfterTestCase()
	{
		strCurrentTestCaseIDFolder = NULL;
		strCurrentSourceFile = NULL;
		strCurrentReportFile = NULL;
		objWebDriver = null;
		rsmtdCurrentQueryResult = null;
	}

	public static void cleanUpAfterTestSuite()
	{
		iExecute_TestSuiteID = -1;
		strCurrentTestSuiteTimeStampFolder = NULL;
		strCurrentTestCaseIDFolder = NULL;
		strCurrentSourceFile = NULL;
		strCurrentReportFile = NULL;
		objWebDriver = null;
		rsmtdCurrentQueryResult = null;
	}

	public static void destroyTestScript_TestSteps()
	{
		BIRT_DataObject.alTestScript_CreateTestSteps = new ArrayList<Object[]>();
	}

	public static void destroyTestSuite_SelectedTestCase()
	{
		BIRT_DataObject.alTestCasesSelectedForTestSuite = new ArrayList<Object[]>();
	}

	public static int getApp_Panel_DBConnection_Browse_SelectedDBConnectionID()
	{
		return App_Panel_DBConnection_Browse_SelectedDBConnectionID;
	}

	public static String getApp_Panel_DBConnection_Browse_SelectedDBConnectionName()
	{
		return App_Panel_DBConnection_Browse_SelectedDBConnectionName;
	}

	public static int getApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID()
	{
		return App_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID;
	}

	public static String getApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName()
	{
		return App_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName;
	}

	public static final String getApp_Panel_Query_Browse_SelectedQueryDesc()
	{
		return App_Panel_Query_Browse_SelectedQueryDesc;
	}

	public static final int getApp_Panel_Query_Browse_SelectedQueryID()
	{
		return App_Panel_Query_Browse_SelectedQueryID;
	}

	public static final String getApp_Panel_Query_Browse_SelectedQueryName()
	{
		return App_Panel_Query_Browse_SelectedQueryName;
	}

	public static int getApp_Panel_Query_BrowsenAdd_SelectedQueryID()
	{
		return App_Panel_Query_BrowsenAdd_SelectedQueryID;
	}

	public static String getApp_Panel_Query_BrowsenAdd_SelectedQueryName()
	{
		return App_Panel_Query_BrowsenAdd_SelectedQueryName;
	}

	public static int getApp_Panel_TestCase_Browse_SelectedTestCaseID()
	{
		return App_Panel_TestCase_Browse_SelectedTestCaseID;
	}

	public static String getApp_Panel_TestCase_Browse_SelectedTestCaseName()
	{
		return App_Panel_TestCase_Browse_SelectedTestCaseName;
	}

	public static int getApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseID()
	{
		return App_Panel_TestCase_BrowsenAdd_SelectedTestCaseID;
	}

	public static String getApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseName()
	{
		return App_Panel_TestCase_BrowsenAdd_SelectedTestCaseName;
	}

	public static String getApp_Panel_TestScript_Browse_SelectedTestScriptDescription()
	{
		return App_Panel_TestScript_Browse_SelectedTestScriptDescription;
	}

	public static int getApp_Panel_TestScript_Browse_SelectedTestScriptID()
	{
		return App_Panel_TestScript_Browse_SelectedTestScriptID;
	}

	public static String getApp_Panel_TestScript_Browse_SelectedTestScriptName()
	{
		return App_Panel_TestScript_Browse_SelectedTestScriptName;
	}

	public static int getApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptID()
	{
		return App_Panel_TestScript_BrowsenAdd_SelectedTestScriptID;
	}

	public static String getApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptName()
	{
		return App_Panel_TestScript_BrowsenAdd_SelectedTestScriptName;
	}

	public static String getApp_Panel_TestSuite_Browse_SelectedTestSuiteDesc()
	{
		return App_Panel_TestSuite_Browse_SelectedTestSuiteDesc;
	}

	public static int getApp_Panel_TestSuite_Browse_SelectedTestSuiteID()
	{
		return App_Panel_TestSuite_Browse_SelectedTestSuiteID;
	}

	public static String getApp_Panel_TestSuite_Browse_SelectedTestSuiteName()
	{
		return App_Panel_TestSuite_Browse_SelectedTestSuiteName;
	}
	
	public static String getApp_Panel_TestSuite_Browse_SelectedTestFilePath()
	{
		return App_Panel_TestSuite_Browse_SelectedTestFilePath;
	}

	public static String getApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc()
	{
		return App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc;
	}

	public static int getApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID()
	{
		return App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID;
	}

	public static String getApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName()
	{
		return App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName;
	}
	
	public static String getApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestFilePath()
	{
		return App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestFilePath;
	}
	

	public static int getIExecute_TestSuiteID()
	{
		return iExecute_TestSuiteID;
	}

	public static final int getIProjectID()
	{
		return iProjectID;
	}

	public static int getiTestCaseFailed()
	{
		return iTestCaseFailed;
	}

	public static int getiTestCasePassed()
	{
		return iTestCasePassed;
	}

	public static int getiTestPassPercentage()
	{
		return iTestPassPercentage;
	}

	public static int getiTotalTestCases()
	{
		return iTotalTestCases;
	}

	public static Object[] getObjDBConnectionTableDetails() throws BIRT_Exception
	{
		if (null == objDBConnectionTableDetails)
		{
			objDBConnectionTableDetails = BIRT_Persistence.getObjBIPersistence_DBConnection().getDBConnectionDescriptions(iProjectID);
		}
		return objDBConnectionTableDetails;
	}

	public static final Object[] getObjQueryTableDetails() throws BIRT_Exception
	{
		if (null == objQueryTableDetails)
		{
			objQueryTableDetails = BIRT_Persistence.getObjBIPersistence_Query().getQueryDescriptions(iProjectID);
		}
		return objQueryTableDetails;
	}

	public static Object[] getObjTestCaseTableDetails() throws BIRT_Exception
	{
		if (null == objTestCaseTableDetails)
		{
			objTestCaseTableDetails = BIRT_Persistence.getObjBIPersistence_TestCase().getTestCaseDescriptions(iProjectID);
		}
		return objTestCaseTableDetails;
	}

	public static Object[] getObjTestScriptTableDetails() throws BIRT_Exception
	{
		if (null == objTestScriptTableDetails)
		{
			objTestScriptTableDetails = BIRT_Persistence.getObjBIPersistence_TestScript().getTestScriptDescriptions(iProjectID);
		}
		return objTestScriptTableDetails;
	}

	public static Object[] getObjTestSuiteHistoryTableDetails() throws BIRT_Exception
	{
		if (null == objTestSuiteHistoryTableDetails)
		{
			objTestSuiteHistoryTableDetails = BIRT_Persistence.getObjBIPersistence_TestSuite().getTestSuiteResultHistory(iProjectID,
					BIRT_DataObject.getApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID());
		}
		return objTestSuiteHistoryTableDetails;
	}

	public static final Object[] getObjTestSuiteTableDetails() throws BIRT_Exception
	{
		if (null == objTestSuiteTableDetails)
		{
			objTestSuiteTableDetails = BIRT_Persistence.getObjBIPersistence_TestSuite().getTestSuiteDescriptions(iProjectID);
		}
		return objTestSuiteTableDetails;
	}
	
	// second
	public static String getObjFileName(int TestSuiteID) throws BIRT_Exception
	{
		if (null == objFileName || objFileName.isEmpty())
		{
			objFileName = BIRT_Persistence.getObjBIPersistence_TestSuite().getFileName(TestSuiteID);
		}
		return objFileName;
	}

//	public static BIRT_WebDriver getObjWebDriver()
//	{
//		return objWebDriver;
//	}
	// Zekun
	public static BIRT_WebDriver getObjWebDriver()
	{
		return objWebDriver;
	}
	
	
	public static ResultSetMetaData getRsmtdCurrentQueryResult()
	{
		return rsmtdCurrentQueryResult;
	}

	public static String getStrCurrentReportFile()
	{
		return strCurrentReportFile;
	}

	public static String getStrCurrentSourceFile()
	{
		return strCurrentSourceFile;
	}

	public static String getStrCurrentTestCaseIDFolder()
	{
		return strCurrentTestCaseIDFolder;
	}

	public static String getStrCurrentTestSuiteTimeStampFolder()
	{
		return strCurrentTestSuiteTimeStampFolder;
	}

	public static final String getStrProjectName()
	{
		return strProjectName;
	}

	public static Object[][] getTestScript_CreateTestSteps()
	{

		if (null == alTestScript_CreateTestSteps)
		{
			return new Object[0][TESTSTEP_OBJECT_TOTALSIZE];
		}
		else
		{
			int objArraySize = alTestScript_CreateTestSteps.size();
			Object objTestScript_CreateTestSteps[][] = new Object[objArraySize][TESTSTEP_OBJECT_TOTALSIZE];
			for (int iCtr = 0; iCtr < objArraySize; iCtr++)
			{
				Object objTemp[] = alTestScript_CreateTestSteps.get(iCtr);
				objTestScript_CreateTestSteps[iCtr][TESTSTEP_OBJECT_SEQNO] = objTemp[TESTSTEP_OBJECT_SEQNO];
				objTestScript_CreateTestSteps[iCtr][TESTSTEP_OBJECT_COMMAND] = objTemp[TESTSTEP_OBJECT_COMMAND];
				objTestScript_CreateTestSteps[iCtr][TESTSTEP_OBJECT_METHOD] = objTemp[TESTSTEP_OBJECT_METHOD];
				objTestScript_CreateTestSteps[iCtr][TESTSTEP_OBJECT_TARGET] = objTemp[TESTSTEP_OBJECT_TARGET];
				objTestScript_CreateTestSteps[iCtr][TESTSTEP_OBJECT_VALUE] = objTemp[TESTSTEP_OBJECT_VALUE];
			}
			return objTestScript_CreateTestSteps;
		}

	}

	public static String[] getTestSuite_SelectedTestCase_Names()
	{

		if (null == alTestCasesSelectedForTestSuite)
		{
			return new String[0];
		}
		else
		{
			int objArraySize = alTestCasesSelectedForTestSuite.size();
			String objTestSuite_SelectedTestCaseNames[] = new String[objArraySize];
			for (int iCtr = 0; iCtr < objArraySize; iCtr++)
			{
				Object objTemp[] = alTestCasesSelectedForTestSuite.get(iCtr);
				objTestSuite_SelectedTestCaseNames[iCtr] = (String) objTemp[TESTSUITE_SELECTED_TESTCASE_NAME];
			}
			return objTestSuite_SelectedTestCaseNames;
		}

	}

	public static int[] getTestSuite_SelectedTestCaseIDs()
	{

		if (null == alTestCasesSelectedForTestSuite)
		{
			return new int[0];
		}
		else
		{
			int objArraySize = alTestCasesSelectedForTestSuite.size();
			int objTestSuite_SelectedTestCaseIDs[] = new int[objArraySize];
			for (int iCtr = 0; iCtr < objArraySize; iCtr++)
			{
				Object objTemp[] = alTestCasesSelectedForTestSuite.get(iCtr);
				objTestSuite_SelectedTestCaseIDs[iCtr] = ((Integer) objTemp[TESTSUITE_SELECTED_TESTCASE_ID]).intValue();
			}
			return objTestSuite_SelectedTestCaseIDs;
		}

	}

	public static Object[][] getTestSuite_SelectedTestCases()
	{

		if (null == alTestCasesSelectedForTestSuite)
		{
			return new Object[0][TESTSUITE_SELECTED_TESTCASE_TOTALSIZE];
		}
		else
		{
			int objArraySize = alTestCasesSelectedForTestSuite.size();
			Object objTestSuite_SelectedTestCases[][] = new Object[objArraySize][TESTSUITE_SELECTED_TESTCASE_TOTALSIZE];
			for (int iCtr = 0; iCtr < objArraySize; iCtr++)
			{
				Object objTemp[] = alTestCasesSelectedForTestSuite.get(iCtr);
				objTestSuite_SelectedTestCases[iCtr][TESTSUITE_SELECTED_TESTCASE_ID] = objTemp[TESTSUITE_SELECTED_TESTCASE_ID];
				objTestSuite_SelectedTestCases[iCtr][TESTSUITE_SELECTED_TESTCASE_NAME] = objTemp[TESTSUITE_SELECTED_TESTCASE_NAME];
			}
			return objTestSuite_SelectedTestCases;
		}

	}

	public static boolean isSeqNumberPresent(int seqNo)
	{
		if (null == alTestScript_CreateTestSteps)
		{
			return false;
		}
		else
		{
			int objArraySize = alTestScript_CreateTestSteps.size();
			for (int iCtr = 0; iCtr < objArraySize; iCtr++)
			{
				Object objTemp[] = alTestScript_CreateTestSteps.get(iCtr);
				if (seqNo == ((Integer) objTemp[TESTSTEP_OBJECT_SEQNO]).intValue())
					return true;

			}

		}
		return false;
	}

	public static int nextTestStepSequenceNumber()
	{
		if (null == alTestScript_CreateTestSteps)
		{
			return 1;
		}
		else
		{
			int iNextSeqNo = 0;
			int objArraySize = alTestScript_CreateTestSteps.size();
			for (int iCtr = 0; iCtr < objArraySize; iCtr++)
			{
				Object objTemp[] = alTestScript_CreateTestSteps.get(iCtr);
				if (iNextSeqNo < ((Integer) objTemp[TESTSTEP_OBJECT_SEQNO]).intValue())
					iNextSeqNo = ((Integer) objTemp[TESTSTEP_OBJECT_SEQNO]).intValue();

			}
			return iNextSeqNo + 1;
		}

	}

	public static void removeTestSuite_SelectedTestCase(int rowid)
	{
		if (rowid >= 0)
			BIRT_DataObject.alTestCasesSelectedForTestSuite.remove(rowid);

		else BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception(BIRT_Resources.getRbAppResourceBundle().getString(
				BIRT_Resource_PropertyNames.TXT_APP_FIELD_ERROR_DISPLAYABLE_MSG_QUERY_BROWSE_SELECT_VALUE_NULL)));
	}

	public static void setApp_Panel_DBConnection_Browse_SelectedDBConnectionID(int app_Panel_DBConnection_Browse_SelectedDBConnectionID)
	{
		App_Panel_DBConnection_Browse_SelectedDBConnectionID = app_Panel_DBConnection_Browse_SelectedDBConnectionID;
	}

	public static void setApp_Panel_DBConnection_Browse_SelectedDBConnectionName(String app_Panel_DBConnection_Browse_SelectedDBConnectionName)
	{
		App_Panel_DBConnection_Browse_SelectedDBConnectionName = app_Panel_DBConnection_Browse_SelectedDBConnectionName;
	}

	public static void setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID(int app_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID)
	{
		App_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID = app_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID;
	}

	public static void setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName(String app_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName)
	{
		App_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName = app_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName;
	}

	public static final void setApp_Panel_Query_Browse_SelectedQueryDesc(String app_Panel_Query_Browse_SelectedQueryDesc)
	{
		App_Panel_Query_Browse_SelectedQueryDesc = app_Panel_Query_Browse_SelectedQueryDesc;
	}

	public static final void setApp_Panel_Query_Browse_SelectedQueryID(int app_Panel_Query_Browse_SelectedQueryID)
	{
		App_Panel_Query_Browse_SelectedQueryID = app_Panel_Query_Browse_SelectedQueryID;
	}

	public static final void setApp_Panel_Query_Browse_SelectedQueryName(String app_Panel_Query_Browse_SelectedQueryName)
	{
		App_Panel_Query_Browse_SelectedQueryName = app_Panel_Query_Browse_SelectedQueryName;
	}

	public static void setApp_Panel_Query_BrowsenAdd_SelectedQueryID(int app_Panel_Query_BrowsenAdd_SelectedQueryID)
	{
		App_Panel_Query_BrowsenAdd_SelectedQueryID = app_Panel_Query_BrowsenAdd_SelectedQueryID;
	}

	public static void setApp_Panel_Query_BrowsenAdd_SelectedQueryName(String app_Panel_Query_BrowsenAdd_SelectedQueryName)
	{
		App_Panel_Query_BrowsenAdd_SelectedQueryName = app_Panel_Query_BrowsenAdd_SelectedQueryName;
	}

	public static void setApp_Panel_TestCase_Browse_SelectedTestCaseID(int app_Panel_TestCase_Browse_SelectedTestCaseID)
	{
		App_Panel_TestCase_Browse_SelectedTestCaseID = app_Panel_TestCase_Browse_SelectedTestCaseID;
	}

	public static void setApp_Panel_TestCase_Browse_SelectedTestCaseName(String app_Panel_TestCase_Browse_SelectedTestCaseName)
	{
		App_Panel_TestCase_Browse_SelectedTestCaseName = app_Panel_TestCase_Browse_SelectedTestCaseName;
	}

	public static void setApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseID(int app_Panel_TestCase_BrowsenAdd_SelectedTestCaseID)
	{
		App_Panel_TestCase_BrowsenAdd_SelectedTestCaseID = app_Panel_TestCase_BrowsenAdd_SelectedTestCaseID;
	}

	public static void setApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseName(String app_Panel_TestCase_BrowsenAdd_SelectedTestCaseName)
	{
		App_Panel_TestCase_BrowsenAdd_SelectedTestCaseName = app_Panel_TestCase_BrowsenAdd_SelectedTestCaseName;
	}

	public static void setApp_Panel_TestScript_Browse_SelectedTestScriptDescription(String app_Panel_TestScript_Browse_SelectedTestScriptDescription)
	{
		App_Panel_TestScript_Browse_SelectedTestScriptDescription = app_Panel_TestScript_Browse_SelectedTestScriptDescription;
	}

	public static void setApp_Panel_TestScript_Browse_SelectedTestScriptID(int app_Panel_TestScript_Browse_SelectedTestScriptID)
	{
		App_Panel_TestScript_Browse_SelectedTestScriptID = app_Panel_TestScript_Browse_SelectedTestScriptID;
	}

	public static void setApp_Panel_TestScript_Browse_SelectedTestScriptName(String app_Panel_TestScript_Browse_SelectedTestScriptName)
	{
		App_Panel_TestScript_Browse_SelectedTestScriptName = app_Panel_TestScript_Browse_SelectedTestScriptName;
	}

	public static void setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptID(int app_Panel_TestScript_BrowsenAdd_SelectedTestScriptID)
	{
		App_Panel_TestScript_BrowsenAdd_SelectedTestScriptID = app_Panel_TestScript_BrowsenAdd_SelectedTestScriptID;
	}

	public static void setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptName(String app_Panel_TestScript_BrowsenAdd_SelectedTestScriptName)
	{
		App_Panel_TestScript_BrowsenAdd_SelectedTestScriptName = app_Panel_TestScript_BrowsenAdd_SelectedTestScriptName;
	}

	public static void setApp_Panel_TestSuite_Browse_SelectedTestSuiteDesc(String app_Panel_TestSuite_Browse_SelectedTestSuiteDesc)
	{
		App_Panel_TestSuite_Browse_SelectedTestSuiteDesc = app_Panel_TestSuite_Browse_SelectedTestSuiteDesc;
	}

	public static void setApp_Panel_TestSuite_Browse_SelectedTestSuiteID(int app_Panel_TestSuite_Browse_SelectedTestSuiteID)
	{
		App_Panel_TestSuite_Browse_SelectedTestSuiteID = app_Panel_TestSuite_Browse_SelectedTestSuiteID;
	}

	public static void setApp_Panel_TestSuite_Browse_SelectedTestSuiteName(String app_Panel_TestSuite_Browse_SelectedTestSuiteName)
	{
		App_Panel_TestSuite_Browse_SelectedTestSuiteName = app_Panel_TestSuite_Browse_SelectedTestSuiteName;
	}
	
	public static void setApp_Panel_TestSuite_Browse_SelectedTestFilePath(String app_Panel_TestSuite_Browse_SelectedTestFilePath)
	{
		App_Panel_TestSuite_Browse_SelectedTestFilePath = app_Panel_TestSuite_Browse_SelectedTestFilePath;
	}

	public static void setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc(String app_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc)
	{
		App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc = app_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc;
	}

	public static void setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID(int app_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID)
	{
		App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID = app_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID;
	}

	public static void setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName(String app_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName)
	{
		App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName = app_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName;
	}
	
	public static void setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestFilePath(String app_Panel_TestSuite_BrowseRunNViewHstry_SelectedFilePath)
	{
		App_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestFilePath = app_Panel_TestSuite_BrowseRunNViewHstry_SelectedFilePath;
	}

	public static void setIExecute_TestSuiteID(int execute_TestSuiteID)
	{
		iExecute_TestSuiteID = execute_TestSuiteID;
	}

	public static final void setIProjectID(int projectID)
	{
		iProjectID = projectID;
	}

	public static void setiTestCaseFailed(int iTestCaseFailed)
	{
		BIRT_DataObject.iTestCaseFailed = iTestCaseFailed;
	}

	public static void setiTestCasePassed(int iTestCasePassed)
	{
		BIRT_DataObject.iTestCasePassed = iTestCasePassed;
	}

	public static void setiTestPassPercentage(int iTestPassPercentage)
	{
		BIRT_DataObject.iTestPassPercentage = iTestPassPercentage;
	}

	public static void setiTotalTestCases(int iTotalTestCases)
	{
		BIRT_DataObject.iTotalTestCases = iTotalTestCases;
	}

	public static void setObjDBConnectionTableDetails(Object[] objDBConnectionTableDetails)
	{
		BIRT_DataObject.objDBConnectionTableDetails = objDBConnectionTableDetails;
	}

	public static final void setObjQueryTableDetails(Object[] objQueryTableDetails)
	{
		BIRT_DataObject.objQueryTableDetails = objQueryTableDetails;
	}

	public static void setObjTestCaseTableDetails(Object[] objTestCaseTableDetails)
	{
		BIRT_DataObject.objTestCaseTableDetails = objTestCaseTableDetails;
	}

	public static void setObjTestScriptTableDetails(Object[] objTestScriptTableDetails)
	{
		BIRT_DataObject.objTestScriptTableDetails = objTestScriptTableDetails;
	}

	public static void setObjTestSuiteHistoryTableDetails(Object[] objTestSuiteHistoryTableDetails)
	{
		BIRT_DataObject.objTestSuiteHistoryTableDetails = objTestSuiteHistoryTableDetails;
	}

	public static final void setObjTestSuiteTableDetails(Object[] objTestSuiteTableDetails)
	{
		BIRT_DataObject.objTestSuiteTableDetails = objTestSuiteTableDetails;
	}

//	public static void setObjWebDriver(BIRT_WebDriver objWebDriver)
//	{
//		BIRT_DataObject.objWebDriver = objWebDriver;
//	}
    // Zekun
	public static void setObjWebDriver(BIRT_WebDriver objWebDriver)
	{
		BIRT_DataObject.objWebDriver = objWebDriver;
	}

	public static void setRsmtdCurrentQueryResult(ResultSetMetaData rsmtdCurrentQueryResult)
	{
		BIRT_DataObject.rsmtdCurrentQueryResult = rsmtdCurrentQueryResult;
	}

	public static void setStrCurrentReportFile(String strCurrentReportFile)
	{
		BIRT_DataObject.strCurrentReportFile = strCurrentReportFile;
	}

	public static void setStrCurrentSourceFile(String strCurrentSourceFile)
	{
		BIRT_DataObject.strCurrentSourceFile = strCurrentSourceFile;
	}

	public static void setStrCurrentTestCaseIDFolder(String strCurrentTestCaseIDFolder)
	{
		BIRT_DataObject.strCurrentTestCaseIDFolder = strCurrentTestCaseIDFolder;
	}

	public static void setStrCurrentTestSuiteTimeStampFolder(String strCurrentTestSuiteTimeStampFolder)
	{
		BIRT_DataObject.strCurrentTestSuiteTimeStampFolder = strCurrentTestSuiteTimeStampFolder;
	}

	public static final void setStrProjectName(String strProjectName)
	{
		BIRT_DataObject.strProjectName = strProjectName;
	}

}
