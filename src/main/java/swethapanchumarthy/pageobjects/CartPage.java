package swethapanchumarthy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swethapanchumarthy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(xpath="//div[@class='cartSection']/h3")
	private List<WebElement> productTitles;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutEle;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		//Intialized at first before anything happens
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyproductDisplay(String ProductName)
	{
		 Boolean match = productTitles.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		 //No assertions in page object files
		 return match;
	}
	public CheckOutPage goToCheckout()
	{
		checkOutEle.click();
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
	}
	

}
