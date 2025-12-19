package rough;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class DoUserReg extends BaseTest{
	@Test(priority=1)
	public void doLogin1() throws InterruptedException
	{
		Browser browser=getBrowser("chrome");
		navigate(browser, "https://google.com");
		type("searchBox","Playwright");
		Thread.sleep(5000);
		
	}
	@Test(priority=2)
	public void doGmailLogin1() throws InterruptedException
	{
		Browser browser=getBrowser("firefox");
		navigate(browser, "https://mail.rediff.com/cgi-bin/login.cgi");
		type("username","Playwright");
		type("password","play123");
		Thread.sleep(5000);
		
	}
}
