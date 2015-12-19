package actionlistener.windows.panels.testscript;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class BIRT_ActionListener_TestScript_Browse implements ActionListener, ListSelectionListener
{
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			return;
		}

		BIRT_Panels.getPanelBIRT_TestScript_Browse().setValueSelected(true);

		try
		{

			BIRT_DataObject.setApp_Panel_TestScript_Browse_SelectedTestScriptID(((int[]) BIRT_DataObject.getObjTestScriptTableDetails()[BIRT_DataObject.TESTSCRIPTID])[BIRT_Panels
					.getPanelBIRT_TestScript_Browse().getSelectedRow()]);
			Object[] TestScriptNameDesc = ((Object[][]) BIRT_DataObject.getObjTestScriptTableDetails()[BIRT_DataObject.TESTSCRIPT_TABLE_DATA])[BIRT_Panels.getPanelBIRT_TestScript_Browse()
					.getSelectedRow()];
			BIRT_DataObject.setApp_Panel_TestScript_Browse_SelectedTestScriptName((String) TestScriptNameDesc[0]);
			BIRT_DataObject.setApp_Panel_TestScript_Browse_SelectedTestScriptDescription((String) TestScriptNameDesc[1]);

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
			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}
			else if (BIRT_Panels.getPanelBIRT_TestScript_Browse().ensureFieldsProper())
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_VIEW))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_View());
				}

				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_EDIT))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_Edit());
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_DELETE))
				{
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_Delete());
				}
			}

		}

	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}

}
