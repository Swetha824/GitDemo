package swethapanchumarthy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swethapanchumarthy.AbstractComponents.AbstractComponent;

public class productCatalogue extends AbstractComponent {

	WebDriver driver;
	
	//List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));

	public productCatalogue(WebDriver driver) {
		
		super(driver);
		//Intialized at first before anything happens
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}



	//PageFactory																		
	@FindBy(css="div.mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productsby =By.cssSelector("div.mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList()
	{
		WaitForElementToAppear(productsby);
		return products;
		
	}
	public WebElement getProductByName(String ProductName)
	{
		WebElement prod = getProductList().stream().filter(product
	    		->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
		
	}
	public void addProdToCart(String ProductName) throws InterruptedException
	{
		WebElement prod = getProductByName(ProductName);
		 prod.findElement(addToCart).click();
		 WaitForElementToAppear(toastMsg);
		 WaitForElementToDisAppear(spinner);
		 
		 
	}
}
