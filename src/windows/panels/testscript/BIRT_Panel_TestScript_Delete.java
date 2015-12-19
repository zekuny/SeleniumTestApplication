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

public class BIRT_Panel_TestScript_Delete extends BIRT_Panel
{
	private static final long			serialVersionUID			= 1L;
	private static final String[]		strColumnNames				= new String[]
																	{ "Sequence No.", "Command", "Method", "Target", "Value" };

	private static DefaultTableModel	objBIRT_Default_TableModel	= new DefaultTableModel(BIRT_DataObject.getTestScript_CreateTestSteps(), strColumnNames);

	private javax.swing.JButton			jbCancel;
	private javax.swing.JButton			jbDelete;
	private javax.swing.JLabel			jlHeader;
	private javax.swing.JLabel			jlTestScriptDescription;
	private javax.swing.JLabel			jlTestScriptName;
	private javax.swing.JSeparator		jsSeparator;
	private javax.swing.JScrollPane		jspTestScriptDescription;
	private javax.swing.JScrollPane		jspTestSteps;
	private javax.swing.JTextField		jtTestScriptName;
	private javax.swing.JTable			jtTestSteps;
	private javax.swing.JTextArea		jtaTestScriptDescription;

	protected void addPanelComponentActionListeners()
	{
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Delete());
		jbDelete.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Delete());

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
		jtTestSteps = new javax.swing.JTable();
		jbDelete = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		updateContents();

		jtTestScriptName.setEditable(false);
		jtaTestScriptDescription.setEditable(false);

		jtaTestScriptDescription.setColumns(20);
		jtaTestScriptDescription.setRows(5);
		jspTestScriptDescription.setViewportView(jtaTestScriptDescription);

		jtTestSteps.setModel(objBIRT_Default_TableModel);
		jtTestSteps.setRowSelectionAllowed(false);
		jtTestSteps.setColumnSelectionAllowed(false);
		jtTestSteps.setEnabled(false);
		jtTestSteps.getTableHeader().setReorderingAllowed(false);
		jspTestSteps.setViewportView(jtTestSteps);
		jtTestSteps.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	}

	protected boolean isFieldsProper()
	{
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
				layout.createSequentialGroup().addContainerGap(23, Short.MAX_VALUE).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(
								layout.createSequentialGroup().addComponent(jbDelete).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(jbCancel)).addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jspTestSteps, javax.swing.GroupLayout.Alignment.LEADING).addGroup(
										layout.createSequentialGroup().addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestScriptName).addComponent(jlTestScriptDescription)).addGap(69,
												69, 69).addGroup(
												layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jspTestScriptDescription).addComponent(jtTestScriptName,
														javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))).addGap(36, 36, 36)).addGroup(
				layout.createSequentialGroup().addGap(197, 197, 197).addComponent(jlHeader).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addGap(18, 18, 18).addComponent(jsSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jlTestScriptName).addComponent(jtTestScriptName, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jlTestScriptDescription).addComponent(jspTestScriptDescription,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE).addComponent(jspTestSteps, javax.swing.GroupLayout.PREFERRED_SIZE, 207,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbDelete).addComponent(jbCancel)).addGap(24, 24, 24)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbDelete.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_DELETE_DELETE);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_DELETE_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Delete Test Script");

		jlTestScriptName.setText("Test Script Name");

		jlTestScriptDescription.setText("Test Script Description");

		jbDelete.setText("Delete");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateTestStepDetails();

	}

	private void updateTestStepDetails()
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
}
