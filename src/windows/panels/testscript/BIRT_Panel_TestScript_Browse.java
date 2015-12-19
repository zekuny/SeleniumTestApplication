package windows.panels.testscript;

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

public class BIRT_Panel_TestScript_Browse extends BIRT_Panel
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

	private static String[]			strTestScriptBrowseTableColumnNames	=
																		{ "TestScript Name", "TestScript Description" };

	private javax.swing.JButton		jbCancel;
	private javax.swing.JButton		jbDelete;
	private javax.swing.JButton		jbEdit;
	private javax.swing.JButton		jbView;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestScriptDetails;
	private javax.swing.JTable		jtblTestScriptDetails;

	protected void addPanelComponentActionListeners()
	{
		jtblTestScriptDetails.getSelectionModel().addListSelectionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Browse());

		jbView.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Browse());

		jbEdit.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Browse());

		jbDelete.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Browse());

		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Browse());

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
		jbView = new javax.swing.JButton();
		jbEdit = new javax.swing.JButton();
		jbDelete = new javax.swing.JButton();
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
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsHeaderSeparator).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addGap(0, 73, Short.MAX_VALUE).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
												layout.createSequentialGroup().addComponent(jbView, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(30, 30,
														30).addComponent(jbEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(33, 33, 33).addComponent(
														jbDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(34, 34, 34).addComponent(jbCancel,
														javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jspTestScriptDetails,
												javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(49, 49, 49)).addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jlHeader).addGap(207, 207, 207)))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jlHeader).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jsHeaderSeparator,
						javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
						jspTestScriptDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(30, 30, 30).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbEdit).addComponent(jbView, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
								javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(
								jbDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(41, Short.MAX_VALUE)));
	}

	protected void setPanelComponentActionCommands()
	{

		jbView.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_VIEW);

		jbEdit.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_EDIT);

		jbDelete.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_DELETE);

		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_BROWSE_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Browse Test Script");

		jbView.setText("View");

		jbEdit.setText("Edit");

		jbDelete.setText("Delete");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateTestScriptDescriptionDetails();

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
