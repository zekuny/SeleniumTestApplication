package persistence.db;

import java.sql.Connection;

import persistence.BIRT_Persistence_TestCase;
import exception.BIRT_Exception;

public abstract class BIRT_Persistence_TestCase_DB implements BIRT_Persistence_TestCase
{
	protected static String			strUserName;
	protected static String			strPassword;
	protected static String			strDBConnectionServer;
	protected static int			iDBConnectionPort;
	protected static String			strDBDatabase;
	protected final static String	SINGLE_QUOTE	= "'";

	protected static Connection		DBConnection;

	public BIRT_Persistence_TestCase_DB() throws BIRT_Exception
	{
		initializeParameters();
		getConnection();
	}

	/*
	 * Initialize the Connection Parameters such as UserName, password, DB
	 * Connection Server etc...
	 */
	protected abstract void initializeParameters();

	/*
	 * Initialize the Connection by using the correct Driver
	 */
	protected abstract Connection intializeConnection() throws BIRT_Exception;

	protected Connection getConnection() throws BIRT_Exception
	{
		if (null == DBConnection)
			DBConnection = intializeConnection();
		return DBConnection;

	}

}
