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

public class BIRT_Panel_TestSuite_Browse extends BIRT_Panel
{

	private static final long	serialVersionUID					= 1L;
	private boolean				isValueSelected;

	private static String[]		strTestSuiteBrowseTableColumnNames	=
																	{ "TestSuite Name", "TestSuite Description" };

	private final boolean isValueSelected()
	{
		return isValueSelected;
	}

	public final void setValueSelected(boolean isValueSelected)
	{
		this.isValueSelected = isValueSelected;
	}

	private javax.swing.JButton		jbCancel;
	private javax.swing.JButton		jbDelete;
	private javax.swing.JButton		jbEdit;
	private javax.swing.JButton		jbView;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestSuites;
	private javax.swing.JTable		jtblTestSuites;

	protected void addPanelComponentActionListeners()
	{
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Browse());
		jbEdit.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Browse());
		jbView.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Browse());
		jbDelete.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Browse());
		jtblTestSuites.getSelectionModel().addListSelectionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_Browse());

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
		jbView = new javax.swing.JButton();
		jbEdit = new javax.swing.JButton();
		jbDelete = new javax.swing.JButton();
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
												layout.createSequentialGroup().addComponent(jbView).addGap(70, 70, 70).addComponent(jbEdit).addPreferredGap(
														javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jbDelete).addGap(62,
														62, 62).addComponent(jbCancel)).addComponent(jspTestSuites, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE,
												452, javax.swing.GroupLayout.PREFERRED_SIZE)))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addGap(18, 18, 18).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jspTestSuites,
						javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbView).addComponent(jbEdit).addComponent(jbDelete).addComponent(jbCancel))
						.addContainerGap()));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_CANCEL);
		jbView.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_VIEW);
		jbDelete.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_DELETE);
		jbEdit.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_BROWSE_EDIT);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Browse Test Suite");

		jbView.setText("View");

		jbEdit.setText("Edit");

		jbDelete.setText("Delete");

		jbCancel.setText("Cancel");

	}

	public void updateContents() throws BIRT_Exception
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
