package persistence;

import exception.BIRT_Exception;

public interface BIRT_Persistence_Query
{
	/*
	 * Function used to save new Query.
	 * Function has to implement logic to create new Query ID and then save.
	 */
	public boolean saveQuery(int ProjectID, String QueryName, String QueryDescription, String Query) throws BIRT_Exception;

	/*
	 * Function used to save edited Query.
	 * The query with the provided QueryID has to be overwritten. 
	 * Function has to implement logic to create new Query ID and then save.
	 */
	public boolean editQuery(int ProjectID, int QueryID, String QueryName, String QueryDescription, String Query) throws BIRT_Exception;

	public boolean isQueryNameDuplicate(String QueryName) throws BIRT_Exception;

	/*
	 * Contents of Object[]objQueryTableDetails
	 * objQueryTableDetails[QUERYID] =  private static Integer[] iQueryIDs;
	 * objQueryTableDetails[QUERY_TABLE_DATA] = Object[][] objQueryDetailsTableData;
	 * 
	 * Object[][] objQueryDetailsTableData - Two Dimensional Array with Data for each row
	 * Row1Col1, Row1Col2.... Row1Coln
	 * Row2Col1, Row2Col2 ....Row2Coln
	 * .
	 * .
	 * .
	 * RownCol1, RownCol2 .... RownColn 
	 */
	public Object[] getQueryDescriptions(int ProjectID) throws BIRT_Exception;

	/*
	 * Return type String - Query
	 */
	public String getQueryDetail(int QueryID) throws BIRT_Exception;

	/*
	 * Return Type String - Query Description as in DB
	 */
	public String getQueryDescription(int QueryID) throws BIRT_Exception;

	public String getQueryName(int ProjectID, int QueryID) throws BIRT_Exception;

	public boolean deleteQuery(int ProjectID, int QueryID) throws BIRT_Exception;

}
