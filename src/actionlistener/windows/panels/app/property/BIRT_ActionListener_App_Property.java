package actionlistener.windows.panels.app.property;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import logger.BIRT_Logger;
import utility.file.FileDialog;
import utility.file.FileUtility;
import windows.panels.app.property.BIRT_Panel_App_Property;
import actionlistener.BIRT_ActionListener;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Windows;

import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;

public class BIRT_ActionListener_App_Property implements BIRT_ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_FIREFOX_BROWSE))
				{
					FileDialog objFileDialog = new FileDialog();
					int iSelectedState = objFileDialog.showOpenDialog(BIRT_DataObject.FILES_ONLY, BIRT_DataObject.SINGLE_FILE, BIRT_DataObject.FD_FIREFOX_EXE_LOC_BRWSE,
							BIRT_DataObject.FE_EXECUTABLES, BIRT_Windows.getObjMainWindow(), BIRT_DataObject.FDBT_SELECT);
					if (iSelectedState == JFileChooser.APPROVE_OPTION)
					{
						BIRT_Panels.getPanelBIRT_App_Property().setFirefoxLocation(FileUtility.filePathTransform(objFileDialog.getFileSelected().getAbsolutePath()));
					}
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_IEDRIVER_BROWSE))
				{
					FileDialog objFileDialog = new FileDialog();
					int iSelectedState = objFileDialog.showOpenDialog(BIRT_DataObject.FILES_ONLY, BIRT_DataObject.SINGLE_FILE, BIRT_DataObject.FD_IE_DRIVER_BRWSE, BIRT_DataObject.FE_EXECUTABLES,
							BIRT_Windows.getObjMainWindow(), BIRT_DataObject.FDBT_SELECT);
					if (iSelectedState == JFileChooser.APPROVE_OPTION)
					{
						BIRT_Panels.getPanelBIRT_App_Property().setIEDriverLocation(FileUtility.filePathTransform(objFileDialog.getFileSelected().getAbsolutePath()));
					}
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_FIREFOX_PROFILE_BROWSE))
				{
					FileDialog objFileDialog = new FileDialog();
					int iSelectedState = objFileDialog.showOpenDialog(BIRT_DataObject.DIRECTORIES_ONLY, BIRT_DataObject.SINGLE_FILE, BIRT_DataObject.FD_RPT_ARCHIVE_DIR,
							BIRT_DataObject.FE_EXECUTABLES, BIRT_Windows.getObjMainWindow(), BIRT_DataObject.FDBT_SELECT);
					if (iSelectedState == JFileChooser.APPROVE_OPTION)
					{
						BIRT_Panels.getPanelBIRT_App_Property().setFirefoxProfileDir(FileUtility.filePathTransform(objFileDialog.getFileSelected().getAbsolutePath()));
					}
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_REPORT_ARCHIVE_BROWSE))
				{
					FileDialog objFileDialog = new FileDialog();
					int iSelectedState = objFileDialog.showOpenDialog(BIRT_DataObject.DIRECTORIES_ONLY, BIRT_DataObject.SINGLE_FILE, BIRT_DataObject.FD_RPT_ARCHIVE_DIR,
							BIRT_DataObject.FE_EXECUTABLES, BIRT_Windows.getObjMainWindow(), BIRT_DataObject.FDBT_SELECT);
					if (iSelectedState == JFileChooser.APPROVE_OPTION)
					{
						BIRT_Panels.getPanelBIRT_App_Property().setReportArchiveDirectory(FileUtility.filePathTransform(objFileDialog.getFileSelected().getAbsolutePath()));
					}
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_REPORT_DOWNLOAD_BROWSE))
				{
					FileDialog objFileDialog = new FileDialog();
					int iSelectedState = objFileDialog.showOpenDialog(BIRT_DataObject.DIRECTORIES_ONLY, BIRT_DataObject.SINGLE_FILE, BIRT_DataObject.FD_RPT_DWNLD_DIR, BIRT_DataObject.FE_EXECUTABLES,
							BIRT_Windows.getObjMainWindow(), BIRT_DataObject.FDBT_SELECT);
					if (iSelectedState == JFileChooser.APPROVE_OPTION)
					{
						BIRT_Panels.getPanelBIRT_App_Property().setReportDownloadDirectory(FileUtility.filePathTransform(objFileDialog.getFileSelected().getAbsolutePath()));
					}
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_SAVE))
				{
					BIRT_Panel_App_Property objBiPanel_App_Property = BIRT_Panels.getPanelBIRT_App_Property();
					if (objBiPanel_App_Property.ensureFieldsProper())
					{
						ArrayList<String[]> alPropertiesToSet = new ArrayList<String[]>();

						String[] strFirefoxLocation =
						{ BIRT_AppProperty.PROP_FIREFOX_EXE_LOCATION, objBiPanel_App_Property.getFirefoxLocation() };
						String[] strFirefoxProfileDir =
						{ BIRT_AppProperty.PROP_FIREFOX_PROFILE_DIR, objBiPanel_App_Property.getFirefoxProfileDir() };

						String[] strIEDriverLocation =
						{ BIRT_AppProperty.PROP_IEDRIVER_EXE_LOCATION, objBiPanel_App_Property.getIEDriverLocation() };
						String[] strReportArchiveDir =
						{ BIRT_AppProperty.PROP_REPORT_ARCHIVE_DIR, objBiPanel_App_Property.getReportArchiveDirectory() };
						String[] strReportDownloadDir =
						{ BIRT_AppProperty.PROP_REPORT_DOWNLOAD_DIR, objBiPanel_App_Property.getReportDownloadDirectory() };

						alPropertiesToSet.add(strFirefoxLocation);
						alPropertiesToSet.add(strFirefoxProfileDir);
						alPropertiesToSet.add(strIEDriverLocation);
						alPropertiesToSet.add(strReportArchiveDir);
						alPropertiesToSet.add(strReportDownloadDir);

						if (objBiPanel_App_Property.getSelectedProjectRepository() == BIRT_AppProperty.iPRJCT_REPO_DATABASE)
						{
							if (objBiPanel_App_Property.getSelectedConnectionType() == BIRT_AppProperty.iPERSISTENCE_MEDIA_DB_MYSQL)
							{
								String[] strDBUserName =
								{ BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME, objBiPanel_App_Property.getUsername() };
								String[] strDBPassword =
								{ BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD, objBiPanel_App_Property.getPassword() };
								String[] strDBServer =
								{ BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER, objBiPanel_App_Property.getDBServer() };
								String[] strDBPort =
								{ BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT, "" + objBiPanel_App_Property.getDBPort() };
								String[] strDBDatabase =
								{ BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME, objBiPanel_App_Property.getDatabase() };

								alPropertiesToSet.add(strDBUserName);
								alPropertiesToSet.add(strDBPassword);
								alPropertiesToSet.add(strDBServer);
								alPropertiesToSet.add(strDBPort);
								alPropertiesToSet.add(strDBDatabase);
							}
						}
						int iSuccess = BIRT_AppProperty.saveProperties(alPropertiesToSet);

						if (iSuccess == 1)
						{
							BIRT_Logger.info("Application Configuration Saved Successfully.");
							BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Application Configuration Saved Successfully");
						}
						else
						{
							BIRT_Logger.error("Unable to save Application Configuration.");
							throw new BIRT_Temp_Exception("Unable to save Application Configuration. Please contact Administrator.");
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
