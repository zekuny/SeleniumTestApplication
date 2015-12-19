package windows.panels.testsuite;

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

public class BIRT_Panel_TestCase_BrowsenAdd extends BIRT_Panel
{

	private static final long			serialVersionUID	= 1L;

	private boolean						isValueSelected;

	private static BIRT_ActionListener	objBIRT_ActionListener;

	public static void setObjBIRT_ActionListener(BIRT_ActionListener objBIRTActionListener)
	{
		BIRT_Panel_TestCase_BrowsenAdd.objBIRT_ActionListener = objBIRTActionListener;

	}

	private final boolean isValueSelected()
	{
		return isValueSelected;
	}

	public final void setValueSelected(boolean isValueSelected)
	{
		this.isValueSelected = isValueSelected;
	}

	// Query Details Table Data Properties
	// Two Columns - Query Name and Query Description
	private static String[]			strTestCaseBrowseTableColumnNames	=
																		{ "TestCase Name", "TestCase Description" };

	private javax.swing.JButton		jbAdd;
	private javax.swing.JButton		jbCancel;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestCaseDetails;
	private javax.swing.JTable		jtblTestCaseDetails;

	protected void addPanelComponentActionListeners()
	{
		jtblTestCaseDetails.getSelectionModel().addListSelectionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Create_TestCaseBrowseNAdd());
		updateActionListeners();

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jspTestCaseDetails = new javax.swing.JScrollPane();
		jtblTestCaseDetails = new javax.swing.JTable();
		jbAdd = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		DefaultTableModel objBIRT_Default_TableModel = null;
		try
		{
			objBIRT_Default_TableModel = new DefaultTableModel((Object[][]) BIRT_DataObject.getObjTestCaseTableDetails()[BIRT_DataObject.TESTCASE_TABLE_DATA], strTestCaseBrowseTableColumnNames);
		}
		catch (BIRT_Exception e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}

		jtblTestCaseDetails.setModel(objBIRT_Default_TableModel);
		jtblTestCaseDetails.setRowSelectionAllowed(true);
		jtblTestCaseDetails.setColumnSelectionAllowed(false);

		jtblTestCaseDetails.getTableHeader().setReorderingAllowed(false);
		jspTestCaseDetails.setViewportView(jtblTestCaseDetails);
		jtblTestCaseDetails.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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
								layout.createSequentialGroup().addContainerGap().addComponent(jspTestCaseDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addGap(191, 191, 191).addComponent(jlHeader))).addGap(0, 17, Short.MAX_VALUE)).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(56, 56, 56).addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(
						160, 160, 160)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jsHeaderSeparator,
						javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jspTestCaseDetails, javax.swing.GroupLayout.PREFERRED_SIZE,
						247, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jbAdd).addComponent(jbCancel)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbAdd.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTCASE_ADD);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_BROWSENADD_TESTCASE_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Browse & Add Test Case");
		jbAdd.setText("Add");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateTestCaseDescriptionDetails();
		updateActionListeners();

	}

	private void updateActionListeners()
	{
		jbAdd.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Create());
		jbCancel.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Create());

		jbAdd.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Edit());
		jbCancel.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Edit());

		jbAdd.addActionListener(objBIRT_ActionListener);
		jbCancel.addActionListener(objBIRT_ActionListener);

	}

	private void updateTestCaseDescriptionDetails()
	{
		try
		{
			BIRT_DataObject.setObjTestCaseTableDetails(BIRT_Persistence.getObjBIPersistence_TestCase().getTestCaseDescriptions(BIRT_DataObject.getIProjectID()));
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
		return jtblTestCaseDetails.getSelectedRow();
	}

}
