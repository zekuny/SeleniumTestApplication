package windows.panels.testscript;

import javax.swing.table.DefaultTableModel;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_Panel_TestScript_Edit extends BIRT_Panel
{

	private static final long			serialVersionUID			= 1L;
	private static final String[]		strColumnNames				= new String[]
																	{ "Sequence No.", "Command", "Method", "Target", "Value" };
	private static DefaultTableModel	objBIRT_Default_TableModel	= new DefaultTableModel(BIRT_DataObject.getTestScript_CreateTestSteps(), strColumnNames);

	private javax.swing.JButton			jbCancel;
	private javax.swing.JButton			jbCreateStep;
	private javax.swing.JButton			jbDeleteStep;
	private javax.swing.JButton			jbExportStep;
	private javax.swing.JButton			jbImportStep;
	private javax.swing.JButton			jbSave;
	private javax.swing.JLabel			jlHeader;
	private javax.swing.JLabel			jlTestScriptDescription;
	private javax.swing.JLabel			jlTestScriptName;
	private javax.swing.JSeparator		jsSeparator;
	private javax.swing.JScrollPane		jspTestScriptDescription;
	private javax.swing.JScrollPane		jspTestSteps;
	private javax.swing.JTextField		jtTestScriptName;
	private javax.swing.JTable			jtblTestSteps;
	private javax.swing.JTextArea		jtaTestScriptDescription;

	protected void addPanelComponentActionListeners()
	{
		jbSave.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Edit());
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Edit());
		jbCreateStep.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Edit());
		jbImportStep.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Edit());
		jbExportStep.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Edit());
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

		loadExistingTestStepsFromDB();

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

	private void loadExistingTestStepsFromDB()
	{
		try
		{
			jtTestScriptName.setText(BIRT_DataObject.getApp_Panel_TestScript_Browse_SelectedTestScriptName());
			jtaTestScriptDescription.setText(BIRT_DataObject.getApp_Panel_TestScript_Browse_SelectedTestScriptDescription());
			BIRT_DataObject.addTestScript_TestSteps(BIRT_Persistence.getObjBIPersistence_TestScript().getTestStepsforTestScript(BIRT_DataObject.getIProjectID(),
					BIRT_DataObject.getApp_Panel_TestScript_Browse_SelectedTestScriptID()));
			objBIRT_Default_TableModel.setDataVector(BIRT_DataObject.getTestScript_CreateTestSteps(), strColumnNames);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}

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
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(294, 294, 294).addComponent(jlHeader)).addGroup(
								layout.createSequentialGroup().addGap(39, 39, 39).addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
												layout.createSequentialGroup().addGroup(
														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestScriptName).addComponent(jlTestScriptDescription))
														.addGap(69, 69, 69).addGroup(
																layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jspTestScriptDescription).addComponent(
																		jtTestScriptName, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(
												layout.createSequentialGroup().addComponent(jbSave).addGap(311, 311, 311).addComponent(jbCancel)).addGroup(
												layout.createSequentialGroup().addComponent(jspTestSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(
														18, 18, 18).addGroup(
														layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jbExportStep, javax.swing.GroupLayout.DEFAULT_SIZE,
																117, Short.MAX_VALUE).addComponent(jbImportStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
																.addComponent(jbCreateStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(
																		jbDeleteStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
						.addContainerGap(38, Short.MAX_VALUE)));
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
		jbSave.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_SAVE);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_CANCEL);
		jbCreateStep.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_CREATE_STEP);
		jbImportStep.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_IMPORT);
		jbExportStep.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_EXPORT);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Edit Test Script");

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
