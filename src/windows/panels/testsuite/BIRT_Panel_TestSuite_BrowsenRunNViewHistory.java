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

public class BIRT_Panel_TestSuite_BrowsenRunNViewHistory extends BIRT_Panel
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
	private static String[]			strTestSuiteBrowseTableColumnNames	=
																		{ "TestSuite Name", "TestSuite Description" };

	private javax.swing.JButton		jbCancel;
	private javax.swing.JButton		jbRun;
	private javax.swing.JButton		jbViewHistory;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestSuites;
	private javax.swing.JTable		jtblTestSuites;

	protected void addPanelComponentActionListeners()
	{
		jbRun.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_BrowseRunNViewHistory());
		jbViewHistory.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_BrowseRunNViewHistory());
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_BrowseRunNViewHistory());
		jtblTestSuites.getSelectionModel().addListSelectionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_BrowseRunNViewHistory());
	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jspTestSuites = new javax.swing.JScrollPane();
		jtblTestSuites = new javax.swing.JTable();
		jbRun = new javax.swing.JButton();
		jbViewHistory = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		DefaultTableModel objBIRT_Default_TableModel = null;
		try
		{
			objBIRT_Default_TableModel = new DefaultTableModel((Object[][]) BIRT_DataObject.getObjTestSuiteTableDetails()[BIRT_DataObject.TESTSUITE_TABLE_DATA], strTestSuiteBrowseTableColumnNames);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}

		jtblTestSuites.setModel(objBIRT_Default_TableModel);
		jtblTestSuites.setRowSelectionAllowed(true);
		jtblTestSuites.setColumnSelectionAllowed(false);

		jtblTestSuites.getTableHeader().setReorderingAllowed(false);
		jspTestSuites.setViewportView(jtblTestSuites);
		jtblTestSuites.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	}

	protected boolean isFieldsProper()
	{
		if (isValueSelected())
			return true;
		else return handleFieldProperError(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_FIELD_ERROR_DISPLAYABLE_MSG_QUERY_BROWSE_SELECT_VALUE_NULL));

	}

	public void resetContents()
	{
		BIRT_DataObject.setObjTestSuiteTableDetails(null);

	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(197, 197, 197).addComponent(jlHeader)).addGroup(
								layout.createSequentialGroup().addContainerGap().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(
												layout.createSequentialGroup().addComponent(jbRun).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jbViewHistory).addGap(119, 119, 119).addComponent(jbCancel)).addComponent(
												jspTestSuites, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addGap(18, 18, 18).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jspTestSuites,
						javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbCancel).addComponent(jbViewHistory).addComponent(jbRun)).addContainerGap()));

	}

	protected void setPanelComponentActionCommands()
	{
		jbRun.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_RUN);
		jbViewHistory.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_VIEWHSTRY);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_RUNNVIEWHSTRY_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Browse Test Suite");

		jbRun.setText("Run");

		jbViewHistory.setText("View History");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateTestSuiteDescriptionDetails();
	}

	private void updateTestSuiteDescriptionDetails()
	{
		try
		{
			BIRT_DataObject.setObjTestSuiteTableDetails(BIRT_Persistence.getObjBIPersistence_TestSuite().getTestSuiteDescriptions(BIRT_DataObject.getIProjectID()));
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
		return jtblTestSuites.getSelectedRow();
	}

}
