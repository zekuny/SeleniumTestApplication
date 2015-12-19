package framework.selenium.driver.web;

import logger.BIRT_Logger;
import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;
import framework.selenium.driver.web.firefox.BIRT_FirefoxDriver;
import framework.selenium.driver.web.ie.BIRT_IEDriver;



public class BIRT_WebDriverFactory
{
	private final int	iDriverType;

	public BIRT_WebDriverFactory(int iDriverType)
	{
		this.iDriverType = iDriverType;
	}

	public BIRT_WebDriver getBIRT_WebDriver() throws BIRT_Exception
	{
		if (iDriverType == BIRT_WebDriver.FIREFOX)
		{
			// System.out.println("yes!firefox!");
			return new BIRT_FirefoxDriver();
		}
		else if (iDriverType == BIRT_WebDriver.INTERNET_EXPLORER)
		{
			return new BIRT_IEDriver();
		}

		BIRT_Logger.error("Unknown Browser. Please select valid Web browser to test");
		throw new BIRT_Temp_Exception("Unknown Browser. Please select valid Web browser to test");
	}

}
