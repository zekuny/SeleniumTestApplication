package windows.panels.testsuite;

import javax.swing.DefaultListModel;

import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;
import exception.BIRT_Exception;

public class BIRT_Panel_TestSuite_Delete extends BIRT_Panel
{

	private static final long			serialVersionUID	= 1L;

	private DefaultListModel<String>	listModel;

	private javax.swing.JButton			jbCancel;
	private javax.swing.JButton			jbDelete;
	private javax.swing.JLabel			jlHeader;
	private javax.swing.JLabel			jlTestCases;
	private javax.swing.JLabel			jlTestSuiteDescription;
	private javax.swing.JLabel			jlTestSuiteName;

	private javax.swing.JList<String>	jlstTestCases;
	private javax.swing.JSeparator		jsHeaderSeparator;
	private javax.swing.JScrollPane		jspTestCases;
	private javax.swing.JScrollPane		jspTestSuiteDescription;
	private javax.swing.JTextField		jtTestSuiteName;
	private javax.swing.JTextArea		jtaTestSuiteDescription;

	protected void addPanelComponentActionListeners()
	{
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Delete());
		jbDelete.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Delete());

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
		jlTestSuiteDescription = new javax.swing.JLabel();
		jspTestSuiteDescription = new javax.swing.JScrollPane();
		jtaTestSuiteDescription = new javax.swing.JTextArea();
		jlTestCases = new javax.swing.JLabel();
		jspTestCases = new javax.swing.JScrollPane();
		jlstTestCases = new javax.swing.JList<String>();
		jbDelete = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();
		jtaTestSuiteDescription.setColumns(20);
		jtaTestSuiteDescription.setRows(5);
		jspTestSuiteDescription.setViewportView(jtaTestSuiteDescription);

		jtTestSuiteName.setEditable(false);
		jtaTestSuiteDescription.setEditable(false);

		listModel = new DefaultListModel<String>();

		jtaTestSuiteDescription.setColumns(20);
		jtaTestSuiteDescription.setRows(5);
		jspTestSuiteDescription.setViewportView(jtaTestSuiteDescription);

		jlstTestCases.setModel(listModel);

		jlstTestCases.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jlstTestCases.setEnabled(false);
		jspTestCases.setViewportView(jlstTestCases);

	}

	protected boolean isFieldsProper()
	{
		return true;
	}

	public void resetContents()
	{
		jtTestSuiteName.setText(BIRT_DataObject.NULL);
		jtaTestSuiteDescription.setText(BIRT_DataObject.NULL);
		BIRT_DataObject.destroyTestSuite_SelectedTestCase();
		resetTestCaseList();

	}

	private void resetTestCaseList()
	{
		int sizeOfList = jlstTestCases.getModel().getSize();

		for (int iCtr = sizeOfList; iCtr < 0; iCtr--)
			jlstTestCases.remove(iCtr);
	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(26, 26, 26)
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(jlTestSuiteDescription)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addGroup(
																										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																												.addComponent(jspTestSuiteDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 228,
																														Short.MAX_VALUE)
																												.addComponent(jspTestCases)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(jbDelete)
																																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																		javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																.addComponent(jbCancel))))
																				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup().addComponent(jlTestCases).addGap(309, 309, 309))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(jlTestSuiteName)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(jtTestSuiteName, javax.swing.GroupLayout.PREFERRED_SIZE, 228,
																										javax.swing.GroupLayout.PREFERRED_SIZE))))
												.addGroup(layout.createSequentialGroup().addGap(163, 163, 163).addComponent(jlHeader))).addContainerGap(26, Short.MAX_VALUE))
				.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(13, 13, 13)
						.addComponent(jlHeader)
						.addGap(18, 18, 18)
						.addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlTestSuiteName)
										.addComponent(jtTestSuiteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(31, 31, 31)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestSuiteDescription)
										.addComponent(jspTestSuiteDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, Short.MAX_VALUE)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestCases)
										.addComponent(jspTestCases, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jbDelete)
										.addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap()));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_DELETE_CANCEL);
		jbDelete.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_DELETE_DELETE);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Delete Test Suite");

		jlTestSuiteName.setText("Test Suite Name");

		jlTestSuiteDescription.setText("Test Suite Description");

		jlTestCases.setText("Test Cases");

		jbDelete.setText("Delete");

		jbCancel.setText("Cancel");

	}

	public void updateContents() throws BIRT_Exception
	{
		jtTestSuiteName.setText(BIRT_DataObject.getApp_Panel_TestSuite_Browse_SelectedTestSuiteName());
		jtaTestSuiteDescription.setText(BIRT_DataObject.getApp_Panel_TestSuite_Browse_SelectedTestSuiteDesc());
		updateList();

	}

	private void updateList() throws BIRT_Exception
	{

		String strTestCaseNames[] = BIRT_Persistence.getObjBIPersistence_TestCase().getTestCaseNames(BIRT_DataObject.getIProjectID(),
				BIRT_Persistence.getObjBIPersistence_TestSuite().getTestSuiteTestCases(BIRT_DataObject.getApp_Panel_TestSuite_Browse_SelectedTestSuiteID(), BIRT_DataObject.getIProjectID()));

		listModel = new DefaultListModel<String>();
		for (int iCtr = 0; iCtr < strTestCaseNames.length; iCtr++)
			listModel.addElement(strTestCaseNames[iCtr]);

		jlstTestCases.setModel(listModel);

	}

}
