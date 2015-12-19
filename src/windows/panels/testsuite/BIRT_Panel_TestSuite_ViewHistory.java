package windows.panels.testsuite;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logger.BIRT_Logger;
import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Persistence;

import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;

public class BIRT_Panel_TestSuite_ViewHistory extends BIRT_Panel
{

	/**
	 * 
	 */
	private static final long		serialVersionUID					= 1L;
	private javax.swing.JButton		jbCancel;
	private javax.swing.JLabel		jlHeader;
	private javax.swing.JSeparator	jsHeaderSeparator;
	private javax.swing.JScrollPane	jspTestSuiteHistory;
	private javax.swing.JTable		jtblTestSuiteHistory;
	private static String[]			strTestSuiteHistoryTableColumnNames	=
																		{ "TimeStamp", "Total Test Cases", "Pass", "Fail", "Pass Percentage", "Archive Path" };

	protected void addPanelComponentActionListeners()
	{
		jbCancel.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestSuite_ViewHistory());

	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jsHeaderSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jspTestSuiteHistory = new javax.swing.JScrollPane();
		jtblTestSuiteHistory = new javax.swing.JTable();
		jbCancel = new javax.swing.JButton();

		DefaultTableModel objBIRT_Default_TableModel = null;
		try
		{
			objBIRT_Default_TableModel = new DefaultTableModel((Object[][]) BIRT_DataObject.getObjTestSuiteHistoryTableDetails()[BIRT_DataObject.TESTSUITEHIST_TABLE_DATA],
					strTestSuiteHistoryTableColumnNames);
		}
		catch (BIRT_Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_ExceptionHandler.handleFatalError(e);
		}

		jtblTestSuiteHistory.setModel(objBIRT_Default_TableModel);
		jtblTestSuiteHistory.setRowSelectionAllowed(false);
		jtblTestSuiteHistory.setColumnSelectionAllowed(false);

		jtblTestSuiteHistory.getTableHeader().setReorderingAllowed(false);
		jtblTestSuiteHistory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//Setting Column Width
		jtblTestSuiteHistory.getColumnModel().getColumn(0).setPreferredWidth(125);
		jtblTestSuiteHistory.getColumnModel().getColumn(1).setPreferredWidth(105);
		jtblTestSuiteHistory.getColumnModel().getColumn(2).setPreferredWidth(40);
		jtblTestSuiteHistory.getColumnModel().getColumn(3).setPreferredWidth(40);
		jtblTestSuiteHistory.getColumnModel().getColumn(4).setPreferredWidth(110);
		jtblTestSuiteHistory.getColumnModel().getColumn(5).setPreferredWidth(510);

		jspTestSuiteHistory.setViewportView(jtblTestSuiteHistory);
		jspTestSuiteHistory.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jtblTestSuiteHistory.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	}

	protected boolean isFieldsProper()
	{
		return true;
	}

	public void resetContents()
	{
		BIRT_DataObject.setObjTestSuiteHistoryTableDetails(null);

	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.Alignment.TRAILING).addGroup(
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
								layout.createSequentialGroup().addContainerGap().addComponent(jspTestSuiteHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)).addGroup(
								layout.createSequentialGroup().addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(292, 292, 292).addComponent(jbCancel))
												.addGroup(layout.createSequentialGroup().addGap(256, 256, 256).addComponent(jlHeader))).addGap(0, 0, Short.MAX_VALUE))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addComponent(jlHeader).addGap(18, 18, 18).addComponent(jsHeaderSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
						javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jspTestSuiteHistory,
						javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jbCancel).addContainerGap(24, Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_VIEWHISTORY_CANCEL);

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("View Test Suite Result History");
		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{

		updateTestSuiteHistoryResults();
	}

	private void updateTestSuiteHistoryResults()
	{
		try
		{
			BIRT_DataObject.setObjTestSuiteHistoryTableDetails(BIRT_Persistence.getObjBIPersistence_TestSuite().getTestSuiteResultHistory(BIRT_DataObject.getIProjectID(),
					BIRT_DataObject.getApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID()));
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
