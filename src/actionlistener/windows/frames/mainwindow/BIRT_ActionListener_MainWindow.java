package actionlistener.windows.frames.mainwindow;

import java.awt.event.ActionEvent;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_Panels;
import comm.BIRT_Windows;

public class BIRT_ActionListener_MainWindow implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		try{
		if (null != e)
		{
			// By Default choosing any option resets all Windows
			{
				BIRT_Windows.getObjMainWindow().reset();
			}
			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_QUERY_CREATE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_Query_Create());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_QUERY_BROWSE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_Query_Browse());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_DBCONN_CREATE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_DBConnection_Create());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSCRIPT_CREATE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_Create());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTCASE_CREATE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Create());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTCASE_BROWSE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestCase_Browse());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_CREATE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Create());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_BROWSERUNNVIEWHSTRY))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_BrowsenRunNViewHistory());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSUITE_BROWSE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestSuite_Browse());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_APP_CONFIG))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_App_Property());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_DBCONN_BROWSE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_DBConnection_Browse());
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.aPP_WINDOWS_FRAMES_MAINWINDOW_INVOKE_TESTSCRIPT_BROWSE))
			{
				BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_Browse());
			}
			else
			{
				BIRT_Logger.debug("Action Command not found. Please check.");
			}
		}
		}
		catch(Exception ex)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
		}
	}
}
