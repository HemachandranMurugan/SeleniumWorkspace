package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import initialSetUp.WaitingClass;
 
public class HoverPage {
	
	WebDriver driver;
	
	//Constructor -> Initiate Driver
	
	public HoverPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	
	@FindBy(xpath = "//*[@class='arrow arrowDown']") private WebElement arrowDown; // dropdown hover
 
	@FindBy(xpath = "//a[@data-cy='submenu_Giftcards']") private WebElement giftCardsLink;//giftcard slection
 
	// Actions
	public void clickArrowDown() {
		WaitingClass.wait_vis(driver, arrowDown).click();
	}
 
	public void clickGiftCards() {
		giftCardsLink.click();
	}
 
}
 
 
