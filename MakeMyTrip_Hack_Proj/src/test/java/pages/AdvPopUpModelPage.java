package pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import initialSetUp.WaitingClass;

public class AdvPopUpModelPage {
	
	WebDriver driver;
	
	public AdvPopUpModelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//h2[text()='For your Delhi-Manali trip']")
	List<WebElement> modalTitles;
	@FindBy(xpath ="//img[@alt='Close' and contains(@src, 'cancel-filled')]")
	WebElement closeButton;

	public void closePopUP() {
		if (!modalTitles.isEmpty() && modalTitles.get(0).isDisplayed()) {
			try {
				WaitingClass.wait_vis(driver, closeButton).click();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}	
}
