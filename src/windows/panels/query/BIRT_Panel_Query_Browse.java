package windows.panels.query;

import javax.swing.table.DefaultTableModel;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import actionlistener.windows.panels.query.BIRT_ActionListener_Query_Browse;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;
import comm.BIRT_Resources;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_Panel_Query_Browse extends BIRT_Panel
{

	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspBrowseQuery;
	private javax.swing.JTable		jtblBrowseQuery;
	private javax.swing.JButton		jbCancel;
	private javax.swing.JButton		jbDelete;
	private javax.swing.JButton		jbEdit;
	private javax.swing.JButton		jbView;

	private boolean					isValueSelected;

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
	private static String[]		strQueryBrowseTableColumnNames	=
																{ BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_QUERYNAME),
			BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_QUERYDESCRIPTION) };

	/*private static Class[] ClassTypeofColumn =
	{String.class, String.class};
	private static boolean[] canEditColumns =
	{false, false};*/

	private static final long	serialVersionUID				= 1L;

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		updateContents();

		jspBrowseQuery = new javax.swing.JScrollPane();
		jtblBrowseQuery = new javax.swing.JTable();

		DefaultTableModel objBIRT_Default_TableModel = null;
		try
		{
			objBIRT_Default_TableModel = new DefaultTableModel((Object[][]) BIRT_DataObject.getObjQueryTableDetails()[BIRT_DataObject.QUERY_TABLE_DATA], strQueryBrowseTableColumnNames);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			BIRT_ExceptionHandler.handleFatalError(e);
		}
		//,strQueryBrowseTableColumnNames, ClassTypeofColumn, canEditColumns);

		jtblBrowseQuery.setModel(objBIRT_Default_TableModel);
		jtblBrowseQuery.setRowSelectionAllowed(true);
		jtblBrowseQuery.setColumnSelectionAllowed(false);

		jtblBrowseQuery.getTableHeader().setReorderingAllowed(false);
		jspBrowseQuery.setViewportView(jtblBrowseQuery);
		jtblBrowseQuery.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

		jbView = new javax.swing.JButton();
		jbEdit = new javax.swing.JButton();
		jbDelete = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_PANELS_QUERY_BROWSEQUERY));

		jbView.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_VIEW));

		jbEdit.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_EDIT));

		jbDelete.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_DELETE));

		jbCancel.setText(objResourceBundle.getString(BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_CANCEL));

	}

	protected void setPanelComponentActionCommands()
	{

		jbDelete.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_BROWSE_DELETE);
		jbEdit.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_BROWSE_EDIT);
		jbView.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_BROWSE_VIEW);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_QUERY_BROWSE_CANCEL);

	}

	protected void addPanelComponentActionListeners()
	{
		BIRT_ActionListener_Query_Browse objActionListener_Query_Browse = BIRT_ActionListeners.getObjActionListener_Panel_Query_Browse();
		jbView.addActionListener(objActionListener_Query_Browse);
		jbEdit.addActionListener(objActionListener_Query_Browse);
		jbDelete.addActionListener(objActionListener_Query_Browse);
		jtblBrowseQuery.getSelectionModel().addListSelectionListener(objActionListener_Query_Browse);
		jbCancel.addActionListener(objActionListener_Query_Browse);
	}

	public void resetContents()
	{
		BIRT_DataObject.setObjQueryTableDetails(null);

	}

	public void updateContents()
	{
		updateQueryDescriptionDetails();
	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(462, 462, 462).addComponent(jlHeader)).addGroup(
								layout.createSequentialGroup().addGap(33, 33, 33).addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jspBrowseQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 959,
												javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(
												layout.createSequentialGroup().addComponent(jbView, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(123,
														123, 123).addComponent(jbEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(
														javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jbDelete,
														javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(128, 128, 128).addComponent(jbCancel,
														javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))).addContainerGap(32, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jlHeader).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jsHeaderSeparator,
						javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(
						jspBrowseQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(35, 35, 35).addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbView, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jbEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jbDelete,
										javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
										javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(106, Short.MAX_VALUE)));
	}

	private void updateQueryDescriptionDetails()
	{
		try
		{
			BIRT_DataObject.setObjQueryTableDetails(BIRT_Persistence.getObjBIPersistence_Query().getQueryDescriptions(BIRT_DataObject.getIProjectID()));
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
		return jtblBrowseQuery.getSelectedRow();
	}

	protected boolean isFieldsProper()
	{
		if (isValueSelected())
			return true;
		else return handleFieldProperError(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_FIELD_ERROR_DISPLAYABLE_MSG_QUERY_BROWSE_SELECT_VALUE_NULL));

	}

}
