package comm;

import persistence.BIRT_Persistence_DBConnection;
import persistence.BIRT_Persistence_Factory;
import persistence.BIRT_Persistence_Query;
import persistence.BIRT_Persistence_TestCase;
import persistence.BIRT_Persistence_TestScript;
import persistence.BIRT_Persistence_TestSuite;

import com.anugraha.birt.app.BIRT_AppProperty;

import exception.BIRT_Exception;

public class BIRT_Persistence
{

	private static BIRT_Persistence_Query			objBIPersistence_Query;
	private static BIRT_Persistence_DBConnection	objBIPersistence_DBConnection;
	private static BIRT_Persistence_TestScript		objBIPersistence_TestScript;
	private static BIRT_Persistence_TestCase		objBIPersistence_TestCase;
	private static BIRT_Persistence_TestSuite		objBIPersistence_TestSuite;

	public static final BIRT_Persistence_TestSuite getObjBIPersistence_TestSuite() throws BIRT_Exception
	{
		if (null == objBIPersistence_TestSuite)
			initializePersistenceObjects();
		return objBIPersistence_TestSuite;
	}

	public static final void setObjBIPersistence_TestSuite(BIRT_Persistence_TestSuite objBIPersistence_TestSuite)
	{
		BIRT_Persistence.objBIPersistence_TestSuite = objBIPersistence_TestSuite;
	}

	public static final BIRT_Persistence_TestCase getObjBIPersistence_TestCase() throws BIRT_Exception
	{
		if (null == objBIPersistence_TestCase)
			initializePersistenceObjects();
		return objBIPersistence_TestCase;
	}

	public static final void setObjBIPersistence_TestCase(BIRT_Persistence_TestCase objBIPersistence_TestCase)
	{
		BIRT_Persistence.objBIPersistence_TestCase = objBIPersistence_TestCase;
	}

	public static final BIRT_Persistence_TestScript getObjBIPersistence_TestScript() throws BIRT_Exception
	{
		if (null == objBIPersistence_TestScript)
			initializePersistenceObjects();
		return objBIPersistence_TestScript;
	}

	public static final void setObjBIPersistence_TestScript(BIRT_Persistence_TestScript objBIPersistence_TestScript)
	{
		BIRT_Persistence.objBIPersistence_TestScript = objBIPersistence_TestScript;
	}

	public static final BIRT_Persistence_DBConnection getObjBIPersistence_DBConnection() throws BIRT_Exception
	{
		if (null == objBIPersistence_DBConnection)
			initializePersistenceObjects();
		return objBIPersistence_DBConnection;
	}

	public static final void setObjBIPersistence_DBConnection(BIRT_Persistence_DBConnection objBIPersistence_DBConnection)
	{
		BIRT_Persistence.objBIPersistence_DBConnection = objBIPersistence_DBConnection;
	}

	public static final BIRT_Persistence_Query getObjBIPersistence_Query() throws BIRT_Exception
	{
		if (null == objBIPersistence_Query)
			initializePersistenceObjects();
		return objBIPersistence_Query;
	}

	public static final void setObjBIPersistence_Query(BIRT_Persistence_Query objBIPersistence_Query)
	{
		BIRT_Persistence.objBIPersistence_Query = objBIPersistence_Query;
	}

	private static void initializePersistenceObjects() throws BIRT_Exception
	{

		BIRT_Persistence_Factory objBIPersistence_Factory = new BIRT_Persistence_Factory(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA));
		objBIPersistence_Factory.initializeBIRT_Persistence_Objects();
	}

}
