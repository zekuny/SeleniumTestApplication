package actionlistener.windows.panels.query;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import windows.panels.query.BIRT_Panel_Query_Edit;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Resources;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.windows.panels.query.BIRT_DuplicateQueryNameException;
import exception.windows.panels.query.BIRT_EditQueryException;

public class BIRT_ActionListener_Query_Edit implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_EDIT_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_EDIT_SAVE))
				{
					BIRT_Panel_Query_Edit objBIPanel_Query_Edit = BIRT_Panels.getPanelBIRT_Query_Edit();
					if (objBIPanel_Query_Edit.ensureFieldsProper())
					{
						if (!BIRT_DataObject.getApp_Panel_Query_Browse_SelectedQueryName().equals(objBIPanel_Query_Edit.getQueryName()))
						{
							if (BIRT_Persistence.getObjBIPersistence_Query().isQueryNameDuplicate(objBIPanel_Query_Edit.getQueryName()))
							{
								BIRT_ExceptionHandler.handleError(new BIRT_DuplicateQueryNameException(BIRT_Resources.getRbAppResourceBundle().getString(
										BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_DUPLICATE_QUERY_NAME)));
							}
						}
						else
						{
							if (BIRT_Persistence.getObjBIPersistence_Query().editQuery(BIRT_DataObject.getIProjectID(), BIRT_DataObject.getApp_Panel_Query_Browse_SelectedQueryID(),
									objBIPanel_Query_Edit.getQueryName(), objBIPanel_Query_Edit.getQueryDescription(), objBIPanel_Query_Edit.getQuery()))
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess(
										BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_EDIT_SUCCESS));
							}
							else
							{
								BIRT_ExceptionHandler.handleError(new BIRT_EditQueryException(BIRT_Resources.getRbAppResourceBundle().getString(
										BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_EDIT_QUERY)));
							}
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

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}

}
