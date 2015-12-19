package windows.panels.app.property;

import javax.swing.JOptionPane;

import logger.BIRT_Logger;
import utility.file.DirectoryUtility;
import utility.file.FileUtility;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;

import com.anugraha.birt.app.BIRT_AppProperty;

import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import framework.dbconnection.driver.BIRT_DBConnectionDriver;

public class BIRT_Panel_App_Property extends BIRT_Panel
{

	private static final long				serialVersionUID	= 1L;

	private javax.swing.JButton				jbCancel;
	private javax.swing.JButton				jbFirefoxLocation;
	private javax.swing.JButton				jbIEDriverLocation;
	private javax.swing.JButton				jbReportArchiveDirectory;
	private javax.swing.JButton				jbReportDownloadDirectory;
	private javax.swing.JButton				jbSave;

	private javax.swing.JComboBox<String>	jcbConnectionType;

	private javax.swing.JComboBox<String>	jcbProjectRepository;
	private javax.swing.JLabel				jlConnectionType;
	private javax.swing.JLabel				jlDBPort;
	private javax.swing.JLabel				jlDBServer;
	private javax.swing.JLabel				jlDatabase;
	private javax.swing.JLabel				jlFirefoxLocation;
	private javax.swing.JLabel				jlHeader;
	private javax.swing.JLabel				jlIEDriverLocation;
	private javax.swing.JLabel				jlPassword;
	private javax.swing.JLabel				jlProjectRepository;
	private javax.swing.JLabel				jlReportArchiveDirectory;
	private javax.swing.JLabel				jlReportDownloadDirectory;
	private javax.swing.JLabel				jlUserName;
	private javax.swing.JPasswordField		jpfPassword;
	private javax.swing.JSeparator			jsHeaderSeparator;
	private javax.swing.JTextField			jsrDBPort;
	private javax.swing.JTextField			jtDBServer;
	private javax.swing.JTextField			jtDatabase;
	private javax.swing.JTextField			jtFirefoxLocation;
	private javax.swing.JTextField			jtIEDriverLocation;
	private javax.swing.JTextField			jtReportArchiveDirectory;
	private javax.swing.JTextField			jtReportDownloadDirectory;
	private javax.swing.JTextField			jtUserName;

	private javax.swing.JButton				jbFirefoxProfileDir;
	private javax.swing.JLabel				jlFirefoxProfileDir;
	private javax.swing.JTextField			jtFirefoxProfileDir;

	protected void addPanelComponentActionListeners()
	{
		BIRT_ActionListener objActionListener = BIRT_ActionListeners.getObjActionListener_Panel_App_Property();

		jbCancel.addActionListener(objActionListener);
		jbSave.addActionListener(objActionListener);
		jbFirefoxLocation.addActionListener(objActionListener);
		jbFirefoxProfileDir.addActionListener(objActionListener);
		jbIEDriverLocation.addActionListener(objActionListener);
		jbReportArchiveDirectory.addActionListener(objActionListener);
		jbReportDownloadDirectory.addActionListener(objActionListener);

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

		jlHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jlHeader.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

	}

	protected void initPanelComponents()
	{
		jlFirefoxLocation = new javax.swing.JLabel();
		jtFirefoxLocation = new javax.swing.JTextField();
		jbFirefoxLocation = new javax.swing.JButton();
		jlIEDriverLocation = new javax.swing.JLabel();
		jtIEDriverLocation = new javax.swing.JTextField();
		jbIEDriverLocation = new javax.swing.JButton();
		jlReportDownloadDirectory = new javax.swing.JLabel();
		jtReportDownloadDirectory = new javax.swing.JTextField();
		jbReportDownloadDirectory = new javax.swing.JButton();
		jlReportArchiveDirectory = new javax.swing.JLabel();
		jtReportArchiveDirectory = new javax.swing.JTextField();
		jbReportArchiveDirectory = new javax.swing.JButton();
		jlProjectRepository = new javax.swing.JLabel();
		jcbProjectRepository = new javax.swing.JComboBox<String>();
		jlConnectionType = new javax.swing.JLabel();
		jcbConnectionType = new javax.swing.JComboBox<String>();
		jlDBServer = new javax.swing.JLabel();
		jtDBServer = new javax.swing.JTextField();
		jlDBPort = new javax.swing.JLabel();
		jsrDBPort = new javax.swing.JTextField();
		jlUserName = new javax.swing.JLabel();
		jtUserName = new javax.swing.JTextField();
		jlPassword = new javax.swing.JLabel();
		jpfPassword = new javax.swing.JPasswordField();
		jtDatabase = new javax.swing.JTextField();
		jlDatabase = new javax.swing.JLabel();
		jbSave = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();
		jlFirefoxProfileDir = new javax.swing.JLabel();
		jtFirefoxProfileDir = new javax.swing.JTextField();
		jbFirefoxProfileDir = new javax.swing.JButton();

		jcbProjectRepository.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_AppProperty.strProjectRepositories));
		jcbConnectionType.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_DBConnectionDriver.DBCONNECTIONTYPES));

		jtFirefoxLocation.setEditable(false);
		jtFirefoxProfileDir.setEditable(false);
		jtIEDriverLocation.setEditable(false);
		jtReportDownloadDirectory.setEditable(false);
		jtReportArchiveDirectory.setEditable(false);

	}

	protected boolean isFieldsProper()
	{
		try
		{

			if (FileUtility.isValidFileWithExtn(getFirefoxLocation(), "exe"))
			{
				if (DirectoryUtility.isValidDirectory(getFirefoxProfileDir()))
				{
					if (FileUtility.isValidFileWithExtn(getIEDriverLocation(), "exe"))
					{
						if (DirectoryUtility.isValidDirectory(getReportArchiveDirectory()))
						{
							if (DirectoryUtility.isValidDirectory(getReportDownloadDirectory()))
							{
								if (getSelectedProjectRepository() >= 0)
								{
									if (getSelectedConnectionType() >= 0)
									{
										if (getDBServer().equals(BIRT_DataObject.NULL))
											return handleFieldProperError("DB Server cannot be null. Please enter a DB Server.");
										else
										{
											try
											{
												if (getDBPort() <= 0 && getDBPort() > 65535)
													return handleFieldProperError("DB Port should be a positive integer between 0 and 65536. Please enter a valid DB Port ");
												else if (getUsername().equals(BIRT_DataObject.NULL))
													return handleFieldProperError("Username cannot be null. Please enter a Username.");
												else if (getDatabase().equals(BIRT_DataObject.NULL))
													return handleFieldProperError("Database cannot be null. Please enter a Database.");

											}
											catch (NumberFormatException ex)
											{
												if(BIRT_AppProperty.PRINT_STACK_TRACE)
													BIRT_Logger.printStackTrace(ex);
												BIRT_Logger.error(ex.getMessage());
												return handleFieldProperError("DB Port should be a positive integer between 0 and 65536. Please enter a valid DB Port ");
											}
										}

										if (getPassword().equals(BIRT_DataObject.NULL))
										{
											int response = JOptionPane.showConfirmDialog(this, "The Password Field is empty. Do you want to continue?", "Confirm Password Length",
													JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
											if (response == JOptionPane.NO_OPTION)
												return false;

										}
										return true;
									}
									else
									{
										throw new BIRT_Temp_Exception("Please select a Connection Type to continue.");
									}
								}
								else
								{
									throw new BIRT_Temp_Exception("Please select a Project Repository to continue.");
								}
							}
							else
							{
								throw new BIRT_Temp_Exception("Not a valid Report Download Directory. Please choose a valid directory to continue.");
							}

						}
						else
						{
							throw new BIRT_Temp_Exception("Not a valid Report Archive Directory. Please choose a valid directory to continue.");
						}

					}
					else
					{
						throw new BIRT_Temp_Exception("Not a valid IEDriverServer.exe. Please choose a valid *.exe file to continue.");
					}
				}
				else
				{
					throw new BIRT_Temp_Exception("Not a valid Firefox Profile Directory. Please choose a valid directory to continue.");
				}

			}
			else
			{
				throw new BIRT_Temp_Exception("Not a valid Firefox.exe. Please choose a valid *.exe file to continue.");
			}

		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			return handleFieldProperError(e.getDisplayableMessage());
		}
	}

	public String getFirefoxProfileDir()
	{
		return jtFirefoxProfileDir.getText();
	}

	public int getSelectedConnectionType()
	{
		return jcbConnectionType.getSelectedIndex();
	}

	public int getSelectedProjectRepository()
	{
		return jcbProjectRepository.getSelectedIndex();
	}

	public String getReportDownloadDirectory()
	{
		return jtReportDownloadDirectory.getText();
	}

	public String getReportArchiveDirectory()
	{
		return jtReportArchiveDirectory.getText();
	}

	public String getIEDriverLocation()
	{
		return jtIEDriverLocation.getText();
	}

	public String getFirefoxLocation()
	{
		return jtFirefoxLocation.getText();
	}

	public void setReportDownloadDirectory(String strReportDownloadDir)
	{
		jtReportDownloadDirectory.setText(strReportDownloadDir);
	}

	public void setReportArchiveDirectory(String strReportArchiveDir)
	{
		jtReportArchiveDirectory.setText(strReportArchiveDir);
	}

	public void setIEDriverLocation(String strIEDriverLocation)
	{
		jtIEDriverLocation.setText(strIEDriverLocation);
	}

	public void setFirefoxLocation(String strFirefoxLocation)
	{
		jtFirefoxLocation.setText(strFirefoxLocation);
	}

	public void setFirefoxProfileDir(String strFirefoxProfileDir)
	{
		jtFirefoxProfileDir.setText(strFirefoxProfileDir);
	}

	public void resetContents()
	{
		resetFireFoxLocation();
		resetFirefoxProfileDir();
		resetIEDriverLocation();
		resetReportDownloadDirectory();
		resetReportArchiveDirectory();

		jcbConnectionType.setSelectedIndex(0);
		jcbProjectRepository.setSelectedIndex(0);
		
		resetDBServer();
		resetDBPort();	
		resetUserName();
		resetPassword();
		resetDatabase();
		
		//jtDBServer.setText(BIRT_DataObject.NULL);
		//jsrDBPort.setText(BIRT_DataObject.NULL);
		//jtUserName.setText(BIRT_DataObject.NULL);
		//jpfPassword.setText(BIRT_DataObject.NULL);
		//jtDatabase.setText(BIRT_DataObject.NULL);

	}

	//Divya added the functions to load the details in Application propert settings panel:START
	private void resetDBServer()
	{
		jtDBServer.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER));

	}

	private void resetDBPort()
	{
		jsrDBPort.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT));
	}

	private void resetDatabase()
	{
		jtDatabase.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME));
	}	
	
	private void resetUserName()
	{
		jtUserName.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME));
	}
	private void resetPassword()
	{
		jpfPassword.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD));
	}
	
	//Divya added the functions to load the details in Application propert settings panel:END
	
	private void resetFirefoxProfileDir()
	{
		jtFirefoxProfileDir.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_FIREFOX_PROFILE_DIR));

	}

	private void resetReportArchiveDirectory()
	{
		jtReportArchiveDirectory.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_ARCHIVE_DIR));

	}

	private void resetReportDownloadDirectory()
	{
		jtReportDownloadDirectory.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_DOWNLOAD_DIR));

	}

	private void resetIEDriverLocation()
	{
		jtIEDriverLocation.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_IEDRIVER_EXE_LOCATION));

	}

	private void resetFireFoxLocation()
	{
		jtFirefoxLocation.setText(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_FIREFOX_EXE_LOCATION));
	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(jlFirefoxProfileDir, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18)
																.addComponent(jtFirefoxProfileDir, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(jlFirefoxLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18, 18).addComponent(jtFirefoxLocation))).addGap(28, 47, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jbFirefoxLocation).addComponent(jbFirefoxProfileDir)).addGap(20, 20, 20))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup().addGap(219, 219, 219).addComponent(jlHeader))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addGap(452, 452, 452)
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(jbIEDriverLocation).addComponent(jbReportDownloadDirectory)
																												.addComponent(jbReportArchiveDirectory)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																																.addComponent(jlDatabase, javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																.addComponent(jlPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 155,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addGroup(
																														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																																.addGroup(
																																		layout.createSequentialGroup()
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.TRAILING,
																																								false)
																																								.addComponent(
																																										jlIEDriverLocation,
																																										javax.swing.GroupLayout.Alignment.LEADING,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addComponent(
																																										jlReportDownloadDirectory,
																																										javax.swing.GroupLayout.Alignment.LEADING,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addComponent(
																																										jlProjectRepository,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addComponent(
																																										jlReportArchiveDirectory,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										155,
																																										javax.swing.GroupLayout.PREFERRED_SIZE))
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.TRAILING,
																																								false)
																																								.addGroup(
																																										javax.swing.GroupLayout.Alignment.LEADING,
																																										layout.createSequentialGroup()
																																												.addGap(18, 18, 18)
																																												.addComponent(
																																														jcbProjectRepository,
																																														0,
																																														javax.swing.GroupLayout.DEFAULT_SIZE,
																																														Short.MAX_VALUE))
																																								.addGroup(
																																										javax.swing.GroupLayout.Alignment.LEADING,
																																										layout.createSequentialGroup()
																																												.addGap(18, 18, 18)
																																												.addGroup(
																																														layout.createParallelGroup(
																																																javax.swing.GroupLayout.Alignment.TRAILING)
																																																.addComponent(
																																																		jtReportDownloadDirectory)
																																																.addComponent(
																																																		jtReportArchiveDirectory,
																																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																																		232,
																																																		javax.swing.GroupLayout.PREFERRED_SIZE)))
																																								.addGroup(
																																										layout.createSequentialGroup()
																																												.addPreferredGap(
																																														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																												.addComponent(
																																														jtIEDriverLocation,
																																														javax.swing.GroupLayout.PREFERRED_SIZE,
																																														232,
																																														javax.swing.GroupLayout.PREFERRED_SIZE))))
																																.addGroup(
																																		layout.createSequentialGroup()
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.TRAILING,
																																								false)
																																								.addComponent(
																																										jlDBPort,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addComponent(
																																										jlDBServer,
																																										javax.swing.GroupLayout.Alignment.LEADING,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addComponent(
																																										jlConnectionType,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addComponent(
																																										jlUserName,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										155,
																																										javax.swing.GroupLayout.PREFERRED_SIZE))
																																				.addGap(18, 18, 18)
																																				.addGroup(
																																						layout.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.LEADING,
																																								false)
																																								.addGroup(
																																										layout.createSequentialGroup()
																																												.addComponent(jbSave)
																																												.addPreferredGap(
																																														javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																														javax.swing.GroupLayout.DEFAULT_SIZE,
																																														Short.MAX_VALUE)
																																												.addComponent(jbCancel))
																																								.addComponent(jtDatabase)
																																								.addComponent(jpfPassword)
																																								.addComponent(jtUserName)
																																								.addComponent(jsrDBPort)
																																								.addComponent(
																																										jcbConnectionType,
																																										0,
																																										javax.swing.GroupLayout.DEFAULT_SIZE,
																																										Short.MAX_VALUE)
																																								.addComponent(
																																										jtDBServer,
																																										javax.swing.GroupLayout.PREFERRED_SIZE,
																																										232,
																																										javax.swing.GroupLayout.PREFERRED_SIZE)))))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116,
																										javax.swing.GroupLayout.PREFERRED_SIZE))))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlHeader)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlFirefoxLocation)
										.addComponent(jtFirefoxLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jbFirefoxLocation))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlFirefoxProfileDir)
										.addComponent(jtFirefoxProfileDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jbFirefoxProfileDir))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlIEDriverLocation)
										.addComponent(jtIEDriverLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jbIEDriverLocation))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlReportDownloadDirectory).addComponent(jbReportDownloadDirectory)
										.addComponent(jtReportDownloadDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlReportArchiveDirectory)
										.addComponent(jtReportArchiveDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jbReportArchiveDirectory))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlProjectRepository)
										.addComponent(jcbProjectRepository, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(27, 27, 27)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jcbConnectionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jlConnectionType))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jtDBServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jlDBServer))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlDBPort)
										.addComponent(jsrDBPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlUserName)
										.addComponent(jtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlPassword)
										.addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlDatabase)
										.addComponent(jtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbSave).addComponent(jbCancel))
						.addContainerGap(26, Short.MAX_VALUE)));
	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_CANCEL);
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_SAVE);
		jbFirefoxLocation.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_FIREFOX_BROWSE);
		jbFirefoxProfileDir.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_FIREFOX_PROFILE_BROWSE);
		jbIEDriverLocation.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_IEDRIVER_BROWSE);
		jbReportArchiveDirectory.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_REPORT_ARCHIVE_BROWSE);
		jbReportDownloadDirectory.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_APPCONFIG_REPORT_DOWNLOAD_BROWSE);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Application Configuration");

		jlFirefoxLocation.setText("Firefox.exe Location");

		jbFirefoxLocation.setText("Browse");

		jlIEDriverLocation.setText("IE Driver.exe Location");

		jbIEDriverLocation.setText("Browse");

		jlReportDownloadDirectory.setText("Report Download Directory");

		jbReportDownloadDirectory.setText("Browse");

		jlReportArchiveDirectory.setText("Report Archive Directory");

		jbReportArchiveDirectory.setText("Browse");

		jlProjectRepository.setText("Project Repository");
		jlConnectionType.setText("Connection Type");

		jlDBServer.setText("DB Server");

		jlDBPort.setText("DB Port");

		jlUserName.setText("Username");

		jlPassword.setText("Password");

		jlDatabase.setText("Database");

		jbSave.setText("Save");

		jbCancel.setText("Cancel");

		jlFirefoxProfileDir.setText("Firefox Profile Directory");

		jbFirefoxProfileDir.setText("Browse");

	}

	public void updateContents() throws BIRT_Exception
	{
		resetContents();

	}

	public int getConnectionType()
	{
		return jcbConnectionType.getSelectedIndex();
	}

	public String getDBServer()
	{
		return jtDBServer.getText().trim();
	}

	public int getDBPort()
	{

		return Integer.parseInt(jsrDBPort.getText().trim());
	}

	public String getUsername()
	{
		return jtUserName.getText().trim();
	}

	public String getPassword()
	{
		return new String(jpfPassword.getPassword());
	}

	public String getDatabase()
	{
		return jtDatabase.getText().trim();
	}

}
