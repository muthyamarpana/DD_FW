package testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest {

	Object[][] data;
	XSSFWorkbook wb;
	FileInputStream fis;
	XSSFSheet sheet;
	String src="./src/test/resources/excel/TestData.xlsx";
	@Test(priority=1)
	public void LoginAsBankManager() throws InterruptedException
	{
		Browser browser = getBrowser("chrome");
		navigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
		click("bmlBtn_CSS");
		Thread.sleep(5000);
		Assert.assertTrue(isElementPresent("addCustBtn_CSS"),"Bank Manager not Logged in");
	}
	
//	@DataProvider(name="addData")
//	public void getData() throws IOException
//	{
//		
//		fis=new FileInputStream(src);
//		wb=new XSSFWorkbook(fis);
//		sheet=wb.getSheetAt(0);
//		int rowCount=sheet.getLastRowNum();
//		int colCount=sheet.getRow(0).getLastCellNum();
//		data=new Object[rowCount][colCount];
//		System.out.println("rowCount="+rowCount+"ColumnCount="+colCount);
//		for(int i=0;i<rowCount;i++) //focus Row
//		{
//			for(int j=0;j<colCount;j++)
//			{
//				//data[i][j]=
//			}
//		}
//		wb.close();
//		
//	}
//	@Test(priority=2)
//	public void addCustomer() throws InterruptedException
//	{
//		click("addCustBtn_CSS");
//		type("firstName_CSS", "John");
//		Thread.sleep(2000);
//		type("lastName_XPATH","Smith");
//		Thread.sleep(2000);
//		type("postCode_CSS","500011");
//		Thread.sleep(2000);
//		click("addBtn_CSS");
//		
//	}
	
	
}
