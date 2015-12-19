package framework.selenium.driver.exec_engine;

public class BIRT_SeleniumDriverFactory
{

	private static BIRT_SeleniumDriver	objBIRT_SeleniumDriver	= null;

	public static BIRT_SeleniumDriver getBIRT_SeleniumDriver()
	{
		if (null == objBIRT_SeleniumDriver)
			objBIRT_SeleniumDriver = new BIRT_SeleniumDriver_2_33();
		return objBIRT_SeleniumDriver;

	}

}
