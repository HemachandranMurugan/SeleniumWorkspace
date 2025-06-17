package miniProjectSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverSetup {
	static WebDriver driver;

	public static WebDriver getChromeDriver() {

		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver getEdgeDriver() {

		EdgeOptions options = new EdgeOptions();
		options.setAcceptInsecureCerts(true);
		WebDriver driver = new EdgeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}
}
