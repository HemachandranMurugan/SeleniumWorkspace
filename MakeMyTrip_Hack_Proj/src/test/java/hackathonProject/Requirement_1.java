package hackathonProject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import fileReading.ConfigParser;
import fileReading.ExcelUtils;
import initialSetUp.DriverFactory;
import initialSetUp.DriverSetup;
import initialSetUp.WaitingClass;
import pages.AdvPopUpModelPage;
import pages.CabSelectionPage;
import pages.CabsFilterPage1;
import pages.CabsFilterPage2;
import pages.LoginPage;
import pages.OneWayRoundTripPage;

public class Requirement_1 {		
	
	WebDriver driver;
	ConfigParser parser;
	WaitingClass wait;
	LoginPage login;
	CabSelectionPage cabSelection;
	OneWayRoundTripPage oneWayRoundTrip;
	AdvPopUpModelPage advPopUp;
	CabsFilterPage1 cabsFilter1;
	CabsFilterPage2 cabsFilter2;
	
	@BeforeClass
	@Parameters({"Browser"})
	public WebDriver setUP(String browser) throws Exception {
		driver = DriverSetup.getDriver(browser);
		DriverFactory.setDriver(driver);
		parser = new ConfigParser("src/test/resources/config.properties");
		
		driver.get(parser.getObjectLocator("baseUrl"));
		
		wait = new WaitingClass(driver);
		login = new LoginPage(driver);
		cabSelection = new CabSelectionPage(driver);
		oneWayRoundTrip = new OneWayRoundTripPage(driver);
		advPopUp = new AdvPopUpModelPage(driver);
		cabsFilter1 = new CabsFilterPage1(driver);
		cabsFilter2 = new CabsFilterPage2(driver);
		
		return driver;
	}
	
	@DataProvider(name ="getValiddata")
	public  String[][] getExcelData() throws Exception{
		String fileName = parser.getObjectLocator("excelFilePath");
		String sheetName = parser.getObjectLocator("excelSheetName");
		
		return ExcelUtils.readExcelFile(fileName, sheetName);
	}
	
	@Test(priority =1)
	public void loginTest() {
		login.clickCancelButton();
	}
	
	@Test(priority =2,dependsOnMethods = "loginTest")
	public void cabselectionTest() {
		cabSelection.clickCabIcon();
	}
	
	@Test(priority =3,dependsOnMethods = "cabselectionTest", dataProvider ="getValiddata")
	public void OnewayTripTest(String Departure, String Destination, String suggestion, String date, String time) throws Exception {
		oneWayRoundTrip.selectDeparture(Departure);
		oneWayRoundTrip.selectDestination(Destination,suggestion);
		oneWayRoundTrip.selectDepatureDate(date);
		oneWayRoundTrip.selectPickupTime(time);
		oneWayRoundTrip.clickSearch();
	}
	
	@Test(priority =4,dependsOnMethods = "OnewayTripTest")
	public void filtercab() {
		advPopUp.closePopUP();
		cabsFilter1.selectPricing();
		cabsFilter2.selectPricing();	
	}
	
	@AfterClass
	public void closeBrowser() {
		 if (driver != null) {
	            driver.quit();
	        }
	}
}
