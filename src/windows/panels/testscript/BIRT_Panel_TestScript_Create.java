package windows.panels.testscript;

import javax.swing.table.DefaultTableModel;

import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;

import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;

public class BIRT_Panel_TestScript_Create extends BIRT_Panel
{

	private static final long			serialVersionUID			= 1L;
	private static final String[]		strColumnNames				= new String[]
																	{ "Sequence No.", "Command", "Method", "Target", "Value" };

	private javax.swing.JButton			jbCancel;
	private javax.swing.JButton			jbCreateStep;
	private javax.swing.JButton			jbDeleteStep;
	private javax.swing.JButton			jbExportStep;
	private javax.swing.JButton			jbSave;
	private javax.swing.JButton			jbImportStep;
	private javax.swing.JLabel			jlHeader;
	private javax.swing.JLabel			jlTestScriptDescription;
	private javax.swing.JLabel			jlTestScriptName;
	private javax.swing.JSeparator		jsSeparator;
	private javax.swing.JScrollPane		jspTestScriptDescription;
	private javax.swing.JScrollPane		jspTestSteps;
	private javax.swing.JTextField		jtTestScriptName;
	private javax.swing.JTable			jtblTestSteps;
	private javax.swing.JTextArea		jtaTestScriptDescription;

	private static DefaultTableModel	objBIRT_Default_TableModel	= new DefaultTableModel(BIRT_DataObject.getTestScript_CreateTestSteps(), strColumnNames);

	protected void addPanelComponentActionListeners()
	{
		jbSave.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Create());
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Create());
		jbCreateStep.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Create());
		jbImportStep.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Create());
		jbExportStep.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Create());

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{

		jlTestScriptName = new javax.swing.JLabel();
		jtTestScriptName = new javax.swing.JTextField();
		jlTestScriptDescription = new javax.swing.JLabel();
		jspTestScriptDescription = new javax.swing.JScrollPane();
		jtaTestScriptDescription = new javax.swing.JTextArea();
		jspTestSteps = new javax.swing.JScrollPane();
		jtblTestSteps = new javax.swing.JTable();
		jbCreateStep = new javax.swing.JButton();
		jbImportStep = new javax.swing.JButton();
		jbExportStep = new javax.swing.JButton();
		jbDeleteStep = new javax.swing.JButton();
		jbSave = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		updateContents();

		jtaTestScriptDescription.setColumns(20);
		jtaTestScriptDescription.setRows(5);
		jspTestScriptDescription.setViewportView(jtaTestScriptDescription);

		jtblTestSteps.setModel(objBIRT_Default_TableModel);
		jtblTestSteps.setRowSelectionAllowed(true);
		jtblTestSteps.setColumnSelectionAllowed(false);
		jtblTestSteps.setEnabled(false);
		jtblTestSteps.getTableHeader().setReorderingAllowed(false);
		jspTestSteps.setViewportView(jtblTestSteps);
		jtblTestSteps.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		jbDeleteStep.setVisible(false);

	}

	protected boolean isFieldsProper()
	{
		if (getTestScriptName().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("TestScript Name cannot be null. Please enter a TestScript Name.");
		else if (getTestScriptDescription().equals(BIRT_DataObject.NULL))
			return handleFieldProperError("TestScript Description cannot be null. Please enter a TestScript Description.");
		else if (BIRT_DataObject.getTestScript_CreateTestSteps().length <= 0)
			return handleFieldProperError("TestSteps cannot be empty. Please add atleast one TestStep.");
		return true;
	}

	public void resetContents()
	{
		jtTestScriptName.setText(BIRT_DataObject.NULL);
		jtaTestScriptDescription.setText(BIRT_DataObject.NULL);
		BIRT_DataObject.destroyTestScript_TestSteps();
		updateContents();
	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsSeparator).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestScriptName).addComponent(jlTestScriptDescription)).addGap(60, 60, 60)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jspTestScriptDescription).addComponent(jtTestScriptName)))
								.addComponent(jspTestSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(
										layout.createSequentialGroup().addComponent(jbSave).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addComponent(jbCancel))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jbDeleteStep, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jbCreateStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(jbImportStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jbExportStep,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()).addGroup(
				layout.createSequentialGroup().addGap(267, 267, 267).addComponent(jlHeader).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addGap(18, 18, 18).addComponent(jsSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlTestScriptName).addComponent(jtTestScriptName, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestScriptDescription).addComponent(jspTestScriptDescription,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jspTestSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 207,
								javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(
								layout.createSequentialGroup().addComponent(jbCreateStep).addGap(18, 18, 18).addComponent(jbImportStep).addGap(18, 18, 18).addComponent(jbExportStep)
										.addGap(18, 18, 18).addComponent(jbDeleteStep))).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jbSave).addComponent(jbCancel)).addGap(24, 24, 24)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_SAVE);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_CANCEL);
		jbCreateStep.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_CREATE_STEP);
		jbImportStep.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_IMPORT);
		jbExportStep.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_CREATE_EXPORT);

	}

	protected void setPanelComponentText()
	{

		jlHeader.setText("Create Test Script");

		jlTestScriptName.setText("Test Script Name");

		jlTestScriptDescription.setText("Test Script Description");

		jbCreateStep.setText("Create Step");

		jbImportStep.setText("Import Steps");

		jbExportStep.setText("Export Steps");

		jbDeleteStep.setText("Delete Step");

		jbSave.setText("Save Test Script");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateTestStepDetails();

	}

	private void updateTestStepDetails()
	{

		objBIRT_Default_TableModel.setDataVector(BIRT_DataObject.getTestScript_CreateTestSteps(), strColumnNames);

	}

	public String getTestScriptName()
	{
		return jtTestScriptName.getText().trim();
	}

	public String getTestScriptDescription()
	{
		return jtaTestScriptDescription.getText().trim();
	}

}
