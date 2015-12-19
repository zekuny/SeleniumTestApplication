package actionlistener.windows.panels.testsuite;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_ActionListener_TestSuite_TestCaseBrowseNAdd implements ListSelectionListener
{
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			return;
		}

		BIRT_Panels.getPanelBIRT_TestCase_BrowsenAdd().setValueSelected(true);

		try
		{

			BIRT_DataObject.setApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseID(((int[]) BIRT_DataObject.getObjTestCaseTableDetails()[BIRT_DataObject.TESTCASEID])[BIRT_Panels
					.getPanelBIRT_TestCase_BrowsenAdd().getSelectedRow()]);
			Object[] TestCaseDesc = ((Object[][]) BIRT_DataObject.getObjTestCaseTableDetails()[BIRT_DataObject.TESTCASE_TABLE_DATA])[BIRT_Panels.getPanelBIRT_TestCase_BrowsenAdd().getSelectedRow()];

			BIRT_DataObject.setApp_Panel_TestCase_BrowsenAdd_SelectedTestCaseName((String) TestCaseDesc[0]);
		}
		catch (BIRT_Exception ex)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(ex);
		}

	}
}
