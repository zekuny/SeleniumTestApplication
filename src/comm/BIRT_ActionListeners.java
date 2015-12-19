package comm;

import actionlistener.windows.frames.mainwindow.BIRT_ActionListener_MainWindow;
import actionlistener.windows.panels.app.property.BIRT_ActionListener_App_Property;
import actionlistener.windows.panels.dbconnection.BIRT_ActionListener_DBConnection_Browse;
import actionlistener.windows.panels.dbconnection.BIRT_ActionListener_DBConnection_Create;
import actionlistener.windows.panels.dbconnection.BIRT_ActionListener_DBConnection_Delete;
import actionlistener.windows.panels.dbconnection.BIRT_ActionListener_DBConnection_Edit;
import actionlistener.windows.panels.dbconnection.BIRT_ActionListener_DBConnection_View;
import actionlistener.windows.panels.query.BIRT_ActionListener_Query_Browse;
import actionlistener.windows.panels.query.BIRT_ActionListener_Query_Create;
import actionlistener.windows.panels.query.BIRT_ActionListener_Query_Delete;
import actionlistener.windows.panels.query.BIRT_ActionListener_Query_Edit;
import actionlistener.windows.panels.query.BIRT_ActionListener_Query_View;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_Browse;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_Copy;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_Create;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_DBConnectionBrowsenAdd;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_Delete;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_Edit;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_QueryBrowsenAdd;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_TestScriptBrowsenAdd;
import actionlistener.windows.panels.testcase.BIRT_ActionListener_TestCase_View;
import actionlistener.windows.panels.testscript.BIRT_ActionListener_TestScript_Browse;
import actionlistener.windows.panels.testscript.BIRT_ActionListener_TestScript_Create;
import actionlistener.windows.panels.testscript.BIRT_ActionListener_TestScript_Delete;
import actionlistener.windows.panels.testscript.BIRT_ActionListener_TestScript_Edit;
import actionlistener.windows.panels.testscript.BIRT_ActionListener_TestScript_TestStep_Create;
import actionlistener.windows.panels.testscript.BIRT_ActionListener_TestScript_View;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_Browse;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_BrowseRunNViewHistory;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_Create;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_Delete;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_Edit;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_Run;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_TestCaseBrowseNAdd;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_View;
import actionlistener.windows.panels.testsuite.BIRT_ActionListener_TestSuite_ViewHistory;

public class BIRT_ActionListeners
{
	private static BIRT_ActionListener_MainWindow						objActionListener_MainWindow;
	private static BIRT_ActionListener_Query_Create						objActionListener_Panel_Query_Create;
	private static BIRT_ActionListener_Query_Browse						objActionListener_Panel_Query_Browse;
	private static BIRT_ActionListener_Query_View						objActionListener_Panel_Query_View;
	private static BIRT_ActionListener_Query_Edit						objActionListener_Panel_Query_Edit;
	private static BIRT_ActionListener_Query_Delete						objActionListener_Panel_Query_Delete;
	private static BIRT_ActionListener_DBConnection_Create				objActionListener_Panel_DBConnection_Create;
	private static BIRT_ActionListener_DBConnection_Browse				objActionListener_Panel_DBConnection_Browse;
	private static BIRT_ActionListener_DBConnection_View				objActionListener_Panel_DBConnection_View;
	private static BIRT_ActionListener_DBConnection_Delete				objActionListener_Panel_DBConnection_Delete;
	private static BIRT_ActionListener_DBConnection_Edit				objActionListener_Panel_DBConnection_Edit;
	private static BIRT_ActionListener_TestScript_Create				objActionListener_Panel_TestScript_Create;
	private static BIRT_ActionListener_TestCase_Create					objActionListener_Panel_TestCase_Create;
	private static BIRT_ActionListener_TestCase_Copy					objActionListener_Panel_TestCase_Copy;
	private static BIRT_ActionListener_TestCase_Edit					objActionListener_Panel_TestCase_Edit;
	private static BIRT_ActionListener_TestCase_Browse					objActionListener_Panel_TestCase_Browse;
	private static BIRT_ActionListener_TestCase_View					objActionListener_Panel_TestCase_View;
	private static BIRT_ActionListener_TestCase_Delete					objActionListener_Panel_TestCase_Delete;
	private static BIRT_ActionListener_TestCase_DBConnectionBrowsenAdd	objActionListener_Panel_TestCase_Create_DBConnectionBrowsenAdd;
	private static BIRT_ActionListener_TestCase_TestScriptBrowsenAdd	objActionListener_Panel_TestCase_Create_TestScriptBrowsenAdd;
	private static BIRT_ActionListener_TestCase_QueryBrowsenAdd			objActionListener_Panel_TestCase_Create_QueryBrowsenAdd;
	private static BIRT_ActionListener_TestSuite_TestCaseBrowseNAdd		objActionListener_Panel_TestSuite_Create_TestCaseBrowseNAdd;
	private static BIRT_ActionListener_TestSuite_Create					objActionListener_Panel_TestSuite_Create;
	private static BIRT_ActionListener_TestSuite_Browse					objActionListener_Panel_TestSuite_Browse;
	private static BIRT_ActionListener_TestScript_TestStep_Create		objActionListener_Panel_TestScript_TestStep_Create;
	private static BIRT_ActionListener_TestScript_Browse				objActionListener_Panel_TestScript_Browse;
	private static BIRT_ActionListener_TestScript_View					objActionListener_Panel_TestScript_View;
	private static BIRT_ActionListener_TestScript_Delete				objActionListener_Panel_TestScript_Delete;
	private static BIRT_ActionListener_TestScript_Edit					objActionListener_Panel_TestScript_Edit;
	private static BIRT_ActionListener_TestSuite_BrowseRunNViewHistory	objActionListener_Panel_TestSuite_BrowseRunNViewHistory;
	private static BIRT_ActionListener_TestSuite_Run					objActionListener_Panel_TestSuite_Run;
	private static BIRT_ActionListener_TestSuite_View					objActionListener_Panel_TestSuite_View;
	private static BIRT_ActionListener_TestSuite_Edit					objActionListener_Panel_TestSuite_Edit;
	private static BIRT_ActionListener_TestSuite_Delete					objActionListener_Panel_TestSuite_Delete;
	private static BIRT_ActionListener_TestSuite_ViewHistory			objActionListener_Panel_TestSuite_ViewHistory;
	private static BIRT_ActionListener_App_Property						objActionListener_Panel_App_Property;

	public static final BIRT_ActionListener_TestScript_Edit getObjActionListener_Panel_TestScript_Edit()
	{
		if (null == objActionListener_Panel_TestScript_Edit)
			objActionListener_Panel_TestScript_Edit = new BIRT_ActionListener_TestScript_Edit();
		return objActionListener_Panel_TestScript_Edit;

	}

	public static final BIRT_ActionListener_TestScript_Delete getObjActionListener_Panel_TestScript_Delete()
	{
		if (null == objActionListener_Panel_TestScript_Delete)
			objActionListener_Panel_TestScript_Delete = new BIRT_ActionListener_TestScript_Delete();
		return objActionListener_Panel_TestScript_Delete;

	}

	public static final BIRT_ActionListener_TestScript_View getObjActionListener_Panel_TestScript_View()
	{
		if (null == objActionListener_Panel_TestScript_View)
			objActionListener_Panel_TestScript_View = new BIRT_ActionListener_TestScript_View();
		return objActionListener_Panel_TestScript_View;

	}

	public static final BIRT_ActionListener_TestScript_Browse getObjActionListener_Panel_TestScript_Browse()
	{
		if (null == objActionListener_Panel_TestScript_Browse)
			objActionListener_Panel_TestScript_Browse = new BIRT_ActionListener_TestScript_Browse();
		return objActionListener_Panel_TestScript_Browse;

	}

	public static final BIRT_ActionListener_TestSuite_Delete getObjActionListener_Panel_TestSuite_Delete()
	{
		if (null == objActionListener_Panel_TestSuite_Delete)
			objActionListener_Panel_TestSuite_Delete = new BIRT_ActionListener_TestSuite_Delete();
		return objActionListener_Panel_TestSuite_Delete;

	}

	public static final BIRT_ActionListener_TestSuite_Edit getObjActionListener_Panel_TestSuite_Edit()
	{
		if (null == objActionListener_Panel_TestSuite_Edit)
			objActionListener_Panel_TestSuite_Edit = new BIRT_ActionListener_TestSuite_Edit();
		return objActionListener_Panel_TestSuite_Edit;

	}

	public static final BIRT_ActionListener_TestSuite_Browse getObjActionListener_Panel_TestSuite_Browse()
	{
		if (null == objActionListener_Panel_TestSuite_Browse)
			objActionListener_Panel_TestSuite_Browse = new BIRT_ActionListener_TestSuite_Browse();
		return objActionListener_Panel_TestSuite_Browse;

	}

	public static final BIRT_ActionListener_TestSuite_View getObjActionListener_Panel_TestSuite_View()
	{
		if (null == objActionListener_Panel_TestSuite_View)
			objActionListener_Panel_TestSuite_View = new BIRT_ActionListener_TestSuite_View();
		return objActionListener_Panel_TestSuite_View;

	}

	public static final BIRT_ActionListener_TestCase_Delete getObjActionListener_Panel_TestCase_Delete()
	{
		if (null == objActionListener_Panel_TestCase_Delete)
			objActionListener_Panel_TestCase_Delete = new BIRT_ActionListener_TestCase_Delete();
		return objActionListener_Panel_TestCase_Delete;

	}

	public static final BIRT_ActionListener_TestCase_Edit getObjActionListener_Panel_TestCase_Edit()
	{
		if (null == objActionListener_Panel_TestCase_Edit)
			objActionListener_Panel_TestCase_Edit = new BIRT_ActionListener_TestCase_Edit();
		return objActionListener_Panel_TestCase_Edit;

	}

	public static final BIRT_ActionListener_TestCase_Copy getObjActionListener_Panel_TestCase_Copy()
	{
		if (null == objActionListener_Panel_TestCase_Copy)
			objActionListener_Panel_TestCase_Copy = new BIRT_ActionListener_TestCase_Copy();
		return objActionListener_Panel_TestCase_Copy;

	}

	public static final BIRT_ActionListener_TestCase_View getObjActionListener_Panel_TestCase_View()
	{
		if (null == objActionListener_Panel_TestCase_View)
			objActionListener_Panel_TestCase_View = new BIRT_ActionListener_TestCase_View();
		return objActionListener_Panel_TestCase_View;

	}

	public static final BIRT_ActionListener_TestCase_Browse getObjActionListener_Panel_TestCase_Browse()
	{
		if (null == objActionListener_Panel_TestCase_Browse)
			objActionListener_Panel_TestCase_Browse = new BIRT_ActionListener_TestCase_Browse();
		return objActionListener_Panel_TestCase_Browse;

	}

	public static final BIRT_ActionListener_DBConnection_Edit getObjActionListener_Panel_DBConnection_Edit()
	{
		if (null == objActionListener_Panel_DBConnection_Edit)
			objActionListener_Panel_DBConnection_Edit = new BIRT_ActionListener_DBConnection_Edit();
		return objActionListener_Panel_DBConnection_Edit;

	}

	public static final BIRT_ActionListener_DBConnection_Delete getObjActionListener_Panel_DBConnection_Delete()
	{
		if (null == objActionListener_Panel_DBConnection_Delete)
			objActionListener_Panel_DBConnection_Delete = new BIRT_ActionListener_DBConnection_Delete();
		return objActionListener_Panel_DBConnection_Delete;

	}

	public static final BIRT_ActionListener_DBConnection_View getObjActionListener_Panel_DBConnection_View()
	{
		if (null == objActionListener_Panel_DBConnection_View)
			objActionListener_Panel_DBConnection_View = new BIRT_ActionListener_DBConnection_View();
		return objActionListener_Panel_DBConnection_View;

	}

	public static final BIRT_ActionListener_DBConnection_Browse getObjActionListener_Panel_DBConnection_Browse()
	{
		if (null == objActionListener_Panel_DBConnection_Browse)
			objActionListener_Panel_DBConnection_Browse = new BIRT_ActionListener_DBConnection_Browse();
		return objActionListener_Panel_DBConnection_Browse;

	}

	public static final BIRT_ActionListener_TestSuite_ViewHistory getObjActionListener_Panel_TestSuite_ViewHistory()
	{
		if (null == objActionListener_Panel_TestSuite_ViewHistory)
			objActionListener_Panel_TestSuite_ViewHistory = new BIRT_ActionListener_TestSuite_ViewHistory();
		return objActionListener_Panel_TestSuite_ViewHistory;

	}

	public static final BIRT_ActionListener_App_Property getObjActionListener_Panel_App_Property()
	{
		if (null == objActionListener_Panel_App_Property)
			objActionListener_Panel_App_Property = new BIRT_ActionListener_App_Property();
		return objActionListener_Panel_App_Property;

	}

	public static final BIRT_ActionListener_TestSuite_Run getObjActionListener_Panel_TestSuite_Run()
	{
		if (null == objActionListener_Panel_TestSuite_Run)
			objActionListener_Panel_TestSuite_Run = new BIRT_ActionListener_TestSuite_Run();
		return objActionListener_Panel_TestSuite_Run;

	}

	public static final BIRT_ActionListener_TestSuite_BrowseRunNViewHistory getObjActionListener_Panel_TestSuite_BrowseRunNViewHistory()
	{
		if (null == objActionListener_Panel_TestSuite_BrowseRunNViewHistory)
			objActionListener_Panel_TestSuite_BrowseRunNViewHistory = new BIRT_ActionListener_TestSuite_BrowseRunNViewHistory();
		return objActionListener_Panel_TestSuite_BrowseRunNViewHistory;

	}

	public static final BIRT_ActionListener_TestScript_TestStep_Create getObjActionListener_Panel_TestScript_TestStep_Create()
	{
		if (null == objActionListener_Panel_TestScript_TestStep_Create)
			objActionListener_Panel_TestScript_TestStep_Create = new BIRT_ActionListener_TestScript_TestStep_Create();
		return objActionListener_Panel_TestScript_TestStep_Create;

	}

	public static final BIRT_ActionListener_TestSuite_TestCaseBrowseNAdd getObjActionListener_Panel_TestSuite_Create_TestCaseBrowseNAdd()
	{
		if (null == objActionListener_Panel_TestSuite_Create_TestCaseBrowseNAdd)
			objActionListener_Panel_TestSuite_Create_TestCaseBrowseNAdd = new BIRT_ActionListener_TestSuite_TestCaseBrowseNAdd();
		return objActionListener_Panel_TestSuite_Create_TestCaseBrowseNAdd;

	}

	public static final BIRT_ActionListener_TestSuite_Create getObjActionListener_Panel_TestSuite_Create()
	{
		if (null == objActionListener_Panel_TestSuite_Create)
			objActionListener_Panel_TestSuite_Create = new BIRT_ActionListener_TestSuite_Create();
		return objActionListener_Panel_TestSuite_Create;

	}

	public static final BIRT_ActionListener_TestCase_TestScriptBrowsenAdd getObjActionListener_Panel_TestCase_Create_TestScriptBrowsenAdd()
	{
		if (null == objActionListener_Panel_TestCase_Create_TestScriptBrowsenAdd)
			objActionListener_Panel_TestCase_Create_TestScriptBrowsenAdd = new BIRT_ActionListener_TestCase_TestScriptBrowsenAdd();
		return objActionListener_Panel_TestCase_Create_TestScriptBrowsenAdd;

	}

	public static final BIRT_ActionListener_TestCase_QueryBrowsenAdd getObjActionListener_Panel_TestCase_Create_QueryBrowsenAdd()
	{
		if (null == objActionListener_Panel_TestCase_Create_QueryBrowsenAdd)
			objActionListener_Panel_TestCase_Create_QueryBrowsenAdd = new BIRT_ActionListener_TestCase_QueryBrowsenAdd();
		return objActionListener_Panel_TestCase_Create_QueryBrowsenAdd;

	}

	public static final BIRT_ActionListener_TestCase_DBConnectionBrowsenAdd getObjActionListener_Panel_TestCase_Create_DBConnectionBrowsenAdd()
	{
		if (null == objActionListener_Panel_TestCase_Create_DBConnectionBrowsenAdd)
			objActionListener_Panel_TestCase_Create_DBConnectionBrowsenAdd = new BIRT_ActionListener_TestCase_DBConnectionBrowsenAdd();
		return objActionListener_Panel_TestCase_Create_DBConnectionBrowsenAdd;

	}

	public static final BIRT_ActionListener_TestCase_Create getObjActionListener_Panel_TestCase_Create()
	{
		if (null == objActionListener_Panel_TestCase_Create)
			objActionListener_Panel_TestCase_Create = new BIRT_ActionListener_TestCase_Create();
		return objActionListener_Panel_TestCase_Create;

	}

	public static final BIRT_ActionListener_TestScript_Create getObjActionListener_Panel_TestScript_Create()
	{
		if (null == objActionListener_Panel_TestScript_Create)
			objActionListener_Panel_TestScript_Create = new BIRT_ActionListener_TestScript_Create();
		return objActionListener_Panel_TestScript_Create;

	}

	public static final BIRT_ActionListener_DBConnection_Create getObjActionListener_Panel_DBConnection_Create()
	{
		if (null == objActionListener_Panel_DBConnection_Create)
			objActionListener_Panel_DBConnection_Create = new BIRT_ActionListener_DBConnection_Create();
		return objActionListener_Panel_DBConnection_Create;

	}

	public static final BIRT_ActionListener_Query_Delete getObjActionListener_Panel_Query_Delete()
	{
		if (null == objActionListener_Panel_Query_Delete)
			objActionListener_Panel_Query_Delete = new BIRT_ActionListener_Query_Delete();
		return objActionListener_Panel_Query_Delete;

	}

	public static final BIRT_ActionListener_Query_Edit getObjActionListener_Panel_Query_Edit()
	{
		if (null == objActionListener_Panel_Query_Edit)
			objActionListener_Panel_Query_Edit = new BIRT_ActionListener_Query_Edit();
		return objActionListener_Panel_Query_Edit;

	}

	public static final BIRT_ActionListener_Query_View getObjActionListener_Panel_Query_View()
	{
		if (null == objActionListener_Panel_Query_View)
			objActionListener_Panel_Query_View = new BIRT_ActionListener_Query_View();
		return objActionListener_Panel_Query_View;

	}

	public static final BIRT_ActionListener_Query_Browse getObjActionListener_Panel_Query_Browse()
	{
		if (null == objActionListener_Panel_Query_Browse)
			objActionListener_Panel_Query_Browse = new BIRT_ActionListener_Query_Browse();
		return objActionListener_Panel_Query_Browse;

	}

	public static final BIRT_ActionListener_MainWindow getObjActionListener_MainWindow()
	{
		if (null == objActionListener_MainWindow)
			objActionListener_MainWindow = new BIRT_ActionListener_MainWindow();
		return objActionListener_MainWindow;
	}

	public static final BIRT_ActionListener_Query_Create getObjActionListener_Panel_Query_Create()
	{
		if (null == objActionListener_Panel_Query_Create)
			objActionListener_Panel_Query_Create = new BIRT_ActionListener_Query_Create();
		return objActionListener_Panel_Query_Create;

	}

}
