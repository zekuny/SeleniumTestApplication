package main;

import java.util.concurrent.TimeUnit;

import utility.process.ProcessControl;

import com.anugraha.birt.app.BIRT_AppProperty;

import logger.BIRT_Logger;
import comm.BIRT_DataObject;
import comm.BIRT_Windows;

public class Main {
	public static void main(String args[]) {
		try {
			// System.out.println("main1");
			BIRT_Logger.info("Starting Execution");
			BIRT_DataObject.setIProjectID(001);
			BIRT_Logger.debug("Project ID set to 1");
			BIRT_DataObject.setStrProjectName("Dummy");
			BIRT_Logger.debug("Project ID Name set to Dummy");
			BIRT_Windows.getObjMainWindow();
			BIRT_Logger.debug("Main Window Launched");

			BIRT_Logger
					.debug("Closing EXCEL and IEDriver Server previously opened");
			//ProcessControl.killExcel();
			//ProcessControl.killIEDriverServer();

			BIRT_Logger.debug("Checking for PrintStackTrace property");
			//String strIsPrintStackTrace = BIRT_AppProperty
					//.getProperty(BIRT_AppProperty.PROP_PRINT_STACK_TRACE);
//			if (strIsPrintStackTrace.toUpperCase().equals("TRUE")) {
//				System.out.println("main2");
//				BIRT_AppProperty.PRINT_STACK_TRACE = true;
//				BIRT_Logger
//						.debug("Print Stack Trace set to true as per App Property File");
//			} else if (strIsPrintStackTrace.toUpperCase().equals("FALSE")) {
//				System.out.println("main3");
//				BIRT_AppProperty.PRINT_STACK_TRACE = false;
//				BIRT_Logger
//						.debug("Print Stack Trace set to false as per App Property File");
//			} else {
//				System.out.println("main4");
//				BIRT_AppProperty.PRINT_STACK_TRACE = false;
//				BIRT_Logger.debug("Print Stack Trace defaulted to false.");
//			}
			BIRT_Logger
					.debug("Checking for Selenium Implicit Timeout property");

			String strImplicitTimeout = BIRT_AppProperty
					.getProperty(BIRT_AppProperty.PROP_SELENIUM_IMPLICIT_TIMEOUT);

			String strImplicitTimeUnit = BIRT_AppProperty
					.getProperty(BIRT_AppProperty.PROP_SELENIUM_TIME_UNIT);


			try {
				int iTimeout = Integer.parseInt(strImplicitTimeout);
				BIRT_AppProperty.SELENIUM_IMPLICIT_TIMEOUT = iTimeout;
				BIRT_Logger.debug("Selenium Implicit Timeout set to "
						+ iTimeout);
			} catch (NumberFormatException ex) {
				if (BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(ex);
				BIRT_Logger.debug("Selenium Implicit Timeout defaulted to 1");
			}

			if (strImplicitTimeUnit.toUpperCase().equals("DAYS")) {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.DAYS;
				BIRT_Logger.debug("Selenium Implicit Time Unit set to DAYS");
			} else if (strImplicitTimeUnit.toUpperCase().equals("HOURS")) {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.HOURS;
				BIRT_Logger.debug("Selenium Implicit Time Unit set to HOURS");
			} else if (strImplicitTimeUnit.toUpperCase().equals("MINUTES")) {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.MINUTES;
				BIRT_Logger.debug("Selenium Implicit Time Unit set to MINUTES");
			} else if (strImplicitTimeUnit.toUpperCase().equals("SECONDS")) {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.SECONDS;
				BIRT_Logger.debug("Selenium Implicit Time Unit set to SECONDS");
			} else if (strImplicitTimeUnit.toUpperCase().equals("MICROSECONDS")) {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.MICROSECONDS;
				BIRT_Logger
						.debug("Selenium Implicit Time Unit set to MICROSECONDS");
			} else if (strImplicitTimeUnit.toUpperCase().equals("MILLISECONDS")) {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.MILLISECONDS;
				BIRT_Logger
						.debug("Selenium Implicit Time Unit set to MILLISECONDS");
			} else if (strImplicitTimeUnit.toUpperCase().equals("NANOSECONDS")) {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.NANOSECONDS;
				BIRT_Logger
						.debug("Selenium Implicit Time Unit set to NANOSECONDS");
			} else {
				BIRT_AppProperty.SELENIUM_TIME_UNIT = TimeUnit.MINUTES;
				BIRT_Logger
						.debug("Selenium Implicit Time Unit defaulted to MINUTES");
			}

		} catch (Exception ex) {
			if (BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
		}
	}

}
