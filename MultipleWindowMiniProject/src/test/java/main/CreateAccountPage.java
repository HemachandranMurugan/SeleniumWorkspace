package main;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage {
	WebDriver driver;

	// @FindBy(xpath="//*[@class='cnt']/h2")

	@FindBy(xpath = "/html/body/div[2]/p/a[1]")
	WebElement termsLink;

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isHeadingDisplayed() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement headingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='cnt']/h2")));
	    return headingElement.isDisplayed();
	}


	public int countHrefLinks() {
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		int count = 0;
		for (WebElement link : allLinks) {
			String href = link.getAttribute("href");
			if (href != null && !href.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	public void clickTermsLink() {
		WebElement link = termsLink;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);

	}
}
