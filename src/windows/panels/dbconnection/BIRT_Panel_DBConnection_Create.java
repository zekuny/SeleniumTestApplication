package windows.panels.dbconnection;

import javax.swing.JOptionPane;

//import com.anugraha.birt.app.BIRT_AppProperty;




//import logger.BIRT_Logger;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import framework.dbconnection.driver.BIRT_DBConnectionDriver;

public class BIRT_Panel_DBConnection_Create extends BIRT_Panel
{

	private static final long				serialVersionUID	= 1L;
	private javax.swing.JButton				jbCancel;
	private javax.swing.JButton				jbSave;
	private javax.swing.JComboBox<String>	jcbConnectionType;
	private javax.swing.JLabel				jlConnectionDescription;
	private javax.swing.JLabel				jlConnectionName;
	private javax.swing.JLabel				jlConnectionType;
	//private javax.swing.JLabel				jlDBPort;
	//private javax.swing.JLabel				jlDBServer;
//	private javax.swing.JLabel				jlDatabase;
	private javax.swing.JLabel				jlConnectionString;
	private javax.swing.JLabel				jlHeader;
	private javax.swing.JLabel				jlPassword;
	private javax.swing.JLabel				jlServiceName;
	private javax.swing.JLabel				jlUserName;
	private javax.swing.JPasswordField		jpfPassword;
	private javax.swing.JSeparator			jsHeaderSeparator;
	private javax.swing.JScrollPane			jspConnectionDescription;
	//private javax.swing.JTextField			jsrDBPort;
	private javax.swing.JTextField			jtConnectionName;
	//private javax.swing.JTextField			jtDBServer;
	//private javax.swing.JTextField			jtDatabase;
	private javax.swing.JTextField			jtConnectionString;
	private javax.swing.JTextField			jtServiceName;
	private javax.swing.JTextField			jtUserName;
	private javax.swing.JTextArea			jtaConnectionDescription;

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
		jcbConnectionType = new javax.swing.JComboBox<String>();
		//jlDBServer = new javax.swing.JLabel();
		//jtDBServer = new javax.swing.JTextField();
		//jlDBPort = new javax.swing.JLabel();
	//jsrDBPort = new javax.swing.JTextField();
		
		jlConnectionString= new javax.swing.JLabel();
		jtConnectionString=new javax.swing.JTextField();
		jlUserName = new javax.swing.JLabel();
		jtUserName = new javax.swing.JTextField();
		jlPassword = new javax.swing.JLabel();
		jpfPassword = new javax.swing.JPasswordField();
		jbSave = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();
	//	jlDatabase = new javax.swing.JLabel();
	//	jtDatabase = new javax.swing.JTextField();
		jlServiceName = new javax.swing.JLabel();
		jtServiceName = new javax.swing.JTextField();

		jtaConnectionDescription.setColumns(20);
		jtaConnectionDescription.setRows(5);
		jspConnectionDescription.setViewportView(jtaConnectionDescription);

		jcbConnectionType.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_DBConnectionDriver.DBCONNECTIONTYPES));

		jlServiceName.setVisible(false);
		jtServiceName.setVisible(false);

	}

	protected void addPanelComponentActionListeners()
	{
		jbSave.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_DBConnection_Create());
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_DBConnection_Create());

	}

	protected boolean isFieldsProper()
	{
		if (getDBConnectionName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("DB Connection Name cannot be null. Please enter a DB Connection Name.");
		else if (getDBConnectionDescription().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("DB Connection Description cannot be null. Please enter a DB Connection Description.");
		//else if (getDBServer().equals(BIRT_DataObject.NULL))
		//	return handleFieldProperError("DB Server cannot be null. Please enter a DB Server.");
		else if (getConnectionString().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("DB Server cannot be null. Please enter a DB Server.");
		else if (getUsername().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Username cannot be null. Please enter a Username.");
	//	else if (getDatabase().equals(BIRT_DataObject.NULL))
		//	return handleFieldProperError("Database cannot be null. Please enter a Database.");
		/*
		try
		{
			if (getDBPort() <= 0 && getDBPort() > 65535)
				return handleFieldProperError("DB Port should be a positive integer between 0 and 65536. Please enter a valid DB Port ");
		}
		catch (NumberFormatException ex)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			return handleFieldProperError("DB Port should be a positive integer between 0 and 65536. Please enter a valid DB Port ");
		}
		*/
		if (getPassword().equals(BIRT_DataObject.NULL))
		{
			int response = JOptionPane.showConfirmDialog(this, "The Password Field is empty. Do you want to continue?", "Confirm Password Length", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (response == JOptionPane.NO_OPTION)
				return false;
		}
		return true;
	}

	public void resetContents()
	{
		jtConnectionName.setText(BIRT_DataObject.NULL);
		jtaConnectionDescription.setText(BIRT_DataObject.NULL);
		jcbConnectionType.setSelectedIndex(0);
	//	jtDBServer.setText(BIRT_DataObject.NULL);
	//	jsrDBPort.setText(BIRT_DataObject.NULL);
		jtUserName.setText(BIRT_DataObject.NULL);
		jpfPassword.setText(BIRT_DataObject.NULL);
	//	jtDatabase.setText(BIRT_DataObject.NULL);
		jtConnectionString.setText(BIRT_DataObject.NULL);

		jtServiceName.setText(BIRT_DataObject.NULL);
	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																.addGroup(
																		layout.createSequentialGroup()
																				.addContainerGap()
																				.addComponent(jlConnectionName, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																				.addGroup(
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addGroup(layout.createSequentialGroup().addComponent(jbSave).addGap(0, 0, Short.MAX_VALUE))
																								.addComponent(jtConnectionName, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
																.addGroup(layout.createSequentialGroup().addGap(162, 162, 162).addComponent(jlHeader))
																.addGroup(
																		layout.createSequentialGroup()
																				.addContainerGap()
																				.addGroup(
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(jlConnectionType)
																								.addComponent(jlConnectionDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
																										javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																				.addGroup(
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																								.addComponent(jspConnectionDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
																								.addComponent(jcbConnectionType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																																//.addComponent(jlDBServer, javax.swing.GroupLayout.DEFAULT_SIZE,
																															//			javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																//.addComponent(jlDBPort, javax.swing.GroupLayout.DEFAULT_SIZE,
																																	//	javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																														//Divya : removing DB server details and adding Connection String
																																		.addComponent(jlConnectionString, javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																												.addGroup(
																														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																																.addComponent(jlServiceName, javax.swing.GroupLayout.Alignment.LEADING,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																//.addComponent(jlDatabase, javax.swing.GroupLayout.Alignment.LEADING,
																																	//	javax.swing.GroupLayout.DEFAULT_SIZE,
																																	//	javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																.addComponent(jlPassword, javax.swing.GroupLayout.Alignment.LEADING,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																.addComponent(jlUserName, javax.swing.GroupLayout.Alignment.LEADING,
																																		javax.swing.GroupLayout.PREFERRED_SIZE, 144,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																												.addComponent(jtServiceName)
																												.addComponent(jpfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
																												.addComponent(jtUserName)
																												//.addComponent(jsrDBPort)
																												//.addComponent(jtDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
																												//.addComponent(jtDBServer, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
																												.addComponent(jtConnectionString, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
																				.addComponent(jbCancel, javax.swing.GroupLayout.Alignment.TRAILING)))).addContainerGap(32, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlHeader)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlConnectionName)
										.addComponent(jtConnectionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jspConnectionDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jlConnectionDescription))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jcbConnectionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jlConnectionType))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									//	.addComponent(jtDBServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										//.addComponent(jlDBServer))
								.addComponent(jtConnectionString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jlConnectionString))
						.addGap(18, 18, 18)
						//.addGroup(
						//		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlDBPort)
						//				.addComponent(jsrDBPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						//.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlUserName)
										.addComponent(jtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlPassword)
										.addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
					//	.addGroup(
						//		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlDatabase)
						//				.addComponent(jtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						//.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlServiceName)
										.addComponent(jtServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbSave).addComponent(jbCancel))
						.addContainerGap(22, Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONN_CREATE_CANCEL);
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONN_CREATE_SAVE);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Create DB Connection");

		jlConnectionName.setText("Connection Name");

		jlConnectionDescription.setText("Connection Description");
		jlConnectionType.setText("Connection Type");
	//	jlDBServer.setText("DB Server");

	//	jlDBPort.setText("DB Port");

		jlConnectionString.setText("DB Connection String");
		jlUserName.setText("Username");

		jlPassword.setText("Password");

		jbSave.setText("Save");

		jbCancel.setText("Cancel");

	//	jlDatabase.setText("Database");

		jlServiceName.setText("Service Name");
	}

	public void updateContents()
	{

	}

	public String getDBConnectionName()
	{
		return jtConnectionName.getText().trim();
	}

	public String getDBConnectionDescription()
	{
		return jtaConnectionDescription.getText().trim();
	}

	public int getConnectionType()
	{
		return jcbConnectionType.getSelectedIndex();
	}

	/*
	public String getDBServer()
	{
		return jtDBServer.getText().trim();
	}

	public int getDBPort()
	{

		return Integer.parseInt(jsrDBPort.getText().trim());
	}
	
*/
	
	public String getConnectionString()
	{
		return jtConnectionString.getText().trim();
	}
	public String getUsername()
	{
		return jtUserName.getText().trim();
	}

	public String getPassword()
	{
		return new String(jpfPassword.getPassword());
	}
/*
	public String getDatabase()
	{
		return jtDatabase.getText().trim();
	}
*/
	public String getServiceName()
	{
		return jtServiceName.getText().trim();
	}

}
