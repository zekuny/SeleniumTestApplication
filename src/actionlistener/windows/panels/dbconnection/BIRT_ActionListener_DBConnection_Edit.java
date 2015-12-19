package actionlistener.windows.panels.dbconnection;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import windows.panels.dbconnection.BIRT_Panel_DBConnection_Edit;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;

public class BIRT_ActionListener_DBConnection_Edit implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONNECTION_EDIT_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONNECTION_EDIT_SAVE))
				{
					BIRT_Panel_DBConnection_Edit objBiPanel_DBConn_Edit = BIRT_Panels.getPanelBIRT_DBConnection_Edit();
					if (objBiPanel_DBConn_Edit.ensureFieldsProper())
					
						if (!BIRT_DataObject.getApp_Panel_DBConnection_Browse_SelectedDBConnectionName().equals(objBiPanel_DBConn_Edit.getDBConnectionName()))
						{
							if (BIRT_Persistence.getObjBIPersistence_DBConnection().isDBConnectionNameDuplicate(objBiPanel_DBConn_Edit.getDBConnectionName()))
							{
								BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception("Connection Name is duplicate. Please"));
							}
				//Divya: Modified to enable saving after editing			
				//	}
						else
						{
							/*
							if (BIRT_Persistence.getObjBIPersistence_DBConnection().editDBConnection(BIRT_DataObject.getIProjectID(),
									BIRT_DataObject.getApp_Panel_DBConnection_Browse_SelectedDBConnectionID(), objBiPanel_DBConn_Edit.getDBConnectionName(),
									objBiPanel_DBConn_Edit.getDBConnectionDescription(), objBiPanel_DBConn_Edit.getConnectionType(), objBiPanel_DBConn_Edit.getDBServer(),
									objBiPanel_DBConn_Edit.getDBPort(), objBiPanel_DBConn_Edit.getUsername(), objBiPanel_DBConn_Edit.getPassword(), objBiPanel_DBConn_Edit.getDatabase(),
									objBiPanel_DBConn_Edit.getServiceName()))
									*/
								if (BIRT_Persistence.getObjBIPersistence_DBConnection().editDBConnection(BIRT_DataObject.getIProjectID(),
										BIRT_DataObject.getApp_Panel_DBConnection_Browse_SelectedDBConnectionID(), objBiPanel_DBConn_Edit.getDBConnectionName(),
										objBiPanel_DBConn_Edit.getDBConnectionDescription(), objBiPanel_DBConn_Edit.getConnectionType(), objBiPanel_DBConn_Edit.getDBConnectionString(),
										 objBiPanel_DBConn_Edit.getUsername(), objBiPanel_DBConn_Edit.getPassword(),
										objBiPanel_DBConn_Edit.getServiceName()))
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Database Connection Saved Successfully");
							}
							else
							{
								BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception("Database Connection save unsuccessful. Please try again later"));
							}
						}
						//adding
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
