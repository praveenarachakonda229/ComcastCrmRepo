package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class CreateContactTest {
	public static void main(String[] args) throws IOException {
		//Create Object
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		
		//Read Common Data
		String browser=fLib.getDataFromPropertiesFile("Browser");
		String url=fLib.getDataFromPropertiesFile("URL");
		String username=fLib.getDataFromPropertiesFile("username");
		String password=fLib.getDataFromPropertiesFile("password");
		
		//generate random number
		Random random=new Random();
		int randomInt=random.nextInt();
		
		//Read data from excel file
		String lastName=eLib.getDataFromExcel("Readdata", 1, 2)+randomInt;
		
		WebDriver driver=null;
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click(); 
		  
		  //STEP 3: click on "create contact" button
		  driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		  
		  // STEp 4: enter all the details & Create new contact
		  driver.findElement(By.name("lastname")).sendKeys(lastName);
		  driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
	      
		  //Verify
		  String contactHeading=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		  if(contactHeading.contains(lastName)) {
			  System.out.println(lastName + " Pass");
		  }
		  else {
			  System.out.println("Fail");
		  }
		  
		  //STEP 6: Logout
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		  driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		  
		  driver.quit();
	}
}
