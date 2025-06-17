package initialSetUp;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver(String Browser) {
		switch (Browser.toLowerCase()) {
		case "chrome" :
			driver=new ChromeDriver();
			break;
		case "edge" :
			driver = new EdgeDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

}
