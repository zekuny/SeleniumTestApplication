package actionlistener.windows.panels.testcase;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import windows.panels.testcase.BIRT_Panel_TestCase_Copy;
import windows.panels.testcase.BIRT_Panel_TestCase_DBConnection_BrowsenAdd;
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

public class BIRT_ActionListener_TestCase_Copy implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_COPY_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_COPY_CREATE))
				{
					BIRT_Panel_TestCase_Copy objBiPanel_TestCase_Copy = BIRT_Panels.getPanelBIRT_TestCase_Copy();
					if (objBiPanel_TestCase_Copy.ensureFieldsProper())
					{
						if (BIRT_Persistence.getObjBIPersistence_TestCase().isTestCaseNameDuplicate(objBiPanel_TestCase_Copy.getTestCaseName()))
						{
							BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception("New TestCase Name already exists. Please change the New Test Case Name."));
						}
						else
						{

							if (BIRT_Persistence.getObjBIPersistence_TestCase().saveTestCase(BIRT_DataObject.getIProjectID(), objBiPanel_TestCase_Copy.getTestCaseName(),
									objBiPanel_TestCase_Copy.getTestCaseDescription(), objBiPanel_TestCase_Copy.getTypeOfBrowser(), objBiPanel_TestCase_Copy.getBaseURL(),
									BIRT_DataObject.getApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptID(), BIRT_DataObject.getApp_Panel_Query_BrowsenAdd_SelectedQueryID(),
									BIRT_DataObject.getApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID(), objBiPanel_TestCase_Copy.getDownloadedFileType(),
									objBiPanel_TestCase_Copy.getDownloadedFileName(), objBiPanel_TestCase_Copy.getReportHeaderLine(), objBiPanel_TestCase_Copy.getDataStartLine()))
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Test Case Saved Successfully");
							}
						}
					}

				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_COPY_BROWSE_DBCONNECTION)))
				{
					BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd();
					BIRT_Panel_TestCase_DBConnection_BrowsenAdd.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_DBCONNECTION_CANCEL)))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Copy());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_DBCONNECTION_ADD)))
				{
					if (BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd().ensureFieldsProper())
					{
						BIRT_Panels.getPanelBIRT_TestCase_Copy().setDBConnectionName(BIRT_DataObject.getApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName());
						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Copy());
					}
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_COPY_BROWSE_QUERY)))
				{
					BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd();
					BIRT_Panel_TestCase_Query_BrowsenAdd.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_QUERY_CANCEL)))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Copy());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_QUERY_ADD)))
				{
					if (BIRT_Panels.getPanelBIRT_TestCase_Query_BrowsenAdd().ensureFieldsProper())
					{
						BIRT_Panels.getPanelBIRT_TestCase_Copy().setQueryName(BIRT_DataObject.getApp_Panel_Query_BrowsenAdd_SelectedQueryName());
						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Copy());
					}
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_COPY_BROWSE_TESTSCRIPT)))
				{
					BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd();
					BIRT_Panel_TestCase_TestScript_BrowsenAdd.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_CANCEL)))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Copy());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_ADD)))
				{
					if (BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd().ensureFieldsProper())
					{
						BIRT_Panels.getPanelBIRT_TestCase_Copy().setTestScriptName(BIRT_DataObject.getApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptName());
						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Copy());
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
