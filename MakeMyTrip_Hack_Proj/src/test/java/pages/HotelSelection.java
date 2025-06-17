package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fileReading.ExcelUtils;

public class HotelSelection {

    WebDriver driver;

    public HotelSelection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Hotel button
    private @FindBy(xpath = "//span[@class='headerIconTextAlignment chNavText darkGreyText'][normalize-space()='Hotels']")
    WebElement hotelBtn;
    
    // Guest selection button
    private @FindBy(id = "guest")
    WebElement guestBtn;
    
    // Click on adult dropdown
    private @FindBy(xpath = "//div[@class='makeFlex primaryTraveler']//div[2]//div[2]//div[1]")
    WebElement adultDropdown;
    
    // List of adult options
    private @FindBy(xpath = "//*[@data-cy ='GuestSelect$$_323']")
    List<WebElement> adultOptions;

    public void clickHotelBtn() {
        hotelBtn.click();
    }

    public void clickGuestBtn() {
        guestBtn.click();
    }

    public void clickAdultDropdown() {
        adultDropdown.click();
    }

    public List<WebElement> getAdultOptions() {
        return adultOptions;
    }

    public void printAdultOptions() {
    	ExcelUtils.writeExcelFile_3(adultOptions);
        
    	/*for (WebElement option : adultOptions) {
            System.out.println(option.getText());
        }*/
    }
}

