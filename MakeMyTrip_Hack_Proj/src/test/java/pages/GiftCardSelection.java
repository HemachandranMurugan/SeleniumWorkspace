package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
public class GiftCardSelection {
	
	WebDriver driver;
	
	//Constructor -> Initiate Driver
	
	public GiftCardSelection(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath = "//img[@alt='corporateGiftImage']") private WebElement corporateGiftImage; //image location
 
	public void clickCorporateGiftImage() {
	    corporateGiftImage.click();
	}
 
 
}
 
 
