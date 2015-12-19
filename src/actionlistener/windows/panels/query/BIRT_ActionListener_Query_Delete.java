package actionlistener.windows.panels.query;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Resources;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.windows.panels.query.BIRT_DeleteQueryException;

public class BIRT_ActionListener_Query_Delete implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_DELETE_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_DELETE_DELETE))
			{
				try
				{
					if (BIRT_Panels.getPanelBIRT_Query_Delete().ensureFieldsProper())
					{
						if (BIRT_Windows.getObjMainWindow().getUserConfirmation(
								BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_DELETE_CONFIRM)))
						{
							if (BIRT_Persistence.getObjBIPersistence_Query().deleteQuery(BIRT_DataObject.getIProjectID(), BIRT_DataObject.getApp_Panel_Query_Browse_SelectedQueryID()))
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess(
										BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_DELETE_SUCCESS));
							}
							else
							{
								BIRT_ExceptionHandler.handleError(new BIRT_DeleteQueryException(BIRT_Resources.getRbAppResourceBundle().getString(
										BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_DELETE_QUERY)));
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
