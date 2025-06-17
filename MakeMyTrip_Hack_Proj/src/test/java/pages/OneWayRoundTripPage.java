	package pages;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import initialSetUp.WaitingClass;


public class OneWayRoundTripPage {
	
	WebDriver driver;

	public OneWayRoundTripPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//Depature city locators
	@FindBy(id = "fromCity")
	WebElement fromCity;
	@FindBy(xpath = "//*[@id='react-autowhatever-1']")
	WebElement sourceSuggesstionContainer;
	@FindBy(xpath="//*[@class='sr_city blackText']")
	List<WebElement> sourceCities;
	
	//Destination city locators
	@FindBy(xpath ="//input[@placeholder='To']")
	WebElement toCity;
	@FindBy(xpath="//div[@id='react-autowhatever-1']")
	WebElement destinationSuggestionsContainer;
	
	//Departure date
	@FindBy (xpath ="//span[text()='Departure']")
	//div[@class='csw_inner']/div[3]/label/span
	WebElement depatureArrow;
	
	//pickuptime
	@FindBy(xpath="//div[@data-cy='OutstationOneWayWidget_62']")
	WebElement pickuptimeArrow;
	@FindBy(className="newTimeSlotHrUl")
	WebElement hrSlotContainer;
	@FindBy(className="newTimeSlotMinUl")
	WebElement MinSlotContainer;
	@FindBy(className="newTimeSlotMerUl")
	WebElement MerSlotContainer;
	@FindBy(className="applyBtnText")
	WebElement applyBtn;
	
	//search button
	@FindBy(xpath="//a[@data-cy='OutstationOneWayWidget_64']")
	WebElement searchBtn;
	
	public void selectDeparture(String departureCity) {	
		WaitingClass.wait_vis(driver, fromCity).click();
		WaitingClass.wait_vis(driver, sourceSuggesstionContainer);
		WaitingClass.wait_visList(driver, sourceCities);
		for (WebElement source : sourceCities ) {
			String sou = source.getText();
			if (sou.equalsIgnoreCase(departureCity)) {
				source.click();
				break;
			}
		}
	}
	
	public void selectDestination(String destinationCity,String Suggestion) throws InterruptedException {
		WaitingClass.wait_vis(driver, toCity);
		toCity.click();
		toCity.sendKeys(destinationCity);
		
		Thread.sleep(1000);

		WaitingClass.wait_vis(driver, destinationSuggestionsContainer);
		List<WebElement> clickableSuggestionItems = destinationSuggestionsContainer.findElements(By.tagName("li"));
		if (!clickableSuggestionItems.isEmpty()) {
			for (WebElement item : clickableSuggestionItems) {
				if (item.getText().contains(Suggestion)) {
					item.click();
					break;
				}
			}
		}
	}
	
	public void selectDepatureDate(String date) {
		WaitingClass.wait_vis(driver, depatureArrow).click();
		
		LocalDate userdate;
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		userdate = LocalDate.parse(date, inputFormatter);
		
		DateTimeFormatter ariaLabelFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		String userDateAriaLabel = userdate.format(ariaLabelFormatter);
		
		WebElement userDateELement = WaitingClass.wait_clickByLoc(driver,By.xpath("//div[contains(@class, 'DayPicker-Day') and @aria-label='" + userDateAriaLabel + "']"));
		userDateELement.click();
	}
	/*
	public void selectPickupTime() {
		
		WaitingClass.wait_vis(driver, pickuptimeArrow).click();
		WaitingClass.wait_vis(driver, hrSlotContainer);
		List<WebElement> hrSlot = hrSlotContainer.findElements(By.tagName("li"));
		String Desired_Hr = "03 Hr";
		for (WebElement e : hrSlot) {
			if (e.getText().equalsIgnoreCase(Desired_Hr)) {
				e.click();
			}
		}
		
		WaitingClass.wait_vis(driver, MinSlotContainer);
		List<WebElement> MinSlot = MinSlotContainer.findElements(By.tagName("li"));
		String Desired_Min = "30 min";
		for (WebElement e : MinSlot) {
			if (e.getText().equalsIgnoreCase(Desired_Min)) {
				e.click();
			}
		}
		
		WaitingClass.wait_vis(driver, MerSlotContainer);
		List<WebElement> MerSlot = MerSlotContainer.findElements(By.tagName("li"));
		String Desired_Mer = "AM";
		for (WebElement e : MerSlot) {
			if (e.getText().equalsIgnoreCase(Desired_Mer)) {
				e.click();
			}
		}
		WaitingClass.wait_vis(driver, applyBtn).click();
	}*/
	
	
	public void selectPickupTime(String timeString) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
	    LocalTime time = LocalTime.parse(timeString, formatter);

	    String Desired_Hr = String.format("%02d Hr", time.getHour() % 12 == 0 ? 12 : time.getHour() % 12);
	    String Desired_Min = String.format("%02d min", ((time.getMinute() + 2) / 5) * 5);
	    String Desired_Mer = time.getHour() >= 12 ? "PM" : "AM";
	    
	    WaitingClass.wait_vis(driver, pickuptimeArrow).click();
	    
	    WaitingClass.wait_vis(driver, hrSlotContainer);
	    for (WebElement e : hrSlotContainer.findElements(By.tagName("li"))) {
	        if (e.getText().equalsIgnoreCase(Desired_Hr)) {
	            e.click();
	            break;
	        }
	    }
	    
		WaitingClass.wait_vis(driver, MinSlotContainer);
		List<WebElement> MinSlot = MinSlotContainer.findElements(By.tagName("li"));
		for (WebElement e : MinSlot) {
			if (e.getText().equalsIgnoreCase(Desired_Min)) {
				e.click();
			}
		}
	    WaitingClass.wait_vis(driver, MerSlotContainer);
	    for (WebElement e : MerSlotContainer.findElements(By.tagName("li"))) {
	        if (e.getText().equalsIgnoreCase(Desired_Mer)) {
	            e.click();
	            break;
	        }
	    }

	    WaitingClass.wait_vis(driver, applyBtn).click();
	}

	public void clickSearch() {
		WaitingClass.wait_vis(driver, searchBtn).click();
	}
}
