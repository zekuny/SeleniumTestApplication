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

public class BIRT_Panel_TestCase_Browse extends BIRT_Panel
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

	// Query Details Table Data Properties
	// Two Columns - Query Name and Query Description
	private static String[]			strTestCaseBrowseTableColumnNames	=
																		{ "TestCase Name", "TestCase Description" };

	private javax.swing.JButton		jbCancel;
	private javax.swing.JButton		jbCopy;
	private javax.swing.JButton		jbDelete;
	private javax.swing.JButton		jbEdit;
	private javax.swing.JButton		jbView;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestCaseDetails;
	private javax.swing.JTable		jtblTestCaseDetails;

	protected void addPanelComponentActionListeners()
	{
		jtblTestCaseDetails.getSelectionModel().addListSelectionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Browse());
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Browse());
		jbCopy.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Browse());
		jbDelete.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Browse());
		jbEdit.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Browse());
		jbView.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestCase_Browse());
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
		jbView = new javax.swing.JButton();
		jbCopy = new javax.swing.JButton();
		jbEdit = new javax.swing.JButton();
		jbDelete = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		DefaultTableModel objBIRT_Default_TableModel = null;
		try
		{
			objBIRT_Default_TableModel = new DefaultTableModel((Object[][]) BIRT_DataObject.getObjTestCaseTableDetails()[BIRT_DataObject.TESTCASE_TABLE_DATA], strTestCaseBrowseTableColumnNames);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
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
								layout.createSequentialGroup().addGap(27, 27, 27).addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jspTestCaseDetails).addGroup(
												layout.createSequentialGroup().addComponent(jbView, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18,
														18).addComponent(jbCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(
														jbEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jbDelete,
														javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jbCancel,
														javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGroup(
								layout.createSequentialGroup().addGap(228, 228, 228).addComponent(jlHeader))).addContainerGap(22, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jsHeaderSeparator,
						javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jspTestCaseDetails, javax.swing.GroupLayout.PREFERRED_SIZE,
						247, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbEdit).addComponent(jbView).addComponent(jbCopy).addComponent(jbDelete).addComponent(
								jbCancel)).addContainerGap(21, Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_CANCEL);
		jbCopy.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_COPY);
		jbDelete.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_DELETE);
		jbEdit.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_EDIT);
		jbView.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTCASE_BROWSE_VIEW);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Browse Test Case");

		jbView.setText("View");

		jbCopy.setText("Copy");

		jbEdit.setText("Edit");

		jbDelete.setText("Delete");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateTestCaseDescriptionDetails();

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
