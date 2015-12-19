package framework.dbconnection.driver.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.anugraha.birt.app.BIRT_AppProperty;

import resources.resourcebundle.BIRT_Resource_PropertyNames;
import comm.BIRT_Resources;
import logger.BIRT_Logger;
import exception.BIRT_Exception;
import exception.persistence.db.mysql.BIRT_MySQLInstantiationException;
import framework.dbconnection.driver.BIRT_DBConnectionDriver;



public class BIRT_DBConnectionDriver_MS_SQL implements BIRT_DBConnectionDriver
{
	
	
		protected final static String	SINGLE_QUOTE	= "'";

		public Connection getConnection(Object[] oParameters) throws BIRT_Exception
		{
		//	String strDBConnectionServer = (String) oParameters[DBCONNECTION_CONNECTION_SERVER];
		//	Integer strDBConnectionPort = (Integer) oParameters[DBCONNECTION_CONNECTION_PORT];
			String strDBUserName = (String) oParameters[DBCONNECTION_CONNECTION_USERNAME];
			String strDBPassword = (String) oParameters[DBCONNECTION_CONNECTION_PASSWORD];
		//	String strDBDatabase = (String) oParameters[DBCONNECTION_CONNECTION_DATABASE];
			


			
			//String strConnectionURL = "jdbc:sqlserver://" + strDBConnectionServer + ":" + strDBConnectionPort + ";databaseName=" + strDBDatabase+";";
			String strConnectionURL=(String) oParameters[DBCONNECTION_CONNECTION_STRING];
			System.err.println(strConnectionURL);
			
			System.out.println(strConnectionURL);
			try
			{
				Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance ();
			}
			catch (InstantiationException e)
			{

				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));

			}
			catch (IllegalAccessException e)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
			}
			catch (ClassNotFoundException e)
			{

				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
			}
			try
			{
				return DriverManager.getConnection(strConnectionURL, strDBUserName, strDBPassword);
			}
			catch (SQLException e)
			{

				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
			}

		}

	}

	

	

