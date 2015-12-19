package persistence.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logger.BIRT_Logger;
import persistence.db.BIRT_Persistence_TestScript_DB;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import utility.string.StringUtility;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_DataObject;
import comm.BIRT_Resources;

import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import exception.persistence.db.mysql.BIRT_MySQLInstantiationException;

public class BIRT_Persistence_TestScript_DB_MySQL extends BIRT_Persistence_TestScript_DB
{

	private final static String	TestScriptInsertQuery			= "INSERT INTO BIRT_TestScript (TestScriptName, TestScriptDescription, ProjectID) VALUES (?,?,?) ";

	private final static String	TestScriptEditQuery				= "UPDATE BIRT_TestScript SET TestScriptName=?, TestScriptDescription=? WHERE ProjectID=? AND TestScriptID=?";

	private final static String	TestScriptIDQuery				= "SELECT MAX(TestScriptID) FROM BIRT_TestScript WHERE ProjectID = ";
	private final static String	TestStepInsertQuery				= "INSERT INTO BIRT_TestSteps (SequenceNo, Command, Method, Target, Value, TestScriptID,ProjectID ) VALUES (?,?,?,?,?,?,?)";

	private final static String	FetchTestStepsforTestScript		= "SELECT SequenceNo, Command, Method, Target, Value FROM BIRT_TestSteps WHERE TestScriptID = ? AND ProjectID = ? ORDER BY SequenceNo ASC";

	private final static String	strIsDuplicateTestScriptName	= "SELECT 1 FROM BIRT_TestScript WHERE TestScriptName = ";
	private final static String	strTestScriptDescriptions		= "SELECT TestScriptID, TestScriptName, TestScriptDescription FROM BIRT_TestScript WHERE ProjectID = ";
	private final static String	strGetTestScriptName			= "SELECT TestScriptName FROM BIRT_TestScript WHERE ProjectID = ? AND TestScriptID = ?";
	private final static String	strDeleteTestScript				= "DELETE FROM BIRT_TestScript WHERE ProjectID = ? AND TestScriptID = ?";
	private final static String	strDeleteTestStepsforTestScript	= "DELETE FROM BIRT_TestSteps WHERE TestScriptID = ? AND ProjectID = ? ";

	public BIRT_Persistence_TestScript_DB_MySQL() throws BIRT_Exception
	{
		super();
	}

	protected void initializeParameters()
	{
		BIRT_Persistence_TestScript_DB_MySQL.strUserName = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME);
		BIRT_Persistence_TestScript_DB_MySQL.strPassword = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD);
		BIRT_Persistence_TestScript_DB_MySQL.strDBConnectionServer = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER);
		BIRT_Persistence_TestScript_DB_MySQL.iDBConnectionPort = Integer.parseInt(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT));
		BIRT_Persistence_TestScript_DB_MySQL.strDBDatabase = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME);

	}

	protected Connection intializeConnection() throws BIRT_Exception
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

	public boolean isTestScriptNameDuplicate(String TestScriptName) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strIsDuplicateTestScriptName + SINGLE_QUOTE + TestScriptName + SINGLE_QUOTE);

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

	public boolean saveTestScriptNTestSteps(int ProjectID, String TestScriptName, String TestScriptDescription, Object[][] TestSteps) throws BIRT_Exception
	{

		PreparedStatement insertTestScript = null;
		PreparedStatement insertTestSteps = null;

		Statement queryTestScriptID = null;

		Connection DBConnection = getConnection();

		int TestScriptID = -1;

		try
		{
			DBConnection.setAutoCommit(false);
			insertTestScript = DBConnection.prepareStatement(TestScriptInsertQuery);
			insertTestSteps = DBConnection.prepareStatement(TestStepInsertQuery);
			queryTestScriptID = DBConnection.createStatement();

			// TestScript Insert
			// INSERT INTO BIRT_TestScript (TestScriptName,
			// TestScriptDescription, ProjectID)
			insertTestScript.setString(1, TestScriptName);
			insertTestScript.setString(2, TestScriptDescription);
			insertTestScript.setInt(3, ProjectID);

			insertTestScript.execute();
			DBConnection.commit();

			// Get latest TestScriptID
			ResultSet rsTestScriptID = queryTestScriptID.executeQuery(TestScriptIDQuery + ProjectID);

			if (rsTestScriptID.next())
				TestScriptID = rsTestScriptID.getInt(1);

			/*
			 * If TestScript not created return false;
			 */

			// System.out.println("TestScript ID : " + TestScriptID);

			int iTestSteps = TestSteps.length;

			int count = 0;
			for (int iCtr = 0; iCtr < iTestSteps; iCtr++)
			{
				// INSERT INTO BIRT_TestScript (SequenceNo, Command, Method,
				// Target, Value, TestScriptID,ProjectID )
				Object[] objSingleTestStep = TestSteps[iCtr];
				insertTestSteps.setInt(1, ((Integer) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO]).intValue());
				insertTestSteps.setString(2, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND]);
				insertTestSteps.setString(3, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD]);
				insertTestSteps.setString(4, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET]);
				insertTestSteps.setString(5, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE]);
				insertTestSteps.setInt(6, TestScriptID);
				insertTestSteps.setInt(7, ProjectID);

				int iResult = insertTestSteps.executeUpdate();
				// System.out.println("saving... " + count++);
				if (iResult <= 0)
					throw new SQLException();

			}

			DBConnection.commit();

		}

		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			if (TestScriptID > 0)
			{
				try
				{
					DBConnection.createStatement().execute("DELETE FROM BIRT_TestScript WHERE TestScriptID = " + TestScriptID);
				}
				catch (SQLException e1)
				{
					if(BIRT_AppProperty.PRINT_STACK_TRACE)
						BIRT_Logger.printStackTrace(e1);
					BIRT_Logger.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		}

		finally
		{
			try
			{
				if (insertTestScript != null)
				{

					insertTestScript.close();

				}
				if (insertTestSteps != null)
				{
					insertTestSteps.close();
				}
				if (queryTestScriptID != null)
				{
					queryTestScriptID.close();

				}

				DBConnection.setAutoCommit(true);
			}
			catch (SQLException e)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());

				e.printStackTrace();
			}

		}

		return false;
	}

	public Object[] getTestScriptDescriptions(int ProjectID) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strTestScriptDescriptions + ProjectID);

			ArrayList<Object> resultObjects = new ArrayList<Object>();

			while (rsQueryResult.next())
			{
				// Test Script ID
				resultObjects.add(new Integer(rsQueryResult.getInt(1)));
				// Test Script Name
				resultObjects.add(rsQueryResult.getString(2));
				// Test Script Description
				resultObjects.add(StringUtility.removeLineBreak(rsQueryResult.getString(3)));
			}

			int no_of_QueryItemsRetrieved = resultObjects.size() / 3;

			int[] iTestScriptIDs = new int[no_of_QueryItemsRetrieved];
			Object[][] objTestScriptDisplayTableData = new Object[no_of_QueryItemsRetrieved][2];

			for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
			{
				// Current Pointer in ArrayList = iCtr*3
				int iCurrentArrayListPtr = iCtr * 3;
				iTestScriptIDs[iCtr] = ((Integer) resultObjects.get(iCurrentArrayListPtr)).intValue();
				objTestScriptDisplayTableData[iCtr][0] = resultObjects.get(iCurrentArrayListPtr + 1);
				objTestScriptDisplayTableData[iCtr][1] = resultObjects.get(iCurrentArrayListPtr + 2);
			}

			return new Object[]
			{ iTestScriptIDs, objTestScriptDisplayTableData };

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

	}

	public Object[] getTestStepsforTestScript(int ProjectID, int TestScriptID) throws BIRT_Exception
	{
		PreparedStatement psFetchTestStepsforTestScript = null;

		try
		{
			Connection DBConnection = getConnection();

			psFetchTestStepsforTestScript = DBConnection.prepareStatement(FetchTestStepsforTestScript);

			// TestStep Fetch
			/*
			 * SELECT 
			 * SequenceNo, Command, Method, Target, Value 
			 * FROM BIRT_TestSteps 
			 * WHERE TestScriptID = ? AND ProjectID = ? 
			 * ORDER BY SequenceNo ASC
			 */
			psFetchTestStepsforTestScript.setInt(1, TestScriptID);
			psFetchTestStepsforTestScript.setInt(2, ProjectID);

			psFetchTestStepsforTestScript.executeQuery();

			// Get TestSteps
			ResultSet rsTestSteps = psFetchTestStepsforTestScript.executeQuery();

			ArrayList<Object> alTestSteps = new ArrayList<Object>();

			// System.out.println("Haha, here we go!");
			// System.out.println("I saw you. Every step of the way.");
			while (rsTestSteps.next())
			{
				//SequenceNo, Command, Method, Target, Value
				Object[] objSingleTestStep = new Object[5];

				objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO] = new Integer(rsTestSteps.getInt(1));
				objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND] = new String(rsTestSteps.getString(2));
				objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD] = new String(rsTestSteps.getString(3));
				objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET] = new String(rsTestSteps.getString(4));
				objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE] = new String(rsTestSteps.getString(5));
				
				//System.out.println("***T E S T C A S E S T E P S***");
				//System.out.println("***1***");
				//System.out.println(objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO]);
				//System.out.println("***2***");
				//System.out.println(objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND]);
				//System.out.println("***3***");
				//System.out.println(objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD]);
				//System.out.println("***4***");
				//System.out.println(objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET]);
				//System.out.println("***5***");
				//System.out.println(objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE]);
				//System.out.println("***E N D***");
				alTestSteps.add(objSingleTestStep);

			}

			int totalTestSteps = alTestSteps.size();
			Object[] testSteps = new Object[totalTestSteps];
			for (int iCtr = 0; iCtr < totalTestSteps; iCtr++)
				testSteps[iCtr] = alTestSteps.get(iCtr);

			// System.out.println("I will go back because I have all steps");
			return testSteps;

		}

		catch (Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to fetch TestSteps. Please try again later");
		}

	}

	public String getTestScriptName(int ProjectID, int TestScriptID) throws BIRT_Exception
	{

		PreparedStatement psFetchTestScriptName = null;

		try
		{
			Connection DBConnection = getConnection();

			psFetchTestScriptName = DBConnection.prepareStatement(strGetTestScriptName);

			/*
			 * strGetTestScriptName =  "SELECT TestScriptName
			 * FROM BIRT_TestScript 
			 * WHERE ProjectID = ? AND TestScriptID = ?"; 
			 */

			psFetchTestScriptName.setInt(2, TestScriptID);
			psFetchTestScriptName.setInt(1, ProjectID);

			psFetchTestScriptName.executeQuery();

			// Get TestSteps
			ResultSet rsTestSteps = psFetchTestScriptName.executeQuery();

			if (rsTestSteps.next())
			{
				String strTestStepName = rsTestSteps.getString(1);
				return strTestStepName;
			}
			else throw new BIRT_Temp_Exception("Unable to fetch TestScript. Please try again later");

		}

		catch (Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to fetch TestScript. Please try again later");
		}

	}

	public boolean deleteTestScript(int ProjectID, int TestScriptID) throws BIRT_Exception
	{

		PreparedStatement psDeleteTestScript = null;
		PreparedStatement psDeleteTestSteps = null;

		try
		{
			Connection DBConnection = getConnection();

			psDeleteTestScript = DBConnection.prepareStatement(strDeleteTestScript);
			psDeleteTestSteps = DBConnection.prepareStatement(strDeleteTestStepsforTestScript);

			/*
			 * DELETE
			 * FROM BIRT_TestScript 
			 * WHERE ProjectID = ? AND TestScriptID = ?"; 
			 */

			psDeleteTestScript.setInt(2, TestScriptID);
			psDeleteTestScript.setInt(1, ProjectID);

			psDeleteTestSteps.setInt(2, TestScriptID);
			psDeleteTestSteps.setInt(1, ProjectID);

			int count = psDeleteTestScript.executeUpdate();

			if (count == 1)
			{
				psDeleteTestSteps.executeUpdate();
				return true;
			}
			else
			{
				return false;
			}

		}

		catch (Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to fetch TestScript. Please try again later");
		}

	}

	public boolean editTestScriptNTestSteps(int ProjectID, int TestScriptID, String TestScriptName, String TestScriptDescription, Object[][] TestSteps) throws BIRT_Exception
	{
		PreparedStatement editTestScript = null;
		PreparedStatement deleteTestSteps = null;
		PreparedStatement insertTestSteps = null;

		/*
		 * strDeleteTestStepsforTestScript=
		 * DELETE FROM BIRT_TestSteps 
		 * WHERE TestScriptID = ? AND ProjectID = ? ";
		 */

		/*
		 * TestStepInsertQuery=
		 * INSERT INTO BIRT_TestSteps
		 * (SequenceNo, Command, Method, Target, Value, TestScriptID,ProjectID)
		 * VALUES
		 * (?,?,?,?,?,?,?);
		 */

		Connection DBConnection = getConnection();

		try
		{
			DBConnection.setAutoCommit(false);

			editTestScript = DBConnection.prepareStatement(TestScriptEditQuery);
			deleteTestSteps = DBConnection.prepareStatement(strDeleteTestStepsforTestScript);
			insertTestSteps = DBConnection.prepareStatement(TestStepInsertQuery);

			/*
			 * TestScriptEditQuery=
			 * UPDATE BIRT_TestScript
			 * SET TestScriptName=?, TestScriptDescription=?
			 * WHERE ProjectID=? AND TestScriptID=?
			 */

			editTestScript.setString(1, TestScriptName);
			editTestScript.setString(2, TestScriptDescription);
			editTestScript.setInt(3, ProjectID);
			editTestScript.setInt(4, TestScriptID);

			int rowsAffected = editTestScript.executeUpdate();

			if (rowsAffected == 1)
			{

				/*Delete TestSteps already in DB*/
				/*
				 * strDeleteTestStepsforTestScript=
				 * DELETE FROM BIRT_TestSteps 
				 * WHERE TestScriptID = ? AND ProjectID = ? ";
				 */

				deleteTestSteps.setInt(2, ProjectID);
				deleteTestSteps.setInt(1, TestScriptID);

				deleteTestSteps.executeUpdate();

				int iTestSteps = TestSteps.length;

				for (int iCtr = 0; iCtr < iTestSteps; iCtr++)
				{
					// INSERT INTO BIRT_TestScript (SequenceNo, Command, Method,
					// Target, Value, TestScriptID,ProjectID )
					Object[] objSingleTestStep = TestSteps[iCtr];
					insertTestSteps.setInt(1, ((Integer) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO]).intValue());
					insertTestSteps.setString(2, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND]);
					insertTestSteps.setString(3, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD]);
					insertTestSteps.setString(4, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET]);
					insertTestSteps.setString(5, (String) objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE]);
					insertTestSteps.setInt(6, TestScriptID);
					insertTestSteps.setInt(7, ProjectID);

					int iResult = insertTestSteps.executeUpdate();

					if (iResult <= 0)
					{
						editTestScript.close();
						deleteTestSteps.close();
						insertTestSteps.close();
						throw new SQLException();
					}

				}

				DBConnection.commit();
			}
			else
			{
				DBConnection.rollback();
				return false;
			}

		}

		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			if (TestScriptID > 0)
			{
				try
				{
					DBConnection.createStatement().execute("DELETE FROM BIRT_TestScript WHERE TestScriptID = " + TestScriptID);
				}
				catch (SQLException e1)
				{
					if(BIRT_AppProperty.PRINT_STACK_TRACE)
						BIRT_Logger.printStackTrace(e1);
					BIRT_Logger.error(e1.getMessage());
				}
			}
		}

		finally
		{
			try
			{

				if (editTestScript != null)
				{

					editTestScript.close();

				}
				if (deleteTestSteps != null)
				{
					deleteTestSteps.close();
				}
				if (insertTestSteps != null)
				{
					insertTestSteps.close();

				}

				DBConnection.setAutoCommit(true);
				return true;
			}
			catch (SQLException e)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				e.printStackTrace();
			}

		}

		return false;
	}
}
