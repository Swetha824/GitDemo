package swethapanchumarthy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import swethapanchumarthy.Testcomponents.BaseTests;
import swethapanchumarthy.pageobjects.CartPage;
import swethapanchumarthy.pageobjects.CheckOutPage;
import swethapanchumarthy.pageobjects.LandingPage;
import swethapanchumarthy.pageobjects.OrderPage;
import swethapanchumarthy.pageobjects.confirmationPage;
import swethapanchumarthy.pageobjects.productCatalogue;

public class SubmitOrderTest extends BaseTests {

	String productName ="ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	//public void submitOrder(String email ,String password , String productName) throws IOException, InterruptedException
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		productCatalogue productcata = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcata.getProductList();
		productcata.addProdToCart(input.get("product"));
		productcata.addProdToCart(input.get("product"));
		
		CartPage cartPage = productcata.goToCartPage();
		Boolean match = cartPage.verifyproductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationPage confirmationpg =checkoutpage.submitorder();
		String Msg = confirmationpg.getConfirmationPage();
		Assert.assertTrue(Msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		checkoutpage.Signout();
	}
	
	//To verify ZARA COAT 3 is displayed in orders page
	@Test(dependsOnMethods =("submitOrder"))
	public void OrderHistoryTest()
	{
		productCatalogue productcata = landingpage.loginApplication("pswetha308@gmail.com", "Practice@3239");
		OrderPage orderspage = productcata.goToOrdersPage();
		Assert.assertTrue(orderspage.verifyorderDisplay(productName));
	}
	
	
	
	//Extent Reports
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\swethapanchumarthy\\data\\purchaseOrder.json");
		return new Object[][] {{data.get(0)} ,{data.get(1)}};
		
		//return new Object[][] {{"pswetha308@gmail.com","Practice@3239","ZARA COAT 3"} ,{"trainee@gmail.com","Practice@3239","IPHONE 13 PRO"}};
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "pswetha308@gmail.com");
//		map.put("password", "Practice@3239");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "trainee@gmail.com");
//		map1.put("password", "Practice@3239");
//		map1.put("product", "IPHONE 13 PRO");
		
		
	}
}