package main;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RediffTest {

    static WebDriver driver;
    BaseTest base = new BaseTest();

    HomePage home;
    CreateAccountPage account;
    TermsPage terms;
    String parentHandle;

    @BeforeClass
    public void createDriver() throws Exception {
        
    	driver = base.setUp("chrome");
        home = new HomePage(driver);
        account = new CreateAccountPage(driver);
        terms = new TermsPage(driver);
        parentHandle = driver.getWindowHandle();
    }
    
	@Test
	public void clickCreateAccount() {
		home.clickCreateAccount();
		System.out.println("creat acc link ---> clicked");
	}
		
	@Test
	public void cntNumberOfLinks() {
		System.out.println("Number of links with href: " + account.countHrefLinks());
	}
	
	@Test
	public void handleTermsWindow() {
		account.clickTermsLink();
		System.out.println("Temrms and conditions link ---> clicked");

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				TermsPage terms = new TermsPage(driver);
				System.out.println("Child Window Title: " + terms.getTitle());
				System.out.println("Terms header displayed: " + terms.isTermsHeaderDisplayed());
				System.out.println("Title match: " + terms.getTitle().equals("Rediffmail: Terms and Conditions"));
				driver.close();
				break;
			}
		}
		driver.switchTo().window(parentHandle);
	}
	
	@AfterClass()
	public void closeBrowser() {
		base.tearDown();
	}
}




