package comm;

import windows.frames.mainwindow.BIRT_MainWindow;

public class BIRT_Windows
{
	private static BIRT_MainWindow objMainWindow;
	
	public static final BIRT_MainWindow getObjMainWindow()
	{
		if(null==objMainWindow)
			objMainWindow=new BIRT_MainWindow();
		return objMainWindow;
	}
}
