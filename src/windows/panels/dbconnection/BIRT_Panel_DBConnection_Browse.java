package windows.panels.dbconnection;

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

public class BIRT_Panel_DBConnection_Browse extends BIRT_Panel
{

	private static final long		serialVersionUID	= 1L;

	private javax.swing.JButton		jbCancel;
	private javax.swing.JButton		jbDelete;
	private javax.swing.JButton		jbEdit;
	private javax.swing.JButton		jbView;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspBrowseDBConnection;
	private javax.swing.JTable		jtblBrowseDBConnection;

	private boolean					isValueSelected;

	private final boolean isValueSelected()
	{
		return isValueSelected;
	}

	public final void setValueSelected(boolean isValueSelected)
	{
		this.isValueSelected = isValueSelected;
	}

	// DbConnection Details Table Data Properties
	// Two Columns - Query Name and Query Description
	private static String[]	strDBConnectionBrowseTableColumnNames	=
																	{ "DBConnection Name", "DBConnection Description" };

	protected void addPanelComponentActionListeners()
	{

		BIRT_ActionListener objActionListener = BIRT_ActionListeners.getObjActionListener_Panel_DBConnection_Browse();
		jbCancel.addActionListener(objActionListener);
		jbDelete.addActionListener(objActionListener);
		jbEdit.addActionListener(objActionListener);
		jbView.addActionListener(objActionListener);

		jtblBrowseDBConnection.getSelectionModel().addListSelectionListener(BIRT_ActionListeners.getObjActionListener_Panel_DBConnection_Browse());

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jspBrowseDBConnection = new javax.swing.JScrollPane();
		jtblBrowseDBConnection = new javax.swing.JTable();
		jbView = new javax.swing.JButton();
		jbEdit = new javax.swing.JButton();
		jbDelete = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		DefaultTableModel objBIRT_Default_TableModel = null;
		try
		{
			objBIRT_Default_TableModel = new DefaultTableModel((Object[][]) BIRT_DataObject.getObjDBConnectionTableDetails()[BIRT_DataObject.DBCONN_TABLE_DATA], strDBConnectionBrowseTableColumnNames);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}

		jtblBrowseDBConnection.setModel(objBIRT_Default_TableModel);
		jtblBrowseDBConnection.setRowSelectionAllowed(true);
		jtblBrowseDBConnection.setColumnSelectionAllowed(false);

		jtblBrowseDBConnection.getTableHeader().setReorderingAllowed(false);
		jspBrowseDBConnection.setViewportView(jtblBrowseDBConnection);
		jtblBrowseDBConnection.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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
								layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jspBrowseDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 663,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGap(266, 266, 266).addComponent(jlHeader)).addGroup(
								layout.createSequentialGroup().addGap(190, 190, 190).addComponent(jbView).addGap(28, 28, 28).addComponent(jbEdit).addGap(29, 29, 29).addComponent(jbDelete).addGap(18,
										18, 18).addComponent(jbCancel))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jsHeaderSeparator,
						javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
						jspBrowseDBConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbView).addComponent(jbCancel).addComponent(jbDelete).addComponent(jbEdit))
						.addContainerGap(29, Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_CANCEL);
		jbDelete.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_DELETE);
		jbEdit.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_EDIT);
		jbView.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_DBCONNECTION_BROWSE_VIEW);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Browse DB Connection");

		jbView.setText("View");

		jbEdit.setText("Edit");

		jbDelete.setText("Delete");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updateDBConnectionDescriptionDetails();

	}

	private void updateDBConnectionDescriptionDetails()
	{
		try
		{
			BIRT_DataObject.setObjDBConnectionTableDetails(BIRT_Persistence.getObjBIPersistence_DBConnection().getDBConnectionDescriptions(BIRT_DataObject.getIProjectID()));
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
		return jtblBrowseDBConnection.getSelectedRow();
	}

}
