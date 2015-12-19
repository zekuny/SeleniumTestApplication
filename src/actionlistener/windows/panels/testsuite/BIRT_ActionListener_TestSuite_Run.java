package actionlistener.windows.panels.testsuite;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import reportObject.Report;
import reportObject.ReportManager;
import reportObject.Wrapper;
import logger.BIRT_Logger;
import utility.process.ProcessControl;
import actionlistener.BIRT_ActionListener;

import com.anugraha.birt.app.BIRT_AppDriver;
import com.anugraha.birt.app.BIRT_AppDriver_Parameter;
import com.anugraha.birt.app.BIRT_AppDriver_Parameter2;
import com.anugraha.birt.app.BIRT_AppProperty;

import comm.BIRT_ActionListeners;
import comm.BIRT_DataObject;
import comm.BIRT_Windows;
import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;

public class BIRT_ActionListener_TestSuite_Run implements BIRT_ActionListener
{
	// second
	// private int count = 0;
	// private final int totalAddSteps = 10;

	public void actionPerformed(ActionEvent e)
	{
		if (null != e)
		{

			if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_RUN_CANCEL))
			{
				BIRT_ActionListeners.getObjActionListener_MainWindow().actionPerformed(createDelegateActionEventObject(e, BIRT_ActionListener.APP_WINDOWS_FRAMES_MAINWINDOW_CANCEL));
			}
			else if (e.getActionCommand().equals(BIRT_ActionListener.APP_WINDOWS_PANELS_TESTSUITE_RUN_RUN))
			{
				BIRT_DataObject.setIExecute_TestSuiteID(BIRT_DataObject.getApp_Panel_TestSuite_BrowseRunNViewHstry_SelectedTestSuiteID());
				// BIRT_AppDriver objAppDriver = new BIRT_AppDriver();
				// Zekun_change
				BIRT_AppDriver_Parameter2 objAppDriver = new BIRT_AppDriver_Parameter2();
				
				
				// *******Zekun-start*******
				// Zekun_change
				// Read parameters
				// String fileName = "addSteps.txt";
				
				
				String fileName = null;
				System.out.println("Test Suite ID: " + BIRT_DataObject.getIExecute_TestSuiteID());
				try {
					fileName = BIRT_DataObject.getObjFileName(BIRT_DataObject.getIExecute_TestSuiteID());
				} catch (BIRT_Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				// last
				// fileName = "addSteps2.txt";
				
				System.out.println(fileName);
				Wrapper wp = processInputFile(fileName);
				
				printWrapper(wp);
				//int testCaseID = iExecuteTestCases[0];
				// *******zekun-end*******
				int loop = wp.getQueryList().size();
				System.out.println("Size is: " + loop);
				try
				{
					// System.out.println("Begin running test cases...");
					boolean isSuccess = true;
					int total = 0;
					int passed = 0;
					int failed = 0;
					String[] reportFolders = new String[loop];
					int index = 0;
					// second
					//for(int i = 0; i < 1; i++){
					for(int i = 0; i < loop; i++){
						boolean tmp = objAppDriver.runTestSuite(BIRT_DataObject.getIProjectID(), BIRT_DataObject.getIExecute_TestSuiteID(), wp.getStepList().get(i), wp.getQueryList().get(i));
						if(!tmp){
							isSuccess = false;
						}
						total += BIRT_DataObject.getiTotalTestCases();
						passed += BIRT_DataObject.getiTestCasePassed();
						failed += BIRT_DataObject.getiTestCaseFailed();
						reportFolders[index++] = BIRT_DataObject.getStrCurrentTestSuiteTimeStampFolder();
					}
					// Zekun_change
					//boolean isSuccess = objAppDriver.runTestSuite(BIRT_DataObject.getIProjectID(), BIRT_DataObject.getIExecute_TestSuiteID());
						
					ProcessControl.killExcel();
					System.out.println("not reach");
					// ProcessControl.killIEDriverServer();
					String tmp = "";
					for(int i = 0; i < loop; i++){
						tmp += reportFolders[i] + "\t\n";
					}
					tmp = tmp.trim();
					if (isSuccess){
						System.out.println("Test Suite run successful.\n No. of Total TestCases Executed: "+total+"\n No. of Test Cases Passed: "+passed+"\n No. of Test Cases Failed: "+failed+"\n Please find report at: " + tmp);
						//System.out.println("Test Suite run successful.\n No. of Total TestCases Executed: "+BIRT_DataObject.getiTotalTestCases()+"\n No. of Test Cases Passed: "+BIRT_DataObject.getiTestCasePassed()+"\n No. of Test Cases Failed: "+BIRT_DataObject.getiTestCaseFailed()+"\n Please find report at " + BIRT_DataObject.getStrCurrentTestSuiteTimeStampFolder());
						//BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Test Suite run successful.\n No. of Total TestCases Executed: "+BIRT_DataObject.getiTotalTestCases()+"\n No. of Test Cases Passed: "+BIRT_DataObject.getiTestCasePassed()+"\n No. of Test Cases Failed: "+BIRT_DataObject.getiTestCaseFailed()+"\n Please find report at " + BIRT_DataObject.getStrCurrentTestSuiteTimeStampFolder());
						BIRT_Windows.getObjMainWindow().notifyUserOfSuccess("Test Suite run successful.\n No. of Total TestCases Executed: "+total+"\n No. of Test Cases Passed: "+passed+"\n No. of Test Cases Failed: "+failed+"\n Please find report at: " + tmp);
					}else{
						System.out.println("not all success...");
						BIRT_Windows.getObjMainWindow().notifyUserOfFatalError("Test Suite run successful.\n No. of Total TestCases Executed: "+total+"\n No. of Test Cases Passed: "+passed+"\n No. of Test Cases Failed: "+failed+"\n Please find report at: " + tmp);
					}
					//Runtime.getRuntime().exec("explorer.exe " + (BIRT_DataObject.getStrCurrentTestSuiteTimeStampFolder()).replaceAll("/", "\\\\"));
					System.out.println("Almost done...");
					BIRT_DataObject.cleanUpAfterTestSuite();
					System.out.println("All Finish.");
				}
				catch (BIRT_Exception e1)
				{if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e1);
					BIRT_Logger.error(e1.getMessage());
					BIRT_ExceptionHandler.handleFatalError(e1);
				}
				catch (Exception ex)
				{if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(ex);
					BIRT_Logger.error(ex.getMessage());
					BIRT_ExceptionHandler.handleFatalError(new BIRT_Temp_Exception("Runtime Exception. Application aborting."));
				}
			}
		}
	}

	private ActionEvent createDelegateActionEventObject(ActionEvent ex, String strActionCommand)
	{
		return new ActionEvent(ex, 0, strActionCommand);
	}
	
	private Wrapper processInputFile(String fileName){
		String line = null;
		// Save all steps in the ArrayList 
		ArrayList<Object[]> stepList = null;
		
		ArrayList<String> queryList = null;
		// int count = 0;
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			ReportManager rm = new ReportManager();
			Report report = rm.getReport(line);
			
			int start = 15;
			int index = 0;
			String[] click = report.getClick();
			String runXpath = report.getRunXpath();
			String timeXpath = report.getTimeXpath();
			
			stepList = new ArrayList<Object[]>();
			queryList = new ArrayList<String>();
			int pic = 0;

			line = bufferedReader.readLine();
			while(line != null){
				// int[] tmpCount = new int[total];
				// each Object corresponds to an Object[]
				ArrayList<Object> tmpStep = new ArrayList<Object>();
				ArrayList<String> addTime = new ArrayList<String>();
				
				System.out.println("The query is: " + line);
				System.out.println("The qeury list size is: " + queryList.size());
				queryList.add(line);
				
				line = bufferedReader.readLine();
				// boolean first = true;
				// MapReduce
				for(HashSet<String> set : report.getList()){
					int tmp = 0;
					while(set.contains(line)){
						tmp++;
						// the 5 here is different from the other 5
						// line = whitespace(line);
						String tline = whitespace(line);
//						if(first){
//							line = "//div[contains(text(),'" + line + "')]";
//						}else{
//							// line = "//div[contains(text(),'" + line + "')]";
//							// line = "//div[normalize-space(text())=normalize-space('" + line + "')]";
//							line = tmpxpath;
//						}
						Object[] objSingleTestStep = new Object[5];
						objSingleTestStep[0] = new Integer(tmp++);
						objSingleTestStep[1] = new String("Click");
						objSingleTestStep[2] = new String("XPATH");
						objSingleTestStep[3] = new String(tline);
						objSingleTestStep[4] = new String("");	
						tmpStep.add(objSingleTestStep);
						
						objSingleTestStep = new Object[5];
						objSingleTestStep[0] = new Integer(tmp++);
						objSingleTestStep[1] = new String("Click");
						objSingleTestStep[2] = new String("XPATH");
						objSingleTestStep[3] = new String(click[pic]);
						objSingleTestStep[4] = new String("");	
						tmpStep.add(objSingleTestStep);	
						
						// only one
						if(line.equals("Plan Ship Cancel Date Custom Selection")){
							line = bufferedReader.readLine();
							addTime.add(line);
							line = bufferedReader.readLine();
							addTime.add(line);
						}
						
						line = bufferedReader.readLine();
					}
					
					if(tmp == 0){
						Object[] objSingleTestStep = new Object[5];
						objSingleTestStep[0] = new Integer(tmp++);
						objSingleTestStep[1] = new String("Wait (in ms)");
						objSingleTestStep[2] = new String("");
						objSingleTestStep[3] = new String("");
						objSingleTestStep[4] = new String("10");	
						tmpStep.add(objSingleTestStep);
								
						objSingleTestStep = new Object[5];
						objSingleTestStep[0] = new Integer(tmp++);
						objSingleTestStep[1] = new String("Wait (in ms)");
						objSingleTestStep[2] = new String("");
						objSingleTestStep[3] = new String("");
						objSingleTestStep[4] = new String("10");	
						tmpStep.add(objSingleTestStep);
						line = bufferedReader.readLine();
					}
					// 加click image
					pic++;
					// first = false;
					// tmpCount[index++] = tmp;
				}
				
				// add "run report"
				appendent(tmpStep, addTime, runXpath, timeXpath);
				stepList.add(listToArray(tmpStep));
				// countList.add(tmpCount);
				// index = 0;
				pic = 0;
			}
			bufferedReader.close();
		}catch(FileNotFoundException e1){
			// System.out.println("Unable to open file '" + fileName + "'"); 
			e1.printStackTrace();  
		}catch(IOException e1){
			e1.printStackTrace();
		}
		
		return new Wrapper(stepList, queryList);
	}
	private Object[] listToArray(ArrayList<Object> list){
		int length = list.size();
		Object[] tmp = new Object[length];
		
		for(int i = 0; i < length; i++){
			tmp[i] = list.get(i);
		}
		return tmp;
	}
	
	
	private void appendent(ArrayList<Object> tmpStep, ArrayList<String> addTime, String runXpath, String timeXpath){
		// add "run" button
		Object[] objSingleTestStep = new Object[5];
		objSingleTestStep[0] = new Integer(0);
		objSingleTestStep[1] = new String("Click");
		objSingleTestStep[2] = new String("XPATH");
		objSingleTestStep[3] = new String(runXpath);
		objSingleTestStep[4] = new String("");	
		tmpStep.add(objSingleTestStep);
		
		objSingleTestStep = new Object[5];
		objSingleTestStep[0] = new Integer(0);
		objSingleTestStep[1] = new String("Wait (in ms)");
		objSingleTestStep[2] = new String("");
		objSingleTestStep[3] = new String("");
		objSingleTestStep[4] = new String("1000");	
		tmpStep.add(objSingleTestStep);	
		
		// if there is one more page
		if(addTime.size() == 2){
			
			// ***some of the value need to be parameterized later***
			// say the start step and the end step
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(1);
			objSingleTestStep[1] = new String("Click");
			objSingleTestStep[2] = new String("XPATH");
			objSingleTestStep[3] = new String(".//*[@id='id_mstr28_radio_simpleAnswerView']");   // 明天早上给弄过来
			objSingleTestStep[4] = new String("");	
			tmpStep.add(objSingleTestStep);
	
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(2);
			objSingleTestStep[1] = new String("Clear");
			objSingleTestStep[2] = new String("ID");   
			objSingleTestStep[3] = new String("id_mstr32_txt");  
			objSingleTestStep[4] = new String("");	
			tmpStep.add(objSingleTestStep);
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(3);
			objSingleTestStep[1] = new String("Type");
			objSingleTestStep[2] = new String("ID");   
			objSingleTestStep[3] = new String("id_mstr32_txt");  
			objSingleTestStep[4] = new String(addTime.get(0));	
			tmpStep.add(objSingleTestStep);
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(4);
			objSingleTestStep[1] = new String("Click");
			objSingleTestStep[2] = new String("XPATH");
			objSingleTestStep[3] = new String(".//*[@id='id_mstr35_radio_simpleAnswerView']");  
			objSingleTestStep[4] = new String("");	
			tmpStep.add(objSingleTestStep);
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(5);
			objSingleTestStep[1] = new String("Clear");
			objSingleTestStep[2] = new String("ID");  
			objSingleTestStep[3] = new String("id_mstr39_txt");   
			objSingleTestStep[4] = new String("");	
			tmpStep.add(objSingleTestStep);
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(6);
			objSingleTestStep[1] = new String("Type");
			objSingleTestStep[2] = new String("ID");  
			objSingleTestStep[3] = new String("id_mstr39_txt");  
			objSingleTestStep[4] = new String(addTime.get(1));	
			tmpStep.add(objSingleTestStep);
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(7);
			objSingleTestStep[1] = new String("Click");
			objSingleTestStep[2] = new String("XPATH");
			objSingleTestStep[3] = new String(timeXpath);
			objSingleTestStep[4] = new String("");	
			tmpStep.add(objSingleTestStep);
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(8);
			objSingleTestStep[1] = new String("Wait (in ms)");
			objSingleTestStep[2] = new String("");
			objSingleTestStep[3] = new String("");
			objSingleTestStep[4] = new String("1000");	
			tmpStep.add(objSingleTestStep);
			
			// switch to "home" tab
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(9);
			objSingleTestStep[1] = new String("Click");
			objSingleTestStep[2] = new String("XPATH");
			objSingleTestStep[3] = new String(".//*[@id='ribbonToolbarTabsListContainer']/div[1]/table/tbody/tr/td[2]/div");
			objSingleTestStep[4] = new String("");	
			tmpStep.add(objSingleTestStep);
			
			objSingleTestStep = new Object[5];
			objSingleTestStep[0] = new Integer(10);
			objSingleTestStep[1] = new String("Wait (in ms)");
			objSingleTestStep[2] = new String("");
			objSingleTestStep[3] = new String("");
			objSingleTestStep[4] = new String("100");	
			tmpStep.add(objSingleTestStep);
		}
	}
	
	private void printWrapper(Wrapper wp){
		ArrayList<Object[]> list = wp.getStepList();
		for(Object[] o : list){
			System.out.println("--------------Start---------------");
			int m = o.length;
			for(int i = 0; i < m; i++){
				Object[] tmp = (Object[]) o[i];
				System.out.print(tmp[0]);
				System.out.print(tmp[1]);
				System.out.print(tmp[2]);
				System.out.print(tmp[3]);
				System.out.print(tmp[4]);
				System.out.println("");
			}
			
			System.out.println("--------------end---------------");
		}
	}
	
	private String whitespace(String s){
		String[] tmp = s.split("\\s+");
		if(tmp.length <= 1){
			return "//div[contains(text(),'" + s + "')]";
		}
		int length = tmp.length;
		StringBuilder sb = new StringBuilder();
		sb.append("//div[contains(text(),'");
		for(int i = 0; i < length - 1; i++){
			sb.append(tmp[i]);
			sb.append("') and contains(text(),'");
		}
		sb.append(tmp[length - 1]);
		sb.append("')]");
		return sb.toString();
	}
	
	//"//div[contains(text(),'" + line + "')]"
}
