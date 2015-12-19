package persistence.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logger.BIRT_Logger;
import persistence.db.BIRT_Persistence_Query_DB;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import utility.string.StringUtility;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_Resources;

import exception.BIRT_Exception;
import exception.persistence.BIRT_ImproperQueryUpdateException;
import exception.persistence.BIRT_QueryNotFoundException;
import exception.persistence.db.mysql.BIRT_MySQLInstantiationException;

public class BIRT_Persistence_Query_DB_MySQL extends BIRT_Persistence_Query_DB
{

	private final static String	strIsDuplicateQueryName	= "SELECT 1 FROM Birt_Query WHERE QueryName = ";
	private final static String	strQueryDescriptions	= "SELECT QueryID, QueryName, QueryDescription FROM Birt_Query WHERE ProjectID = ";
	private final static String	strQueryDescription		= "SELECT QueryDescription FROM Birt_Query WHERE QueryID = ";
	private final static String	strQueryName			= "SELECT QueryName FROM Birt_Query WHERE ProjectID = ? AND QueryID = ?";
	private final static String	strQueryDetail			= "SELECT Query FROM Birt_Query WHERE QueryID = ";
	private final static String	strInsertQuery			= "INSERT INTO BIRT_Query (QueryName, QueryDescription, Query, ProjectID) VALUES (?,?,?,?) ";
	private final static String	strUpdateQuery			= "UPDATE BIRT_Query SET QueryName = ? , QueryDescription = ?, Query = ? WHERE ProjectID = ? AND QueryID = ?";
	private final static String	strDeleteQuery			= "DELETE FROM BIRT_Query WHERE ProjectID = ? AND QueryID = ?";

	public BIRT_Persistence_Query_DB_MySQL() throws BIRT_Exception
	{
		super();
	}

	public boolean saveQuery(int projectID, String strQueryName, String strQueryDescription, String strQuery) throws BIRT_Exception
	{

		try
		{
			PreparedStatement psInsertQuery = getConnection().prepareStatement(strInsertQuery);
			psInsertQuery.setString(1, strQueryName);
			psInsertQuery.setString(2, strQueryDescription);
			psInsertQuery.setString(3, strQuery);
			psInsertQuery.setInt(4, projectID);

			if (psInsertQuery.executeUpdate() == 1)
				return true;

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

		return false;

		

	}

	public boolean editQuery(int ProjectID, int QueryID, String QueryName, String QueryDescription, String Query) throws BIRT_Exception
	{
		// /UPDATE BIRT_Query SET QueryName = ? , QueryDescription = ?, Query =
		// ?, WHERE ProjectID = ? AND QueryID = ?

		try
		{
			PreparedStatement psDeleteQuery = getConnection().prepareStatement(strUpdateQuery);
			psDeleteQuery.setString(1, QueryName);
			psDeleteQuery.setString(2, QueryDescription);
			psDeleteQuery.setString(3, Query);
			psDeleteQuery.setInt(4, ProjectID);
			psDeleteQuery.setInt(5, QueryID);

			if (psDeleteQuery.executeUpdate() == 1)
				return true;
			else if (psDeleteQuery.executeUpdate() == 0)
				return false;
			else throw new BIRT_ImproperQueryUpdateException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_IMPROPER_QUERY_UPDATE));

		}
		catch (SQLException e)
		{

			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

		
	}

	public boolean isQueryNameDuplicate(String strQueryName) throws BIRT_Exception
	{

		

		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strIsDuplicateQueryName + SINGLE_QUOTE + strQueryName + SINGLE_QUOTE);

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
	 * Contents of Object[] objQueryTableDetails objQueryTableDetails[QUERYID] =
	 * private static int [] iQueryIDs; objQueryTableDetails[QUERY_TABLE_DATA] =
	 * Object[][] objQueryDetailsTableData;
	 */

	public Object[] getQueryDescriptions(int iProjectID) throws BIRT_Exception
	{

		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strQueryDescriptions + iProjectID);

			ArrayList<Object> resultObjects = new ArrayList<Object>();

			// SELECT QueryID, QueryName, QueryDescription FROM Birt_Query WHERE
			// ProjectID =

			while (rsQueryResult.next())
			{
				// Query ID
				resultObjects.add(new Integer(rsQueryResult.getInt(1)));
				// Query Name
				resultObjects.add(rsQueryResult.getString(2));
				// Query Description
				resultObjects.add(StringUtility.removeLineBreak(rsQueryResult.getString(3)));
			}

			int no_of_QueryItemsRetrieved = resultObjects.size() / 3;

			int[] iQueryIDs = new int[no_of_QueryItemsRetrieved];
			Object[][] objQueryDisplayTableData = new Object[no_of_QueryItemsRetrieved][2];

			for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
			{
				// Current Pointer in ArrayList = iCtr*3
				int iCurrentArrayListPtr = iCtr * 3;
				iQueryIDs[iCtr] = ((Integer) resultObjects.get(iCurrentArrayListPtr)).intValue();
				objQueryDisplayTableData[iCtr][0] = resultObjects.get(iCurrentArrayListPtr + 1);
				objQueryDisplayTableData[iCtr][1] = resultObjects.get(iCurrentArrayListPtr + 2);
			}

			return new Object[]
			{ iQueryIDs, objQueryDisplayTableData };

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

	public String getQueryDetail(int QueryID) throws BIRT_Exception
	{

		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strQueryDetail + QueryID);

			if (rsQueryResult.first())
			{
				return rsQueryResult.getString(1);
			}
			else
			{
				throw new BIRT_QueryNotFoundException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_QUERY_NOT_FOUND));
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

	protected void initializeParameters()
	{
		BIRT_Persistence_Query_DB.strUserName = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME);
		BIRT_Persistence_Query_DB.strPassword = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD);
		BIRT_Persistence_Query_DB.strDBConnectionServer = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER);
		BIRT_Persistence_Query_DB.iDBConnectionPort = Integer.parseInt(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT));
		BIRT_Persistence_Query_DB.strDBDatabase = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME);
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

	public boolean deleteQuery(int ProjectID, int QueryID) throws BIRT_Exception
	{
		// DELETE FROM BIRT_Query WHERE ProjectID = ? AND QueryID = ?
		try
		{
			PreparedStatement psDeleteQuery = getConnection().prepareStatement(strDeleteQuery);
			psDeleteQuery.setInt(1, ProjectID);
			psDeleteQuery.setInt(2, QueryID);

			if (psDeleteQuery.executeUpdate() == 1)
				return true;
			else if (psDeleteQuery.executeUpdate() == 0)
				return false;
			else throw new BIRT_ImproperQueryUpdateException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_IMPROPER_QUERY_UPDATE));

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

	}

	public String getQueryDescription(int QueryID) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strQueryDescription + QueryID);

			if (rsQueryResult.first())
			{
				return rsQueryResult.getString(1);
			}
			else
			{
				throw new BIRT_QueryNotFoundException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_QUERY_NOT_FOUND));
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

	public String getQueryName(int ProjectID, int QueryID) throws BIRT_Exception
	{
		try
		{
			PreparedStatement stmtQuery = getConnection().prepareStatement(strQueryName);
			stmtQuery.setInt(1, ProjectID);
			stmtQuery.setInt(2, QueryID);

			ResultSet rsQueryResult = stmtQuery.executeQuery();

			if (rsQueryResult.first())
			{
				return rsQueryResult.getString(1);
			}
			else
			{
				throw new BIRT_QueryNotFoundException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_QUERY_NOT_FOUND));
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
