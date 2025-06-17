package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fileReading.ExcelUtils;
import initialSetUp.WaitingClass;

public class CabsFilterPage1 {
	WebDriver driver;

	public CabsFilterPage1(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className ="accordion_contentWrapper__ihqBf")
	WebElement filterSection;
	@FindBy(xpath="//div[@role='checkbox'][.//span[text()='SUV']]")
	WebElement suvCheckBox;
	@FindBy(xpath ="//*[@class='cabDetailsCard_price__SHN6W']")
	List<WebElement> priceElements;
	
	public void selectPricing() {
		try {
			WaitingClass.wait_vis(driver, filterSection);
			WaitingClass.wait_vis(driver, suvCheckBox).click();
			WaitingClass.wait_visList(driver, priceElements);
			
			int lowestFare = Integer.MAX_VALUE;	
			for (WebElement priceElement : priceElements) {
				String priceText = priceElement.getText().replaceAll("[^0-9]", "");
				int price = Integer.parseInt(priceText);
				
				if (price < lowestFare) {
					lowestFare = price;
				}
			}
			ExcelUtils.writeExcelFile_1(priceElements,"Lowest Fare: ₹" + lowestFare);
			
		}catch (Exception e) {
			//System.err.println("\tError encountered");
		}
	}
}
