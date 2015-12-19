package actionlistener.windows.panels.testcase;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_ActionListener_TestCase_DBConnectionBrowsenAdd implements ListSelectionListener
{
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			return;
		}

		BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd().setValueSelected(true);

		try
		{

			BIRT_DataObject.setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID(((int[]) BIRT_DataObject.getObjDBConnectionTableDetails()[BIRT_DataObject.DBCONNID])[BIRT_Panels
					.getPanelBIRT_TestCase_DBConnection_BrowsenAdd().getSelectedRow()]);
			Object[] DBNameDesc = ((Object[][]) BIRT_DataObject.getObjDBConnectionTableDetails()[BIRT_DataObject.DBCONN_TABLE_DATA])[BIRT_Panels.getPanelBIRT_TestCase_DBConnection_BrowsenAdd()
					.getSelectedRow()];
			BIRT_DataObject.setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName((String) DBNameDesc[0]);
		}
		catch (BIRT_Exception ex)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(ex);
		}

	}
}
