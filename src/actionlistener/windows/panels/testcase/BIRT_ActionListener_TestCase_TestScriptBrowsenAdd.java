package actionlistener.windows.panels.testcase;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_ActionListener_TestCase_TestScriptBrowsenAdd implements ListSelectionListener
{
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			return;
		}

		BIRT_Panels.getPanelBIRT_TestCase_TestScript_BrowsenAdd().setValueSelected(true);

		try
		{

			BIRT_DataObject.setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptID(((int[]) BIRT_DataObject.getObjTestScriptTableDetails()[BIRT_DataObject.TESTSCRIPTID])[BIRT_Panels
					.getPanelBIRT_TestCase_TestScript_BrowsenAdd().getSelectedRow()]);
			Object[] TestScriptNameDesc = ((Object[][]) BIRT_DataObject.getObjTestScriptTableDetails()[BIRT_DataObject.TESTSCRIPT_TABLE_DATA])[BIRT_Panels
					.getPanelBIRT_TestCase_TestScript_BrowsenAdd().getSelectedRow()];
			BIRT_DataObject.setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptName((String) TestScriptNameDesc[0]);
		}
		catch (BIRT_Exception ex)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(ex);
		}

	}
}
