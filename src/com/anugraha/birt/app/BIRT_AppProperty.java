package com.anugraha.birt.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import logger.BIRT_Logger;

public class BIRT_AppProperty
{
	// Properties inside the app

	public static boolean			PRINT_STACK_TRACE							= true;

	
	private static Properties		prpAppProperty								= null;

	// App_Property_File
	private final static String		APP_PROPERTY_FILE							= "./rsc/properties/app/AppProperty.xml";

	public final static String		REPORT_TEMPLATE_FILE						= "./rsc/template/Auto_ETL_Tester.xls";
	public final static String		REPORT_EXTN									= "xls";
		
	public final static String		PROP_RESOURCE_BUNDLE						= "App_Resource_Bundle";
	public final static String		PROP_PERSISTENCE_MEDIA						= "App_Persistence_Media";

	public final static String		PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME	= "App_Persistence_Media_DB_MySQL_Username";
	public final static String		PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD	= "App_Persistence_Media_DB_MySQL_Password";
	public final static String		PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER	= "App_Persistence_Media_DB_MySQL_Connection_Server";
	public final static String		PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT	= "App_Persistence_Media_DB_MySQL_Connection_Port";
	public final static String		PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME		= "App_Persistence_Media_DB_MySQL_DB_Name";

	public final static String		PROP_FIREFOX_EXE_LOCATION					= "Firefox.exe Location";
	public final static String		PROP_FIREFOX_PROFILE_DIR					= "Firefox Profile Dir";
	public final static String		PROP_IEDRIVER_EXE_LOCATION					= "IEDriver.exe Location";
	public final static String		PROP_REPORT_DOWNLOAD_DIR					= "Report Download Directory";
	public final static String		PROP_REPORT_ARCHIVE_DIR						= "Report Archive Directory";
	public final static String		PROP_PRINT_STACK_TRACE						= "PrintStackTrace";
	
	public final static String		PROP_SELENIUM_IMPLICIT_TIMEOUT				= "Selenium Implicit Timeout";
	public final static String		PROP_SELENIUM_TIME_UNIT						= "Selenium Implicit Time Unit";
	
	public static long		SELENIUM_IMPLICIT_TIMEOUT	= 1;
	public static TimeUnit	SELENIUM_TIME_UNIT			= TimeUnit.MINUTES;

	/*
	 * Can be XML, File, Database etc...
	 */
	public static final int			iPRJCT_REPO_DATABASE						= 0;
	public static final String[]	strProjectRepositories						=
																				{ "Database" };

	public static final int			iPERSISTENCE_MEDIA_DB_MYSQL					= 0;
	public static final String[]	PERSISTENCE_MEDIA_DB						=
																				{ "Persistence_DB_MySQL" };

	// Properties Listed

	private static void setProperties(ArrayList<String[]> objProperties)
	{
		if (null == prpAppProperty)
			initializeProperties();
		for (int iCtr = 0; iCtr < objProperties.size(); iCtr++)
		{
			String[] strTempProp = objProperties.get(iCtr);
			prpAppProperty.setProperty(strTempProp[0], strTempProp[1]);
		}
	}

	public static int setProperty(String strKey, String strValue)
	{
		if (null == prpAppProperty)
			initializeProperties();
		prpAppProperty.setProperty(strKey, strValue);
		return savePropertiesToXML();
	}

	public static int removeProperty(String strKey)
	{
		if (null == prpAppProperty)
			initializeProperties();
		prpAppProperty.remove(strKey);
		return savePropertiesToXML();
	}

	private static int savePropertiesToXML()
	{
		if (null == prpAppProperty)
			initializeProperties();

		if (null != prpAppProperty)
		{
			FileOutputStream fosPropertyXML;
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss:SS zzz");

				Date objDate = new Date(System.currentTimeMillis());
				fosPropertyXML = new FileOutputStream(APP_PROPERTY_FILE);
				prpAppProperty.storeToXML(fosPropertyXML, "Saving Property on :" + sdf.format(objDate));
				fosPropertyXML.flush();
				fosPropertyXML.close();
				return 1;

			}
			catch (FileNotFoundException e)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				return -1;
			}
			catch (IOException e)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				return -1;
			}

		}
		return -1;
	}

	public static int saveProperties(ArrayList<String[]> objProperties)
	{
		if (null == prpAppProperty)
			initializeProperties();
		setProperties(objProperties);

		return savePropertiesToXML();
	}

	public static void initializeProperties()
	{
		try
		{
			if (null == prpAppProperty)
			{
				prpAppProperty = new Properties();
				File fProperty = new File(APP_PROPERTY_FILE);
				if (!fProperty.exists())
					fProperty.createNewFile();
				prpAppProperty.loadFromXML(new FileInputStream(fProperty));
				// prpAppProperty.list(System.out);
			}
		}
		catch (FileNotFoundException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
		}
		catch (IOException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
		}

	}

	public static String getProperty(String strPropertyKey)
	{
		if (null == prpAppProperty)
			initializeProperties();
		return prpAppProperty.getProperty(strPropertyKey);
	}

}
