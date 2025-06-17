package miniProjectSelenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchSchoolTest {
	private WebDriver driver;
	SearchSchoolAction obj;
	ConfigParser parser;
	@BeforeClass
	@Parameters({"browser"})
	public WebDriver setUp(String browser) throws Exception {
		switch(browser.toLowerCase()) {
			case "chrome":driver = DriverSetup.getChromeDriver();break;
			case "edge" : driver = DriverSetup.getEdgeDriver();break;	
			default: break;
		}
		
		DriverFactory.setDriver(driver);
		
		parser = new ConfigParser("src/test/resources/config.properties");
		
		driver.get(parser.getObjectLocator("baseUrl"));
		obj = new SearchSchoolAction(driver,parser);
		return driver;
	}
	
	@DataProvider(name ="getValidData")
	public String[][] getExcelData() throws Exception{
		String fileName = parser.getObjectLocator("excelFilePath");
		String sheetName = parser.getObjectLocator("excelSheetName");
		
		return ExcelUtils.readExcelFile(fileName, sheetName);
	}
	
	@Test(priority =1)
	public void clickSchoolTest() {
		obj.setSchools();
	}
	
	@Test(priority=2, dataProvider = "getValidData",dependsOnMethods = "clickSchoolTest")
	public void setAttributesTest(String course,String city) throws Exception{
		obj.setCourses(course);
		obj.setCity(city);
	}
	
	@Test(priority =3, dependsOnMethods="setAttributesTest")
	public void clickSearchLink() {
		obj.search();
	}
	
	@Test(priority =4, dependsOnMethods="clickSearchLink")
	public void takeScreenshotTest() throws Exception {
		obj.takeScreenShot();
	}
	
	@AfterClass
	@Parameters({"browser"})
	public void closeBrowser(String browser) {
		System.out.println(browser + "_Test Completed");
		DriverFactory.quitDriver();
	}
}
