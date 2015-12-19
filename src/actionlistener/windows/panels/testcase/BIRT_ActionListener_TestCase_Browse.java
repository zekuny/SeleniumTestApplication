package actionlistener.windows.panels.testcase;

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

public class BIRT_ActionListener_TestCase_Browse implements BIRT_ActionListener, ListSelectionListener
{
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			return;
		}

		BIRT_Panels.getPanelBIRT_TestCase_Browse().setValueSelected(true);

		try
		{

			BIRT_DataObject.setApp_Panel_TestCase_Browse_SelectedTestCaseID(((int[]) BIRT_DataObject.getObjTestCaseTableDetails()[BIRT_DataObject.TESTCASEID])[BIRT_Panels
					.getPanelBIRT_TestCase_Browse().getSelectedRow()]);
			Object[] TestCaseDesc = ((Object[][]) BIRT_DataObject.getObjTestCaseTableDetails()[BIRT_DataObject.TESTCASE_TABLE_DATA])[BIRT_Panels.getPanelBIRT_TestCase_Browse().getSelectedRow()];

			BIRT_DataObject.setApp_Panel_TestCase_Browse_SelectedTestCaseName((String) TestCaseDesc[0]);
		}
		catch (BIRT_Exception ex)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(ex);
		}

	}

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}
			else if (BIRT_Panels.getPanelBIRT_TestCase_Browse().ensureFieldsProper())
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_COPY))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Copy());
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_DELETE))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Delete());
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_EDIT))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Edit());
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_VIEW))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_View());
				}

			}

		}

	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}
}
