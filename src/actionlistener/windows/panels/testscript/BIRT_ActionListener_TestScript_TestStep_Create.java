package actionlistener.windows.panels.testscript;

import java.awt.event.ActionEvent;

import actionlistener.BIRT_ActionListener;

import comm.BIRT_Panels;

public class BIRT_ActionListener_TestScript_TestStep_Create implements BIRT_ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			BIRT_Panels.getPanelBIRT_TestStep_Create().updateComboBoxes();

		}
	}

}
