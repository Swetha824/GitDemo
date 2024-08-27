package swethapanchumarthy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("pswetha308@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Practice@3239");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
	    WebElement prod = products.stream().filter(product
	    		->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	    driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
	    List<WebElement> CartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	    Boolean match = CartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(match);
	    driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
	    //driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
	    Actions a= new Actions(driver);
	    a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
	    driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	    driver.findElement(By.cssSelector("a[class*='submit']")).click();
	    String ConfirmMsg = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
	    Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    driver.quit();

	}

}
