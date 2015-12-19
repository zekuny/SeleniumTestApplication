package persistence.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logger.BIRT_Logger;
import persistence.db.BIRT_Persistence_DBConnection_DB;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import utility.string.StringUtility;

import com.anugraha.birt.app.BIRT_AppProperty;
/*import comm.BIRT_DataObject;*/
import comm.BIRT_Resources;

import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import exception.persistence.BIRT_ImproperQueryUpdateException;
import exception.persistence.db.mysql.BIRT_MySQLInstantiationException;
import framework.dbconnection.driver.BIRT_DBConnectionDriver;

public class BIRT_Persistence_DBConnection_DB_MySQL extends BIRT_Persistence_DBConnection_DB
{

	private final static String	strDeleteDBConnection		= "DELETE FROM BIRT_DBConnection WHERE ProjectID = ? AND DBConnection_ID = ?";
	private final static String	strGetDBConnectionName		= "SELECT DBConnectionName FROM BIRT_DBConnection WHERE ProjectID = ? AND DBConnection_ID = ?";
	// Divya : Modify insert connection query to add ConnectionString
	//private final static String	strInsertDBConnection		= "INSERT INTO BIRT_DBConnection (ProjectID, DBConnectionName, DBConnectionDescription, ConnectionType, DBServer, DBPort, Username, Password, DatabaseName, ServiceName) VALUES (?,?,?,?,?,?,?,?,?,?) ";
	private final static String	strInsertDBConnection		= "INSERT INTO BIRT_DBConnection (ProjectID, DBConnectionName, DBConnectionDescription, ConnectionType, DBConnectionString, Username, Password) VALUES (?,?,?,?,?,?,?) ";
	private final static String	strDBConnectionDescriptions	= "SELECT DBConnection_ID, DBConnectionName, DBConnectionDescription FROM BIRT_DBConnection WHERE ProjectID = ";

	//private final static String	strUpdateDBConnection		= "UPDATE BIRT_DBConnection SET DBConnectionName=?, DBConnectionDescription=?, ConnectionType=?, DBServer=?, DBPort=?, Username=?, Password=?, DatabaseName=?, ServiceName=? WHERE ProjectID=? AND DBConnection_ID = ? ";
	private final static String	strUpdateDBConnection		= "UPDATE BIRT_DBConnection SET DBConnectionName=?, DBConnectionDescription=?, ConnectionType=?, DBConnectionString=?, Username=?, Password=? WHERE ProjectID=? AND DBConnection_ID = ? ";

	//private final static String	strGetDBConnectionDetail	= "SELECT ConnectionType, DBServer, DBPort, Username, Password, DatabaseName, ServiceName, DBConnectionName,DBConnectionDescription  FROM BIRT_DBConnection WHERE DBConnection_ID = ";
	//Divya: Modifying to fetch connection string
	private final static String	strGetDBConnectionDetail	= "SELECT ConnectionType, DBConnectionString, Username, Password, DBConnectionName,DBConnectionDescription  FROM BIRT_DBConnection WHERE DBConnection_ID = ";
	private final static String	strIsDuplicateDBConnName	= "SELECT 1 FROM BIRT_DBConnection WHERE DBConnectionName = ";

	public BIRT_Persistence_DBConnection_DB_MySQL() throws BIRT_Exception
	{
		super();

	}

	protected void initializeParameters()
	{
		BIRT_Persistence_DBConnection_DB_MySQL.strUserName = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME);
		BIRT_Persistence_DBConnection_DB_MySQL.strPassword = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD);
		BIRT_Persistence_DBConnection_DB_MySQL.strDBConnectionServer = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER);
		BIRT_Persistence_DBConnection_DB_MySQL.iDBConnectionPort = Integer.parseInt(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT));
		BIRT_Persistence_DBConnection_DB_MySQL.strDBDatabase = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME);
	}

	protected Connection intializeConnection() throws BIRT_MySQLInstantiationException
	{

		String strConnectionURL = "jdbc:mysql://" + strDBConnectionServer + ":" + iDBConnectionPort + "/" + strDBDatabase;

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
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
			// System.out.println("strConnectionURL" + strConnectionURL + " " +strUserName +  " " +strPassword);
			return DriverManager.getConnection(strConnectionURL, strUserName, strPassword);
			
		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

	}

	

	public boolean isDBConnectionNameDuplicate(String DBConnectionName) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strIsDuplicateDBConnName + SINGLE_QUOTE + DBConnectionName + SINGLE_QUOTE);

			return rsQueryResult.first();
		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

	}

	/*
	public boolean saveDBConnection(int ProjectID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String DBServer, int DBPort, String Username, String Password,
			String Database, String ServiceName) throws BIRT_Exception
	{
		/*
		 * INSERT INTO BIRT_DBConnection (ProjectID, DBConnectionName,
		 * DBConnectionDescription, ConnectionType, DBServer, DBPort, Username,
		 * Password, DatabaseName, ServiceName) VALUES (?,?,?,?,?,?,?,?,?,?) ";
		 */
	/*

		PreparedStatement psInsertDBConnection = null;

		try
		{
			psInsertDBConnection = getConnection().prepareStatement(strInsertDBConnection);
			psInsertDBConnection.setInt(1, ProjectID);
			psInsertDBConnection.setString(2, DBConnectionName);
			psInsertDBConnection.setString(3, DBConnectionDescription);
			psInsertDBConnection.setInt(4, ConnectionType);
			psInsertDBConnection.setString(5, DBServer);
			psInsertDBConnection.setInt(6, DBPort);
			psInsertDBConnection.setString(7, Username);
			psInsertDBConnection.setString(8, Password);
			if (Database.equals(BIRT_DataObject.NULL))
				psInsertDBConnection.setNull(9, java.sql.Types.VARCHAR);
			else psInsertDBConnection.setString(9, Database);
			if (ServiceName.equals(BIRT_DataObject.NULL))
				psInsertDBConnection.setNull(10, java.sql.Types.VARCHAR);
			else psInsertDBConnection.setString(10, ServiceName);

			System.out.println(strInsertDBConnection);
			if (psInsertDBConnection.executeUpdate() == 1)
				return true;

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}
		finally
		{
			if (psInsertDBConnection != null)
				try
				{
					psInsertDBConnection.close();
				}
				catch (SQLException e)
				{
					if(BIRT_AppProperty.PRINT_STACK_TRACE)
						BIRT_Logger.printStackTrace(e);
					BIRT_Logger.error(e.getMessage());
				}
		}

		return false;
	}
*/
	//Divya Modified method for saveDBConnection to save connection string
	public boolean saveDBConnection(int ProjectID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String connectionString, String Username, String Password,
			 String ServiceName) throws BIRT_Exception
	{
		/*
		 *INSERT INTO BIRT_DBConnection (ProjectID, DBConnectionName, DBConnectionDescription, 
		 *ConnectionType, DBConnectionString, Username, Password)
		 * VALUES (?,?,?,?,?,?,?) 
		 */

		PreparedStatement psInsertDBConnection = null;

		try
		{
			psInsertDBConnection = getConnection().prepareStatement(strInsertDBConnection);
			psInsertDBConnection.setInt(1, ProjectID);
			psInsertDBConnection.setString(2, DBConnectionName);
			psInsertDBConnection.setString(3, DBConnectionDescription);
			psInsertDBConnection.setInt(4, ConnectionType);
			//psInsertDBConnection.setString(5, DBServer);
			psInsertDBConnection.setString(5, connectionString);
		//	psInsertDBConnection.setInt(6, DBPort);
			psInsertDBConnection.setString(6, Username);
			psInsertDBConnection.setString(7, Password);
			//if (Database.equals(BIRT_DataObject.NULL))
		//		psInsertDBConnection.setNull(9, java.sql.Types.VARCHAR);
			//else psInsertDBConnection.setString(9, Database);
			//if (ServiceName.equals(BIRT_DataObject.NULL))
			//	psInsertDBConnection.setNull(8, java.sql.Types.VARCHAR);
		//	else psInsertDBConnection.setString(8, ServiceName);

			// System.out.println(psInsertDBConnection);
			if (psInsertDBConnection.executeUpdate() == 1)
				return true;

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}
		finally
		{
			if (psInsertDBConnection != null)
				try
				{
					psInsertDBConnection.close();
				}
				catch (SQLException e)
				{
					if(BIRT_AppProperty.PRINT_STACK_TRACE)
						BIRT_Logger.printStackTrace(e);
					BIRT_Logger.error(e.getMessage());
				}
		}

		return false;
	}

	public Object[] getDBConnectionDescriptions(int ProjectID) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strDBConnectionDescriptions + ProjectID);

			ArrayList<Object> resultObjects = new ArrayList<Object>();

			while (rsQueryResult.next())
			{
				// DB Connection ID
				resultObjects.add(new Integer(rsQueryResult.getInt(1)));
				// DB Connection Name
				resultObjects.add(rsQueryResult.getString(2));
				// DB Connection Description
				resultObjects.add(StringUtility.removeLineBreak(rsQueryResult.getString(3)));
			}

			int no_of_QueryItemsRetrieved = resultObjects.size() / 3;

			int[] iDBConnectionIDs = new int[no_of_QueryItemsRetrieved];
			Object[][] objDBConnectionDisplayTableData = new Object[no_of_QueryItemsRetrieved][2];

			for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
			{
				// Current Pointer in ArrayList = iCtr*3
				int iCurrentArrayListPtr = iCtr * 3;
				iDBConnectionIDs[iCtr] = ((Integer) resultObjects.get(iCurrentArrayListPtr)).intValue();
				objDBConnectionDisplayTableData[iCtr][0] = resultObjects.get(iCurrentArrayListPtr + 1);
				objDBConnectionDisplayTableData[iCtr][1] = resultObjects.get(iCurrentArrayListPtr + 2);
			}

			return new Object[]
			{ iDBConnectionIDs, objDBConnectionDisplayTableData };

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

		// return new
		// Object[]{Data.getIQueryIDs(),Data.getObjQueryDescriptionTableData()};

	}
	
	//Divya: TBD

	public Object[] getDBConnectionDetail(int DBConnectionID) throws BIRT_Exception
	{
		Statement stmtQuery;
		try
		{

			stmtQuery = getConnection().createStatement();
			ResultSet rsQueryResult = stmtQuery.executeQuery(strGetDBConnectionDetail + DBConnectionID);

			if (rsQueryResult.next())
			{
				/*
				 * SELECT ConnectionType, 
				 * DBConnectionString,
				 *  Username, 
				 *  Password, 
				 *  DBConnectionName,
				 *  DBConnectionDescription  FROM BIRT_DBConnection WHERE DBConnection_ID = ";
				 *  
				 * Return Type Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PARAMS_TOTAL]
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_TYPE] - ConnectionType - Integer
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVER] - DBServer - String
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PORT] - DBPort - Integer
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_USERNAME] - Username - String
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PASSWORD] - Password - String
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_DATABASE] - DatabaseName - String
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVICENAME] - ServiceName - String
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_CONNECTIONNAME] - ConnectionName - String
				 * Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_CONNECTIONDESC] - ConnectionDescription - String
				 */

				Object[] objDBConnectionDetails = new Object[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PARAMS_TOTAL];
				
				
				objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_TYPE] = new Integer(rsQueryResult.getInt(1));
				objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_STRING] = new String(rsQueryResult.getString(2));
				//objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVER] = new String(rsQueryResult.getString(2));
				//objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PORT] = new Integer(rsQueryResult.getInt(3));
				objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_USERNAME] = new String(rsQueryResult.getString(3));
				objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_PASSWORD] = new String(rsQueryResult.getString(4));
				//objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_DATABASE] = new String((null != rsQueryResult.getString(6)) ? rsQueryResult.getString(6) : "");
				//objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_SERVICENAME] = new String((null != rsQueryResult.getString(5)) ? rsQueryResult.getString(7) : "");
				objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_CONNECTIONNAME] = new String(rsQueryResult.getString(5));
				objDBConnectionDetails[BIRT_DBConnectionDriver.DBCONNECTION_CONNECTION_CONNECTIONDESC] = new String(rsQueryResult.getString(6));

				return objDBConnectionDetails;
			}
			else
			{

				BIRT_Logger.error("Database Connection not found.");

				throw new BIRT_Temp_Exception("Database Connection not found.");
			}
		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());

		}
		return null;
	}

	
	public boolean deleteDBConnection(int ProjectID, int DBConnectionID) throws BIRT_Exception
	{
		try
		{
			PreparedStatement psDeleteDBConnection = getConnection().prepareStatement(strDeleteDBConnection);
			psDeleteDBConnection.setInt(1, ProjectID);
			psDeleteDBConnection.setInt(2, DBConnectionID);

			if (psDeleteDBConnection.executeUpdate() == 1)
				return true;
			else if (psDeleteDBConnection.executeUpdate() == 0)
				return false;
			else throw new BIRT_ImproperQueryUpdateException(BIRT_Resources.getRbAppResourceBundle().getString("DB Connection not updated properly."));

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

	}

	/*
	public boolean editDBConnection(int ProjectID, int DBConnectionID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String DBServer, int DBPort, String Username,
			String Password, String DatabaseName, String ServiceName) throws BIRT_Exception
			*/
	public boolean editDBConnection(int ProjectID, int DBConnectionID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String DBConnectionString, String Username,
					String Password,  String ServiceName) throws BIRT_Exception
	{
		try
		{

			/*
			 * UPDATE BIRT_DBConnection SET DBConnectionName=?, 
			 * DBConnectionDescription=?, 
			 * ConnectionType=?,
			 *  DBConnectionString=?,
			 *   Username=?,
			 *    Password=? WHERE ProjectID=? AND DBConnection_ID = ? 
			 *	
			 */
		
			PreparedStatement psEditDBConnection = getConnection().prepareStatement(strUpdateDBConnection);
			psEditDBConnection.setString(1, DBConnectionName);
			psEditDBConnection.setString(2, DBConnectionDescription);
			psEditDBConnection.setInt(3, ConnectionType);
			//psEditDBConnection.setString(4, DBServer);
			psEditDBConnection.setString(4, DBConnectionString);
			//psEditDBConnection.setInt(5, DBPort);
			psEditDBConnection.setString(5, Username);
			psEditDBConnection.setString(6, Password);
			// System.out.println("psEditDBConnection"+psEditDBConnection);
			/*
			if (DatabaseName.equals(BIRT_DataObject.NULL))
				psEditDBConnection.setNull(8, java.sql.Types.VARCHAR);
			else psEditDBConnection.setString(8, DatabaseName);
			*/
			//if (ServiceName.equals(BIRT_DataObject.NULL))
			//	psEditDBConnection.setNull(7, java.sql.Types.VARCHAR);
		//	else psEditDBConnection.setString(7, ServiceName);
			psEditDBConnection.setInt(7, ProjectID);
			psEditDBConnection.setInt(8, DBConnectionID);

			if (psEditDBConnection.executeUpdate() == 1)
				return true;
			else if (psEditDBConnection.executeUpdate() == 0)
				return false;
			else throw new BIRT_ImproperQueryUpdateException("DB Connection Update Unsuccessful. Please try again later");

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}
	}

	public String getDBConnectionName(int ProjectID, int DBConnectionID) throws BIRT_Exception
	{

		try
		{
			PreparedStatement psDBConnectionName = getConnection().prepareStatement(strGetDBConnectionName);
			psDBConnectionName.setInt(1, ProjectID);
			psDBConnectionName.setInt(2, DBConnectionID);

			ResultSet rsQueryResult = psDBConnectionName.executeQuery();

			if (rsQueryResult.first())
			{
				return rsQueryResult.getString(1);
			}
			else
			{
				throw new BIRT_Temp_Exception("DB Connection not found. Please try again later.");
			}
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
