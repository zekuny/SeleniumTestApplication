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

public class BIRT_ActionListener_TestSuite_BrowseRunNViewHistory implements BIRT_ActionListener, ListSelectionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{

			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}
			else if (BIRT_Panels.getPanelBIRT_TestSuite_BrowsenRunNViewHistory().ensureFieldsProper())
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_RUN))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Run());
				}

				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_VIEWHSTRY))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_ViewHistory());
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

		BIRT_Panels.getPanelBIRT_TestSuite_BrowsenRunNViewHistory().setValueSelected(true);

		try
		{

			BIRT_DataObject.setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID(((int[]) BIRT_DataObject.getObjTestSuiteTableDetails()[BIRT_DataObject.TESTSUITE_ID])[BIRT_Panels
					.getPanelBIRT_TestSuite_BrowsenRunNViewHistory().getSelectedRow()]);
			Object[] TestSuiteDesc = ((Object[][]) BIRT_DataObject.getObjTestSuiteTableDetails()[BIRT_DataObject.TESTSUITE_TABLE_DATA])[BIRT_Panels.getPanelBIRT_TestSuite_BrowsenRunNViewHistory()
					.getSelectedRow()];

			BIRT_DataObject.setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteName((String) TestSuiteDesc[0]);
			BIRT_DataObject.setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteDesc((String) TestSuiteDesc[1]);
			BIRT_DataObject.setApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestFilePath((String) TestSuiteDesc[2]);
		}
		catch (BIRT_Exception ex)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(ex);
		}

	}
}
