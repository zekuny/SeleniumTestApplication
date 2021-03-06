package actionlistener.windows.panels.testsuite;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import windows.panels.testsuite.BIRT_Panel_TestCase_BrowsenAdd;
import windows.panels.testsuite.BIRT_Panel_TestSuite_Edit;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;

public class BIRT_ActionListener_TestSuite_Edit implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_CREATE))
				{
					BIRT_Panel_TestSuite_Edit objBiPanel_TestSuite_Edit = BIRT_Panels.getPanelBIRT_TestSuite_Edit();
					if (objBiPanel_TestSuite_Edit.ensureFieldsProper())
					{
						if (!BIRT_DataObject.getApp_Panel_TestSuite_Browse_SelectedTestSuiteName().equals(objBiPanel_TestSuite_Edit.getTestSuiteName()))
						{
							if (BIRT_Persistence.getObjBIPersistence_TestSuite().isTestSuiteNameDuplicate(objBiPanel_TestSuite_Edit.getTestSuiteName()))
							{
								BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception("TestSuite Name already exists. Please change the TestSuite Name."));
							}
						}
						else
						{

							if (BIRT_Persistence.getObjBIPersistence_TestSuite().editTestSuite(BIRT_DataObject.getIProjectID(), BIRT_DataObject.getApp_Panel_TestSuite_Browse_SelectedTestSuiteID(),
									objBiPanel_TestSuite_Edit.getTestSuiteName(), objBiPanel_TestSuite_Edit.getTestFilePath(), objBiPanel_TestSuite_Edit.getTestSuiteDescription(), BIRT_DataObject.getTestSuite_SelectedTestCaseIDs()))
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("TestSuite Saved Successfully");
							}
						}
					}

				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_ADD_TESTCASE)))
				{
					BIRT_Panels.getPanelBIRT_TestCase_BrowsenAdd();
					BIRT_Panel_TestCase_BrowsenAdd.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestCase_BrowsenAdd().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_BrowsenAdd());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_REMOVE_TESTCASE)))
				{
					BIRT_DataObject.removeTestSuite_SelectedTestCase(BIRT_Panels.getPanelBIRT_TestSuite_Edit().getSelectedRow());
					BIRT_Panels.getPanelBIRT_TestSuite_Edit().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Edit());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTCASE_CANCEL)))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Edit());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTCASE_ADD)))
				{
					if (BIRT_Panels.getPanelBIRT_TestCase_BrowsenAdd().ensureFieldsProper())
					{
						Object objSelectedTestCase[] = new Object[BIRT_DataObject.TESTSUITE_SELECTED_TESTCASE_TOTALSIZE];

						objSelectedTestCase[BIRT_DataObject.TESTSUITE_SELECTED_TESTCASE_ID] = new Integer(BIRT_DataObject.getApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseID());
						objSelectedTestCase[BIRT_DataObject.TESTSUITE_SELECTED_TESTCASE_NAME] = new String(BIRT_DataObject.getApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseName());
						BIRT_DataObject.addTestSuite_SelectedTestCase(objSelectedTestCase);

						BIRT_Panels.getPanelBIRT_TestSuite_Edit().updateContents();

						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Edit());
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
