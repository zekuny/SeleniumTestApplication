package windows.panels.testcase;

import javax.swing.table.DefaultTableModel;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;
import comm.BIRT_Resources;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_Panel_TestCase_TestScript_BrowsenAdd extends BIRT_Panel
{

	private static final long	serialVersionUID	= 1L;

	private boolean				isValueSelected;

	private final boolean isValueSelected()
	{
		return isValueSelected;
	}

	public final void setValueSelected(boolean isValueSelected)
	{
		this.isValueSelected = isValueSelected;
	}

	private static BIRT_ActionListener	objBIRT_ActionListener	= null;

	public static void setObjBIRT_ActionListener(BIRT_ActionListener objBIRTActionListener)
	{
		BIRT_Panel_TestCase_TestScript_BrowsenAdd.objBIRT_ActionListener = objBIRTActionListener;

	}

	// Test Script Details Table Data Properties
	// Two Columns - Query Name and Query Description
	private static String[]			strTestScriptBrowseTableColumnNames	=
																		{ "TestScript Name", "TestScript Description" };

	private javax.swing.JButton		jbAdd;
	private javax.swing.JButton		jbCancel;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestScriptDetails;
	private javax.swing.JTable		jtblTestScriptDetails;

	protected void addPanelComponentActionListeners()
	{
		updateActionListeners();
		jtblTestScriptDetails.getSelectionModel().addListSelectionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Create_TestScriptBrowsenAdd());
	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jspTestScriptDetails = new javax.swing.JScrollPane();
		jtblTestScriptDetails = new javax.swing.JTable();
		jbAdd = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		DefaultTableModel objBIRT_Default_TableModel = null;
		try
		{
			objBIRT_Default_TableModel = new DefaultTableModel((Object[][]) BIRT_DataObject.getObjTestScriptTableDetails()[BIRT_DataObject.TESTSCRIPT_TABLE_DATA], strTestScriptBrowseTableColumnNames);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}

		jtblTestScriptDetails.setModel(objBIRT_Default_TableModel);
		jtblTestScriptDetails.setRowSelectionAllowed(true);
		jtblTestScriptDetails.setColumnSelectionAllowed(false);

		jtblTestScriptDetails.getTableHeader().setReorderingAllowed(false);
		jspTestScriptDetails.setViewportView(jtblTestScriptDetails);
		jtblTestScriptDetails.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	}

	protected boolean isFieldsProper()
	{
		if (isValueSelected())
			return true;
		else return handleFieldProperError(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_FIELD_ERROR_DISPLAYABLE_MSG_QUERY_BROWSE_SELECT_VALUE_NULL));

	}

	public void resetContents()
	{
		updateContents();

	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								layout.createSequentialGroup().addGap(142, 142, 142).addComponent(jbAdd).addGap(55, 55, 55).addComponent(jbCancel)).addGroup(
								layout.createSequentialGroup().addGap(197, 197, 197).addComponent(jlHeader)).addGroup(
								layout.createSequentialGroup().addContainerGap()
										.addComponent(jspTestScriptDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addGap(18, 18, 18).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jspTestScriptDetails,
						javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbAdd).addComponent(jbCancel)).addContainerGap(21, Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbAdd.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_ADD);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTSCRIPT_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Browse & Add TestScript");

		jbAdd.setText("Add");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateTestScriptDescriptionDetails();
		updateActionListeners();
	}

	private void updateActionListeners()
	{
		// Remove all action listeners existing.
		jbAdd.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Create());
		jbCancel.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Create());
		jbAdd.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Copy());
		jbCancel.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Copy());
		jbAdd.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Edit());
		jbCancel.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Edit());

		jbAdd.addActionListener(objBIRT_ActionListener);
		jbCancel.addActionListener(objBIRT_ActionListener);

	}

	private void updateTestScriptDescriptionDetails()
	{
		try
		{
			BIRT_DataObject.setObjTestScriptTableDetails(BIRT_Persistence.getObjBIPersistence_TestScript().getTestScriptDescriptions(BIRT_DataObject.getIProjectID()));
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}
	}

	public int getSelectedRow()
	{
		return jtblTestScriptDetails.getSelectedRow();
	}

}
