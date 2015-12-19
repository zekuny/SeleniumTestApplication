package framework.dbconnection.driver;

import java.sql.Connection;

import exception.BIRT_Exception;

public interface BIRT_DBConnectionDriver
{

	public final static String	DBCONNECTIONTYPES[]						=
																		{ "MySQL", "Teradata", "MS SQL","Oracle" };

	public final static int		DBCONNECTION_TYPE_MYSQL					= 0;
	public final static int		DBCONNECTION_TYPE_TERADATA				= 1;
	public final static int		DBCONNECTION_TYPE_MSSQL					= 2;
	public final static int		DBCONNECTION_TYPE_ORACLE				= 3;

	public static final int		DBCONNECTION_CONNECTION_TYPE			= 0;
	// public static final int		DBCONNECTION_CONNECTION_SERVER			= 1;
	// public static final int		DBCONNECTION_CONNECTION_PORT			= 2;
	public static final int		DBCONNECTION_CONNECTION_STRING			= 1;
	public static final int		DBCONNECTION_CONNECTION_USERNAME		= 2;
	public static final int		DBCONNECTION_CONNECTION_PASSWORD		= 3;
	//public static final int		DBCONNECTION_CONNECTION_DATABASE		= 5;
	public static final int		DBCONNECTION_CONNECTION_SERVICENAME		= 4;
	public static final int		DBCONNECTION_CONNECTION_CONNECTIONNAME	= 5;
	public static final int		DBCONNECTION_CONNECTION_CONNECTIONDESC	= 6;
	public static final int		DBCONNECTION_CONNECTION_PARAMS_TOTAL	= 7;

	public Connection getConnection(Object[] oParameters) throws BIRT_Exception;

}
