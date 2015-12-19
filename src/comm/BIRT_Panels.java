package comm;

import windows.panels.app.property.BIRT_Panel_App_Property;
import windows.panels.dbconnection.BIRT_Panel_DBConnection_Browse;
import windows.panels.dbconnection.BIRT_Panel_DBConnection_Create;
import windows.panels.dbconnection.BIRT_Panel_DBConnection_Delete;
import windows.panels.dbconnection.BIRT_Panel_DBConnection_Edit;
import windows.panels.dbconnection.BIRT_Panel_DBConnection_View;
import windows.panels.query.BIRT_Panel_Query_Browse;
import windows.panels.query.BIRT_Panel_Query_Create;
import windows.panels.query.BIRT_Panel_Query_Delete;
import windows.panels.query.BIRT_Panel_Query_Edit;
import windows.panels.query.BIRT_Panel_Query_View;
import windows.panels.testcase.BIRT_Panel_TestCase_Browse;
import windows.panels.testcase.BIRT_Panel_TestCase_Copy;
import windows.panels.testcase.BIRT_Panel_TestCase_Create;
import windows.panels.testcase.BIRT_Panel_TestCase_DBConnection_BrowsenAdd;
import windows.panels.testcase.BIRT_Panel_TestCase_Delete;
import windows.panels.testcase.BIRT_Panel_TestCase_Edit;
import windows.panels.testcase.BIRT_Panel_TestCase_Query_BrowsenAdd;
import windows.panels.testcase.BIRT_Panel_TestCase_TestScript_BrowsenAdd;
import windows.panels.testcase.BIRT_Panel_TestCase_View;
import windows.panels.testscript.BIRT_Panel_TestScript_Browse;
import windows.panels.testscript.BIRT_Panel_TestScript_Create;
import windows.panels.testscript.BIRT_Panel_TestScript_Delete;
import windows.panels.testscript.BIRT_Panel_TestScript_Edit;
import windows.panels.testscript.BIRT_Panel_TestScript_View;
import windows.panels.teststep.BIRT_Panel_TestStep_Create;
import windows.panels.testsuite.BIRT_Panel_TestCase_BrowsenAdd;
import windows.panels.testsuite.BIRT_Panel_TestSuite_Browse;
import windows.panels.testsuite.BIRT_Panel_TestSuite_BrowsenRunNViewHistory;
import windows.panels.testsuite.BIRT_Panel_TestSuite_Create;
import windows.panels.testsuite.BIRT_Panel_TestSuite_Delete;
import windows.panels.testsuite.BIRT_Panel_TestSuite_Edit;
import windows.panels.testsuite.BIRT_Panel_TestSuite_Run;
import windows.panels.testsuite.BIRT_Panel_TestSuite_View;
import windows.panels.testsuite.BIRT_Panel_TestSuite_ViewHistory;

public class BIRT_Panels
{
	private static BIRT_Panel_Query_Create						objBIRT_Query_Create;
	private static BIRT_Panel_Query_Browse						objBIRT_Query_Browse;
	private static BIRT_Panel_Query_View						objBIRT_Query_View;
	private static BIRT_Panel_Query_Edit						objBIRT_Query_Edit;
	private static BIRT_Panel_Query_Delete						objBIRT_Query_Delete;
	private static BIRT_Panel_DBConnection_Create				objBIRT_DBConn_Create;
	private static BIRT_Panel_DBConnection_Browse				objBIRT_DBConn_Browse;
	private static BIRT_Panel_DBConnection_View					objBIRT_DBConn_View;
	private static BIRT_Panel_DBConnection_Delete				objBIRT_DBConn_Delete;
	private static BIRT_Panel_DBConnection_Edit					objBIRT_DBConn_Edit;
	private static BIRT_Panel_TestScript_Create					objBIRT_TestScript_Create;
	private static BIRT_Panel_TestScript_Browse					objBIRT_TestScript_Browse;
	private static BIRT_Panel_TestScript_View					objBIRT_TestScript_View;
	private static BIRT_Panel_TestScript_Delete					objBIRT_TestScript_Delete;
	private static BIRT_Panel_TestScript_Edit					objBIRT_TestScript_Edit;
	private static BIRT_Panel_TestStep_Create					objBIRT_TestStep_Create;
	private static BIRT_Panel_TestCase_Create					objBIRT_TestCase_Create;
	private static BIRT_Panel_TestCase_Browse					objBIRT_TestCase_Browse;
	private static BIRT_Panel_TestCase_Copy						objBIRT_TestCase_Copy;
	private static BIRT_Panel_TestCase_Edit						objBIRT_TestCase_Edit;
	private static BIRT_Panel_TestCase_Delete					objBIRT_TestCase_Delete;
	private static BIRT_Panel_TestCase_Query_BrowsenAdd			objBIRT_TestCase_Query_BrowsenAdd;
	private static BIRT_Panel_TestCase_DBConnection_BrowsenAdd	objBIRT_TestCase_DBConnection_BrowsenAdd;
	private static BIRT_Panel_TestCase_TestScript_BrowsenAdd	objBIRT_TestCase_TestScript_BrowsenAdd;
	private static BIRT_Panel_TestCase_BrowsenAdd				objBIRT_TestCase_BrowsenAdd;
	private static BIRT_Panel_TestSuite_Create					objBIRT_TestSuite_Create;
	private static BIRT_Panel_TestSuite_Browse					objBIRT_TestSuite_Browse;
	private static BIRT_Panel_TestSuite_BrowsenRunNViewHistory	objBIRT_TestSuite_BrowsenRunNViewHistory;
	private static BIRT_Panel_TestSuite_Run						objBIRT_TestSuite_Run;
	private static BIRT_Panel_TestSuite_View					objBIRT_TestSuite_View;
	private static BIRT_Panel_TestSuite_ViewHistory				objBIRT_TestSuite_ViewHistory;
	private static BIRT_Panel_TestSuite_Edit					objBIRT_TestSuite_Edit;
	private static BIRT_Panel_TestSuite_Delete					objBIRT_TestSuite_Delete;
	private static BIRT_Panel_App_Property						objBIRT_App_Property;
	private static BIRT_Panel_TestCase_View						objBIRT_TestCase_View;

	public static final void destroyPanels()
	{
		destroyPanelBIRT_Query_Create();
		destroyPanelBIRT_Query_Browse();
		destroyPanelBIRT_Query_View();
		destroyPanelBIRT_Query_Edit();
		destroyPanelBIRT_Query_Delete();
		destroyPanelBIRT_DBConnection_Create();
		destroyPanelBIRT_TestScript_Create();
		destroyPanelBIRT_TestStep_Create();
		destroyPanelBIRT_TestCase_Create();
		destroyPanelBIRT_TestCase_Browse();
		destroyPanelBIRT_TestCase_Query_BrowsenAdd();
		destroyPanelBIRT_TestCase_DBConnection_BrowsenAdd();
		destroyPanelBIRT_TestCase_TestScript_BrowsenAdd();
		destroyPanelBIRT_TestCase_BrowsenAdd();
		destroyPanelBIRT_TestSuite_Create();
		destroyPanelBIRT_TestSuite_BrowsenRunNViewHistory();
		destroyPanelBIRT_TestSuite_Run();
		destroyPanelBIRT_TestSuite_ViewHistory();
		destroyPanelBIRT_App_Property();
		destroyPanelBIRT_DBConnection_Browse();
		destroyPanelBIRT_DBConnection_View();
		destroyPanelBIRT_DBConnection_Delete();
		destroyPanelBIRT_DBConnection_Edit();
		destroyPanelBIRT_TestCase_View();
		destroyPanelBIRT_TestCase_Copy();
		destroyPanelBIRT_TestCase_Edit();
		destroyPanelBIRT_TestCase_Delete();
		destroyPanelBIRT_TestSuite_View();
		destroyPanelBIRT_TestSuite_Browse();
		destroyPanelBIRT_TestSuite_Edit();
		destroyPanelBIRT_TestSuite_Delete();
		destroyPanelBIRT_TestScript_Browse();
		destroyPanelBIRT_TestScript_View();
		destroyPanelBIRT_TestScript_Delete();
		destroyPanelBIRT_TestScript_Edit();
		invokeGarbageCollector();
	}

	public static final BIRT_Panel_TestScript_Edit getPanelBIRT_TestScript_Edit()
	{
		if (null == objBIRT_TestScript_Edit)
			objBIRT_TestScript_Edit = new BIRT_Panel_TestScript_Edit();
		return objBIRT_TestScript_Edit;
	}

	private static final void destroyPanelBIRT_TestScript_Edit()
	{
		if (null != objBIRT_TestScript_Edit)
			objBIRT_TestScript_Edit.resetContents();
		objBIRT_TestScript_Edit = null;
	}

	public static final BIRT_Panel_TestScript_Delete getPanelBIRT_TestScript_Delete()
	{
		if (null == objBIRT_TestScript_Delete)
			objBIRT_TestScript_Delete = new BIRT_Panel_TestScript_Delete();
		return objBIRT_TestScript_Delete;
	}

	private static final void destroyPanelBIRT_TestScript_Delete()
	{
		if (null != objBIRT_TestScript_Delete)
			objBIRT_TestScript_Delete.resetContents();
		objBIRT_TestScript_Delete = null;
	}

	public static final BIRT_Panel_TestScript_View getPanelBIRT_TestScript_View()
	{
		if (null == objBIRT_TestScript_View)
			objBIRT_TestScript_View = new BIRT_Panel_TestScript_View();
		return objBIRT_TestScript_View;
	}

	private static final void destroyPanelBIRT_TestScript_View()
	{
		if (null != objBIRT_TestScript_View)
			objBIRT_TestScript_View.resetContents();
		objBIRT_TestScript_View = null;
	}

	public static final BIRT_Panel_TestScript_Browse getPanelBIRT_TestScript_Browse()
	{
		if (null == objBIRT_TestScript_Browse)
			objBIRT_TestScript_Browse = new BIRT_Panel_TestScript_Browse();
		return objBIRT_TestScript_Browse;
	}

	private static final void destroyPanelBIRT_TestScript_Browse()
	{
		if (null != objBIRT_TestScript_Browse)
			objBIRT_TestScript_Browse.resetContents();
		objBIRT_TestScript_Browse = null;
	}

	public static final BIRT_Panel_TestSuite_Delete getPanelBIRT_TestSuite_Delete()
	{
		if (null == objBIRT_TestSuite_Delete)
			objBIRT_TestSuite_Delete = new BIRT_Panel_TestSuite_Delete();
		return objBIRT_TestSuite_Delete;
	}

	private static final void destroyPanelBIRT_TestSuite_Delete()
	{
		if (null != objBIRT_TestSuite_Delete)
			objBIRT_TestSuite_Delete.resetContents();
		objBIRT_TestSuite_Delete = null;
	}

	public static final BIRT_Panel_TestSuite_Edit getPanelBIRT_TestSuite_Edit()
	{
		if (null == objBIRT_TestSuite_Edit)
			objBIRT_TestSuite_Edit = new BIRT_Panel_TestSuite_Edit();
		return objBIRT_TestSuite_Edit;
	}

	private static final void destroyPanelBIRT_TestSuite_Edit()
	{
		if (null != objBIRT_TestSuite_Edit)
			objBIRT_TestSuite_Edit.resetContents();
		objBIRT_TestSuite_Edit = null;
	}

	public static final BIRT_Panel_TestSuite_Browse getPanelBIRT_TestSuite_Browse()
	{
		if (null == objBIRT_TestSuite_Browse)
			objBIRT_TestSuite_Browse = new BIRT_Panel_TestSuite_Browse();
		return objBIRT_TestSuite_Browse;
	}

	private static final void destroyPanelBIRT_TestSuite_Browse()
	{
		if (null != objBIRT_TestSuite_Browse)
			objBIRT_TestSuite_Browse.resetContents();
		objBIRT_TestSuite_Browse = null;
	}

	public static final BIRT_Panel_TestSuite_View getPanelBIRT_TestSuite_View()
	{
		if (null == objBIRT_TestSuite_View)
			objBIRT_TestSuite_View = new BIRT_Panel_TestSuite_View();
		return objBIRT_TestSuite_View;
	}

	private static final void destroyPanelBIRT_TestSuite_View()
	{
		if (null != objBIRT_TestSuite_View)
			objBIRT_TestSuite_View.resetContents();
		objBIRT_TestSuite_View = null;
	}

	public static final BIRT_Panel_TestCase_Delete getPanelBIRT_TestCase_Delete()
	{
		if (null == objBIRT_TestCase_Delete)
			objBIRT_TestCase_Delete = new BIRT_Panel_TestCase_Delete();
		return objBIRT_TestCase_Delete;
	}

	private static final void destroyPanelBIRT_TestCase_Delete()
	{
		if (null != objBIRT_TestCase_Delete)
			objBIRT_TestCase_Delete.resetContents();
		objBIRT_TestCase_Delete = null;
	}

	public static final BIRT_Panel_TestCase_Edit getPanelBIRT_TestCase_Edit()
	{
		if (null == objBIRT_TestCase_Edit)
			objBIRT_TestCase_Edit = new BIRT_Panel_TestCase_Edit();
		return objBIRT_TestCase_Edit;
	}

	private static final void destroyPanelBIRT_TestCase_Edit()
	{
		if (null != objBIRT_TestCase_Edit)
			objBIRT_TestCase_Edit.resetContents();
		objBIRT_TestCase_Edit = null;
	}

	public static final BIRT_Panel_TestCase_Copy getPanelBIRT_TestCase_Copy()
	{
		if (null == objBIRT_TestCase_Copy)
			objBIRT_TestCase_Copy = new BIRT_Panel_TestCase_Copy();
		return objBIRT_TestCase_Copy;
	}

	private static final void destroyPanelBIRT_TestCase_Copy()
	{
		if (null != objBIRT_TestCase_Copy)
			objBIRT_TestCase_Copy.resetContents();
		objBIRT_TestCase_Copy = null;
	}

	public static final BIRT_Panel_TestCase_View getPanelBIRT_TestCase_View()
	{
		if (null == objBIRT_TestCase_View)
			objBIRT_TestCase_View = new BIRT_Panel_TestCase_View();
		return objBIRT_TestCase_View;
	}

	private static final void destroyPanelBIRT_TestCase_View()
	{
		if (null != objBIRT_TestCase_View)
			objBIRT_TestCase_View.resetContents();
		objBIRT_TestCase_View = null;
	}

	public static final BIRT_Panel_TestCase_Browse getPanelBIRT_TestCase_Browse()
	{
		if (null == objBIRT_TestCase_Browse)
			objBIRT_TestCase_Browse = new BIRT_Panel_TestCase_Browse();
		return objBIRT_TestCase_Browse;
	}

	private static final void destroyPanelBIRT_TestCase_Browse()
	{
		if (null != objBIRT_TestCase_Browse)
			objBIRT_TestCase_Browse.resetContents();
		objBIRT_TestCase_Browse = null;
	}

	public static final BIRT_Panel_DBConnection_Edit getPanelBIRT_DBConnection_Edit()
	{
		if (null == objBIRT_DBConn_Edit)
			objBIRT_DBConn_Edit = new BIRT_Panel_DBConnection_Edit();
		return objBIRT_DBConn_Edit;
	}

	private static final void destroyPanelBIRT_DBConnection_Edit()
	{
		if (null != objBIRT_DBConn_Edit)
			objBIRT_DBConn_Edit.resetContents();
		objBIRT_DBConn_Edit = null;
	}

	public static final BIRT_Panel_DBConnection_Delete getPanelBIRT_DBConnection_Delete()
	{
		if (null == objBIRT_DBConn_Delete)
			objBIRT_DBConn_Delete = new BIRT_Panel_DBConnection_Delete();
		return objBIRT_DBConn_Delete;
	}

	private static final void destroyPanelBIRT_DBConnection_Delete()
	{
		if (null != objBIRT_DBConn_Delete)
			objBIRT_DBConn_Delete.resetContents();
		objBIRT_DBConn_Delete = null;
	}

	public static final BIRT_Panel_DBConnection_View getPanelBIRT_DBConnection_View()
	{
		if (null == objBIRT_DBConn_View)
			objBIRT_DBConn_View = new BIRT_Panel_DBConnection_View();
		return objBIRT_DBConn_View;
	}

	private static final void destroyPanelBIRT_DBConnection_View()
	{
		if (null != objBIRT_DBConn_View)
			objBIRT_DBConn_View.resetContents();
		objBIRT_DBConn_View = null;
	}

	public static final BIRT_Panel_DBConnection_Browse getPanelBIRT_DBConnection_Browse()
	{
		if (null == objBIRT_DBConn_Browse)
			objBIRT_DBConn_Browse = new BIRT_Panel_DBConnection_Browse();
		return objBIRT_DBConn_Browse;
	}

	private static final void destroyPanelBIRT_DBConnection_Browse()
	{
		if (null != objBIRT_DBConn_Browse)
			objBIRT_DBConn_Browse.resetContents();
		objBIRT_DBConn_Browse = null;
	}

	public static final BIRT_Panel_TestSuite_ViewHistory getPanelBIRT_TestSuite_ViewHistory()
	{
		if (null == objBIRT_TestSuite_ViewHistory)
			objBIRT_TestSuite_ViewHistory = new BIRT_Panel_TestSuite_ViewHistory();
		return objBIRT_TestSuite_ViewHistory;
	}

	private static final void destroyPanelBIRT_TestSuite_ViewHistory()
	{
		if (null != objBIRT_TestSuite_ViewHistory)
			objBIRT_TestSuite_ViewHistory.resetContents();
		objBIRT_TestSuite_ViewHistory = null;
	}

	public static final BIRT_Panel_App_Property getPanelBIRT_App_Property()
	{
		if (null == objBIRT_App_Property)
			objBIRT_App_Property = new BIRT_Panel_App_Property();
		return objBIRT_App_Property;
	}

	private static final void destroyPanelBIRT_App_Property()
	{
		if (null != objBIRT_App_Property)
			objBIRT_App_Property.resetContents();
		objBIRT_App_Property = null;
	}

	public static final BIRT_Panel_TestSuite_Run getPanelBIRT_TestSuite_Run()
	{
		if (null == objBIRT_TestSuite_Run)
			objBIRT_TestSuite_Run = new BIRT_Panel_TestSuite_Run();
		return objBIRT_TestSuite_Run;
	}

	private static final void destroyPanelBIRT_TestSuite_Run()
	{
		if (null != objBIRT_TestSuite_Run)
			objBIRT_TestSuite_Run.resetContents();
		objBIRT_TestSuite_Run = null;
	}

	public static final BIRT_Panel_TestSuite_BrowsenRunNViewHistory getPanelBIRT_TestSuite_BrowsenRunNViewHistory()
	{
		if (null == objBIRT_TestSuite_BrowsenRunNViewHistory)
			objBIRT_TestSuite_BrowsenRunNViewHistory = new BIRT_Panel_TestSuite_BrowsenRunNViewHistory();
		return objBIRT_TestSuite_BrowsenRunNViewHistory;
	}

	private static final void destroyPanelBIRT_TestSuite_BrowsenRunNViewHistory()
	{
		if (null != objBIRT_TestSuite_BrowsenRunNViewHistory)
			objBIRT_TestSuite_BrowsenRunNViewHistory.resetContents();
		objBIRT_TestSuite_BrowsenRunNViewHistory = null;
	}

	public static final BIRT_Panel_TestSuite_Create getPanelBIRT_TestSuite_Create()
	{
		if (null == objBIRT_TestSuite_Create)
			objBIRT_TestSuite_Create = new BIRT_Panel_TestSuite_Create();
		return objBIRT_TestSuite_Create;
	}

	private static final void destroyPanelBIRT_TestSuite_Create()
	{
		if (null != objBIRT_TestSuite_Create)
			objBIRT_TestSuite_Create.resetContents();
		objBIRT_TestSuite_Create = null;
	}

	public static final BIRT_Panel_TestCase_BrowsenAdd getPanelBIRT_TestCase_BrowsenAdd()
	{
		if (null == objBIRT_TestCase_BrowsenAdd)
			objBIRT_TestCase_BrowsenAdd = new BIRT_Panel_TestCase_BrowsenAdd();
		return objBIRT_TestCase_BrowsenAdd;
	}

	private static final void destroyPanelBIRT_TestCase_BrowsenAdd()
	{
		if (null != objBIRT_TestCase_BrowsenAdd)
			objBIRT_TestCase_BrowsenAdd.resetContents();
		objBIRT_TestCase_BrowsenAdd = null;
	}

	public static final BIRT_Panel_TestCase_TestScript_BrowsenAdd getPanelBIRT_TestCase_TestScript_BrowsenAdd()
	{
		if (null == objBIRT_TestCase_TestScript_BrowsenAdd)
			objBIRT_TestCase_TestScript_BrowsenAdd = new BIRT_Panel_TestCase_TestScript_BrowsenAdd();
		return objBIRT_TestCase_TestScript_BrowsenAdd;
	}

	private static final void destroyPanelBIRT_TestCase_TestScript_BrowsenAdd()
	{
		if (null != objBIRT_TestCase_TestScript_BrowsenAdd)
			objBIRT_TestCase_TestScript_BrowsenAdd.resetContents();
		objBIRT_TestCase_TestScript_BrowsenAdd = null;
	}

	public static final BIRT_Panel_TestCase_DBConnection_BrowsenAdd getPanelBIRT_TestCase_DBConnection_BrowsenAdd()
	{
		if (null == objBIRT_TestCase_DBConnection_BrowsenAdd)
			objBIRT_TestCase_DBConnection_BrowsenAdd = new BIRT_Panel_TestCase_DBConnection_BrowsenAdd();
		return objBIRT_TestCase_DBConnection_BrowsenAdd;
	}

	private static final void destroyPanelBIRT_TestCase_DBConnection_BrowsenAdd()
	{
		if (null != objBIRT_TestCase_DBConnection_BrowsenAdd)
			objBIRT_TestCase_DBConnection_BrowsenAdd.resetContents();
		objBIRT_TestCase_DBConnection_BrowsenAdd = null;
	}

	public static final BIRT_Panel_TestCase_Query_BrowsenAdd getPanelBIRT_TestCase_Query_BrowsenAdd()
	{
		if (null == objBIRT_TestCase_Query_BrowsenAdd)
			objBIRT_TestCase_Query_BrowsenAdd = new BIRT_Panel_TestCase_Query_BrowsenAdd();
		return objBIRT_TestCase_Query_BrowsenAdd;
	}

	private static final void destroyPanelBIRT_TestCase_Query_BrowsenAdd()
	{
		if (null != objBIRT_TestCase_Query_BrowsenAdd)
			objBIRT_TestCase_Query_BrowsenAdd.resetContents();
		objBIRT_TestCase_Query_BrowsenAdd = null;
	}

	public static final BIRT_Panel_TestCase_Create getPanelBIRT_TestCase_Create()
	{
		if (null == objBIRT_TestCase_Create)
			objBIRT_TestCase_Create = new BIRT_Panel_TestCase_Create();
		return objBIRT_TestCase_Create;
	}

	private static final void destroyPanelBIRT_TestCase_Create()
	{
		if (null != objBIRT_TestCase_Create)
			objBIRT_TestCase_Create.resetContents();
		objBIRT_TestCase_Create = null;
	}

	public static final BIRT_Panel_TestStep_Create getPanelBIRT_TestStep_Create()
	{
		if (null == objBIRT_TestStep_Create)
			objBIRT_TestStep_Create = new BIRT_Panel_TestStep_Create();
		return objBIRT_TestStep_Create;
	}

	private static final void destroyPanelBIRT_TestStep_Create()
	{
		if (null != objBIRT_TestStep_Create)
			objBIRT_TestStep_Create.resetContents();
		objBIRT_TestStep_Create = null;
	}

	public static final BIRT_Panel_TestScript_Create getPanelBIRT_TestScript_Create()
	{
		if (null == objBIRT_TestScript_Create)
			objBIRT_TestScript_Create = new BIRT_Panel_TestScript_Create();
		return objBIRT_TestScript_Create;
	}

	private static final void destroyPanelBIRT_TestScript_Create()
	{
		if (null != objBIRT_TestScript_Create)
			objBIRT_TestScript_Create.resetContents();
		objBIRT_TestScript_Create = null;
	}

	public static final BIRT_Panel_DBConnection_Create getPanelBIRT_DBConnection_Create()
	{
		if (null == objBIRT_DBConn_Create)
			objBIRT_DBConn_Create = new BIRT_Panel_DBConnection_Create();
		return objBIRT_DBConn_Create;
	}

	private static final void destroyPanelBIRT_DBConnection_Create()
	{
		if (null != objBIRT_DBConn_Create)
			objBIRT_DBConn_Create.resetContents();
		objBIRT_DBConn_Create = null;
	}

	public static final BIRT_Panel_Query_Delete getPanelBIRT_Query_Delete()
	{
		if (null == objBIRT_Query_Delete)
			objBIRT_Query_Delete = new BIRT_Panel_Query_Delete();
		return objBIRT_Query_Delete;
	}

	private static final void destroyPanelBIRT_Query_Delete()
	{
		if (null != objBIRT_Query_Delete)
			objBIRT_Query_Delete.resetContents();
		objBIRT_Query_Delete = null;
	}

	public static final BIRT_Panel_Query_Edit getPanelBIRT_Query_Edit()
	{
		if (null == objBIRT_Query_Edit)
			objBIRT_Query_Edit = new BIRT_Panel_Query_Edit();
		return objBIRT_Query_Edit;
	}

	private static final void destroyPanelBIRT_Query_Edit()
	{
		if (null != objBIRT_Query_Edit)
			objBIRT_Query_Edit.resetContents();
		objBIRT_Query_Edit = null;
	}

	public static final BIRT_Panel_Query_View getPanelBIRT_Query_View()
	{
		if (null == objBIRT_Query_View)
			objBIRT_Query_View = new BIRT_Panel_Query_View();
		return objBIRT_Query_View;
	}

	private static final void destroyPanelBIRT_Query_View()
	{
		if (null != objBIRT_Query_View)
			objBIRT_Query_View.resetContents();
		objBIRT_Query_View = null;
	}

	public static final BIRT_Panel_Query_Create getPanelBIRT_Query_Create()
	{
		if (null == objBIRT_Query_Create)
			objBIRT_Query_Create = new BIRT_Panel_Query_Create();
		return objBIRT_Query_Create;
	}

	private static final void destroyPanelBIRT_Query_Create()
	{
		if (null != objBIRT_Query_Create)
			objBIRT_Query_Create.resetContents();
		objBIRT_Query_Create = null;
	}

	public static final BIRT_Panel_Query_Browse getPanelBIRT_Query_Browse()
	{
		if (null == objBIRT_Query_Browse)
			objBIRT_Query_Browse = new BIRT_Panel_Query_Browse();
		return objBIRT_Query_Browse;
	}

	private static final void destroyPanelBIRT_Query_Browse()
	{
		if (null != objBIRT_Query_Browse)
			objBIRT_Query_Browse.resetContents();
		objBIRT_Query_Browse = null;
	}

	private static void invokeGarbageCollector()
	{
		System.gc();
	}

}
