package windows.panels.testsuite;

import javax.swing.DefaultListModel;

import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;

public class BIRT_Panel_TestSuite_Create extends BIRT_Panel
{

	private static final long			serialVersionUID	= 1L;

	private javax.swing.JButton			jbAdd;
	private javax.swing.JButton			jbCancel;
	private javax.swing.JButton			jbRemove;
	private javax.swing.JButton			jbSave;
	private javax.swing.JLabel			jlHeader;
	private javax.swing.JLabel			jlTestCases;
	private javax.swing.JLabel			jlTestSuiteDescription;
	private javax.swing.JLabel			jlTestSuiteName;
	// second
	private javax.swing.JLabel			jlTestPath;
	
	private javax.swing.JList<String>	jlstTestCases;
	private javax.swing.JSeparator		jsHeaderSeparator;
	private javax.swing.JScrollPane		jspTestCases;
	private javax.swing.JScrollPane		jspTestSuiteDescription;
	private javax.swing.JTextField		jtTestSuiteName;
	private javax.swing.JTextArea		jtaTestSuiteDescription;
	
	// second
	private javax.swing.JTextField 		jtTestPath;

	protected void addPanelComponentActionListeners()
	{
		BIRT_ActionListener objActionListener = BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Create();
		jbAdd.addActionListener(objActionListener);
		jbCancel.addActionListener(objActionListener);
		jbRemove.addActionListener(objActionListener);
		jbSave.addActionListener(objActionListener);

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{

		jlTestSuiteName = new javax.swing.JLabel();
		jtTestSuiteName = new javax.swing.JTextField();
		
		// second
		jlTestPath = new javax.swing.JLabel();
		jtTestPath = new javax.swing.JTextField();
		
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
		jlstTestCases.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jspTestCases.setViewportView(jlstTestCases);

	}

	protected boolean isFieldsProper()
	{
		if (getTestSuiteName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Test Suite Name cannot be null. Please enter a TestSuite Name.");
		else if(getTestPathName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Test Path Name cannot be null. Please enter a TestPath Name.");
		else if (getTestSuiteDescription().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("Test Suite Description cannot be null. Please enter a TestSuite Description.");
		else if (getTestCases().length == 0)
			return handleFieldProperError("Test Cases cannot be zero. Please add atleast one Test Case.");
		return true;
	}

	public void resetContents()
	{
		jtTestSuiteName.setText(BIRT_DataObject.NULL);
		jtTestPath.setText(BIRT_DataObject.NULL);
		jtaTestSuiteDescription.setText(BIRT_DataObject.NULL);

		BIRT_DataObject.destroyTestSuite_SelectedTestCase();

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
								.addGap(26, 26, 26)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jlTestSuiteName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jlTestPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jlTestSuiteDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
												.addComponent(jlTestCases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(jbSave).addGap(125, 125, 125).addComponent(jbCancel).addGap(131, 131, 131))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(28, 28, 28)
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jtTestSuiteName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(jtTestPath, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																								.addComponent(jspTestCases, javax.swing.GroupLayout.PREFERRED_SIZE, 251,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(jspTestSuiteDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 251,
																										javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addGap(32, 32, 32)
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																				.addComponent(jbRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
																				.addComponent(jbAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																.addContainerGap(28, Short.MAX_VALUE))))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jlHeader).addGap(232, 232, 232)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(13, 13, 13)
						.addComponent(jlHeader)
						.addGap(18, 18, 18)
						.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(21, 21, 21)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout.createSequentialGroup().addComponent(jlTestSuiteName).addGap(34, 34, 34).addComponent(jlTestPath).addGap(34, 34, 34).addComponent(jlTestSuiteDescription).addGap(113, 113, 113)
														.addComponent(jlTestCases))
										.addGroup(
												layout.createSequentialGroup().addComponent(jlTestPath).addGap(34, 34, 34).addComponent(jlTestPath).addGap(34, 34, 34).addComponent(jlTestSuiteDescription).addGap(113, 113, 113)
														.addComponent(jlTestCases))
										.addGroup(
												layout.createSequentialGroup()
														.addComponent(jtTestSuiteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(31, 31, 31)
														.addComponent(jtTestPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(31, 31, 31)
														.addComponent(jspTestSuiteDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(28, 28, 28)
														.addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jspTestCases, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGroup(layout.createSequentialGroup().addComponent(jbAdd).addGap(18, 18, 18).addComponent(jbRemove))).addGap(18, 18, 18)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jbSave).addComponent(jbCancel))))
						.addContainerGap(48, Short.MAX_VALUE)));

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Create Test Suite");

		jlTestSuiteName.setText("Test Suite Name");
		
		jlTestPath.setText("Test Path");

		jlTestSuiteDescription.setText("Test Suite Description");

		jlTestCases.setText("Test Cases");

		jbAdd.setText("Add");

		jbRemove.setText("Remove");

		jbSave.setText("Save");

		jbCancel.setText("Cancel");
	}

	protected void setPanelComponentActionCommands()
	{
		jbAdd.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_CREATE_ADD_TESTCASE);
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_CREATE_CREATE);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_CREATE_CANCEL);
		jbRemove.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_CREATE_REMOVE_TESTCASE);

	}

	public void updateContents()
	{
		jlstTestCases.setListData(BIRT_DataObject.getTestSuite_SelectedTestCase_Names());
	}

	public Object[][] getTestCases()
	{
		return BIRT_DataObject.getTestSuite_SelectedTestCases();
	}

	public String getTestSuiteName()
	{
		return jtTestSuiteName.getText().trim();
	}
	
	// second
	public String getTestPathName()
	{
		return jtTestPath.getText().trim();
	}

	public String getTestSuiteDescription()
	{
		return jtaTestSuiteDescription.getText().trim();
	}

	public int getSelectedRow()
	{
		return jlstTestCases.getSelectedIndex();
	}

}
