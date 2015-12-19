package framework.selenium.driver.exec_engine;

import org.openqa.selenium.WebDriver;

import exception.BIRT_Exception;
import framework.selenium.driver.web.BIRT_WebDriver;

public interface BIRT_SeleniumDriver {

	/*
	 * Lists index of commands in SeleniumCommands Array
	 */

	public static int ROBOT_KEY_PRESS = 0;
	public static int THREAD_WAIT = 1;
	public static int SELENIUM_COMMAND_OPENPAGE = 2;
	public static int SELENIUM_COMMAND_CLICK = 3;
	public static int SELENIUM_COMMAND_CLEAR = 4;
	public static int SELENIUM_COMMAND_TYPE = 5;
	public static int SELENIUM_COMMAND_SWITCH_TO_FRAME = 6;
	public static int SELENIUM_COMMAND_SWITCH_TO_DEFAULTCONTENT = 7;
	public static int SELENIUM_COMMAND_SCROLL_DOWN = 8;
	public static int SELENIUM_COMMAND_SCROLL_UP = 9;

	/*
	 * Lists all the Selenium Commands Supported.
	 */

	/*
	 * Required to be modified to provide only Methods supported by each of
	 * these commands. Need to implement SeleniumMethodsforCommands - Method
	 * available for each command Now it contains all the methods available.
	 * User needs to know which method to use it with what command
	 */

	public static String[] SeleniumMethods = { "XPATH", "ID", "CSS", "LINK",
			"NAME" };
	public static int SELENIUM_METHOD_XPATH = 0;
	public static int SELENIUM_METHOD_ID = 1;
	public static int SELENIUM_METHOD_CSS = 2;
	public static int SELENIUM_METHOD_LINK = 3;
	public static int SELENIUM_METHOD_NAME = 4;

	public static int KEYPRESS_ENTER = 0;
	public static int KEYPRESS_S = 1;
	public static int KEYPRESS_ALT_S = 2;
	public static String[] KeyPressMethods = { "Key Press ENTER",
			"KEY Press S", "KEY Alt+S" };

	public static String[] WaitMethods = { "" };

	public static String[][] MethodsList = { KeyPressMethods, WaitMethods,
			SeleniumMethods };
	/*
	 * Key Press - Robot Event Rest all are selenium Commands
	 */
	public static String[] DriverCommands = { "Key Press", "Wait (in ms)",
			"Open Page", "Click", "Clear", "Type", "Switch to Frame",
			"Switch to DefaultContent", "Scroll Down (No. Of Pixels)",
			"Scroll Up (No. Of Pixels)" };

	/*
	 * Mention whether the Value TextField must be displayed for the chosen
	 * Selenium Command
	 */

	public static boolean[] isValueFieldVisibleForDriverCommands = { false,
			true, false, false, false, true, false, false, true, true };

	/*
	 * Mention whether the Method Combobox must be displayed for the chosen
	 * Selenium Command
	 */

	public static boolean[] isMethodFieldVisibleForDriverCommands = { true,
			false, false, true, true, true, false, false, false, false };

	public static boolean[] isTargetFieldVisibleForDriverCommands = { false,
			false, true, true, true, true, true, false, false, false };

	public boolean executeDriverCommand(BIRT_WebDriver driver, String Command,
			String SeleniumMethod, String Value, String Target)
			throws BIRT_Exception;
//	public boolean executeDriverCommand(WebDriver driver, String Command,
//			String SeleniumMethod, String Value, String Target)
//			throws BIRT_Exception;
}
