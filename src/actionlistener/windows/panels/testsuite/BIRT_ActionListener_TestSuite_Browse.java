package actionlistener.windows.panels.testsuite;

import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_ActionListener_TestSuite_Browse implements BIRT_ActionListener, ListSelectionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{

			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}
			else if (BIRT_Panels.getPanelBIRT_TestSuite_Browse().ensureFieldsProper())
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_DELETE))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Delete());
				}

				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_EDIT))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Edit());
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_VIEW))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_View());
				}

			}

		}
	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}

	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			return;
		}

		BIRT_Panels.getPanelBIRT_TestSuite_Browse().setValueSelected(true);

		try
		{

			BIRT_DataObject.setApp_Panel_TestSuite_Browse_SelectedTestSuiteID(((int[]) BIRT_DataObject.getObjTestSuiteTableDetails()[BIRT_DataObject.TESTSUITE_ID])[BIRT_Panels
					.getPanelBIRT_TestSuite_Browse().getSelectedRow()]);
			Object[] TestSuiteDesc = ((Object[][]) BIRT_DataObject.getObjTestSuiteTableDetails()[BIRT_DataObject.TESTSUITE_TABLE_DATA])[BIRT_Panels.getPanelBIRT_TestSuite_Browse().getSelectedRow()];

			BIRT_DataObject.setApp_Panel_TestSuite_Browse_SelectedTestSuiteName((String) TestSuiteDesc[0]);
			BIRT_DataObject.setApp_Panel_TestSuite_Browse_SelectedTestSuiteDesc((String) TestSuiteDesc[1]);
			BIRT_DataObject.setApp_Panel_TestSuite_Browse_SelectedTestFilePath((String) TestSuiteDesc[2]);
		}
		catch (BIRT_Exception ex)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(ex);
		}

	}
}
