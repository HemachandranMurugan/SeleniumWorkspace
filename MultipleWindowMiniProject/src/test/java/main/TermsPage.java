package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TermsPage {
    WebDriver driver;
    
    @FindBy(xpath="/html/body/div[1]/div[1]/div[1]")
    WebElement termsHeader;
    
    public TermsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }

    public boolean isTermsHeaderDisplayed() {
        return termsHeader.isDisplayed();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
