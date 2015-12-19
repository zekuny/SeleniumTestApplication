package utility.file;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileDialog
{
	private JFileChooser jfSelectFileDialog;
	private int iLastSelectedState;

	private void initJFileChooser()
	{
		if (null == jfSelectFileDialog)
		{
			jfSelectFileDialog = new JFileChooser();
			iLastSelectedState = -1;
		}
	}
	public void tearDownJFileChooser()
	{
		if (null != jfSelectFileDialog)
		{
			jfSelectFileDialog.setSelectedFile(null);
			iLastSelectedState = -1;
		}
	}
	private void setFileSelectionMode(int iFileDirectorySelectMode, boolean bMultiFileSelectionMode, String strDescription, String[] strFileExtensions)
	{
		initJFileChooser();
		setFileSelectionMode(iFileDirectorySelectMode, bMultiFileSelectionMode);
		FileNameExtensionFilter fnefFileExtensions = new FileNameExtensionFilter(strDescription, strFileExtensions);
		jfSelectFileDialog.setFileFilter(fnefFileExtensions);
	}
	private void setFileSelectionMode(int iFileDirectorySelectMode, boolean bMultiFileSelectionMode)
	{
		initJFileChooser();
		jfSelectFileDialog.setFileSelectionMode(iFileDirectorySelectMode);
		jfSelectFileDialog.setMultiSelectionEnabled(bMultiFileSelectionMode);
	}

	public int showOpenDialog(int iFileDirectorySelectMode, boolean bMultiFileSelectionMode, String strDescription, String[] strFileExtensions, Component cParent, String strButtonText)
	{
		initJFileChooser();
		setFileSelectionMode(iFileDirectorySelectMode, bMultiFileSelectionMode, strDescription, strFileExtensions);
		jfSelectFileDialog.setApproveButtonText(strButtonText);
		iLastSelectedState = jfSelectFileDialog.showOpenDialog(null);
		return iLastSelectedState;
	}
	public File[] getFilesSelected()
	{
		if (iLastSelectedState != -1 && null != jfSelectFileDialog)
		{
			return jfSelectFileDialog.getSelectedFiles();
		}
		return null;

	}

	public File getFileSelected()
	{
		if (iLastSelectedState != -1 && null != jfSelectFileDialog)
		{
			return jfSelectFileDialog.getSelectedFile();
		}
		return null;
	}
}
