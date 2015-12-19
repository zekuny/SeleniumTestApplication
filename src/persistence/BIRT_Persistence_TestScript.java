package persistence;

import exception.BIRT_Exception;

public interface BIRT_Persistence_TestScript
{
	/*
	 * Object TestSteps[][] Sequence No1, Command1, Method1, Target1, Value1
	 * Sequence No2, Command2, Method2, Target2, Value2 Sequence No3, Command3,
	 * Method3, Target3, Value3 Sequence No4, Command4, Method4, Target4, Value4
	 */

	// public static String[][] SeleniumMethodsforCommands = {};
	public boolean saveTestScriptNTestSteps(int ProjectID, String TestScriptName, String TestScriptDescription, Object[][] TestSteps) throws BIRT_Exception;

	public boolean editTestScriptNTestSteps(int ProjectID, int TestScriptID, String TestScriptName, String TestScriptDescription, Object[][] TestSteps) throws BIRT_Exception;

	public boolean isTestScriptNameDuplicate(String TestScriptName) throws BIRT_Exception;

	public Object[] getTestScriptDescriptions(int ProjectID) throws BIRT_Exception;

	/*
	 * Object[] - Contains more than one objSingleTestStep;
	 * 
	 * and Each TestStep is as follows
	 * Object[] objSingleTestStep = new Object[5];
	 * objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_SEQNO];
	 * objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_COMMAND];
	 * objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_METHOD];
	 * objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_TARGET];
	 * objSingleTestStep[BIRT_DataObject.TESTSTEP_OBJECT_VALUE];
	 */
	public Object[] getTestStepsforTestScript(int ProjectID, int TestScriptID) throws BIRT_Exception;

	public String getTestScriptName(int ProjectID, int TestScriptID) throws BIRT_Exception;

	public boolean deleteTestScript(int ProjectID, int TestScriptID) throws BIRT_Exception;

}
