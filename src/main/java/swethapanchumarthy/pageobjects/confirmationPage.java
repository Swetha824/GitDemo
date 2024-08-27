package swethapanchumarthy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swethapanchumarthy.AbstractComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent {

	public confirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;

	@FindBy(css="h1[class='hero-primary']")
	WebElement confirmationMsg;
	

	public String getConfirmationPage() {
		// TODO Auto-generated method stub
		return confirmationMsg.getText();
	}
	
}
