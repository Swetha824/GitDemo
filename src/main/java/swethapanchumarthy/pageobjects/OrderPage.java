package swethapanchumarthy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swethapanchumarthy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css ="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutEle;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		//Intialized at first before anything happens
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyorderDisplay(String ProductName)
	{
		 Boolean match = productNames.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		 //No assertions in page object files
		 return match;
	}

	

}
