package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CabSelectionPage {
	
	WebDriver driver;
	
	@FindBy(xpath ="//span[@class='headerIconTextAlignment chNavText darkGreyText'][normalize-space()='Cabs']")
	WebElement cabIcon;
	
	private @FindBy(xpath="//a[normalize-space()='Search']") WebElement btn_search; 
	
	public CabSelectionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickCabIcon() {
		cabIcon.click();
	}
	
	public void clickSearchButton() {
		btn_search.click();
	}	
}
