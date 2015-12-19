package actionlistener.windows.panels.testscript;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import utility.file.FileDialog;
import windows.panels.testscript.BIRT_Panel_TestScript_Edit;
import windows.panels.teststep.BIRT_Panel_TestStep_Create;
import actionlistener.BIRT_ActionListener;
import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Panels;
import comm.BIRT_Persistence;
import comm.BIRT_Resources;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;

public class BIRT_ActionListener_TestScript_Edit implements BIRT_ActionListener
{

	private BufferedReader	brReader;

	public void actionPerformed(ActionEvent e)
	{

		if (null != e)
		{
			try
			{
				if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_CANCEL))
				{
					BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
				}
				else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_SAVE))
				{
					BIRT_Panel_TestScript_Edit objBiPanel_TestScript_Edit = BIRT_Panels.getPanelBIRT_TestScript_Edit();
					if (objBiPanel_TestScript_Edit.ensureFieldsProper())
					{
						if (!BIRT_DataObject.getApp_Panel_TestScript_Browse_SelectedTestScriptName().equals(objBiPanel_TestScript_Edit.getTestScriptName()))
						{
							if (BIRT_Persistence.getObjBIPersistence_TestScript().isTestScriptNameDuplicate(objBiPanel_TestScript_Edit.getTestScriptName()))
							{
								BIRT_ExceptionHandler.handleError(new BIRT_Temp_Exception("TestScript Name already exists. Please change the TestScript Name."));
							}
						}
						else
						{

							if (BIRT_Persistence.getObjBIPersistence_TestScript().editTestScriptNTestSteps(BIRT_DataObject.getIProjectID(),
									BIRT_DataObject.getApp_Panel_TestScript_Browse_SelectedTestScriptID(), BIRT_Panels.getPanelBIRT_TestScript_Create().getTestScriptName(),
									BIRT_Panels.getPanelBIRT_TestScript_Create().getTestScriptDescription(), BIRT_DataObject.getTestScript_CreateTestSteps()))
								;
							{
								BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Test Script Saved Successfully");
							}
						}
					}

				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_CREATE_STEP)))
				{
					BIRT_Panels.getPanelBIRT_TestStep_Create();
					BIRT_Panel_TestStep_Create.setObjBIRT_ActionListener(this);
					BIRT_Panels.getPanelBIRT_TestStep_Create().updateContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestStep_Create());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSTEP_CREATE_CANCELSTEP)))
				{
					BIRT_Panels.getPanelBIRT_TestStep_Create().resetContents();
					BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_Edit());
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSTEP_CREATE_CREATESTEP)))
				{
					if (BIRT_Panels.getPanelBIRT_TestStep_Create().ensureFieldsProper())
					{
						Object[] objTestStep = new Object[BIRT_DataObject.TESTSTEP_OBJECT_TOTALSIZE];
						objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO] = new Integer(BIRT_Panels.getPanelBIRT_TestStep_Create().getSequenceNo());
						objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND] = BIRT_Panels.getPanelBIRT_TestStep_Create().getCommand();
						objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD] = BIRT_Panels.getPanelBIRT_TestStep_Create().getMethod();
						objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET] = BIRT_Panels.getPanelBIRT_TestStep_Create().getTarget();
						objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE] = BIRT_Panels.getPanelBIRT_TestStep_Create().getValue();

						BIRT_DataObject.addTestScript_CreateTestStep(objTestStep);

						BIRT_Panels.getPanelBIRT_TestStep_Create().resetContents();
						BIRT_Panels.getPanelBIRT_TestScript_Edit().updateContents();
						BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_Edit());
					}
				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_IMPORT)))
				{
					FileDialog objFileDialog = new FileDialog();
					int iSelectedState = objFileDialog.showOpenDialog(BIRT_DataObject.FILES_ONLY, BIRT_DataObject.SINGLE_FILE, BIRT_DataObject.FD_TESTSCRIPT_FILE, BIRT_DataObject.FE_TXT, BIRT_Windows
							.getObjMainWindow(), BIRT_DataObject.FDBT_SELECT);
					if (iSelectedState == JFileChooser.APPROVE_OPTION)
					{
						try
						{

							BIRT_DataObject.destroyTestScript_TestSteps();

							String sCurrentLine;
							brReader = new BufferedReader(new FileReader(objFileDialog.getFileSelected().getAbsolutePath()));
							while ((sCurrentLine = brReader.readLine()) != null)
							{
								String[] strFields = sCurrentLine.split("\t");
								Object[] objTestStep = new Object[BIRT_DataObject.TESTSTEP_OBJECT_TOTALSIZE];

								objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO] = new Integer(Integer.parseInt(strFields[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO]));

								objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND] = strFields[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND];

								if (strFields.length > BIRT_DataObject.TESTSTEP_OBJECT_METHOD)
									objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD] = strFields[BIRT_DataObject.TESTSTEP_OBJECT_METHOD];
								else objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD] = new String(BIRT_DataObject.NULL);

								if (strFields.length > BIRT_DataObject.TESTSTEP_OBJECT_TARGET)
									objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET] = strFields[BIRT_DataObject.TESTSTEP_OBJECT_TARGET];
								else objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET] = new String(BIRT_DataObject.NULL);

								if (strFields.length > BIRT_DataObject.TESTSTEP_OBJECT_VALUE)
									objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE] = strFields[BIRT_DataObject.TESTSTEP_OBJECT_VALUE];
								else objTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE] = new String(BIRT_DataObject.NULL);

								BIRT_DataObject.addTestScript_CreateTestStep(objTestStep);

							}

							BIRT_Panels.getPanelBIRT_TestStep_Create().resetContents();
							BIRT_Panels.getPanelBIRT_TestScript_Edit().updateContents();
							BIRT_Windows.getObjMainWindow().resetNAdd(BIRT_Panels.getPanelBIRT_TestScript_Edit());

						}
						catch (IOException exx)
						{if(BIRT_AppProperty.PRINT_STACK_TRACE)
							BIRT_Logger.printStackTrace(exx);
							BIRT_Logger.error(exx.getMessage());
							BIRT_ExceptionHandler.handleFatalError(new BIRT_Temp_Exception("Invalid entry in the script file"));
						}

					}

				}
				else if ((e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSCRIPT_EDIT_EXPORT)))
				{
					FileDialog objFileDialog = new FileDialog();
					int iSelectedState = objFileDialog.showOpenDialog(BIRT_DataObject.DIRECTORIES_ONLY, BIRT_DataObject.SINGLE_FILE, BIRT_DataObject.FD_TESTSCRIPT_EXPORT_DIR, BIRT_DataObject.FE_TXT,
							BIRT_Windows.getObjMainWindow(), BIRT_DataObject.FDBT_SELECT);
					if (iSelectedState == JFileChooser.APPROVE_OPTION)
					{

						try
						{

							File file = new File(objFileDialog.getFileSelected().getAbsolutePath() + "\\" + "Exported_TestScript.txt");

							if (!file.exists())
							{
								file.createNewFile();
							}

							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);

							Object objTestScript_CreateTestSteps[][] = BIRT_DataObject.getTestScript_CreateTestSteps();
							int objArraySize = objTestScript_CreateTestSteps.length;

							for (int iCtr = 0; iCtr < objArraySize; iCtr++)
							{
								bw.write("" + (objTestScript_CreateTestSteps[iCtr][BIRT_DataObject.TESTSTEP_OBJECT_SEQNO]));
								bw.write("\t");
								bw.write((String) objTestScript_CreateTestSteps[iCtr][BIRT_DataObject.TESTSTEP_OBJECT_COMMAND]);
								bw.write("\t");
								bw.write((String) objTestScript_CreateTestSteps[iCtr][BIRT_DataObject.TESTSTEP_OBJECT_METHOD]);
								bw.write("\t");
								bw.write((String) objTestScript_CreateTestSteps[iCtr][BIRT_DataObject.TESTSTEP_OBJECT_TARGET]);
								bw.write("\t");
								bw.write((String) objTestScript_CreateTestSteps[iCtr][BIRT_DataObject.TESTSTEP_OBJECT_VALUE]);
								bw.write("\n");
							}

							bw.flush();
							bw.close();

							JOptionPane.showMessageDialog(null, "Test Script exported successfully", BIRT_Resources.getRbAppResourceBundle().getString(
									BIRT_Resource_PropertyNames.TXT_APP_WINDOWS_GENERAL_SUCCESS), JOptionPane.INFORMATION_MESSAGE);

						}
						catch (IOException exp)
						{if(BIRT_AppProperty.PRINT_STACK_TRACE)
							BIRT_Logger.printStackTrace(exp);
							BIRT_Logger.error(exp.getMessage());
							BIRT_ExceptionHandler.handleFatalError(new BIRT_Temp_Exception("Unable to export file. Please try again later."));
						}

					}

				}

			}
			catch (BIRT_Exception ex)
			{if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
				BIRT_Logger.error(ex.getMessage());
				BIRT_ExceptionHandler.handleFatalError(ex);
			}

		}
	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}

}
