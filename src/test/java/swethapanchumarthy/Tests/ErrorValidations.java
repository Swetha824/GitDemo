package swethapanchumarthy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import swethapanchumarthy.Testcomponents.BaseTests;
import swethapanchumarthy.Testcomponents.Retry;
import swethapanchumarthy.pageobjects.CartPage;
import swethapanchumarthy.pageobjects.productCatalogue;

public class ErrorValidations extends BaseTests {
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginerrorValidation() throws IOException, InterruptedException
	{
		
		landingpage.loginApplication("pswetha308@gmail.com", "Practic@3239");//Giving wrong password
		Assert.assertEquals("Incorrect email password.",landingpage.getErrorMessage());
	}
	@Test
	public void producterrorValidation() throws IOException, InterruptedException
	{
		String 	productName = "ZARA COAT 3";
		productCatalogue productcata = landingpage.loginApplication("trainee@gmail.com", "Practice@3239");
		List<WebElement> products = productcata.getProductList();
		productcata.addProdToCart(productName);
		CartPage cartPage = productcata.goToCartPage();
		Boolean match = cartPage.verifyproductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
