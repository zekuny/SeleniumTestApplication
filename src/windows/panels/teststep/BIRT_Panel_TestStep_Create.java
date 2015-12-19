package windows.panels.teststep;

import logger.BIRT_Logger;

import com.anugraha.birt.app.BIRT_AppProperty;

import windows.panels.BIRT_Panel;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Windows;
import framework.selenium.driver.exec_engine.BIRT_SeleniumDriver;

public class BIRT_Panel_TestStep_Create extends BIRT_Panel
{

	private static final long				serialVersionUID		= 1L;

	private javax.swing.JButton				jbCreate;
	private javax.swing.JButton				jbCancel;
	private javax.swing.JComboBox<String>	jcbCommand;
	private javax.swing.JComboBox<String>	jcbMethod;
	private javax.swing.JLabel				jlCommand;
	private javax.swing.JLabel				jlHeader;
	private javax.swing.JLabel				jlMethod;
	private javax.swing.JSeparator			jlSeparator;
	private javax.swing.JLabel				jlSequenceNo;
	private javax.swing.JLabel				jlTarget;
	private javax.swing.JLabel				jlValue;
	private javax.swing.JTextField			jsrSequenceNo;
	private javax.swing.JTextField			jtTarget;
	private javax.swing.JTextField			jtValue;

	private static BIRT_ActionListener		objBIRT_ActionListener	= null;

	public static void setObjBIRT_ActionListener(BIRT_ActionListener objBIRT_ActionListener)
	{
		BIRT_Panel_TestStep_Create.objBIRT_ActionListener = objBIRT_ActionListener;
	}

	protected void initHeader()
	{
		jlHeader = new javax.swing.JLabel();
		jlSeparator = new javax.swing.JSeparator();

	}

	protected void initPanelComponents()
	{
		jlSequenceNo = new javax.swing.JLabel();
		jsrSequenceNo = new javax.swing.JTextField();
		jlCommand = new javax.swing.JLabel();
		jcbCommand = new javax.swing.JComboBox<String>();
		jlTarget = new javax.swing.JLabel();
		jtTarget = new javax.swing.JTextField();
		jlMethod = new javax.swing.JLabel();
		jcbMethod = new javax.swing.JComboBox<String>();
		jlValue = new javax.swing.JLabel();
		jtValue = new javax.swing.JTextField();
		jbCreate = new javax.swing.JButton();
		jbCancel = new javax.swing.JButton();

		jcbCommand.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_SeleniumDriver.DriverCommands));

	}

	protected boolean isFieldsProper()
	{
		try
		{
			if (BIRT_DataObject.isSeqNumberPresent(getSequenceNo()))
				return handleFieldProperError("Sequence Number already exists. Please choose another sequence number.");
			else if (getSequenceNo() <= 0)
				return handleFieldProperError("Sequence Number should be a non zero positive integer. Please choose another sequence number.");
			else if (jcbCommand.getSelectedIndex() == -1)
				return handleFieldProperError("Please select a Command to continue.");
			else if (BIRT_SeleniumDriver.isMethodFieldVisibleForDriverCommands[jcbCommand.getSelectedIndex()] && jcbMethod.getSelectedIndex() == -1)
				return handleFieldProperError("Please select a Method to continue.");
			else if (BIRT_SeleniumDriver.isTargetFieldVisibleForDriverCommands[jcbCommand.getSelectedIndex()] && getTarget().equals(BIRT_DataObject.NULL))
				return handleFieldProperError("Target cannot be empty. Please enter a target.");
			else if (BIRT_SeleniumDriver.isValueFieldVisibleForDriverCommands[jcbCommand.getSelectedIndex()] && getValue().equals(BIRT_DataObject.NULL))
				return handleFieldProperError("Value cannot be empty. Please enter a value.");

		}
		catch (NumberFormatException nfe)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(nfe);
			return handleFieldProperError("Sequence Number should be a non zero positive integer. Please choose another sequence number.");
		}
		return true;
	}

	public void resetContents()
	{
		jsrSequenceNo.setText(BIRT_DataObject.NULL);
		jcbCommand.setSelectedIndex(0);
		jtTarget.setText(BIRT_DataObject.NULL);
		jtValue.setText(BIRT_DataObject.NULL);
	}

	protected void setLayout()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jlSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addGap(114, 114, 114).addComponent(jlHeader).addContainerGap())
				.addGroup(
						layout.createSequentialGroup()
								.addGap(36, 36, 36)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jlMethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jlTarget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jlCommand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jlSequenceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jlValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jbCreate))
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(43, 43, 43)
																.addGroup(
																		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(jtValue)
																				.addComponent(jcbMethod, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(jtTarget, javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(jcbCommand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(jsrSequenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 199,
																										javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE))))
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(jbCancel))).addGap(41, 41, 41)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jlHeader)
						.addGap(18, 18, 18)
						.addComponent(jlSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jsrSequenceNo)
										.addComponent(jlSequenceNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jlCommand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jcbCommand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jlMethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jcbMethod))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jtTarget)
										.addComponent(jlTarget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jtValue)
										.addComponent(jlValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(30, 30, 30)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jbCreate).addComponent(jbCancel)).addContainerGap(32, Short.MAX_VALUE)));

	}

	protected void setPanelComponentActionCommands()
	{
		jbCreate.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSTEP_CREATE_CREATESTEP);
		jbCancel.setActionCommand(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSTEP_CREATE_CANCELSTEP);

	}

	protected void addPanelComponentActionListeners()
	{

		jcbCommand.addActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_TestStep_Create());
		updatePanelComponentActionListeners();

	}

	protected void setPanelComponentText()
	{
		jlHeader.setText("Create Test Step");

		jlSequenceNo.setText("Sequence No.");

		jlCommand.setText("Command");

		jlTarget.setText("Target");

		jlMethod.setText("Method");

		jlValue.setText("Value");

		jbCreate.setText("Create");

		jbCancel.setText("Cancel");

	}

	public void updateContents()
	{
		updatePanelComponentActionListeners();
		updateComboBoxes();
		jsrSequenceNo.setText("" + BIRT_DataObject.nextTestStepSequenceNumber());
	}

	private void updatePanelComponentActionListeners()
	{
		jbCreate.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Create());
		jbCancel.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Create());
		jbCreate.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Edit());
		jbCancel.removeActionListener(BIRT_ActionListeners.getObjActionListener_Panel_TestScript_Edit());

		jbCreate.addActionListener(objBIRT_ActionListener);
		jbCancel.addActionListener(objBIRT_ActionListener);
	}

	public int getSequenceNo()
	{
		return Integer.parseInt(jsrSequenceNo.getText().trim());
	}

	public String getCommand()
	{
		return BIRT_SeleniumDriver.DriverCommands[jcbCommand.getSelectedIndex()];
	}

	public String getTarget()
	{
		return jtTarget.getText().trim();
	}

	public String getMethod()
	{
		int iSelectedCommand = jcbCommand.getSelectedIndex();
		int iSelectedMethod = jcbMethod.getSelectedIndex();
		if (BIRT_SeleniumDriver.isMethodFieldVisibleForDriverCommands[iSelectedCommand])
		{
			if (iSelectedCommand == 0)
				return BIRT_SeleniumDriver.MethodsList[0][iSelectedMethod];

			else if (iSelectedCommand > 1)
				return BIRT_SeleniumDriver.MethodsList[2][iSelectedMethod];
		}
		return "";

	}

	public String getValue()
	{
		return jtValue.getText().trim();
	}

	public void updateComboBoxes()
	{
		int iSelectedCommand = jcbCommand.getSelectedIndex();
		if (iSelectedCommand >= 0)
		{
			if (iSelectedCommand == 0)
				jcbMethod.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_SeleniumDriver.MethodsList[0]));
			else if (iSelectedCommand == 1)
				jcbMethod.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_SeleniumDriver.MethodsList[1]));
			else jcbMethod.setModel(new javax.swing.DefaultComboBoxModel<String>(BIRT_SeleniumDriver.MethodsList[2]));
			jtTarget.setVisible(BIRT_SeleniumDriver.isTargetFieldVisibleForDriverCommands[iSelectedCommand]);
			jcbMethod.setVisible(BIRT_SeleniumDriver.isMethodFieldVisibleForDriverCommands[iSelectedCommand]);
			jtValue.setVisible(BIRT_SeleniumDriver.isValueFieldVisibleForDriverCommands[iSelectedCommand]);
			BIRT_Windows.getObjMainWindow().resetNAdd(this);
		}
	}

}
