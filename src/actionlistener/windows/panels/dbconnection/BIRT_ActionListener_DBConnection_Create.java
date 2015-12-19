package actionlistener.windows.panels.dbconnection;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import windows.panels.dbconnection.BIRT_Panel_DBConnection_Create;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;

public class BIRT_ActionListener_DBConnection_Create implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONN_CREATE_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONN_CREATE_SAVE))
				{
					BIRT_Panel_DBConnection_Create objBiPanel_DBConn_Create = BIRT_Panels.getPanelBIRT_DBConnection_Create();
					if (objBiPanel_DBConn_Create.ensureFieldsProper())
					{
						if (BIRT_Persistence.getObjBIPersistence_DBConnection().isDBConnectionNameDuplicate(objBiPanel_DBConn_Create.getDBConnectionName()))
						{
							BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception("Connection Name is duplicate"));
						}
						else
						{
							//if (BIRT_Persistence.getObjBIPersistence_DBConnection().saveDBConnection(BIRT_DataObject.getIProjectID(), objBiPanel_DBConn_Create.getDBConnectionName(),
								//	objBiPanel_DBConn_Create.getDBConnectionDescription(), objBiPanel_DBConn_Create.getConnectionType(), objBiPanel_DBConn_Create.getDBServer(),
								//	objBiPanel_DBConn_Create.getDBPort(), objBiPanel_DBConn_Create.getUsername(), objBiPanel_DBConn_Create.getPassword(), objBiPanel_DBConn_Create.getDatabase(),
								//	objBiPanel_DBConn_Create.getServiceName()))
								if (BIRT_Persistence.getObjBIPersistence_DBConnection().saveDBConnection(BIRT_DataObject.getIProjectID(), objBiPanel_DBConn_Create.getDBConnectionName(),
										objBiPanel_DBConn_Create.getDBConnectionDescription(), objBiPanel_DBConn_Create.getConnectionType(), objBiPanel_DBConn_Create.getConnectionString(),
										 objBiPanel_DBConn_Create.getUsername(), objBiPanel_DBConn_Create.getPassword(), 
										objBiPanel_DBConn_Create.getServiceName()))
								
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Database Connection Saved Successfully");
							}
						}
					}

				}

			}
			catch (BIRT_Exception ex)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
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
