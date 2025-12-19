package testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class AddCustomerTest extends BaseTest {
	Object[][] data;
	XSSFWorkbook wb;
	FileInputStream fis;
	XSSFSheet sheet;
	
	String src="./src/test/resources/excel/TestData.xlsx";
	@DataProvider(name="data")
	public Object[][] getData() throws IOException
	{
		try {
			fis=new FileInputStream(src);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb=new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet=wb.getSheetAt(0);
		int rowCount=sheet.getLastRowNum();
		int colCount=sheet.getRow(0).getLastCellNum();
		data=new Object[rowCount][colCount];
		System.out.println("rowCount="+rowCount+"  ColumnCount="+colCount);	
		DataFormatter formatter = new DataFormatter();
		//read from excel..
		for(int i=0;i<rowCount;i++) //Focus Row
		{
			for(int j=0;j<colCount;j++)
			{
				
				data[i][j]=formatter.formatCellValue(sheet.getRow(i+1).getCell(j));
				System.out.println(formatter.formatCellValue(sheet.getRow(i+1).getCell(j)));
			}
		}
		wb.close();
		return data;
	}
	@Test(priority=1)
	@Parameters("url")
	public void geturl(String appnUrl)
	{
		Browser browser = getBrowser("chrome");
		navigate(browser,appnUrl);
		click("bmlBtn_CSS");
		click("addCustBtn_CSS");
	}
	
	@Test(dataProvider = "data",priority=2)
	public void addCustomers(String fName,String lName,String pCode) throws InterruptedException
	{
//		Browser browser = getBrowser("chrome");
//		navigate(browser,"https://www.way2automation.com/angularjs-protractor/banking/#/login");
//		click("bmlBtn_CSS");
//		click("addCustBtn_CSS");
		type("firstName_CSS", fName);
		Thread.sleep(2000);
		type("lastName_XPATH",lName);
		Thread.sleep(2000);
		type("postCode_CSS",pCode);
		Thread.sleep(2000);
		click("addBtn_CSS");
	}

}
