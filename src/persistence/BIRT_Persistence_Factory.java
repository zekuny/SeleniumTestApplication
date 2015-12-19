package persistence;

import persistence.db.mysql.BIRT_Persistence_DBConnection_DB_MySQL;
import persistence.db.mysql.BIRT_Persistence_Query_DB_MySQL;
import persistence.db.mysql.BIRT_Persistence_TestCase_DB_MySQL;
import persistence.db.mysql.BIRT_Persistence_TestScript_DB_MySQL;
import persistence.db.mysql.BIRT_Persistence_TestSuite_DB_MySQL;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_Persistence;

import exception.BIRT_Exception;

public class BIRT_Persistence_Factory
{

	private final String	strBIRT_Persistence_Media;

	public BIRT_Persistence_Factory(String strBIRT_Persistence_Media)
	{
		this.strBIRT_Persistence_Media = strBIRT_Persistence_Media;
	}

	public void initializeBIRT_Persistence_Objects() throws BIRT_Exception
	{
		if (strBIRT_Persistence_Media.equals(BIRT_AppProperty.PERSISTENCE_MEDIA_DB[BIRT_AppProperty.iPERSISTENCE_MEDIA_DB_MYSQL]))
		{
			initializeBIRT_Persistence_Objects_DB_MySQL();

		}
	}

	private void initializeBIRT_Persistence_Objects_DB_MySQL() throws BIRT_Exception
	{
		BIRT_Persistence.setObjBIPersistence_Query(new BIRT_Persistence_Query_DB_MySQL());
		BIRT_Persistence.setObjBIPersistence_DBConnection(new BIRT_Persistence_DBConnection_DB_MySQL());
		BIRT_Persistence.setObjBIPersistence_TestScript(new BIRT_Persistence_TestScript_DB_MySQL());
		BIRT_Persistence.setObjBIPersistence_TestCase(new BIRT_Persistence_TestCase_DB_MySQL());
		BIRT_Persistence.setObjBIPersistence_TestSuite(new BIRT_Persistence_TestSuite_DB_MySQL());
	}
}
