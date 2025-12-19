package extentListeners;

import java.nio.file.Paths;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;

import base.BaseTest;

public class ExtentManager {
public static ExtentReports extent;
public static String filename;
public static ExtentReports createInstance(String fileName)
{
	ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	htmlReporter.config().setTheme(Theme.STANDARD);
	htmlReporter.config().setDocumentTitle(fileName);
	htmlReporter.config().setEncoding("utf-8");
	htmlReporter.config().setReportName(fileName);
	
	extent=new ExtentReports();
	extent.attachReporter(htmlReporter);
	extent.setSystemInfo("Automation Tester", "Jack");
	extent.setSystemInfo("Organization", "Online Training");
	extent.setSystemInfo("Build No", "Way to online");	
	return extent;
	
}
public static void captureScreenshot()
{
	Date d = new Date();
	filename = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
	BaseTest.getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./reports/"+filename)));
}
}
