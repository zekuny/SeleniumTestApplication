package actionlistener.windows.panels.testcase;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import windows.panels.testcase.BIRT_Panel_TestCase_DBConnection_BrowsenAdd;
import windows.panels.testcase.BIRT_Panel_TestCase_Edit;
import windows.panels.testcase.BIRT_Panel_TestCase_Query_BrowsenAdd;
import windows.panels.testcase.BIRT_Panel_TestCase_TestScript_BrowsenAdd;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;

public class BIRT_ActionListener_TestCase_Edit implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_CREATE))
				{
					BIRT_Panel_TestCase_Edit objBiPanel_TestCase_Edit = BIRT_Panels.getPanelBIRT_TestCase_Edit();
					if (objBiPanel_TestCase_Edit.ensureFieldsProper())
					{
						if (!BIRT_DataObject.getApp_Panel_TestCase_Browse_SelectedTestCaseName().equals(objBiPanel_TestCase_Edit.getTestCaseName()))
						{
							if (BIRT_Persistence.getObjBIPersistence_TestCase().isTestCaseNameDuplicate(objBiPanel_TestCase_Edit.getTestCaseName()))
							{
								BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception("New TestCase Name already exists. Please change the New Test Case Name."));
							}
						}
						else

						{

							if (BIRT_Persistence.getObjBIPersistence_TestCase().editTestCase(BIRT_DataObject.getIProjectID(), BIRT_DataObject.getApp_Panel_TestCase_Browse_SelectedTestCaseID(),
									objBiPanel_TestCase_Edit.getTestCaseName(), objBiPanel_TestCase_Edit.getTestCaseDescription(), objBiPanel_TestCase_Edit.getTypeOfBrowser(),
									objBiPanel_TestCase_Edit.getBaseURL(), BIRT_DataObject.getApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptID(),
									BIRT_DataObject.getApp_Panel_Query_BrowsenAdd_SelectedQueryID(), BIRT_DataObject.getApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID(),
									objBiPanel_TestCase_Edit.getDownloadedFileType(), objBiPanel_TestCase_Edit.getDownloadedFileName(), objBiPanel_TestCase_Edit.getReportHeaderLine(),
									objBiPanel_TestCase_Edit.getDataStartLine()))
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Test Case Saved Successfully");
							}
						}
					}

				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_DBCONNECTION)))
				{
					BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd();
					BIRT_Panel_TestCase_DBConnection_BrowsenAdd.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_DBCONNECTION_CANCEL)))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Edit());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_DBCONNECTION_ADD)))
				{
					if (BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd().ensureFieldsProper())
					{
						BIRT_Panels.getPanelBIRT_TestCase_Edit().setDBConnectionName(BIRT_DataObject.getApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName());
						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Edit());
					}
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_QUERY)))
				{
					BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd();
					BIRT_Panel_TestCase_Query_BrowsenAdd.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_QUERY_CANCEL)))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Edit());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_QUERY_ADD)))
				{
					if (BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd().ensureFieldsProper())
					{
						BIRT_Panels.getPanelBIRT_TestCase_Edit().setQueryName(BIRT_DataObject.getApp_Panel_Query_BrowsenAdd_SelectedQueryName());
						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Edit());
					}
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_TESTSCRIPT)))
				{
					BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd();
					BIRT_Panel_TestCase_TestScript_BrowsenAdd.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_CANCEL)))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Edit());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_ADD)))
				{
					if (BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd().ensureFieldsProper())
					{
						BIRT_Panels.getPanelBIRT_TestCase_Edit().setTestScriptName(BIRT_DataObject.getApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptName());
						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Edit());
					}
				}

			}
			catch (BIRT_Exception ex)
			{if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
				BIRT_Logger.error(ex.getMessage());
				BIRT_ExceptionHandler.handleFatalError(ex);
			}

		}
	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}

}
