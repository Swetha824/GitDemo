package swethapanchumarthy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swethapanchumarthy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//Intialized at first before anything happens
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	//PageFactory																		
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement PasswordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css ="div[id='toast-container']")
	WebElement errorMsg;
	
	public productCatalogue loginApplication(String email , String password)
	{
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(password);
		submit.click();
		productCatalogue productcata = new productCatalogue(driver);
		return productcata;
	}
	public String getErrorMessage()
	{

		WaitForWebElementToAppear(errorMsg);
		String em=errorMsg.getText();
		return em ;
		 
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
