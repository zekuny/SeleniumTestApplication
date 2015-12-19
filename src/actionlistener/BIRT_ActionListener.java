package actionlistener;

import java.awt.event.ActionListener;

public interface BIRT_ActionListener extends ActionListener
{
	// Main Window Commands
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_QUERY_CREATE					= "Windows_Frames_MainWindow_Query_Create";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_QUERY_BROWSE					= "Windows_Frames_MainWindow_Query_Browse";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_DBCONN_CREATE					= "Windows_Frames_MainWindow_DbConn_Create";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_DBCONN_BROWSE					= "Windows_Frames_MainWindow_DbConn_Browse";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSCRIPT_CREATE				= "Windows_Frames_MainWindow_TestScript_Create";
	public final static String	aPP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSCRIPT_BROWSE				= "Windows_Frames_MainWindow_TestScript_Browse";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTCASE_CREATE				= "Windows_Frames_MainWindow_TestCase_Create";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTCASE_BROWSE				= "Windows_Frames_MainWindow_TestCase_Browse";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_CREATE				= "Windows_Frames_MainWindow_TestSuite_Create";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_BROWSERUNNVIEWHSTRY	= "Windows_Frames_MainWindow_TestSuite_Browse_RunNViewHistory";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_BROWSE				= "Windows_Frames_MainWindow_TestSuite_Browse";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_APP_CONFIG						= "Windows_Frames_MainWindow_App_Config";
	public final static String	APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL								= "Windows_Frames_MainWindow_Cancel";

	// Panel - Query - Create
	public final static String	APP_WINDOWS_PANELS_QUERY_CREATE_SAVE								= "Windows_Panels_Query_Create_Save";
	public final static String	APP_WINDOWS_PANELS_QUERY_CREATE_CANCEL								= "Windows_Panels_Query_Create_Cancel";

	// Panel - Query - Browse
	public final static String	APP_WINDOWS_PANELS_QUERY_BROWSE_VIEW								= "Windows_Panels_Query_Browse_Save";
	public final static String	APP_WINDOWS_PANELS_QUERY_BROWSE_CANCEL								= "Windows_Panels_Query_Browse_Cancel";
	public final static String	APP_WINDOWS_PANELS_QUERY_BROWSE_EDIT								= "Windows_Panels_Query_Browse_Edit";
	public final static String	APP_WINDOWS_PANELS_QUERY_BROWSE_DELETE								= "Windows_Panels_Query_Browse_Delete";

	// Panel - Query - View
	public final static String	APP_WINDOWS_PANELS_QUERY_VIEW_CANCEL								= "Windows_Panels_Query_View_Cancel";

	// Panel - Query - Edit
	public final static String	APP_WINDOWS_PANELS_QUERY_EDIT_SAVE									= "Windows_Panels_Query_Edit_Save";
	public final static String	APP_WINDOWS_PANELS_QUERY_EDIT_CANCEL								= "Windows_Panels_Query_Edit_Cancel";

	// Panel - Query - Delete
	public final static String	APP_WINDOWS_PANELS_QUERY_DELETE_CANCEL								= "Windows_Panels_Query_Delete_Cancel";
	public final static String	APP_WINDOWS_PANELS_QUERY_DELETE_DELETE								= "Windows_Panels_Query_Delete_Delete";

	// Panel - Db Connection - Create
	public final static String	APP_WINDOWS_PANELS_DBCONN_CREATE_SAVE								= "Windows_Panels_DBConn_Create_Save";
	public final static String	APP_WINDOWS_PANELS_DBCONN_CREATE_CANCEL								= "Windows_Panels_DBConn_Create_Cancel";

	// Panel - TestScript - Create
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_SAVE							= "Windows_Panels_TestScript_Create_Save";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_CANCEL							= "Windows_Panels_TestScript_Create_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_CREATE_STEP					= "Windows_Panels_TestScript_Create_Create_Step";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_IMPORT							= "Windows_Panels_TestScript_Create_Import";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_EXPORT							= "Windows_Panels_TestScript_Create_Export";

	// Panel - TestScript - Edit
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_SAVE								= "Windows_Panels_TestScript_Edit_Save";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_CANCEL							= "Windows_Panels_TestScript_Edit_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_CREATE_STEP						= "Windows_Panels_TestScript_Edit_Create_Step";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_IMPORT							= "Windows_Panels_TestScript_Edit_Import";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_EXPORT							= "Windows_Panels_TestScript_Edit_Export";

	// Panel - TestScript - Browse
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_VIEW							= "Windows_Panels_TestScript_Browse_View";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_EDIT							= "Windows_Panels_TestScript_Browse_Edit";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_DELETE							= "Windows_Panels_TestScript_Browse_Delete";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_CANCEL							= "Windows_Panels_TestScript_Browse_Cancel";

	// Panel - TestScript - Browse
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_VIEW_CANCEL							= "Windows_Panels_TestScript_View_Cancel";

	// Panel - TestScript - Delete
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_DELETE_CANCEL							= "Windows_Panels_TestScript_Delete_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTSCRIPT_DELETE_DELETE							= "Windows_Panels_TestScript_Delete_Delete";

	// Panel Test Step - Create
	public final static String	APP_WINDOWS_PANELS_TESTSTEP_CREATE_CREATESTEP						= "Windows_Panels_TestStep_Create_CreateStep";
	public final static String	APP_WINDOWS_PANELS_TESTSTEP_CREATE_CANCELSTEP						= "Windows_Panels_TestStep_Create_CancelStep";

	// Panel Test Case - Create
	public final static String	APP_WINDOWS_PANELS_TESTCASE_CREATE_CREATE							= "Windows_Panels_TestCase_Create_Create";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_CREATE_CANCEL							= "Windows_Panels_TestCase_Create_Cancel";

	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_TESTSCRIPT						= "Windows_Panels_TestCase_Browse_TestScript";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_DBCONNECTION						= "Windows_Panels_TestCase_Browse_DBConnection";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_QUERY							= "Windows_Panels_TestCase_Browse_Query";

	// Panel Test Case - Copy
	public final static String	APP_WINDOWS_PANELS_TESTCASE_COPY_CREATE								= "Windows_Panels_TestCase_Copy_Create";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_COPY_CANCEL								= "Windows_Panels_TestCase_Copy_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_COPY_BROWSE_TESTSCRIPT					= "Windows_Panels_TestCase_Copy_Browse_TestScript";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_COPY_BROWSE_DBCONNECTION				= "Windows_Panels_TestCase_Copy_Browse_DBConnection";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_COPY_BROWSE_QUERY						= "Windows_Panels_TestCase_Copy_Browse_Query";

	// Panel Test Case - Edit
	public final static String	APP_WINDOWS_PANELS_TESTCASE_EDIT_CREATE								= "Windows_Panels_TestCase_Edit_Create";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_EDIT_CANCEL								= "Windows_Panels_TestCase_Edit_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_TESTSCRIPT					= "Windows_Panels_TestCase_Edit_Browse_TestScript";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_DBCONNECTION				= "Windows_Panels_TestCase_Edit_Browse_DBConnection";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_QUERY						= "Windows_Panels_TestCase_Edit_Browse_Query";

	//Panel TestCase - Browse
	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_COPY								= "Windows_Panels_DBConnection_Browse_Copy";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_EDIT								= "Windows_Panels_DBConnection_Browse_Edit";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_DELETE							= "Windows_Panels_DBConnection_Browse_Delete";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_VIEW								= "Windows_Panels_DBConnection_Browse_View";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_BROWSE_CANCEL							= "Windows_Panels_DBConnection_Browse_Cancel";

	//Panel TestCase - View
	public final static String	APP_WINDOWS_PANELS_TESTCASE_VIEW_CANCEL								= "Windows_Panels_TestCase_View_Cancel";

	//Panel TestCase - Delete
	public final static String	APP_WINDOWS_PANELS_TESTCASE_DELETE_CANCEL							= "Windows_Panels_TestCase_Delete_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTCASE_DELETE_DELETE							= "Windows_Panels_TestCase_Delete_Delete";

	// Panel BrowsenAdd TestScript
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_ADD						= "Windows_Panels_TestCase_Create_Browse_TestScript_Add";
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_CANCEL						= "Windows_Panels_TestCase_Create_Browse_TestScript_Cancel";

	// Panel BrowsenAdd DB Connection
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_DBCONNECTION_ADD						= "Windows_Panels_TestCase_Create_Browse_DBConnection_Add";
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_DBCONNECTION_CANCEL					= "Windows_Panels_TestCase_Create_Browse_DBConnection_Cancel";

	// Panel BrowsenAdd Query
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_QUERY_ADD								= "Windows_Panels_TestCase_Create_Browse_Query_Add";
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_QUERY_CANCEL							= "Windows_Panels_TestCase_Create_Browse_Query_Cancel";

	// Panel BrowsenAdd TestCase
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_TESTCASE_ADD							= "Windows_Panels_TestCase_Create_Browse_TestCase_Add";
	public final static String	APP_WINDOWS_PANELS_BROWSENADD_TESTCASE_CANCEL						= "Windows_Panels_TestCase_Create_Browse_TestCase_Cancel";

	// Panel Test Suite - Create
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_CREATE_CREATE							= "Windows_Panels_TestSuite_Create_Create";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_CREATE_CANCEL							= "Windows_Panels_TestSuite_Create_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_CREATE_ADD_TESTCASE					= "Windows_Panels_TestSuite_Create_Add_TestCase";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_CREATE_REMOVE_TESTCASE					= "Windows_Panels_TestSuite_Create_Remove_TestCase";

	// Panel Test Suite - Edit
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_EDIT_CREATE							= "Windows_Panels_TestSuite_Edit_Create";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_EDIT_CANCEL							= "Windows_Panels_TestSuite_Edit_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_EDIT_ADD_TESTCASE						= "Windows_Panels_TestSuite_Edit_Add_TestCase";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_EDIT_REMOVE_TESTCASE					= "Windows_Panels_TestSuite_Edit_Remove_TestCase";

	// Panel Test Suite - Browse Run N View History
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_RUN				= "Windows_Panels_TestSuite_BrowseRunNViewHistory_Run";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_VIEWHSTRY			= "Windows_Panels_TestSuite_BrowseRunNViewHistory_ViewHistory";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_CANCEL			= "Windows_Panels_TestSuite_BrowseRunNViewHistory_Cancel";

	// Panel Test Suite - Browse
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_BROWSE_VIEW							= "Windows_Panels_TestSuite_Browse_View";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_BROWSE_EDIT							= "Windows_Panels_TestSuite_Browse_Edit";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_BROWSE_DELETE							= "Windows_Panels_TestSuite_Browse_Delete";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_BROWSE_CANCEL							= "Windows_Panels_TestSuite_Browse_Cancel";

	// Panel Test Suite - Run
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_RUN_RUN								= "Windows_Panels_TestSuite_Run_Run";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_RUN_CANCEL								= "Windows_Panels_TestSuite_Run_Cancel";

	// Panel Test Suite - View
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_VIEW_CANCEL							= "Windows_Panels_TestSuite_View_Cancel";

	// Panel Test Suite - Delete
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_DELETE_CANCEL							= "Windows_Panels_TestSuite_Delete_Cancel";
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_DELETE_DELETE							= "Windows_Panels_TestSuite_Delete_Delete";

	// Panel Test Suite - View History
	public final static String	APP_WINDOWS_PANELS_TESTSUITE_VIEWHISTORY_CANCEL						= "Windows_Panels_TestSuite_ViewHistory_Cancel";

	// Panel Application Configuration
	public final static String	APP_WINDOWS_PANELS_APPCONFIG_FIREFOX_BROWSE							= "Windows_Panels_AppConfig_Firefox_Browse";
	public final static String	APP_WINDOWS_PANELS_APPCONFIG_FIREFOX_PROFILE_BROWSE					= "Windows_Panels_AppConfig_Firefox_Profile_Browse";
	public final static String	APP_WINDOWS_PANELS_APPCONFIG_IEDRIVER_BROWSE						= "Windows_Panels_AppConfig_IEDriver_Browse";
	public final static String	APP_WINDOWS_PANELS_APPCONFIG_REPORT_DOWNLOAD_BROWSE					= "Windows_Panels_AppConfig_Report_Download_Browse";
	public final static String	APP_WINDOWS_PANELS_APPCONFIG_REPORT_ARCHIVE_BROWSE					= "Windows_Panels_AppConfig_Report_Archive_Browse";
	public final static String	APP_WINDOWS_PANELS_APPCONFIG_SAVE									= "Windows_Panels_AppConfig_Save";
	public final static String	APP_WINDOWS_PANELS_APPCONFIG_CANCEL									= "Windows_Panels_AppConfig_Cancel";

	//Panel DBConnection Browse
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_CANCEL						= "Windows_Panels_DBConnection_Browse_Cancel";
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_DELETE						= "Windows_Panels_DBConnection_Browse_Delete";
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_EDIT							= "Windows_Panels_DBConnection_Browse_Edit";
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_VIEW							= "Windows_Panels_DBConnection_Browse_View";

	//Panel DBConnection View
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_VIEW_CANCEL							= "Windows_Panels_DBConnection_View_Cancel";

	//Panel DBConnection Delete
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_DELETE_CANCEL						= "Windows_Panels_DBConnection_Delete_Cancel";
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_DELETE_DELETE						= "Windows_Panels_DBConnection_Delete_Delete";

	//Panel DBConnection Edit
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_EDIT_CANCEL							= "Windows_Panels_DBConnection_Edit_Cancel";
	public final static String	APP_WINDOWS_PANELS_DBCONNECTION_EDIT_SAVE							= "Windows_Panels_DBConnection_Edit_Save";

}
