package main;

import org.openqa.selenium.WebDriver;
public class BaseTest {
    
	public static WebDriver driver;
    static String baseUrl = "https://www.rediff.com/";
    
    public WebDriver setUp(String browser) throws Exception {
		switch(browser.toLowerCase()) {
			case "chrome":
				driver = DriverSetup.getChromeDriver();
				break;
			case "edge" : 
				driver = DriverSetup.getEdgeDriver();
				break;	
			default: break;
		}
		driver.get(baseUrl);
		return driver;
	}
    
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
