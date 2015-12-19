package framework.selenium.driver.web;


import org.openqa.selenium.WebDriver;

public interface BIRT_WebDriver
{

	public final static String[]	SUPPORTEDWEBBROWSERS		=
																{ "Firefox", "InternetExplorer" };
	public final static int			FIREFOX						= 0;
	public final static int			INTERNET_EXPLORER			= 1;

	

	public WebDriver getWebDriver();

	public String getBaseUrl();

	public void setBaseUrl(String baseUrl);

	public void tearDown();

}
