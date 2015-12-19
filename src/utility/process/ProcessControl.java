package utility.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessControl
{
    
    private static final String TASKLIST = "tasklist";
    private static final String KILL = "taskkill /f /t /IM ";
    private static final String EXCEL_SERVICE = "EXCEL.EXE";
    private static final String IE_DRIVER_SERVICE = "IEDriverServer.exe";

    public static void killExcel() throws Exception
    {
        if (isProcessRunning(EXCEL_SERVICE))
        {
            Runtime.getRuntime().exec(KILL+EXCEL_SERVICE);
        }
    }
    
    public static void killIEDriverServer() throws Exception
    {
        if (isProcessRunning(IE_DRIVER_SERVICE))
        {
            Runtime.getRuntime().exec(KILL+IE_DRIVER_SERVICE);
        }
    }
    

    private static boolean isProcessRunning(String serviceName) throws Exception
    {
    	// Zekun_change
    	Process p = Runtime.getRuntime().exec("ps -e");
        // Process p = Runtime.getRuntime().exec(TASKLIST);
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
        {
            if (line.contains(serviceName))
            {
                return true;
            }
        }
        return false;
    }
}
