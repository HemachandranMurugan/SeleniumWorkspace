package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
import java.util.Set;
 
public class InterestPage {
 
    WebDriver driver;
  //Constructor -> Initiate Driver
    
    public InterestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    //Locators
 
    @FindBy(xpath = "//button[contains(text(),'I’m Interested')]") private WebElement interestedButton;
    
    //Action Methods
    
    // Method to switch to the new window
    public void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
 
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
 
    // Method to click the "I'm Interested" button
    public void clickInterestedButton() {
        interestedButton.click();
    }
}
 
 
 
