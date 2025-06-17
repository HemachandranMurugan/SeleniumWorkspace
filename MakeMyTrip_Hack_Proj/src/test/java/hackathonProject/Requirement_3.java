package hackathonProject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import fileReading.ConfigParser;
import initialSetUp.DriverFactory;
import initialSetUp.DriverSetup;
import pages.HotelSelection;
import pages.LoginPage;

public class Requirement_3 {
	
	WebDriver driver;
	ConfigParser parser;
	
	LoginPage log;
	HotelSelection hs;
	
	@BeforeClass
	@Parameters({"Browser"})	
	public void createDriver(String browser) throws IOException {
		driver=DriverSetup.getDriver(browser);
		DriverFactory.setDriver(driver);
		
		parser = new ConfigParser("src/test/resources/config.properties");
		driver.get(parser.getObjectLocator("baseUrl"));
		
		log=new LoginPage(driver);
		hs =new HotelSelection(driver);
	}
	
	@Test(priority=0)
	public void login() {
		log.clickCancelButton();
	}
	
	@Test(priority=1)
	public void printGuests() {
		HotelSelection hs=new HotelSelection(driver);
		hs.clickHotelBtn();
		hs.clickGuestBtn();
		hs.clickAdultDropdown();
		hs.getAdultOptions();
		hs.printAdultOptions();
	}
	
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}

