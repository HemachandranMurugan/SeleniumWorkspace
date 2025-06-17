package miniProjectSelenium;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchSchoolAction {

	private WebDriver driver;
	WebDriverWait wait;
	ConfigParser parser;

	public SearchSchoolAction() {

	}

	public SearchSchoolAction(WebDriver driver, ConfigParser parser) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.parser = parser;
	}

	public void setSchools() {
		// Click on "Schools"
		WebElement schoolLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(parser.getObjectLocator("schoolLinkXPath"))));
		schoolLink.click();
	}

	public void setCourses(String course) {
		// Course-Type -> CBSE
		WebElement courseDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.id(parser.getObjectLocator("courseDropdownId"))));
		Select dropCourse = new Select(courseDropdown);
		dropCourse.selectByVisibleText(course);
	}

	public void setCity(String city) {
		// City -> Pune
		WebElement cityDropdown = wait
				.until(ExpectedConditions.elementToBeClickable(By.id(parser.getObjectLocator("cityDropdownId"))));
		Select dropCity = new Select(cityDropdown);
		dropCity.selectByVisibleText(city);
	}

	public void search() {
		// Click Search
		driver.findElement(By.id(parser.getObjectLocator("searchButtonId"))).click();
	}

	public void takeScreenShot() throws Exception {
		// Create screenshots directory if not exists
		String browserName = ((HasCapabilities) driver).getCapabilities().getBrowserName();
		File folder = new File(
				System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + browserName);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		// Get total number of pages
		WebElement pageContainer = driver.findElement(By.cssSelector(parser.getObjectLocator("pageContainer")));
		List<WebElement> pages = pageContainer
				.findElements(By.cssSelector(parser.getObjectLocator("paginationSelector")));
		int noOfPages = pages.size();
		//System.out.println("Number of pages: " + noOfPages);

		for (int i = 0; i < noOfPages; i++) {
			// Re-fetch pagination elements (to avoid stale element)

			pageContainer = driver.findElement(By.cssSelector(parser.getObjectLocator("pageContainer")));
			String pageSelector = String.format(parser.getObjectLocator("PageSelection"), i + 1);
			WebElement pageLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(pageSelector)));
			pageLink.click();
			//System.out.println(browserName + "_" + "Page Link_" + (i + 1) + "--> clicked.");
			Thread.sleep(7000);

			WebElement resultsBox = driver.findElement(By.cssSelector(parser.getObjectLocator("resultsBoxSelector")));

			File screenshotFile = resultsBox.getScreenshotAs(OutputType.FILE);
			
			File outputFile = new File(folder,browserName + "_" + "ss_page_" + (i + 1) + ".png");
			Files.copy(screenshotFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//System.out.println("Saved screenshot: ss_page_" + browserName + "_" + (i + 1) + ".png");
		}
	}
}
