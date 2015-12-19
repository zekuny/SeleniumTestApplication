package exception;

import logger.BIRT_Logger;

import comm.BIRT_Windows;

public class BIRT_ExceptionHandler
{

	public static void handleError(BIRT_Exception objBIRT_Exception)
	{
		notifyUserOfError(objBIRT_Exception);
		logError(objBIRT_Exception);
	}

	public static void handleFatalError(BIRT_Exception objBIRT_Exception)
	{
		notifyUserOfFatalError(objBIRT_Exception);
		logFatalError(objBIRT_Exception);
	}

	private static void notifyUserOfFatalError(BIRT_Exception objBIRT_Exception)
	{
		BIRT_Windows.getObjMainWindow().notifyUserOfFatalError(objBIRT_Exception.getDisplayableMessage());

	}

	private static void logFatalError(BIRT_Exception objBIRT_Exception)
	{
		BIRT_Logger.fatal(objBIRT_Exception.getDisplayableMessage());

	}

	private static void logError(BIRT_Exception objBIRT_Exception)
	{
		BIRT_Logger.error(objBIRT_Exception.getDisplayableMessage());

	}

	private static void notifyUserOfError(BIRT_Exception objBIRT_Exception)
	{
		BIRT_Windows.getObjMainWindow().notifyUserOfError(objBIRT_Exception.getDisplayableMessage());
	}
}
