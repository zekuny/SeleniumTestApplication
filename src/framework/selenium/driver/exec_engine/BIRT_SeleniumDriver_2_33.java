package framework.selenium.driver.exec_engine;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import logger.BIRT_Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.anugraha.birt.app.BIRT_AppProperty;

import exception.BIRT_Exception;
import exception.BIRT_ExceptionHandler;
import exception.BIRT_Temp_Exception;
import framework.selenium.driver.web.BIRT_WebDriver;

public class BIRT_SeleniumDriver_2_33 implements BIRT_SeleniumDriver
{

	private static Robot	objRobot;

	public BIRT_SeleniumDriver_2_33()
	{
		super();
		try
		{
			objRobot = new Robot();
		}
		catch (AWTException ex)
		{

			if(BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(ex);
			BIRT_Logger.error(ex.getMessage());
			BIRT_ExceptionHandler.handleFatalError(new BIRT_Temp_Exception("Unable to initialize app for Proper execution of commands. Please try again later"));
		}
	}
// Zekun
//	public boolean executeDriverCommand(WebDriver driver, String Command, String DriverMethod, String Value, String Target) throws BIRT_Exception
	public boolean executeDriverCommand(BIRT_WebDriver driver, String Command, String DriverMethod, String Value, String Target) throws BIRT_Exception
	{
		// System.out.println("The first time I come here...");
		// System.out.println(Command + " - " + DriverMethod + " - " + Value + " - " + Target);
		if (DriverCommands[ROBOT_KEY_PRESS].equals(Command))
		{
			executeRobotCommand(DriverMethod);
			return true;
		}
		else if (DriverCommands[THREAD_WAIT].equals(Command))
		{
			try
			{
				long currentTime = System.currentTimeMillis();
				long endTime = currentTime + (Integer.parseInt(Value));
				while (System.currentTimeMillis() < endTime)
				{

				}
			}
			catch (NumberFormatException e)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				BIRT_Logger.error(e.getMessage());
				BIRT_ExceptionHandler.handleFatalError(new BIRT_Temp_Exception("Unable to initialize app for Proper execution of commands. Please try again later"));
			}
			/*			catch (InterruptedException e)
						{
							if (BIRT_AppProperty.PRINT_STACK_TRACE)
								e.printStackTrace();
							BIRT_ExceptionHandler.handleFatalError(new BIRT_Temp_Exception("Unable to initialize app for Proper execution of commands. Please try again later"));
						}*/
			return true;
		}
		else if (DriverCommands[SELENIUM_COMMAND_OPENPAGE].equals(Command))
		{
			// System.out.println("I am here to open page... " + Target);
			//driver.getWebDriver().get(driver.getBaseUrl() + Target);
			driver.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.getWebDriver().navigate().to(Target);
			// System.out.println("Successfully open page...");
			return true;
		}
		else if (DriverCommands[SELENIUM_COMMAND_SWITCH_TO_FRAME].equals(Command))
		{
			try
			{
				// Zekun_change
				// int frameCount=Integer.parseInt(Target);
				// driver.getWebDriver().switchTo().frame(frameCount);
				for(String winHandle : driver.getWebDriver().getWindowHandles()){
				    driver.getWebDriver().switchTo().window(winHandle);
				}
			}
			catch(Exception e)
			{
				if(BIRT_AppProperty.PRINT_STACK_TRACE)
					BIRT_Logger.printStackTrace(e);
				driver.getWebDriver().switchTo().frame(Target);
			}
		}
		else if (DriverCommands[SELENIUM_COMMAND_SWITCH_TO_DEFAULTCONTENT].equals(Command))
		{
			driver.getWebDriver().switchTo().defaultContent();
		}
		else if (DriverCommands[SELENIUM_COMMAND_SCROLL_DOWN].equals(Command))
		{
			((JavascriptExecutor) driver).executeScript("scroll(0,"+Value.trim()+");");
		}
		else if (DriverCommands[SELENIUM_COMMAND_SCROLL_UP].equals(Command))
		{
			((JavascriptExecutor) driver).executeScript("scroll(0,-"+Value.trim()+");");
		}
		else
		{
			// System.out.println("I am trying to click...1");
//			try{
//				driver.getWebDriver().findElement(By.xpath(".//*[@id='gbw']/div/div/div[1]/div[2]/a")).click();
//				
//			}catch(Exception e){
//				e.printStackTrace();
//			}
			WebElement wbElement = findby(driver.getWebDriver(), DriverMethod, Target);
			// System.out.println("I am trying to click...3");
			if(wbElement == null){
				return false;
			}
			executeCommand(wbElement, Command, Value);
		}

		return true;
	}

	private void executeRobotCommand(String driverMethod)
	{
		if (KeyPressMethods[KEYPRESS_ENTER].equals(driverMethod))
		{
			objRobot.keyPress(KeyEvent.VK_ENTER);
		}
		else if (KeyPressMethods[KEYPRESS_S].equals(driverMethod))
		{
			objRobot.keyPress(KeyEvent.VK_S);
		}
		else if (KeyPressMethods[KEYPRESS_ALT_S].equals(driverMethod))
		{
			
			objRobot.keyPress(KeyEvent.VK_ALT);
			objRobot.keyPress(KeyEvent.VK_S);
			objRobot.keyRelease(KeyEvent.VK_ALT);
		}

	}

	public boolean executeCommand(WebElement element, String Command, String Value)
	{
		if (DriverCommands[SELENIUM_COMMAND_CLICK].equals(Command))
		{
			// System.out.println("I am trying to click...4");
			try{
				element.click();
			}catch(Exception e){
				e.printStackTrace();
			}
			return true;
		}
		else if (DriverCommands[SELENIUM_COMMAND_CLEAR].equals(Command))
		{
			element.clear();
			return true;
		}
		else if (DriverCommands[SELENIUM_COMMAND_TYPE].equals(Command))
		{
			// System.out.println("I am trying to send key...");
			try{
				element.sendKeys(Value);
			}catch(Exception e){
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public WebElement findby(WebDriver webDriver, String SeleniumMethod, String Target) throws BIRT_Exception
	{
		if (SeleniumMethods[SELENIUM_METHOD_XPATH].equals(SeleniumMethod))
		{
			// System.out.println("I am trying to click...2");
			WebElement e = null;
			try{
				e = webDriver.findElement(By.xpath(Target));
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return e;
//			return webDriver.findElement(By.xpath(Target));
		}
		else if (SeleniumMethods[SELENIUM_METHOD_CSS].equals(SeleniumMethod))
		{
			return webDriver.findElement(By.cssSelector(Target));
		}
		else if (SeleniumMethods[SELENIUM_METHOD_ID].equals(SeleniumMethod))
		{
			// System.out.println("I am trying to get element by id...");
			return webDriver.findElement(By.id(Target));
		}
		else if (SeleniumMethods[SELENIUM_METHOD_LINK].equals(SeleniumMethod))
		{
			return webDriver.findElement(By.linkText(Target));
		}
		else if (SeleniumMethods[SELENIUM_METHOD_NAME].equals(SeleniumMethod))
		{
			System.out.println("for test purpose");
			return webDriver.findElement(By.name(Target));
		}
		else
			{
			throw new BIRT_Temp_Exception("Unable to find element - " + Target);
			}
	}

}
