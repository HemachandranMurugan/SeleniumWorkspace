package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fileReading.ExcelUtils;
 
public class FormPage {
 
    WebDriver driver;

  //Constructor -> Initiate Driver
    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
 
    //Locators
    @FindBy(id = "email")
    private WebElement emailInput;
 
    @FindBy(id = "submitButton")
    private WebElement submitButton;
 
    @FindBy(id = "email_error")
    private WebElement emailError;
 
    //Action Methods
    
    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }
 
    public void clickSubmit() {
        submitButton.click();
    }
 
    public void getEmailErrorMessage() {
        ExcelUtils.writeExcelFile_2(emailError.getText());
    }
}
 
 
 
