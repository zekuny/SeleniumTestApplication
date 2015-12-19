package framework.selenium.driver.web.ie;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.anugraha.birt.app.BIRT_AppProperty;

import framework.selenium.driver.web.BIRT_WebDriver;

public class BIRT_IEDriver implements BIRT_WebDriver
{

	private WebDriver	wbIEDriver	= null;
	private String		baseUrl		= null;

	public BIRT_IEDriver()
	{
		//Setting System property
		System.setProperty("webdriver.reap_profile", "false");
		System.setProperty("webdriver.ie.driver", BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_IEDRIVER_EXE_LOCATION));

		DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
		capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		wbIEDriver = new InternetExplorerDriver(capab);

		wbIEDriver.manage().timeouts().implicitlyWait(BIRT_AppProperty.SELENIUM_IMPLICIT_TIMEOUT, BIRT_AppProperty.SELENIUM_TIME_UNIT);
	}

	public String getBaseUrl()
	{
		return baseUrl;
	}

	public void tearDown()
	{
		wbIEDriver.quit();
		//if(wbIEDriver!=null)
		//wbIEDriver.quit();
	}

	public WebDriver getWebDriver()
	{
		return wbIEDriver;
	}

	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;

	}
}
