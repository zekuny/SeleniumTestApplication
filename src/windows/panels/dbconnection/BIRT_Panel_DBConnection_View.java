package windows.panels.dbconnection;

import logger.BIRT_Logger;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;

import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import framework.dbconnection.driver.BIRT_DBConnectionDriver;

public class BIRT_Panel_DBConnection_View extends BIRT_Panel
{

	private static final long			serialVersionUID	= 1L;

	private javax.swing.JButton			jbCancel;
	private javax.swing.JLabel			jlConnectionDescription;
	private javax.swing.JLabel			jlConnectionName;
	private javax.swing.JLabel			jlConnectionType;
	//private javax.swing.JLabel			jlDBPort;
	//private javax.swing.JLabel			jlDBServer;
	//private javax.swing.JLabel			jlDatabase;
	private javax.swing.JLabel jlConnectionString;
	private javax.swing.JLabel			jlHeader;
	private javax.swing.JLabel			jlPassword;
	
	private javax.swing.JLabel			jlServiceName;
	private javax.swing.JLabel			jlUserName;
	private javax.swing.JPasswordField	jpfPassword;
	private javax.swing.JSeparator		jsHeaderSeparator;
	private javax.swing.JScrollPane		jspConnectionDescription;
	private javax.swing.JTextField		jtConnectionName;
	private javax.swing.JTextField		jtConnectionType;
	//private javax.swing.JTextField		jtDBPort;
	//private javax.swing.JTextField		jtDBServer;
	//private javax.swing.JTextField		jtDatabase;
	private javax.swing.JTextField		jtConnectionString;
	
	private javax.swing.JTextField		jtServiceName;
	private javax.swing.JTextField		jtUserName;
	private javax.swing.JTextArea		jtaConnectionDescription;

	protected void addPanelComponentActionListeners()
	{
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_DBConnection_View());

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jlConnectionName = new javax.swing.JLabel();
		jtConnectionName = new javax.swing.JTextField();
		jlConnectionDescription = new javax.swing.JLabel();
		jspConnectionDescription = new javax.swing.JScrollPane();
		jtaConnectionDescription = new javax.swing.JTextArea();
		jlConnectionType = new javax.swing.JLabel();
		jtConnectionType = new javax.swing.JTextField();
		//jlDBServer = new javax.swing.JLabel();
		//jtDBServer = new javax.swing.JTextField();
		//jlDBPort = new javax.swing.JLabel();
		//jtDBPort = new javax.swing.JTextField();
		jlConnectionString = new javax.swing.JLabel();
		//jtDBServer = new javax.swing.JTextField();
		jlUserName = new javax.swing.JLabel();
		jtUserName = new javax.swing.JTextField();
		jlPassword = new javax.swing.JLabel();
		jpfPassword = new javax.swing.JPasswordField();
		//jlDatabase = new javax.swing.JLabel();
		//jtDatabase = new javax.swing.JTextField();
		jtConnectionString= new javax.swing.JTextField();
		jlServiceName = new javax.swing.JLabel();
	jtServiceName = new javax.swing.JTextField();
		jbCancel = new javax.swing.JButton();

		jtaConnectionDescription.setColumns(20);
		jtaConnectionDescription.setRows(5);
		jspConnectionDescription.setViewportView(jtaConnectionDescription);

		jtConnectionName.setEditable(false);
		jtaConnectionDescription.setEditable(false);
		jtConnectionType.setEditable(false);
		//jtDBServer.setEditable(false);
		//jtDBPort.setEditable(false);
		jtUserName.setEditable(false);
		jpfPassword.setEditable(false);
		//jtDatabase.setEditable(false);
		jtConnectionString.setEditable(false);
	jtServiceName.setEditable(false);

	}

	protected boolean isFieldsProper()
	{
		return true;
	}

	public void resetContents()
	{
		jtConnectionName.setText(BIRT_DataObject.NULL);
		jtaConnectionDescription.setText(BIRT_DataObject.NULL);
		jtConnectionType.setText(BIRT_DataObject.NULL);
	//	jtDBServer.setText(BIRT_DataObject.NULL);
		//jtDBPort.setText(BIRT_DataObject.NULL);
		jtUserName.setText(BIRT_DataObject.NULL);
		jpfPassword.setText(BIRT_DataObject.NULL);
		//jtDatabase.setText(BIRT_DataObject.NULL);
		jtConnectionString.setText(BIRT_DataObject.NULL);
		jtServiceName.setText(BIRT_DataObject.NULL);

	}

	protected void setLayout()
	{
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(
										layout.createSequentialGroup().addContainerGap().addComponent(jlConnectionName, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
												javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jtConnectionName)).addGroup(
										layout.createSequentialGroup().addGap(162, 162, 162).addComponent(jlHeader)).addGroup(
										layout.createSequentialGroup().addContainerGap().addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlConnectionType).addComponent(jlConnectionDescription,
														javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jspConnectionDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 242,
												javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(
								layout.createSequentialGroup().addContainerGap().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												//.addComponent(jlDBServer, javax.swing.GroupLayout.DEFAULT_SIZE,
													//	javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jlConnectionString, javax.swing.GroupLayout.DEFAULT_SIZE,
													javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														//.addComponent(jlDBPort, javax.swing.GroupLayout.DEFAULT_SIZE,
													//	javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
													.addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												
											.addComponent(jlServiceName, javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														//.addComponent(jlDatabase,
														//javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														
														.addComponent(jlPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jlUserName, javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jbCancel).addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jtServiceName).addComponent(jpfPassword,
														javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
														//.addComponent(jtDBPort).addComponent(jtDatabase,
													//	javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
														
														.addComponent(jtConnectionString,
														javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
														
														//.addComponent(jtDBServer, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
														.addComponent(jtConnectionType,
																javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE).addComponent(jtUserName,
																javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))))).addContainerGap(32, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jsHeaderSeparator,
						javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlConnectionName).addComponent(jtConnectionName, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jspConnectionDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 54,
								javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jlConnectionDescription)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlConnectionType).addComponent(jtConnectionType, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(20, 20, 20).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						
						//.addComponent(jtDBServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
							//	javax.swing.GroupLayout.PREFERRED_SIZE)
							//	.addComponent(jlDBServer)).addGap(18, 18, 18)
								.addComponent(jtConnectionString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jlConnectionString)).addGap(18, 18, 18)
								.addGroup(
						//layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						//.addComponent(jlDBPort).addComponent(jtDBPort, javax.swing.GroupLayout.PREFERRED_SIZE,
							//	javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								//.addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jlUserName)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlPassword).addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18)
								//.addGroup(
						//layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						//.addComponent(jlDatabase).addComponent(jtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE,
								//javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18)
								.addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jlServiceName).addComponent(jtServiceName, javax.swing.GroupLayout.PREFERRED_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								).addGap(18, 18, 18).addComponent(jbCancel).addContainerGap(20, Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONNECTION_VIEW_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("View DB Connection");

		jlConnectionName.setText("Connection Name");

		jlConnectionDescription.setText("Connection Description");

		jlConnectionType.setText("Connection Type");

		//jlDBServer.setText("DB Server");

		//jlDBPort.setText("DB Port");
		jlConnectionString.setText("DB Connection String");


		jlUserName.setText("Username");

		jlPassword.setText("Password");

		//jlDatabase.setText("Database");

	jlServiceName.setText("Service Name");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		try
		{

			Object[] objDBConnDetails = BIRT_Persistence.getObjBIPersistence_DBConnection().getDBConnectionDetail(BIRT_DataObject.getApp_Panel_DBConnection_Browse_SelectedDBConnectionID());
			Integer iDBConnType = (Integer) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_TYPE];
			//String strConnServer = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVER];
			//Integer iDBConnPort = (Integer) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PORT];
			String strConnString = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_STRING];
			String strConnUsername = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_USERNAME];
			String strConnPassword = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PASSWORD];
			//String strConnDatabase = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_DATABASE];
			String strConnServiceName = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVICENAME];
			String strConnName = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_CONNECTIONNAME];
			String strConnDesc = (String) objDBConnDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_CONNECTIONDESC];

			jtConnectionName.setText(strConnName);
			jtaConnectionDescription.setText(strConnDesc);
			jtConnectionType.setText(BIRT_DBConnectionDriver.DBCONNECTIONTYPES[iDBConnType]);
			//jtDBServer.setText(strConnServer);
			//jtDBPort.setText("" + iDBConnPort);
			jtConnectionString.setText("" + strConnString);
			jtUserName.setText(strConnUsername);
			jpfPassword.setText(strConnPassword);
			//jtDatabase.setText(strConnDatabase);
			jtServiceName.setText(strConnServiceName);

		}
		catch (BIRT_Exception e)
		{
			BIRT_Logger.error(e.getMessage());

			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);

			BIRT_ExceptionHandler.handleFatalError(e);
		}
	}
}
