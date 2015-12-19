package windows.panels.testcase;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import persistence.BIRT_Persistence_TestCase;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import framework.selenium.driver.web.BIRT_WebDriver;

public class BIRT_Panel_TestCase_Edit extends BIRT_Panel
{

	private static final long				serialVersionUID	= 1L;

	private javax.swing.JButton				jbCancel;
	private javax.swing.JButton				jbDBConnectionBrowse;
	private javax.swing.JButton				jbSave;
	private javax.swing.JButton				jbScriptFileBrowse;
	private javax.swing.JButton				jbTargetQueryFile;
	private javax.swing.JComboBox<String>	jcbDownloadedFileType;
	private javax.swing.JComboBox<String>	jcbTypeOfBrowser;
	private javax.swing.JLabel				jlBaseURL;
	private javax.swing.JLabel				jlDBConnection;
	private javax.swing.JLabel				jlDataStartLine;
	private javax.swing.JLabel				jlDownloadedFileName;
	private javax.swing.JLabel				jlDownloadedFileType;
	private javax.swing.JLabel				jlHeader;
	private javax.swing.JLabel				jlReportHeaderLine;
	private javax.swing.JLabel				jlScriptFile;
	private javax.swing.JLabel				jlTargetQueryFile;
	private javax.swing.JLabel				jlTestCaseDescription;
	private javax.swing.JLabel				jlTestCaseName;
	private javax.swing.JLabel				jlTypeOfBrowser;
	private javax.swing.JTextField			jtfDataStartLine;
	private javax.swing.JSeparator			jsHeaderSeparator;
	private javax.swing.JTextField			jtfReportHeaderLine;
	private javax.swing.JScrollPane			jspTestCaseDescription;
	private javax.swing.JTextArea			jtaTestCaseDescription;
	private javax.swing.JTextField			jtfBaseURL;
	private javax.swing.JTextField			jtfDBConnection;
	private javax.swing.JTextField			jtfDownloadedFileName;
	private javax.swing.JTextField			jtfScriptFile;
	private javax.swing.JTextField			jtfTargetQueryFile;
	private javax.swing.JTextField			jtfTestCaseName;

	protected void addPanelComponentActionListeners()
	{
		BIRT_ActionListener objActionListener = BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Edit();
		jbSave.addActionListener(objActionListener);
		jbCancel.addActionListener(objActionListener);
		jbDBConnectionBrowse.addActionListener(objActionListener);
		jbTargetQueryFile.addActionListener(objActionListener);
		jbScriptFileBrowse.addActionListener(objActionListener);

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jlTestCaseName = new javax.swing.JLabel();
		jtfTestCaseName = new javax.swing.JTextField();
		jlTestCaseDescription = new javax.swing.JLabel();
		jspTestCaseDescription = new javax.swing.JScrollPane();
		jtaTestCaseDescription = new javax.swing.JTextArea();
		jlTypeOfBrowser = new javax.swing.JLabel();
		jcbTypeOfBrowser = new javax.swing.JComboBox<String>();
		jlBaseURL = new javax.swing.JLabel();
		jtfBaseURL = new javax.swing.JTextField();
		jlScriptFile = new javax.swing.JLabel();
		jtfScriptFile = new javax.swing.JTextField();
		jbScriptFileBrowse = new javax.swing.JButton();
		jlTargetQueryFile = new javax.swing.JLabel();
		jtfTargetQueryFile = new javax.swing.JTextField();
		jbTargetQueryFile = new javax.swing.JButton();
		jlDBConnection = new javax.swing.JLabel();
		jtfDBConnection = new javax.swing.JTextField();
		jbDBConnectionBrowse = new javax.swing.JButton();
		jlDownloadedFileType = new javax.swing.JLabel();
		jcbDownloadedFileType = new javax.swing.JComboBox<String>();
		jlDownloadedFileName = new javax.swing.JLabel();
		jtfDownloadedFileName = new javax.swing.JTextField();
		jlReportHeaderLine = new javax.swing.JLabel();
		jtfReportHeaderLine = new javax.swing.JTextField();
		jlDataStartLine = new javax.swing.JLabel();
		jtfDataStartLine = new javax.swing.JTextField();
		jbSave = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		jtaTestCaseDescription.setColumns(20);
		jtaTestCaseDescription.setRows(5);
		jspTestCaseDescription.setViewportView(jtaTestCaseDescription);

		jcbTypeOfBrowser.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_WebDriver.SUPPORTEDWEBBROWSERS));

		jcbDownloadedFileType.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT));

		jtfScriptFile.setEditable(false);
		jtfTargetQueryFile.setEditable(false);
		jtfDBConnection.setEditable(false);

	}

	protected boolean isFieldsProper()
	{
		if (getTestCaseName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("TestCase Name cannot be null. Please enter a TestCase Name.");
		else if (getTestCaseDescription().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("TestCase Description cannot be null. Please enter a TestCase Description.");
		else if (getTypeOfBrowser() < 0)
			return handleFieldProperError("Please select a Type of Browser to continue.");
		else if (getBaseURL().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("BASE URL cannot be null. Please enter a BASE URL.");
		else if (jtfScriptFile.getText().trim().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Test Script cannot be null. Please add a Test Script.");
		else if (jtfTargetQueryFile.getText().trim().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Target Query cannot be null. Please add a Target Query.");
		else if (jtfDBConnection.getText().trim().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("DB Connection cannot be null. Please add a DB Connection.");
		else if (getDownloadedFileType() < 0)
			return handleFieldProperError("Please select file type of the downloaded file to continue.");
		else if (getDownloadedFileName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Downloaded File Name cannot be null. Please enter a Downloaded File Name.");
		try
		{
			if (getReportHeaderLine() < 0)
				return handleFieldProperError("Report Header Line should be either zero or positive integer. Please enter a valid Report Header Line");
		}
		catch (NumberFormatException ex)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			return handleFieldProperError("Report Header Line should be either zero or positive integer. Please enter a valid Report Header Line");
		}
		try
		{
			if (getDataStartLine() <= 0)
				return handleFieldProperError("Data Start Line should be a nonzero positive integer. Please enter a valid Data Start Line");
		}
		catch (NumberFormatException ex)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			return handleFieldProperError("Data Start Line should be a nonzero positive integer. Please enter a valid Data Start Line");
		}

		if (getReportHeaderLine() > getDataStartLine())
			return handleFieldProperError("Data Start Line should be greater than Report Header Line. Please enter valid Report Header Line and Data Start Line");

		return true;
	}

	public void resetContents()
	{
		jtfTestCaseName.setText(BIRT_DataObject.NULL);
		jtaTestCaseDescription.setText(BIRT_DataObject.NULL);
		jtfBaseURL.setText(BIRT_DataObject.NULL);
		jtfScriptFile.setText(BIRT_DataObject.NULL);
		jtfTargetQueryFile.setText(BIRT_DataObject.NULL);
		jtfDBConnection.setText(BIRT_DataObject.NULL);
		jtfDownloadedFileName.setText(BIRT_DataObject.NULL);
		jtfReportHeaderLine.setText(BIRT_DataObject.NULL);
		jtfDataStartLine.setText(BIRT_DataObject.NULL);
		jcbDownloadedFileType.setSelectedIndex(0);
		jcbTypeOfBrowser.setSelectedIndex(0);

		BIRT_DataObject.setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptID(-1);
		BIRT_DataObject.setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptName(null);

		BIRT_DataObject.setApp_Panel_Query_BrowsenAdd_SelectedQueryID(-1);
		BIRT_DataObject.setApp_Panel_Query_BrowsenAdd_SelectedQueryName(null);

		BIRT_DataObject.setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID(-1);
		BIRT_DataObject.setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName(null);
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
								.addGap(19, 19, 19)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jlReportHeaderLine, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jlDataStartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jlTestCaseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																												.addComponent(jbSave, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(jlTargetQueryFile, javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																												.addComponent(jlScriptFile, javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																												.addComponent(jlBaseURL, javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																												.addComponent(jlTypeOfBrowser, javax.swing.GroupLayout.DEFAULT_SIZE, 170,
																														Short.MAX_VALUE)
																												.addComponent(jlTestCaseName, javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																								.addGap(57, 57, 57)
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																												.addGroup(
																														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING, false)
																																				.addComponent(jtfTargetQueryFile)
																																				.addComponent(jtfScriptFile,
																																						javax.swing.GroupLayout.Alignment.TRAILING)
																																				.addComponent(jtfBaseURL,
																																						javax.swing.GroupLayout.Alignment.TRAILING)
																																				.addComponent(jtfTestCaseName)
																																				.addComponent(jspTestCaseDescription,
																																						javax.swing.GroupLayout.DEFAULT_SIZE, 219,
																																						Short.MAX_VALUE)
																																				.addComponent(jcbTypeOfBrowser, 0,
																																						javax.swing.GroupLayout.DEFAULT_SIZE,
																																						Short.MAX_VALUE))
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.TRAILING, false)
																																				.addComponent(jtfDataStartLine,
																																						javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(jtfReportHeaderLine,
																																						javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(jtfDownloadedFileName,
																																						javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(jcbDownloadedFileType,
																																						javax.swing.GroupLayout.Alignment.LEADING, 0,
																																						219, Short.MAX_VALUE)
																																				.addComponent(jtfDBConnection,
																																						javax.swing.GroupLayout.Alignment.LEADING)))
																												.addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addGroup(
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																								.addComponent(jlDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGroup(
																										javax.swing.GroupLayout.Alignment.LEADING,
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																												.addComponent(jlDownloadedFileName, javax.swing.GroupLayout.Alignment.LEADING,
																														javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
																												.addComponent(jlDownloadedFileType, javax.swing.GroupLayout.Alignment.LEADING,
																														javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE))))
																.addGap(18, 18, 18)
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jbTargetQueryFile, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jbScriptFileBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jbDBConnectionBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(31, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup().addGap(247, 247, 247).addComponent(jlHeader).addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlHeader)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jlTestCaseName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jtfTestCaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jlTestCaseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jspTestCaseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(jlTypeOfBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(jlBaseURL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(jlScriptFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jlTargetQueryFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jtfTargetQueryFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jbTargetQueryFile))
														.addGap(18, 18, 18)
														.addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				layout.createSequentialGroup()
																						.addComponent(jlDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(18, 18, 18)
																						.addComponent(jlDownloadedFileType, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(18, 18, 18)
																						.addComponent(jlDownloadedFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(18, 18, 18)
																						.addComponent(jlReportHeaderLine, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(18, 18, 18)
																						.addComponent(jlDataStartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				layout.createSequentialGroup()
																						.addGroup(
																								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(jtfDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(jbDBConnectionBrowse))
																						.addGap(18, 18, 18)
																						.addComponent(jcbDownloadedFileType, javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(18, 18, 18)
																						.addComponent(jtfDownloadedFileName, javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(18, 18, 18)
																						.addComponent(jtfReportHeaderLine, javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(18, 18, 18)
																						.addComponent(jtfDataStartLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(jcbTypeOfBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(jtfBaseURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jtfScriptFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jbScriptFileBrowse)))).addGap(18, 18, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbSave).addComponent(jbCancel)).addGap(20, 20, 20)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_CREATE);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_CANCEL);
		jbDBConnectionBrowse.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_DBCONNECTION);
		jbTargetQueryFile.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_QUERY);
		jbScriptFileBrowse.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_EDIT_BROWSE_TESTSCRIPT);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Edit Test Case");

		jlTestCaseName.setText("Test Case Name");

		jlTestCaseDescription.setText("Test Case Description");

		jlTypeOfBrowser.setText("Type of Browser");

		jlBaseURL.setText("BASE URL");

		jlScriptFile.setText("Test Script");

		jbScriptFileBrowse.setText("Browse");

		jlTargetQueryFile.setText("Target Query");

		jbTargetQueryFile.setText("Browse");

		jlDBConnection.setText("DB Connection");

		jbDBConnectionBrowse.setText("Browse");

		jlDownloadedFileType.setText("Downloaded File Type");

		jlDownloadedFileName.setText("Downloaded File Name");

		jlReportHeaderLine.setText("Report Header Line");

		jlDataStartLine.setText("Data Start Line");

		jbSave.setText("Save");

		jbCancel.setText("Cancel");
	}

	public void updateContents() throws BIRT_Exception
	{
		updateTestCaseContents();

	}

	private void updateTestCaseContents()
	{
		try
		{

			/*
			 * Object[] - Contents of TestCase saved.
			 * Object[0] - String - TestCaseName
			 * Object[1] - Integer - TypeOfBrowser
			 * Object[2] - String - BaseURL
			 * Object[3] - Integer - TestScriptID
			 * Object[4] - Integer - QueryID
			 * Object[5] - Integer - DBConnectionID
			 * Object[6] - Integer - DownloadedFileType
			 * Object[7] - String - DownloadedFileName 
			 * Object[8] - Integer - ReportHeaderLine 
			 * Object[9] - Integer - DataStartLine
			 */

			Object[] TestCaseDetails = BIRT_Persistence.getObjBIPersistence_TestCase().getTestCaseDetails(BIRT_DataObject.getApp_Panel_TestCase_Browse_SelectedTestCaseID());
			jtfTestCaseName.setText((String) TestCaseDetails[0]);
			jcbTypeOfBrowser.setSelectedIndex((Integer) TestCaseDetails[1]);
			jtfBaseURL.setText((String) TestCaseDetails[2]);

			String strTestScriptName = BIRT_Persistence.getObjBIPersistence_TestScript().getTestScriptName(BIRT_DataObject.getIProjectID(), (Integer) TestCaseDetails[3]);
			BIRT_DataObject.setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptID((Integer) TestCaseDetails[3]);
			BIRT_DataObject.setApp_Panel_TestScript_BrowsenAdd_SelectedTestScriptName(strTestScriptName);

			String strQueryName = BIRT_Persistence.getObjBIPersistence_Query().getQueryName(BIRT_DataObject.getIProjectID(), (Integer) TestCaseDetails[4]);
			BIRT_DataObject.setApp_Panel_Query_BrowsenAdd_SelectedQueryID((Integer) TestCaseDetails[4]);
			BIRT_DataObject.setApp_Panel_Query_BrowsenAdd_SelectedQueryName(strQueryName);

			String strDBConnectionName = BIRT_Persistence.getObjBIPersistence_DBConnection().getDBConnectionName(BIRT_DataObject.getIProjectID(), (Integer) TestCaseDetails[5]);
			BIRT_DataObject.setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionID((Integer) TestCaseDetails[5]);
			BIRT_DataObject.setApp_Panel_DBConnection_BrowsenAdd_SelectedDBConnectionName(strDBConnectionName);

			jtfScriptFile.setText(strTestScriptName);
			jtfTargetQueryFile.setText(strQueryName);
			jtfDBConnection.setText(strDBConnectionName);
			jcbDownloadedFileType.setSelectedIndex((Integer) TestCaseDetails[6]);
			jtfDownloadedFileName.setText((String) TestCaseDetails[7]);
			jtfReportHeaderLine.setText("" + TestCaseDetails[8]);
			jtfDataStartLine.setText("" + TestCaseDetails[9]);
			jtaTestCaseDescription.setText(BIRT_Persistence.getObjBIPersistence_TestCase().getTestCaseName(BIRT_DataObject.getIProjectID(),
					BIRT_DataObject.getApp_Panel_TestCase_Browse_SelectedTestCaseID()));

		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}

	}

	public String getTestCaseName()
	{
		return jtfTestCaseName.getText().trim();
	}

	public String getTestCaseDescription()
	{
		return jtaTestCaseDescription.getText().trim();
	}

	public int getTypeOfBrowser()
	{
		return jcbTypeOfBrowser.getSelectedIndex();
	}

	public String getBaseURL()
	{
		return jtfBaseURL.getText().trim();
	}

	public int getDownloadedFileType()
	{
		return jcbDownloadedFileType.getSelectedIndex();
	}

	public String getDownloadedFileName()
	{
		return jtfDownloadedFileName.getText().trim();
	}

	public int getReportHeaderLine()
	{
		return Integer.parseInt(jtfReportHeaderLine.getText());
	}

	public int getDataStartLine()
	{
		return Integer.parseInt(jtfDataStartLine.getText());
	}

	public void setQueryName(String strQueryName)
	{
		jtfTargetQueryFile.setText(strQueryName);
	}

	public void setDBConnectionName(String strDBConnectionName)
	{
		jtfDBConnection.setText(strDBConnectionName);
	}

	public void setTestScriptName(String strTestScriptName)
	{
		jtfScriptFile.setText(strTestScriptName);
	}

}
