package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fileReading.ExcelUtils;
import initialSetUp.WaitingClass;

public class CabsFilterPage2 {
	
	WebDriver driver;
	
	public CabsFilterPage2(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//div[@class='appendBottom30'][1]")
	WebElement filterSection;
	@FindBy(xpath="//div[@data-testid='filter_selection']//label[text()='SUV']")
	WebElement suvCheckBox;
	@FindBy(xpath ="//p[@class='font28 latoBlack blackText ']")
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