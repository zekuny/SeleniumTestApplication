package utility.file.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*import static com4j.Variant.MISSING;

import com4j.Variant;

import excel._Application;
import excel._Workbook;
*/
public class ExcelMacroRun
{
	public static void runMacroInWorkbook(String strExcelWorkBookPath){
		//extracting file name from string strExcelWorkBookPath
		 String re1=".*?";
		 String re2="((?:[a-z][a-z\\.\\d_]+)\\.(?:[a-z\\d]{3}))(?![\\w\\.])";
 	     String filename="";
		 Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(strExcelWorkBookPath);
		    if (m.find())
		    {
		        String file1=m.group(1);
		        filename="\""+file1.toString()+"!Sheet1.CommandButton1_Click\"";
		        // System.out.print(filename);
		    }
		//Creating vb acript with dynamic file name
		try {
			        File statText = new File("/Users/zekuny/Desktop/BITester/vbscript.vbs");
			        FileWriter fw = new FileWriter(statText, false);
			        BufferedWriter bw = new BufferedWriter(fw);
			        String str = "";
			        str = "Option Explicit\n"+
			        	"On Error Resume Next\n"+
			        	"ExcelMacroExample\n"+
			        	"Sub ExcelMacroExample()\n"+
			        	"Dim xlApp\n"+
			        	"Dim xlBook\n"+
			        	"Set xlApp = CreateObject(\"Excel.Application\")\n"+
			        	"Set xlBook = xlApp.Workbooks.Open(\""+strExcelWorkBookPath+"\", 0, False)\n"+ 
			        	"xlApp.Run \"Sheet1.CommandButton1_Click\"\n"+
			        	"xlBook.Save\n"+
			        	"xlApp.Quit\n"+
			        	"Set xlBook = Nothing\n"+
			        	"Set xlApp = Nothing\n"+
			        	"End Sub";
			        bw.write(str);
			        /*str ="Option Explicit\n"+
					        	"Dim xlApp, xlBook\n"+
							    "dim fso\n"+
							    "dim curDir\n" +
							    "set fso = CreateObject(\"Scripting.FileSystemObject\")\n"+
							    "curDir = fso.GetAbsolutePathName(\".\")\n"+
							    "set fso = nothing\n"+
						        "Set xlApp = CreateObject(\"Excel.Application\")\n"+
							    //"Set xlBook = xlApp.Workbooks.Open("+strExcelWorkBookPath+", 0, true)\n"+
							    "Set xlBook = xlApp.Workbooks.Open("+strExcelWorkBookPath+", 0, false)\n"+
							    "xlApp.Run(\"Sheet1.CommandButton1_Click\",0)\n"+
							    "xlBook.Save\n"+
							    "xlBook.Close\n"+
							    "xlApp.Quit\n"+
							    "Set xlBook = Nothing\n"+
							    "Set xlApp = Nothing\n"+
							    "WScript.Echo \"Finished.\"\n";
			        bw.write(str);*/
			        /*
			        FileOutputStream is = new FileOutputStream(statText);
			        OutputStreamWriter osw = new OutputStreamWriter(is);    
			        Writer w = new BufferedWriter(osw);
			        w.write("Option Explicit \n"
			        w.write("Dim xlApp, xlBook \n"
			        w.write("dim fso\n"
			        w.write("dim curDir\n"
			        w.write("set fso = CreateObject(\"Scripting.FileSystemObject\")\n"
			        w.write("curDir = fso.GetAbsolutePathName(\".\")\n"
			        w.write("set fso = nothing\n"

			        w.write("Set xlApp = CreateObject(\"Excel.Application\")\n"
			        w.write("Set xlBook = xlApp.Workbooks.Open("+strExcelWorkBookPath+", 0, true)\n"
			        w.write("xlApp.Run \"Sheet1.CommandButton1_Click\"\n"
			        w.write("xlBook.Save\n"
			        w.write("xlBook.Close\n"
			        w.write("xlApp.Quit\n"

			        w.write("Set xlBook = Nothing\n"
			        w.write("Set xlApp = Nothing\n"

			        w.write("WScript.Echo \"Finished.\"\n");*/
			        bw.close();
			        
			    } catch (IOException e) {
			        System.err.println("Problem writing to the batch file at ExcelMacroRun.runMacroInWorkbook()"+e.getMessage());
			        e.printStackTrace();
			    }
		//creating batch file
		
		try {
			File statText = new File("/Users/zekuny/Desktop/BITester/batch.bat");
	        FileWriter fw = new FileWriter(statText, false);
	        BufferedWriter bw = new BufferedWriter(fw);
	        String str = "";
	        str = "wscript \"/Users/zekuny/Desktop/BITester/vbscript.vbs\"\n"+
	        		" exit 0\n";
	        bw.write(str);
	        bw.close();
		} catch (IOException ex) {
			// System.out.println("problem writing batch file ExcelMacroRun.runMacroInWorkbook()");
			ex.printStackTrace();
		}
		
		try {
			
		// Zekun_change
		// Remember to change the path...
		//Process pr = Runtime.getRuntime().exec("c:\\BITester\\batch.bat");
		Process pr = Runtime.getRuntime().exec("/Users/zekuny/Desktop/BITester/batch.bat");
		pr.waitFor();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/*public static void runMacroInWorkbook(String strExcelWorkBookPath)
	{
		// Creating App variable
		BRTLogger.objLogger.info("Starting to invoke Excel Application");

		_Application app = excel.ClassFactory.createApplication();
		app.visible(0, false);
		Variant saveBeforeExit = new Variant(Variant.Type.VT_BOOL);
		saveBeforeExit.set(1);

		// Opening workbook
		BRTLogger.objLogger.info("Opening workbook");

		_Workbook wb1 = app.workbooks().open(strExcelWorkBookPath, 0, false, 5, "", "", true, true, MISSING, false, false, MISSING, MISSING, MISSING, MISSING, 0);
		Variant macroName = new Variant(Variant.Type.VT_BSTR);

		// Selecting macro
		BRTLogger.objLogger.info("Selecting Macro");
		macroName.set("Sheet1.CommandButton1_Click");
		wb1.application().run(macroName, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null);

		// Macro Run
		BRTLogger.objLogger.info("Macro Run");

		// Saving and quit App
		wb1.close(saveBeforeExit, MISSING, MISSING, 0);

		app.quit();

		BRTLogger.objLogger.info("Workbook saved and quit Excel");

	}
	*/

	
	
}
