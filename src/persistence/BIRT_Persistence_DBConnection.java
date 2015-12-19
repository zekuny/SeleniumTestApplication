package persistence;

import exception.BIRT_Exception;

public interface BIRT_Persistence_DBConnection
{
//Divya: Changinging the input attributes of the method to accept connectionString
	//public boolean saveDBConnection(int ProjectID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String DBServer, int DBPort, String Username, String Password,
	//		String DatabaseName, String ServiceName) throws BIRT_Exception;
	public boolean saveDBConnection(int ProjectID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String connectionString, String Username, String Password,
			 String ServiceName) throws BIRT_Exception;

	public boolean isDBConnectionNameDuplicate(String DBConnectionName) throws BIRT_Exception;

	public boolean deleteDBConnection(int ProjectID, int DBConnectionID) throws BIRT_Exception;

	public Object[] getDBConnectionDescriptions(int ProjectID) throws BIRT_Exception;

	/*
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

	public Object[] getDBConnectionDetail(int DBConnectionID) throws BIRT_Exception;

	/*
	public boolean editDBConnection(int ProjectID, int DBConnectionID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String DBServer, int DBPort, String Username,
			String Password, String DatabaseName, String ServiceName) throws BIRT_Exception;
			*/
	
	public boolean editDBConnection(int ProjectID, int DBConnectionID, String DBConnectionName, String DBConnectionDescription, int ConnectionType, String DBConnectionString, String Username,
			String Password,  String ServiceName) throws BIRT_Exception;

	public String getDBConnectionName(int ProjectID, int DBConnectionID) throws BIRT_Exception;

}
