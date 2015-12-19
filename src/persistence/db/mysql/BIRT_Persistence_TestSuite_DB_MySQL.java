package persistence.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import logger.BIRT_Logger;
import persistence.db.BIRT_Persistence_TestSuite_DB;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import utility.string.StringUtility;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_DataObject;
import comm.BIRT_Resources;

import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import exception.persistence.db.mysql.BIRT_MySQLInstantiationException;

public class BIRT_Persistence_TestSuite_DB_MySQL extends BIRT_Persistence_TestSuite_DB
{

	public BIRT_Persistence_TestSuite_DB_MySQL() throws BIRT_Exception
	{
		super();
	}

	private final static String	TestSuiteInsertQuery				= "INSERT INTO BIRT_TestSuite (TestSuiteName, TestPath, TestSuiteDescription, ProjectID) VALUES (?,?,?,?)";

	private final static String	TestSuiteUpdateQuery				= "UPDATE BIRT_TestSuite SET TestSuiteName=?, TestSuiteDescription=?, TestPath=? WHERE ProjectID=? AND TestSuiteID=?";
	private final static String	TestSuiteRelatedTestCases_Delete	= "DELETE FROM BIRT_TestSuite_TestCase WHERE TestSuiteID=? AND ProjectID =?";

	private final static String	TestSuiteDescriptions				= "SELECT TestSuiteID, TestSuiteName, TestSuiteDescription, TestPath FROM BIRT_TestSuite WHERE ProjectID = ";

	private final static String	TestSuiteIDQuery					= "SELECT MAX(TestSuiteID) FROM BIRT_TestSuite WHERE ProjectID = ";

	private final static String	TestSuiteRelatedTestCases_Insert	= "INSERT INTO BIRT_TestSuite_TestCase (TestSuiteID, TestCaseID, ProjectID) VALUES (?,?,?)";

	private final static String	GetTestSuiteRelatedTestCases		= "SELECT TestCaseID FROM BIRT_TestSuite_TestCase WHERE TestSuiteID = ? AND ProjectID = ?";

	private final static String	GetFileName							= "SELECT TestPath FROM BIRT_TestSuite WHERE TestSuiteID = ?";
	
	private final static String	DeleteTestSuite						= "DELETE FROM BIRT_TestSuite WHERE TestSuiteID = ? AND ProjectID = ?";

	private final static String	strIsDuplicateTestSuiteName			= "SELECT 1 FROM BIRT_TestSuite WHERE TestSuiteName = ";

	private final static String	strSaveTestSuiteResultHistory		= "INSERT INTO Birt_TestSuiteResultHistory (TestSuiteID,TimeStamp,NoOfTestCases,NoOfTestCasePass,NoOfTestCaseFail,NoOfPassPercentage,ProjectID) VALUES (?,?,?,?,?,?,?)";
	private final static String	strGetTestSuiteResultHistory		= "SELECT TimeStamp,NoOfTestCases,NoOfTestCasePass,NoOfTestCaseFail,NoOfPassPercentage FROM Birt_TestSuiteResultHistory WHERE ProjectID=? AND TestSuiteID=? ";

	protected void initializeParameters()
	{
		BIRT_Persistence_TestSuite_DB_MySQL.strUserName = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME);
		BIRT_Persistence_TestSuite_DB_MySQL.strPassword = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD);
		BIRT_Persistence_TestSuite_DB_MySQL.strDBConnectionServer = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER);
		BIRT_Persistence_TestSuite_DB_MySQL.iDBConnectionPort = Integer.parseInt(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT));
		BIRT_Persistence_TestSuite_DB_MySQL.strDBDatabase = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME);

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

	public Object[] getTestSuiteDescriptions(int ProjectID) throws BIRT_Exception
	{
		try
		{
			Statement stmtTestSuiteQuery = getConnection().createStatement();

			ResultSet rsTesSuiteQueryResult = stmtTestSuiteQuery.executeQuery(TestSuiteDescriptions + ProjectID);

			ArrayList<Object> resultObjects = new ArrayList<Object>();

			while (rsTesSuiteQueryResult.next())
			{
				// TesSuite ID
				resultObjects.add(new Integer(rsTesSuiteQueryResult.getInt(1)));
				// TesSuite Name
				resultObjects.add(rsTesSuiteQueryResult.getString(2));
				// TesSuite Description
				resultObjects.add(StringUtility.removeLineBreak(rsTesSuiteQueryResult.getString(3)));
				// TesFile Path
				resultObjects.add(rsTesSuiteQueryResult.getString(4));
			}

			int no_of_QueryItemsRetrieved = resultObjects.size() / 4;

			int[] iTestSuiteIDs = new int[no_of_QueryItemsRetrieved];
			Object[][] objTestSuiteDisplayTableData = new Object[no_of_QueryItemsRetrieved][3];

			for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
			{
				// Current Pointer in ArrayList = iCtr*3
				int iCurrentArrayListPtr = iCtr * 4;
				iTestSuiteIDs[iCtr] = ((Integer) resultObjects.get(iCurrentArrayListPtr)).intValue();
				objTestSuiteDisplayTableData[iCtr][0] = resultObjects.get(iCurrentArrayListPtr + 1);
				objTestSuiteDisplayTableData[iCtr][1] = resultObjects.get(iCurrentArrayListPtr + 2);
				objTestSuiteDisplayTableData[iCtr][2] = resultObjects.get(iCurrentArrayListPtr + 3);
			}

			return new Object[]
			{ iTestSuiteIDs, objTestSuiteDisplayTableData };

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}
	}

	public boolean isTestSuiteNameDuplicate(String TestSuiteName) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strIsDuplicateTestSuiteName + SINGLE_QUOTE + TestSuiteName + SINGLE_QUOTE);

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

	public boolean saveTestSuite(int ProjectID, String TestSuiteName, String TestPath, String TestSuiteDescription, int[] TestCaseIds) throws BIRT_Exception
	{
		PreparedStatement insertTestSuite = null;
		PreparedStatement insertTestSuiteTestCaseRelation = null;

		Statement queryTestSuiteID = null;

		Connection DBConnection = getConnection();

		int TestSuiteID = -1;

		try
		{
			DBConnection.setAutoCommit(false);
			insertTestSuite = DBConnection.prepareStatement(TestSuiteInsertQuery);
			insertTestSuiteTestCaseRelation = DBConnection.prepareStatement(TestSuiteRelatedTestCases_Insert);
			queryTestSuiteID = DBConnection.createStatement();

			// BIRT_TestSuite (TestSuiteName, TestSuiteDescription, ProjectID)
			insertTestSuite.setString(1, TestSuiteName);
			insertTestSuite.setString(2, TestPath);
			insertTestSuite.setString(3, TestSuiteDescription);
			insertTestSuite.setInt(4, ProjectID);

			insertTestSuite.execute();
			DBConnection.commit();

			// Get latest TestScriptID
			ResultSet rsTestSuiteID = queryTestSuiteID.executeQuery(TestSuiteIDQuery + ProjectID);

			if (rsTestSuiteID.next())
				TestSuiteID = rsTestSuiteID.getInt(1);

			/*
			 * If testsuite not created return false;
			 */

			// System.out.println("TestSuite ID : " + TestSuiteID);

			int iTestSteps = TestCaseIds.length;

			// INSERT INTO BIRT_TestSuite_TestCase (TestSuiteID, TestCaseID,
			// ProjectID)
			insertTestSuiteTestCaseRelation.setInt(1, TestSuiteID);
			insertTestSuiteTestCaseRelation.setInt(3, ProjectID);

			for (int iCtr = 0; iCtr < iTestSteps; iCtr++)
			{
				insertTestSuiteTestCaseRelation.setInt(2, TestCaseIds[iCtr]);
				int iResult = insertTestSuiteTestCaseRelation.executeUpdate();

				if (iResult <= 0)
					throw new SQLException();

			}

			DBConnection.commit();
			DBConnection.setAutoCommit(true);
			return true;

		}

		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			if (TestSuiteID > 0)
			{
				try
				{
					DBConnection.createStatement().execute("DELETE FROM BIRT_TestSuite WHERE TestSuiteID = " + TestSuiteID);
				}
				catch (SQLException e1)
				{if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e1);
					BIRT_Logger.error(e1.getMessage());
				}
			}

		}

		finally
		{
			try
			{
				if (insertTestSuiteTestCaseRelation != null)
				{

					insertTestSuiteTestCaseRelation.close();

				}
				if (insertTestSuite != null)
				{
					insertTestSuite.close();
				}

				DBConnection.setAutoCommit(true);
			}
			catch (SQLException e)
			{if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
			}

		}

		return false;
	}

	public int[] getTestSuiteTestCases(int TestSuiteID, int ProjectID) throws BIRT_Exception
	{
		try
		{
			PreparedStatement psTestSuiteRelatedTestCases = getConnection().prepareStatement(GetTestSuiteRelatedTestCases);
			psTestSuiteRelatedTestCases.setInt(1, TestSuiteID);
			psTestSuiteRelatedTestCases.setInt(2, ProjectID);

			ResultSet rsQueryResult = psTestSuiteRelatedTestCases.executeQuery();

			ArrayList<Integer> resultObjects = new ArrayList<Integer>();

			// SELECT TestCaseID

			while (rsQueryResult.next())
			{
				// TestCaseID
				resultObjects.add(new Integer(rsQueryResult.getInt(1)));
			}

			int no_of_QueryItemsRetrieved = resultObjects.size();

			int[] iTestCaseIDs = new int[no_of_QueryItemsRetrieved];

			for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
			{
				iTestCaseIDs[iCtr] = resultObjects.get(iCtr).intValue();
			}

			return iTestCaseIDs;

		}
		catch (SQLException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

		// return new
		// Object[]{Data.getIQueryIDs(),Data.getObjQueryDescriptionTableData()};

	}

	public boolean saveTestSuiteResult(int ProjectID, int TestSuiteID, int[] TestStats, Calendar clTimeStamp) throws BIRT_Exception
	{
		PreparedStatement insertTestSuiteResultHistory = null;
		Connection DBConnection = getConnection();

		try
		{
			insertTestSuiteResultHistory = DBConnection.prepareStatement(strSaveTestSuiteResultHistory);
			// (TestSuiteID,TimeStamp,NoOfTestCases,NoOfTestCasePass,NoOfTestCaseFail,NoOfPassPercentage,ProjectID)
			insertTestSuiteResultHistory.setInt(1, TestSuiteID);
			String TimeStamp = clTimeStamp.get(Calendar.YEAR) + "/" + ((clTimeStamp.get(Calendar.MONTH)) + 1) + "/" + clTimeStamp.get(Calendar.DATE) + " " + clTimeStamp.get(Calendar.HOUR_OF_DAY)
					+ ":" + clTimeStamp.get(Calendar.MINUTE) + ":" + clTimeStamp.get(Calendar.MILLISECOND);
			insertTestSuiteResultHistory.setString(2, TimeStamp);
			insertTestSuiteResultHistory.setInt(3, TestStats[TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASES]);
			insertTestSuiteResultHistory.setInt(4, TestStats[TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS]);
			insertTestSuiteResultHistory.setInt(5, TestStats[TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_FAIL]);
			insertTestSuiteResultHistory.setInt(6, TestStats[TEST_SUITE_RSLT_HSTRY_NO_OF_TESTCASE_PASS_PERCENTAGE]);
			insertTestSuiteResultHistory.setInt(7, ProjectID);

			insertTestSuiteResultHistory.execute();
			return true;
		}
		catch (SQLException e)
		{
			BIRT_Logger.error(e.getMessage());
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			throw new BIRT_Temp_Exception("Unable to connect to Database to save TestSuite Result History");
		}

	}

	/*
	 * Object[] - Contains SingleTestSuiteResultHistory
	 * SingleTestSuiteResultHistory[0] - TimeStamp - String
	 * SingleTestSuiteResultHistory[1] - NoOfTestCases
	 * SingleTestSuiteResultHistory[2] - NoOfTestCasePass
	 * SingleTestSuiteResultHistory[3] - NoOfTestCaseFail
	 * SingleTestSuiteResultHistory[4] - NoOfPassPercentage
	 */

	public Object[] getTestSuiteResultHistory(int projectID, int TestSuiteID) throws BIRT_Exception
	{
		try
		{
			PreparedStatement psTestSuiteQuery = getConnection().prepareStatement(strGetTestSuiteResultHistory);
			/*
			 * "SELECT TimeStamp,NoOfTestCases,NoOfTestCasePass,NoOfTestCaseFail,NoOfPassPercentage 
			 * FROM Birt_TestSuiteResultHistory 
			 * WHERE ProjectID=? AND TestSuiteID=? ";
			 */

			psTestSuiteQuery.setInt(1, projectID);
			psTestSuiteQuery.setInt(2, TestSuiteID);

			ResultSet rsTestSuiteHistory = psTestSuiteQuery.executeQuery();

			ArrayList<Object> resultObjects = new ArrayList<Object>();

			while (rsTestSuiteHistory.next())
			{
				// TimeStamp
				resultObjects.add(new String(rsTestSuiteHistory.getString(1)));
				//NoOfTestCases
				resultObjects.add(new Integer(rsTestSuiteHistory.getInt(2)));
				//NoOfTestCasePass
				resultObjects.add(new Integer(rsTestSuiteHistory.getInt(3)));
				//NoOfTestCaseFail
				resultObjects.add(new Integer(rsTestSuiteHistory.getInt(4)));
				//NoOfPassPercentage
				resultObjects.add(new Integer(rsTestSuiteHistory.getInt(5)));
			}

			int no_of_QueryItemsRetrieved = resultObjects.size() / 5;
			int[] iTestSuiteIDs = new int[no_of_QueryItemsRetrieved];
			Object[][] objTestSuiteResultHistory = new Object[no_of_QueryItemsRetrieved][BIRT_DataObject.TESTSUITEHIST_TOTAL];

			String strArchivePath = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_ARCHIVE_DIR) + "/" + TestSuiteID + "/";

			for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
			{
				// Current Pointer in ArrayList = iCtr*3
				int iCurrentArrayListPtr = iCtr * 5;

				iTestSuiteIDs[iCtr] = TestSuiteID;
				String strTimeStamp = (String) resultObjects.get(iCurrentArrayListPtr);
				objTestSuiteResultHistory[iCtr][BIRT_DataObject.TESTSUITEHIST_TIMESTAMP] = strTimeStamp;
				objTestSuiteResultHistory[iCtr][BIRT_DataObject.TESTSUITEHIST_TOTAL_TESTCASES] = resultObjects.get(iCurrentArrayListPtr + 1);
				objTestSuiteResultHistory[iCtr][BIRT_DataObject.TESTSUITEHIST_PASS_TESTCASES] = resultObjects.get(iCurrentArrayListPtr + 2);
				objTestSuiteResultHistory[iCtr][BIRT_DataObject.TESTSUITEHIST_FAIL_TESTCASES] = resultObjects.get(iCurrentArrayListPtr + 3);
				objTestSuiteResultHistory[iCtr][BIRT_DataObject.TESTSUITEHIST_PASS_PERCENTAGE] = resultObjects.get(iCurrentArrayListPtr + 4);
				String[] strFolderPathContents = strTimeStamp.split(" ");
				strFolderPathContents[0] = strFolderPathContents[0].replace("/", "-");
				strFolderPathContents[1] = strFolderPathContents[1].replace(":", "-");
				objTestSuiteResultHistory[iCtr][BIRT_DataObject.TESTSUITEHIST_ARCHIVE_PATH] = strArchivePath + strFolderPathContents[0] + "-" + strFolderPathContents[1];
			}

			return new Object[]
			{ iTestSuiteIDs, objTestSuiteResultHistory };

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}
	}

	public boolean editTestSuite(int ProjectID, int TestSuiteID, String TestSuiteName, String TestFilePath, String TestSuiteDescription, int[] TestCaseIds) throws BIRT_Exception
	{
		/*
		 *TestSuiteUpdateQuery				= "UPDATE BIRT_TestSuite SET TestSuiteName=?, TestSuiteDescription=? WHERE ProjectID=? AND TestSuiteID=?";
		 *TestSuiteRelatedTestCases_Delete	= "DELETE FROM BIRT_TestSuite_TestCase WHERE TestSuiteID=? AND ProjectID =?";
		 *TestSuiteRelatedTestCases_Insert	= "INSERT INTO BIRT_TestSuite_TestCase (TestSuiteID, TestCaseID, ProjectID) VALUES (?,?,?)"; 
		 */

		PreparedStatement editTestSuite = null;
		PreparedStatement deleteTestSuiteTestCaseRelation = null;
		PreparedStatement editTestSuiteTestCaseRelation = null;

		Connection DBConnection = getConnection();
		try
		{
			DBConnection.setAutoCommit(false);

			//Edit TestSuite
			editTestSuite = DBConnection.prepareStatement(TestSuiteUpdateQuery);
			editTestSuite.setString(1, TestSuiteName);
			editTestSuite.setString(2, TestSuiteDescription);
			editTestSuite.setString(3, TestFilePath);
			editTestSuite.setInt(4, ProjectID);
			editTestSuite.setInt(5, TestSuiteID);
			editTestSuite.executeUpdate();

			//Delete TestSuite Related TestCases
			deleteTestSuiteTestCaseRelation = DBConnection.prepareStatement(TestSuiteRelatedTestCases_Delete);
			deleteTestSuiteTestCaseRelation.setInt(2, ProjectID);
			deleteTestSuiteTestCaseRelation.setInt(1, TestSuiteID);
			deleteTestSuiteTestCaseRelation.executeUpdate();

			//Insert New TestCases
			editTestSuiteTestCaseRelation = DBConnection.prepareStatement(TestSuiteRelatedTestCases_Insert);

			int iTestSteps = TestCaseIds.length;

			// INSERT INTO BIRT_TestSuite_TestCase (TestSuiteID, TestCaseID,
			// ProjectID)
			editTestSuiteTestCaseRelation.setInt(1, TestSuiteID);
			editTestSuiteTestCaseRelation.setInt(3, ProjectID);

			for (int iCtr = 0; iCtr < iTestSteps; iCtr++)
			{
				editTestSuiteTestCaseRelation.setInt(2, TestCaseIds[iCtr]);
				int iResult = editTestSuiteTestCaseRelation.executeUpdate();

				if (iResult <= 0)
				{
					editTestSuite.close();
					deleteTestSuiteTestCaseRelation.close();
					editTestSuiteTestCaseRelation.close();
					throw new SQLException();
				}

			}

			DBConnection.commit();
			DBConnection.setAutoCommit(true);
			return true;

		}

		catch (SQLException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			if (TestSuiteID > 0)
			{
				try
				{
					DBConnection.createStatement().execute("DELETE FROM BIRT_TestSuite WHERE TestSuiteID = " + TestSuiteID);
				}
				catch (SQLException e1)
				{if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e1);

					BIRT_Logger.error(e1.getMessage());
				}
			}

		}

		finally
		{
			try
			{
				if (editTestSuiteTestCaseRelation != null)
				{

					editTestSuiteTestCaseRelation.close();

				}
				if (editTestSuite != null)
				{
					editTestSuite.close();
				}

				DBConnection.setAutoCommit(true);
			}
			catch (SQLException e)
			{if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);

				BIRT_Logger.error(e.getMessage());
			}

		}

		return false;
	}

	public boolean deleteTestSuite(int ProjectID, int TestSuiteID) throws BIRT_Exception
	{
		/*
		 * String	DeleteTestSuite = "DELETE FROM BIRT_TestSuite WHERE TestSuiteID = ? AND ProjectID = ?";
		 */
		try
		{
			PreparedStatement psDeleteTestSuite = getConnection().prepareStatement(DeleteTestSuite);
			psDeleteTestSuite.setInt(1, TestSuiteID);
			psDeleteTestSuite.setInt(2, ProjectID);

			int rs = psDeleteTestSuite.executeUpdate();
			if (rs == 1)
				return true;
			else return false;
		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

	}
	
	
	public String getFileName(int TestSuiteID) throws BIRT_Exception
	{
		try
		{
			PreparedStatement psTestSuiteRelatedTestCases = getConnection().prepareStatement(GetFileName);
			psTestSuiteRelatedTestCases.setInt(1, TestSuiteID);

			ResultSet rsQueryResult = psTestSuiteRelatedTestCases.executeQuery();

			String fileName = "";

			// SELECT TestCaseID

			if(rsQueryResult.next())
			{
				// TestCaseID
				fileName = rsQueryResult.getString("TestPath");
				// fileName = "addSteps.txt";
			}

			return fileName;

		}
		catch (SQLException e)
		{if(BIRT_AppProperty.PRINT_STACK_TRACE)
			BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

		// return new
		// Object[]{Data.getIQueryIDs(),Data.getObjQueryDescriptionTableData()};

	}

}
