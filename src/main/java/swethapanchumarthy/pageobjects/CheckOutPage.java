package swethapanchumarthy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swethapanchumarthy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	

	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="a[class*='submit']")
	WebElement submit;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	@FindBy(css  = "li:nth-child(5) button:nth-child(1)")
	private WebElement Signout;
	
	private By results = By.cssSelector(".ta-results");
	
	public  void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		WaitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	public confirmationPage submitorder()
	{
		submit.click();
		confirmationPage confirmationpg = new confirmationPage(driver);
		return confirmationpg;
	}
	public void Signout()
	{
		Signout.click();
	}
	
}
