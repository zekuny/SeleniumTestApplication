package actionlistener.windows.panels.query;

import java.awt.event.ActionEvent;

import actionlistener.BIRT_ActionListener;

import comm.BIRT_ActionListeners;

public class BIRT_ActionListener_Query_View implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_VIEW_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}

		}

	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}

}
