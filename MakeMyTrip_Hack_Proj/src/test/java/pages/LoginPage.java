package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	//Constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Locators
	private @FindBy(xpath="//span[@class='commonModal__close']") 
	WebElement btn_cancel; //CANCEL BUTTON
	//Action Method
	public void clickCancelButton() {
		btn_cancel.click();
	}
}
