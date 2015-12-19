package framework.dbconnection.driver;

import logger.BIRT_Logger;
import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import framework.dbconnection.driver.mssql.BIRT_DBConnectionDriver_MS_SQL;
import framework.dbconnection.driver.mysql.BIRT_DBConnectionDriver_MySQL;
import framework.dbconnection.driver.oracle.BIRT_DBConnectionDriver_Oracle;
import framework.dbconnection.driver.teradata.BIRT_DBConnectionDriver_Teradata;

public class BIRT_DBConnectionDriverFactory
{
	private final int	iConnectionType;

	public BIRT_DBConnectionDriverFactory(int ConnectionType)
	{
		iConnectionType = ConnectionType;
	}

	public BIRT_DBConnectionDriver getDBConnectionDriver() throws BIRT_Exception
	{
		if (iConnectionType == BIRT_DBConnectionDriver.DBCONNECTION_TYPE_MYSQL)
		{
			return new BIRT_DBConnectionDriver_MySQL();
		}
		else if (iConnectionType == BIRT_DBConnectionDriver.DBCONNECTION_TYPE_TERADATA)
		{
			return new BIRT_DBConnectionDriver_Teradata();
		}
		else if (iConnectionType == BIRT_DBConnectionDriver.DBCONNECTION_TYPE_MSSQL)
		{
			return new BIRT_DBConnectionDriver_MS_SQL();
		}
		else if(iConnectionType == BIRT_DBConnectionDriver.DBCONNECTION_TYPE_ORACLE)
		{
			return new BIRT_DBConnectionDriver_Oracle(); 
		}
		else
		{
			BIRT_Logger.error("Unknown Database. Please select valid database to test");
			throw new BIRT_Temp_Exception("Unknown Database. Please select valid database to test");
		}
	}
}
