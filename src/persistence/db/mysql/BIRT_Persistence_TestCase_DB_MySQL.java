package persistence.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logger.BIRT_Logger;
import persistence.db.BIRT_Persistence_TestCase_DB;
import resources.resourcebundle.BIRT_Resource_PropertyNames;
import utility.string.StringUtility;

import com.anugraha.birt.app.BIRT_AppProperty;
import comm.BIRT_Resources;

import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import exception.persistence.db.mysql.BIRT_MySQLInstantiationException;

public class BIRT_Persistence_TestCase_DB_MySQL extends BIRT_Persistence_TestCase_DB
{

	private final static String	TestCaseInsertQuery			= "INSERT INTO BIRT_TestCase (ProjectID,TestCaseName,TestCaseDescription,TypeOfBrowser,BaseURL,TestScriptID,QueryID,DBConnectionID,DownloadedFileType,DownloadedFileName,ReportHeaderLine,DataStartLine) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

	private final static String	TestCaseEditQuery			= "UPDATE BIRT_TestCase SET ProjectID=?,TestCaseName=?,TestCaseDescription=?,TypeOfBrowser=?,BaseURL=?,TestScriptID=?,QueryID=?,DBConnectionID=?,DownloadedFileType=?,DownloadedFileName=?,ReportHeaderLine=?,DataStartLine=? WHERE TestCaseID = ?";

	private final static String	TestCaseDeleteQuery			= "DELETE FROM BIRT_TestCase WHERE ProjectID = ? AND TestCaseID = ?";

	/*
	 * Object[] - Contents of TestCase saved. Object[0] - String - TestCaseName
	 * Object[1] - Integer - TypeOfBrowser Object[2] - String - BaseURL
	 * Object[3] - Integer - TestScriptID Object[4] - Integer - QueryID
	 * Object[5] - Integer - DBConnectionID Object[6] - Integer -
	 * DownloadedFileType Object[7] - String - DownloadedFileName Object[8] -
	 * Integer - ReportHeaderLine Object[9] - Integer - DataStartLine
	 */
	private final static String	GetTestCaseDetails			= "SELECT TestCaseName," + "TypeOfBrowser," + "BaseURL," + "TestScriptID," + "QueryID," + "DBConnectionID," + "DownloadedFileType,"
																	+ "DownloadedFileName," + "ReportHeaderLine," + "DataStartLine FROM BIRT_TestCase WHERE TestCaseID = ";

	private final static String	strTestCaseDescriptions		= "SELECT TestCaseID, TestCaseName, TestCaseDescription FROM BIRT_TestCase WHERE ProjectID = ";

	private final static String	strIsDuplicateTestCaseName	= "SELECT 1 FROM BIRT_TestCase WHERE TestCaseName = ";

	private final static String	strExistsTestCase			= "SELECT 1 FROM BIRT_TestCase WHERE TestCaseID = ";

	private final static String	strTestCaseNames_Part1		= "SELECT TestCaseName FROM BIRT_TestCase WHERE ProjectID = ";
	private final static String	strTestCaseNames_Part2		= " AND TestCaseID IN ( ";
	private final static String	strTestCaseNames_Part3		= " ) ";

	private final static String	COMMA						= " , ";

	private final static String	strGetTestCaseName			= "SELECT TestCaseName FROM BIRT_TestCase WHERE ProjectID = ? AND TestCaseID = ?";

	public BIRT_Persistence_TestCase_DB_MySQL() throws BIRT_Exception
	{
		super();
	}

	protected void initializeParameters()
	{
		BIRT_Persistence_TestCase_DB_MySQL.strUserName = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_USERNAME);
		BIRT_Persistence_TestCase_DB_MySQL.strPassword = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_PASSWORD);
		BIRT_Persistence_TestCase_DB_MySQL.strDBConnectionServer = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_SERVER);
		BIRT_Persistence_TestCase_DB_MySQL.iDBConnectionPort = Integer.parseInt(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_CONN_PORT));
		BIRT_Persistence_TestCase_DB_MySQL.strDBDatabase = BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_PERSISTENCE_MEDIA_DB_MYSQL_DB_NAME);

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

	public boolean isTestCaseNameDuplicate(String TestCaseName) throws BIRT_Exception
	{

		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strIsDuplicateTestCaseName + SINGLE_QUOTE + TestCaseName + SINGLE_QUOTE);

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

	public boolean saveTestCase(int ProjectID, String TestCaseName, String TestCaseDescription, int TypeOfBrowser, String BaseURL, int TestScriptID, int QueryID, int DBConnectionID,
			int DownloadedFileType, String DownloadedFileName, int ReportHeaderLine, int DataStartLine) throws BIRT_Exception
	{
		try
		{
			PreparedStatement psInsertQuery = getConnection().prepareStatement(TestCaseInsertQuery);
			psInsertQuery.setInt(1, ProjectID);
			psInsertQuery.setString(2, TestCaseName);
			psInsertQuery.setString(3, TestCaseDescription);
			psInsertQuery.setInt(4, TypeOfBrowser);
			psInsertQuery.setString(5, BaseURL);
			psInsertQuery.setInt(6, TestScriptID);
			psInsertQuery.setInt(7, QueryID);
			psInsertQuery.setInt(8, DBConnectionID);
			psInsertQuery.setInt(9, DownloadedFileType);
			psInsertQuery.setString(10, DownloadedFileName);
			psInsertQuery.setInt(11, ReportHeaderLine);
			psInsertQuery.setInt(12, DataStartLine);

			System.out.println(TestCaseInsertQuery + "-" + ProjectID + "-" + TestCaseName + "-" + TestCaseDescription + "-" + TypeOfBrowser + BaseURL + "-" + TestScriptID + "-" + QueryID + "-"
					+ DBConnectionID + "-" + DownloadedFileType + "-" + DownloadedFileName + "-" + ReportHeaderLine + "-" + DataStartLine);

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

	public Object[] getTestCaseDescriptions(int ProjectID) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strTestCaseDescriptions + ProjectID);

			ArrayList<Object> resultObjects = new ArrayList<Object>();

			while (rsQueryResult.next())
			{
				// TestCaseName
				resultObjects.add(new Integer(rsQueryResult.getInt(1)));
				// TestCase Name
				resultObjects.add(rsQueryResult.getString(2));
				// TestCase Description
				resultObjects.add(StringUtility.removeLineBreak(rsQueryResult.getString(3)));
			}

			int no_of_QueryItemsRetrieved = resultObjects.size() / 3;

			int[] iTestCaseIDs = new int[no_of_QueryItemsRetrieved];
			Object[][] objTestCaseDisplayTableData = new Object[no_of_QueryItemsRetrieved][2];

			for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
			{
				// Current Pointer in ArrayList = iCtr*3
				int iCurrentArrayListPtr = iCtr * 3;
				iTestCaseIDs[iCtr] = ((Integer) resultObjects.get(iCurrentArrayListPtr)).intValue();
				objTestCaseDisplayTableData[iCtr][0] = resultObjects.get(iCurrentArrayListPtr + 1);
				objTestCaseDisplayTableData[iCtr][1] = resultObjects.get(iCurrentArrayListPtr + 2);
			}

			return new Object[]
			{ iTestCaseIDs, objTestCaseDisplayTableData };

		}
		catch (SQLException e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_MySQLInstantiationException(BIRT_Resources.getRbAppResourceBundle().getString(BIRT_Resource_PropertyNames.TXT_APP_EXCEPTION_DISPLAYABLE_MSG_MYSQL_INTERNAL_ERROR));
		}

	}

	public Object[] getTestCaseDetails(int TestCaseID) throws BIRT_Exception
	{
		/*
		 * Object[] - Contents of TestCase saved.
		 * Object[0] - String - TestCaseName
		 * Object[1] - Integer - TypeOfBrowser
		 * Object[2] - String - BaseURL
		 * Object[3] - Integer - TestScriptID
		 * Object[4] - Integer - QueryID
		 * Object[5] - Integer - DBConnectionID
		 * Object[6] - Integer - DownloadedFileType
		 * Object[7] - String - DownloadedFileName 
		 * Object[8] - Integer - ReportHeaderLine 
		 * Object[9] - Integer - DataStartLine
		 */

		try
		{
			// System.out.println("Hi, I am inside get case details...");
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(GetTestCaseDetails + TestCaseID);

			Object[] objTestCaseDetail = new Object[10];

			/*
			 * "SELECT TestCaseName," + 
			 * "TypeOfBrowser,"+
			 *  "BaseURL," + 
			 *  "TestScriptID," + 
			 *  "QueryID," + 
			 *  "DBConnectionID," + 
			 *  "DownloadedFileType,"+ 
			 *  "DownloadedFileName," + 
			 *  "ReportHeaderLine," + 
			 *  "DataStartLine 
			 *  FROM BIRT_TestCase 
			 *  WHERE TestCaseID = ";
			 */

			while (rsQueryResult.next())
			{
				//TestCaseName
				objTestCaseDetail[0] = (new String(rsQueryResult.getString(1)));

				//TypeOfBrowser
				objTestCaseDetail[1] = (new Integer(rsQueryResult.getInt(2)));

				//BaseURL
				objTestCaseDetail[2] = new String(rsQueryResult.getString(3));

				//TestScriptID
				objTestCaseDetail[3] = (new Integer(rsQueryResult.getInt(4)));

				//QueryID
				objTestCaseDetail[4] = (new Integer(rsQueryResult.getInt(5)));

				//DBConnectionID
				objTestCaseDetail[5] = (new Integer(rsQueryResult.getInt(6)));

				//DownloadedFileType
				objTestCaseDetail[6] = (new Integer(rsQueryResult.getInt(7)));

				//DownloadedFileName
				objTestCaseDetail[7] = new String(rsQueryResult.getString(8));

				//ReportHeaderLine
				objTestCaseDetail[8] = (new Integer(rsQueryResult.getInt(9)));

				//DataStartLine
				objTestCaseDetail[9] = (new Integer(rsQueryResult.getInt(10)));

			}

			return objTestCaseDetail;

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

	public String[] getTestCaseNames(int ProjectID, int[] TestCaseIDs) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			StringBuffer strQuery = new StringBuffer(strTestCaseNames_Part1 + ProjectID + strTestCaseNames_Part2);

			if (TestCaseIDs.length > 0)
			{
				strQuery.append(TestCaseIDs[0]);

				for (int iCtr = 1; iCtr < TestCaseIDs.length; iCtr++)
					strQuery.append(COMMA + TestCaseIDs[iCtr]);

				strQuery.append(strTestCaseNames_Part3);
				// System.out.println(strQuery.toString());
				ResultSet rsQueryResult = stmtQuery.executeQuery(strQuery.toString());

				ArrayList<Object> resultObjects = new ArrayList<Object>();

				while (rsQueryResult.next())
				{
					// TestCaseName
					resultObjects.add(new String(rsQueryResult.getString(1)));
				}

				int no_of_QueryItemsRetrieved = resultObjects.size();

				String[] strTestCaseNames = new String[no_of_QueryItemsRetrieved];

				for (int iCtr = 0; iCtr < no_of_QueryItemsRetrieved; iCtr++)
				{
					strTestCaseNames[iCtr] = ((String) resultObjects.get(iCtr));
				}

				return strTestCaseNames;

			}
			else
			{
				throw new BIRT_Temp_Exception("There are no testcases in the TestSuite");
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

	public boolean existsTestCase(int TestCaseID) throws BIRT_Exception
	{
		try
		{
			Statement stmtQuery = getConnection().createStatement();

			ResultSet rsQueryResult = stmtQuery.executeQuery(strExistsTestCase + TestCaseID);

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
	

	public String getTestCaseName(int ProjectID, int TestCaseID) throws BIRT_Exception
	{

		PreparedStatement psFetchTestCasetName = null;

		try
		{
			Connection DBConnection = getConnection();

			psFetchTestCasetName = DBConnection.prepareStatement(strGetTestCaseName);

			/*
			 * SELECT TestCaseName 
			 * FROM BIRT_TestCase 
			 * WHERE ProjectID = ? AND TestCaseID = ?
			 */

			psFetchTestCasetName.setInt(2, TestCaseID);
			psFetchTestCasetName.setInt(1, ProjectID);

			// Get TestSteps
			ResultSet rsTestCaseName = psFetchTestCasetName.executeQuery();

			if (rsTestCaseName.next())
			{
				String strTestStepName = rsTestCaseName.getString(1);
				return strTestStepName;
			}
			else throw new BIRT_Temp_Exception("Unable to fetch TestCase. Please try again later");

		}

		catch (Exception e)
		{
			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to fetch TestCase. Please try again later");
		}

	}

	public boolean editTestCase(int ProjectID, int TestCaseID, String TestCaseName, String TestCaseDescription, int TypeOfBrowser, String BaseURL, int TestScriptID, int QueryID, int DBConnectionID,
			int DownloadedFileType, String DownloadedFileName, int ReportHeaderLine, int DataStartLine) throws BIRT_Exception
	{
		try
		{
			PreparedStatement psEditQuery = getConnection().prepareStatement(TestCaseEditQuery);
			psEditQuery.setInt(1, ProjectID);
			psEditQuery.setString(2, TestCaseName);
			psEditQuery.setString(3, TestCaseDescription);
			psEditQuery.setInt(4, TypeOfBrowser);
			psEditQuery.setString(5, BaseURL);
			psEditQuery.setInt(6, TestScriptID);
			psEditQuery.setInt(7, QueryID);
			psEditQuery.setInt(8, DBConnectionID);
			psEditQuery.setInt(9, DownloadedFileType);
			psEditQuery.setString(10, DownloadedFileName);
			psEditQuery.setInt(11, ReportHeaderLine);
			psEditQuery.setInt(12, DataStartLine);
			psEditQuery.setInt(13, TestCaseID);

			if (psEditQuery.executeUpdate() == 1)
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

	public boolean deleteTestCase(int ProjectID, int TestCaseID) throws BIRT_Exception
	{
		try
		{
			/*
			 * DELETE FROM BIRT_TestCase 
			 * WHERE ProjectID = ? 
			 * AND TestCaseID = ?
			 */
			PreparedStatement psEditQuery = getConnection().prepareStatement(TestCaseDeleteQuery);
			psEditQuery.setInt(1, ProjectID);
			psEditQuery.setInt(2, TestCaseID);

			if (psEditQuery.executeUpdate() == 1)
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

}
