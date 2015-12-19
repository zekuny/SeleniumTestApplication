package actionlistener.windows.panels.testscript;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.windows.panels.query.BIRT_DeleteQueryException;

public class BIRT_ActionListener_TestScript_Delete implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{

		if (null != e)
		{

			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_DELETE_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_DELETE_DELETE))
			{
				try
				{
					if (BIRT_Panels.getPanelBIRT_TestScript_Delete().ensureFieldsProper())
					{
						if (BIRT_Windows.getObjMainWindow().getUserConfirmation("Are you sure you want to delete the selected Test Script?"))
						{
							if (BIRT_Persistence.getObjBIPersistence_TestScript().deleteTestScript(BIRT_DataObject.getIProjectID(),
									BIRT_DataObject.getApp_Panel_TestScript_Browse_SelectedTestScriptID()))
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Test Script deleted successfully");
							}
							else
							{
								BIRT_ExceptionHandler.handleError(new BIRT_DeleteQueryException("Test Script delete unsuccessful. Please try again later."));
							}
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
	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}

}
