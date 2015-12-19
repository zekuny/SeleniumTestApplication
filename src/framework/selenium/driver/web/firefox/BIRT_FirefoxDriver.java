package framework.selenium.driver.web.firefox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import framework.selenium.driver.web.BIRT_WebDriver;



public class BIRT_FirefoxDriver implements BIRT_WebDriver
{

//	private final File				fFirefoxBinaryFile = null;
//	private final File				fFirefoxProfileDir = null;
//	private final FirefoxBinary		fbFirefoxBinary = null;
//	private final FirefoxProfile	fpFirefoxProfile = null;
//	// Zekun changed it to WebDriver
	private WebDriver				wbFirefoxDriver	= null;
	private String					baseUrl			= null;

	public BIRT_FirefoxDriver()
	{
		//Setting up Firefox Driver
//		fFirefoxBinaryFile = new File(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_FIREFOX_EXE_LOCATION));
//		fFirefoxProfileDir = new File(BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_FIREFOX_PROFILE_DIR));
//
//		fbFirefoxBinary = new FirefoxBinary(fFirefoxBinaryFile);
//		fpFirefoxProfile = new FirefoxProfile(fFirefoxProfileDir);

		//Setting up profile preferences
//		fpFirefoxProfile.setAcceptUntrustedCertificates(true);
//		fpFirefoxProfile.setAssumeUntrustedCertificateIssuer(false);
//		fpFirefoxProfile.setPreference("browser.download.dir", (BIRT_AppProperty.getProperty(BIRT_AppProperty.PROP_REPORT_DOWNLOAD_DIR)).replaceAll("/", "\\\\"));
//		fpFirefoxProfile.setPreference("browser.download.folderList", 2);
//		fpFirefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
//		fpFirefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
//		fpFirefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);

		//fpFirefoxProfile.setPreference("browser.link.open_newwindow", 1);

		//fpFirefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");

//		String strAppsDwnldWithoutPrmt = "application/x-msdos-program" + ",application/x-unknown-application-octet-stream" + ",application/vnd.ms-powerpoint" + ",application/excel"
//				+ ",application/vnd.ms-publisher" + ",application/x-unknown-message-rfc822" + ",application/vnd.ms-excel" + ",application/msword" + ",application/x-mspublisher" + ",application/x-tar"
//				+ ",application/zip" + ",application/x-gzip" + ",application/x-stuffit" + ",application/vnd.ms-works" + ",application/powerpoint" + ",application/rtf" + ",application/postscript"
//				+ ",application/x-gtar" + ",video/quicktime" + ",video/x-msvideo" + ",video/mpeg" + ",audio/x-wav" + ",audio/x-midi" + ",audio/x-aiff" + ",application/pdf" + ",application/x-excel"
//				+ ",application/x-msexcel";
//
//		fpFirefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", strAppsDwnldWithoutPrmt);

		//Setting System property
		//System.setProperty("webdriver.reap_profile", "false");

		// Zekun
		//wbFirefoxDriver = new FirefoxDriver(fbFirefoxBinary, fpFirefoxProfile);
		FirefoxProfile profile = new FirefoxProfile();
		// To prevent 
		profile.setPreference("browser.download.panel.shown", false);
		//profile.setPreference("browser.helperApps.neverAsk.openFile","text/csv,application/vnd.ms-excel");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/vnd.ms-excel");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/xml,text/plain,text/xml,image/jpeg,text/csv");
	
		String path = "/Users/zekuny/Desktop/BIReport";
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", path);
        profile.setPreference("browser.download.alertOnEXEOpen", false);
        //profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.closeWhenDone", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
		wbFirefoxDriver = new FirefoxDriver(profile);
		//wbFirefoxDriver.manage().timeouts().implicitlyWait(BIRT_AppProperty.SELENIUM_IMPLICIT_TIMEOUT, BIRT_AppProperty.SELENIUM_TIME_UNIT);

	}

	public String getBaseUrl()
	{
		return baseUrl;
	}

	public void tearDown()
	{
		//wbFirefoxDriver.quit();
		if (wbFirefoxDriver != null)
		{
			wbFirefoxDriver.quit();
		}
	}

	public WebDriver getWebDriver()
	{
		return wbFirefoxDriver;
	}

	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;

	}
}
