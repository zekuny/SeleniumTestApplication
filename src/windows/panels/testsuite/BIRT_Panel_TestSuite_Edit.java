package windows.panels.testsuite;

import javax.swing.DefaultListModel;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_Panel_TestSuite_Edit extends BIRT_Panel {

	private static final long serialVersionUID = 1L;

	private javax.swing.JButton jbAdd;
	private javax.swing.JButton jbCancel;
	private javax.swing.JButton jbRemove;
	private javax.swing.JButton jbSave;
	private javax.swing.JLabel jlHeader;
	private javax.swing.JLabel jlTestCases;
	private javax.swing.JLabel jlTestSuiteDescription;
	private javax.swing.JLabel jlTestSuiteName;
	private javax.swing.JLabel jlTestFilePath;

	private javax.swing.JList<String> jlstTestCases;
	private javax.swing.JSeparator jsHeaderSeparator;
	private javax.swing.JScrollPane jspTestCases;
	private javax.swing.JScrollPane jspTestSuiteDescription;
	private javax.swing.JTextField jtTestSuiteName;
	private javax.swing.JTextField jtTestFilePath;

	private javax.swing.JTextArea jtaTestSuiteDescription;

	protected void addPanelComponentActionListeners() {
		BIRT_ActionListener objActionListener = BIRT_ActionListeners
				.getObjActionListener_Panel_TestSuite_Edit();
		jbAdd.addActionListener(objActionListener);
		jbCancel.addActionListener(objActionListener);
		jbRemove.addActionListener(objActionListener);
		jbSave.addActionListener(objActionListener);

	}

	protected void initHeader() {
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents() {
		jlTestSuiteName = new javax.swing.JLabel();
		jtTestSuiteName = new javax.swing.JTextField();
		jlTestFilePath = new javax.swing.JLabel();
		jtTestFilePath = new javax.swing.JTextField();

		jlTestSuiteDescription = new javax.swing.JLabel();
		jspTestSuiteDescription = new javax.swing.JScrollPane();
		jtaTestSuiteDescription = new javax.swing.JTextArea();
		jlTestCases = new javax.swing.JLabel();
		jspTestCases = new javax.swing.JScrollPane();
		jlstTestCases = new javax.swing.JList<String>();
		jbAdd = new javax.swing.JButton();
		jbRemove = new javax.swing.JButton();
		jbSave = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		jtaTestSuiteDescription.setColumns(20);
		jtaTestSuiteDescription.setRows(5);
		jspTestSuiteDescription.setViewportView(jtaTestSuiteDescription);

		jlstTestCases.setModel(new DefaultListModel<String>());
		jlstTestCases
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jspTestCases.setViewportView(jlstTestCases);

		updateSavedDetails();

	}

	protected boolean isFieldsProper() {
		if (getTestSuiteName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Test Suite Name cannot be null. Please enter a TestSuite Name.");
		else if (getTestFilePath().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Test File Path cannot be null. Please enter a TestFile Path.");
		else if (getTestSuiteDescription().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Test Suite Description cannot be null. Please enter a TestSuite Description.");
		else if (getTestCases().length == 0)
			return handleFieldProperError("Test Cases cannot be zero. Please add atleast one Test Case.");
		return true;
	}

	public void resetContents() {
		jtTestSuiteName.setText(BIRT_DataObject.NULL);
		jtTestFilePath.setText(BIRT_DataObject.NULL);
		jtaTestSuiteDescription.setText(BIRT_DataObject.NULL);
		BIRT_DataObject.destroyTestSuite_SelectedTestCase();

	}

	protected void setLayout() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jsHeaderSeparator,
						javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(26, 26, 26)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jlTestSuiteName,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
																								.addComponent(
												jlTestFilePath,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jlTestSuiteDescription,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														126, Short.MAX_VALUE)
												.addComponent(
														jlTestCases,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addGap(28, 28, 28)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jbSave)
																.addGap(125,
																		125,
																		125)
																.addComponent(
																		jbCancel))
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(
																		jtTestSuiteName,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		251,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		jtTestFilePath,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		251,
																		javax.swing.GroupLayout.PREFERRED_SIZE)																
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jspTestCases,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						251,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jspTestSuiteDescription,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						251,
																						javax.swing.GroupLayout.PREFERRED_SIZE))))
								.addGap(32, 32, 32)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jbRemove,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														81, Short.MAX_VALUE)
												.addComponent(
														jbAdd,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(jlHeader)
								.addGap(232, 232, 232)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(13, 13, 13)
								.addComponent(jlHeader)
								.addGap(18, 18, 18)
								.addComponent(jsHeaderSeparator,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(21, 21, 21)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jlTestSuiteName)
																.addGap(34, 34,
																		34)
																.addComponent(
																		jlTestFilePath)
																.addGap(34, 34,
																		34)																
																.addComponent(
																		jlTestSuiteDescription)
																.addGap(113,
																		113,
																		113)
																.addComponent(
																		jlTestCases))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jtTestSuiteName,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(31, 31,
																		31)
																.addComponent(
																		jtTestFilePath,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(31, 31,
																		31)																
																.addComponent(
																		jspTestSuiteDescription,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(28, 28,
																		28)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jspTestCases,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						109,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jbAdd)
																								.addGap(18,
																										18,
																										18)
																								.addComponent(
																										jbRemove)))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jbCancel)
																				.addComponent(
																						jbSave))))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}

	protected void setPanelComponentActionCommands() {
		jbAdd.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_ADD_TESTCASE);
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_CREATE);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_CANCEL);
		jbRemove.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_EDIT_REMOVE_TESTCASE);

	}

	protected void setPanelComponentText() {
		jlHeader.setText("Edit Test Suite");

		jlTestSuiteName.setText("Test Suite Name");
		
		jlTestFilePath.setText("Test File Path");

		jlTestSuiteDescription.setText("Test Suite Description");

		jlTestCases.setText("Test Cases");

		jbAdd.setText("Add");

		jbRemove.setText("Remove");

		jbSave.setText("Save");

		jbCancel.setText("Cancel");

	}

	public void updateContents() throws BIRT_Exception {

		jtTestSuiteName.setText(BIRT_DataObject
				.getApp_Panel_TestSuite_Browse_SelectedTestSuiteName());
		jtTestFilePath.setText(BIRT_DataObject
				.getApp_Panel_TestSuite_Browse_SelectedTestFilePath());		
		jtaTestSuiteDescription.setText(BIRT_DataObject
				.getApp_Panel_TestSuite_Browse_SelectedTestSuiteDesc());
		jlstTestCases.setListData(BIRT_DataObject
				.getTestSuite_SelectedTestCase_Names());

	}

	private void updateSavedDetails() {

		try {


			int[] iTestCaseIDs = BIRT_Persistence
					.getObjBIPersistence_TestSuite()
					.getTestSuiteTestCases(
							BIRT_DataObject
									.getApp_Panel_TestSuite_Browse_SelectedTestSuiteID(),
							BIRT_DataObject.getIProjectID());
			String strTestCaseNames[] = BIRT_Persistence
					.getObjBIPersistence_TestCase().getTestCaseNames(
							BIRT_DataObject.getIProjectID(), iTestCaseIDs);

			for (int iCtr = 0; iCtr < strTestCaseNames.length; iCtr++) {
				Object objSelectedTestCase[] = new Object[BIRT_DataObject.TESTSUITE_SELECTED_TESTCASE_TOTALSIZE];
				objSelectedTestCase[BIRT_DataObject.TESTSUITE_SELECTED_TESTCASE_ID] = new Integer(
						iTestCaseIDs[iCtr]);
				objSelectedTestCase[BIRT_DataObject.TESTSUITE_SELECTED_TESTCASE_NAME] = new String(
						strTestCaseNames[iCtr]);
				BIRT_DataObject
						.addTestSuite_SelectedTestCase(objSelectedTestCase);
			}
		} catch (BIRT_Exception ex) {
			if (BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(ex);
		}

	}

	public Object[][] getTestCases() {
		return BIRT_DataObject.getTestSuite_SelectedTestCases();
	}

	public String getTestSuiteName() {
		return jtTestSuiteName.getText().trim();
	}
	
	public String getTestFilePath() {
		return jtTestFilePath.getText().trim();
	}

	public String getTestSuiteDescription() {
		return jtaTestSuiteDescription.getText().trim();
	}

	public int getSelectedRow() {
		return jlstTestCases.getSelectedIndex();
	}

}
