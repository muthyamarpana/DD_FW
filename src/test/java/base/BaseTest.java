package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.Playwright;

import extentListeners.ExtentListeners;

public class BaseTest {
	
	//Initialization
	private Playwright pl_wright;
	public Browser browser;
	public Page page;
	private static Properties OR = new Properties();
	private static FileInputStream fis;
	private Logger log = Logger.getLogger(this.getClass());
	private static ThreadLocal<Playwright> pw = new ThreadLocal<>();
	private static ThreadLocal<Browser> br = new ThreadLocal<>();
	private static ThreadLocal<Page> pg = new ThreadLocal<>();
	
	public static Playwright getPlayWright()
	{
		return pw.get();
		
	}
	public static Browser getBrowser()
	{
		return br.get();
		
	}
	public static Page getPage()
	{
		return pg.get();
		
	}
	
	//setup Log4j and properties
	@BeforeSuite
	public void setUp()
	{
	PropertyConfigurator.configure("./src/test/resources/properties/Log4j2.properties");
	log.info("Test Execution Started..");
	try {
		fis=new FileInputStream("src/test/resources/properties/OR.properties");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		OR.load(fis);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	//methods 
	public void click(String locatorKey)
	{
		try
		{
		page.locator(OR.getProperty(locatorKey)).click();
		log.info("Clicking on Element: "+locatorKey);
		ExtentListeners.getExtent().info("Clicking on Element: "+locatorKey);
		}
		catch(Throwable t)
		{
			log.error("Error while clicking on an element: "+t.getMessage());
			ExtentListeners.getExtent().fail("Error while clicking on an element: \"+t.getMessage()");
			Assert.fail(t.getMessage());
		}
	}
	public boolean isElementPresent(String locatorKey)
	{
		try {
		getPage().waitForSelector(OR.getProperty(locatorKey));
		
		//getPage().waitForSelector(OR.getProperty(locatorKey), new WaitForSelectorOptions().setTimeout(2000));
		log.info("Found an Element: "+locatorKey);
		ExtentListeners.getExtent().info("Element found: "+locatorKey);
		return(true);
		}
		catch(Throwable t) 
		{
			log.error("Error while finding an element: "+locatorKey);
			ExtentListeners.getExtent().fail("Error while finding  an element: "+locatorKey);
			return(false);
		}
	}
	//typing in a textbox
	public void type(String locatorKey,String value)
	{
		try
		{
		page.locator(OR.getProperty(locatorKey)).fill(value);
		log.info("Typing in an Element: "+locatorKey+" and entered value as: "+value);
		ExtentListeners.getExtent().info("Typing in an Element: "+locatorKey+" and entered value as: "+value);
		}
		catch(Throwable t)
		{
			log.error("Error while typing in an element: "+t.getMessage());
			ExtentListeners.getExtent().fail("Error while typing in an element: \"+t.getMessage()");
			Assert.fail(t.getMessage());
		}
	}
	
	//Launch Browser
	public Browser getBrowser(String browserName)
	{
		System.out.println("Launch Browser...");
		pl_wright=Playwright.create();
		pw.set(pl_wright);
		switch(browserName)
		{
		case "chrome":
			System.out.println("Launching Chrome Browser...");
			log.info("Launching Chrome Browser...");
			return getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		case "firefox":
			System.out.println("Launching Firefox Browser...");
			log.info("Launching Firefox Browser...");
			return getPlayWright().firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));
		default:
			throw new IllegalArgumentException();
		}
	}
	//navigate to Application
	public void navigate(Browser browser, String url)
	{
		this.browser=browser;
		br.set(browser);
		page=getBrowser().newPage();
		pg.set(page);
		getPage().navigate(url);
		log.info("Navigated to :"+url);
	}
	
	//Quit Application
	@AfterTest
	public void quit()
	{
		getBrowser().close();
		getPage().close();
		getPlayWright().close();
		log.info("All connections are closed...");
	}
	
	

}
