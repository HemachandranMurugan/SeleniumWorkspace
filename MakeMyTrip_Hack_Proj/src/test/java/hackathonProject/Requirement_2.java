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
import pages.CabSelectionPage;
import pages.FormPage;
import pages.GiftCardSelection;
import pages.HoverPage;
import pages.InterestPage;
import pages.LoginPage;

public class Requirement_2 {

    WebDriver driver;
    ConfigParser parser;
    
    LoginPage log;
    CabSelectionPage cs;
    HoverPage hp;
    GiftCardSelection gcs;
    InterestPage ip;
    FormPage fp;
    
    @BeforeClass
    @Parameters({"Browser"})
    public void createDriver(String browser) throws IOException {
        driver = DriverSetup.getDriver(browser);
        DriverFactory.setDriver(driver);
        
		parser = new ConfigParser("src/test/resources/config.properties");
		driver.get(parser.getObjectLocator("baseUrl"));
        
    	cs = new CabSelectionPage(driver);
        hp = new HoverPage(driver);
        ip = new InterestPage(driver);
        fp = new FormPage(driver);
        gcs = new GiftCardSelection(driver);
        log = new LoginPage(driver);
    }

    @Test(priority = 0)
    public void testLogin() {

        log.clickCancelButton();
    }

    @Test(priority = 1 , dependsOnMethods="testLogin")
    public void testCabSelection() {

        cs.clickCabIcon();
        cs.clickSearchButton();
    }

    @Test(priority = 2 , dependsOnMethods="testCabSelection")
    public void testHoverPage() {

        hp.clickArrowDown();
        hp.clickGiftCards();
    }

    @Test(priority = 3, dependsOnMethods="testHoverPage")
    public void testGiftCardSelection() {
        gcs.clickCorporateGiftImage();
    }

    @Test(priority = 4 , dependsOnMethods="testGiftCardSelection")
    public void testInterested() {
        ip.switchToNewWindow();
        ip.clickInterestedButton();
    }

    @Test(priority = 5 , dependsOnMethods="testInterested")
    public void testFormFilling() {
        fp.enterEmail("qwerty@uiop");
        fp.clickSubmit();
        fp.getEmailErrorMessage();
    }


    @AfterClass
    public void tearDown() {
    	 if (driver != null) {
             driver.quit();
         }
    }
}
