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

public class BIRT_Panel_TestCase_View extends BIRT_Panel
{

	private static final long		serialVersionUID	= 1L;

	private javax.swing.JButton		jbCancel;
	private javax.swing.JLabel		jlBaseURL;
	private javax.swing.JLabel		jlDBConnection;
	private javax.swing.JLabel		jlDataStartLine;
	private javax.swing.JLabel		jlDownloadedFileName;
	private javax.swing.JLabel		jlDownloadedFileType;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JLabel		jlReportHeaderLine;
	private javax.swing.JLabel		jlScriptFile;
	private javax.swing.JLabel		jlTargetQueryFile;
	private javax.swing.JLabel		jlTestCaseDescription;
	private javax.swing.JLabel		jlTestCaseName;
	private javax.swing.JLabel		jlTypeOfBrowser;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestCaseDescription;
	private javax.swing.JTextArea	jtaTestCaseDescription;
	private javax.swing.JTextField	jtfBaseURL;
	private javax.swing.JTextField	jtfDBConnection;
	private javax.swing.JTextField	jtfDataStartLine;
	private javax.swing.JTextField	jtfDownloadedFileName;
	private javax.swing.JTextField	jtfDownloadedFileType;
	private javax.swing.JTextField	jtfReportHeaderLine;
	private javax.swing.JTextField	jtfScriptFile;
	private javax.swing.JTextField	jtfTargetQueryFile;
	private javax.swing.JTextField	jtfTestCaseName;
	private javax.swing.JTextField	jtfTypeOfBrowser;

	protected void addPanelComponentActionListeners()
	{
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_View());

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
		jtfTypeOfBrowser = new javax.swing.JTextField();
		jlBaseURL = new javax.swing.JLabel();
		jtfBaseURL = new javax.swing.JTextField();
		jlScriptFile = new javax.swing.JLabel();
		jtfScriptFile = new javax.swing.JTextField();
		jlTargetQueryFile = new javax.swing.JLabel();
		jtfTargetQueryFile = new javax.swing.JTextField();
		jlDBConnection = new javax.swing.JLabel();
		jtfDBConnection = new javax.swing.JTextField();
		jlDownloadedFileType = new javax.swing.JLabel();
		jtfDownloadedFileType = new javax.swing.JTextField();
		jlDownloadedFileName = new javax.swing.JLabel();
		jtfDownloadedFileName = new javax.swing.JTextField();
		jlReportHeaderLine = new javax.swing.JLabel();
		jtfReportHeaderLine = new javax.swing.JTextField();
		jlDataStartLine = new javax.swing.JLabel();
		jtfDataStartLine = new javax.swing.JTextField();
		jbCancel = new javax.swing.JButton();

		jtaTestCaseDescription.setColumns(20);
		jtaTestCaseDescription.setRows(5);
		jspTestCaseDescription.setViewportView(jtaTestCaseDescription);

		jtaTestCaseDescription.setEditable(false);
		jtfBaseURL.setEditable(false);
		jtfDBConnection.setEditable(false);
		jtfDataStartLine.setEditable(false);
		jtfDownloadedFileName.setEditable(false);
		jtfDownloadedFileType.setEditable(false);
		jtfReportHeaderLine.setEditable(false);
		jtfScriptFile.setEditable(false);
		jtfTargetQueryFile.setEditable(false);
		jtfTestCaseName.setEditable(false);
		jtfTypeOfBrowser.setEditable(false);

	}

	protected boolean isFieldsProper()
	{
		return true;
	}

	public void resetContents()
	{
		jtaTestCaseDescription.setText(BIRT_DataObject.NULL);
		jtfBaseURL.setText(BIRT_DataObject.NULL);
		jtfDBConnection.setText(BIRT_DataObject.NULL);
		jtfDataStartLine.setText(BIRT_DataObject.NULL);
		jtfDownloadedFileName.setText(BIRT_DataObject.NULL);
		jtfDownloadedFileType.setText(BIRT_DataObject.NULL);
		jtfReportHeaderLine.setText(BIRT_DataObject.NULL);
		jtfScriptFile.setText(BIRT_DataObject.NULL);
		jtfTargetQueryFile.setText(BIRT_DataObject.NULL);
		jtfTestCaseName.setText(BIRT_DataObject.NULL);
		jtfTypeOfBrowser.setText(BIRT_DataObject.NULL);

	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
				layout.createSequentialGroup().addGap(19, 19, 19).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
												layout.createSequentialGroup().addComponent(jlTestCaseName, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18).addComponent(jtfTestCaseName, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup().addComponent(jlDownloadedFileType, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jtfDownloadedFileType, javax.swing.GroupLayout.PREFERRED_SIZE,
																256, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(69, Short.MAX_VALUE)).addGroup(
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
												layout.createSequentialGroup().addGroup(
														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jlTargetQueryFile, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jlBaseURL, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jlTypeOfBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jlScriptFile, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jtfTypeOfBrowser,
																		javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE).addComponent(jtfBaseURL)).addComponent(jtfScriptFile,
																javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfTargetQueryFile,
																javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfDBConnection,
																javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(
												layout.createSequentialGroup().addGroup(
														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlDataStartLine, javax.swing.GroupLayout.PREFERRED_SIZE,
																125, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jlReportHeaderLine, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jtfReportHeaderLine, javax.swing.GroupLayout.PREFERRED_SIZE,
																256, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfDataStartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 256,
																javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
																javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(
												layout.createSequentialGroup().addComponent(jlTestCaseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18).addComponent(jspTestCaseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup().addComponent(jlDownloadedFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
																javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jtfDownloadedFileName, javax.swing.GroupLayout.PREFERRED_SIZE,
																256, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(0, 0, Short.MAX_VALUE)))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jlHeader).addGap(205, 205, 205)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlTestCaseName, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfTestCaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestCaseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jspTestCaseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlTypeOfBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfTypeOfBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
										layout.createSequentialGroup().addGap(18, 18, 18).addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlBaseURL, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfBaseURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(jlScriptFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlTargetQueryFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfTargetQueryFile, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
														javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(
										layout.createSequentialGroup().addGap(56, 56, 56).addComponent(jtfScriptFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlDownloadedFileType, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfDownloadedFileType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlDownloadedFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfDownloadedFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlReportHeaderLine, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfReportHeaderLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlDataStartLine, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jtfDataStartLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, Short.MAX_VALUE).addComponent(jbCancel).addGap(20, 20, 20)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_VIEW_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("View Test Case");

		jlTestCaseName.setText("Test Case Name");

		jlTestCaseDescription.setText("Test Case Description");

		jlTypeOfBrowser.setText("Type of Browser");

		jlBaseURL.setText("BASE URL");

		jlScriptFile.setText("Test Script");

		jlTargetQueryFile.setText("Target Query");

		jlDBConnection.setText("DB Connection");

		jlDownloadedFileType.setText("Downloaded File Type");

		jlDownloadedFileName.setText("Downloaded File Name");

		jlReportHeaderLine.setText("Report Header Line");

		jlDataStartLine.setText("Data Start Line");

		jbCancel.setText("Cancel");

	}

	public void updateContents() throws BIRT_Exception
	{
		updateTestCaseDetails();

	}

	private void updateTestCaseDetails()
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
			jtfTypeOfBrowser.setText(BIRT_WebDriver.SUPPORTEDWEBBROWSERS[(Integer) TestCaseDetails[1]]);
			jtfBaseURL.setText((String) TestCaseDetails[2]);
			String strTestScriptName = BIRT_Persistence.getObjBIPersistence_TestScript().getTestScriptName(BIRT_DataObject.getIProjectID(), (Integer) TestCaseDetails[3]);
			String strQueryName = BIRT_Persistence.getObjBIPersistence_Query().getQueryName(BIRT_DataObject.getIProjectID(), (Integer) TestCaseDetails[4]);
			String strDBConnectionName = BIRT_Persistence.getObjBIPersistence_DBConnection().getDBConnectionName(BIRT_DataObject.getIProjectID(), (Integer) TestCaseDetails[5]);
			jtfScriptFile.setText(strTestScriptName);
			jtfTargetQueryFile.setText(strQueryName);
			jtfDBConnection.setText(strDBConnectionName);
			jtfDownloadedFileType.setText(BIRT_Persistence_TestCase.SUPPORTED_FILE_FORMAT[(Integer) TestCaseDetails[6]]);
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
}
