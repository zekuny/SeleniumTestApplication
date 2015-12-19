package logger;

import java.util.Date;

import org.apache.log4j.Logger;

public class BIRT_Logger
{
	private static Logger	objLogger	= null;

	private static final void getObjLogger()
	{
		objLogger = Logger.getRootLogger();
	}

	public static final void debug(String strMessage)
	{
		if (objLogger == null)
			getObjLogger();
		objLogger.debug((new Date()) + " : " + strMessage);
	}

	public static final void info(String strMessage)
	{
		if (objLogger == null)
			getObjLogger();
		objLogger.info((new Date()) + " : " + strMessage);
	}

	public static final void fatal(String strMessage)
	{
		if (objLogger == null)
			getObjLogger();
		objLogger.fatal((new Date()) + " : " + strMessage);
	}

	public static final void error(String strMessage)
	{
		if (objLogger == null)
			getObjLogger();
		objLogger.error((new Date()) + " : " + strMessage);
	}

public static final void printStackTrace(Exception ex)
{
	StackTraceElement[] objStackTrace= ex.getStackTrace();
	int iLength=objStackTrace.length;
	for(int iCtr=0;iCtr<iLength;iCtr++)
	{
		error(objStackTrace[iCtr].getFileName()+":"+objStackTrace[iCtr].getClassName()+":"+objStackTrace[iCtr].getMethodName()+":"+objStackTrace[iCtr].getLineNumber());
	}
}

}
