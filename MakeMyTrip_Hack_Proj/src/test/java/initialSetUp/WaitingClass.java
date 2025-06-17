package initialSetUp;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class WaitingClass {
	
	WebDriver driver;
	static WebDriverWait wait;
	
	public WaitingClass(WebDriver driver) {
		this.driver = driver;
		WaitingClass.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public static WebElement wait_vis(WebDriver driver,WebElement element) {
		WebElement ele=wait.until(ExpectedConditions.visibilityOf(element));
		return ele;
	}
	public static List<WebElement> wait_visList(WebDriver driver,List<WebElement> element) {
		List<WebElement> elements=wait.until(ExpectedConditions.visibilityOfAllElements(element));
		return elements;
	}
	public static WebElement wait_clickByLoc(WebDriver driver,By locator) {
		WebElement ee=wait.until(ExpectedConditions.elementToBeClickable(locator));
		return ee;
	}
}