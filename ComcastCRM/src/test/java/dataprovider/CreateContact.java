package dataprovider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CreateContact {
	@Test
	public void getProductionTest() {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://amazon.in");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("samsung",Keys.ENTER);
		
		//capture product info9
	}
}
