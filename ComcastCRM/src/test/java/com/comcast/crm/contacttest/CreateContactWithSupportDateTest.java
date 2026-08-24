package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\prave\\OneDrive\\Desktop\\commonData.properties");
		Properties proObject=new Properties();
		proObject.load(fis);
		
		String browser=proObject.getProperty("Browser");
		String url=proObject.getProperty("URL");
		String username=proObject.getProperty("username");
		String password=proObject.getProperty("password");
		
		//support start date
		LocalDate date=LocalDate.now();
		
		//support end date
		LocalDate endDate=LocalDate.now().plusDays(30);
		
		//generate random number
		Random random=new Random();
		int randomInt=random.nextInt();
		
		//Read data from excel file
		FileInputStream fis1=new FileInputStream("C:\\Users\\prave\\OneDrive\\Desktop\\ExcelFiles\\Readdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("Readdata");
		Row row=sh.getRow(1);
		String lastName=row.getCell(5).toString()+randomInt;
		wb.close();
		
		WebDriver driver=null;
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Contacts']")).click(); 
		  
		  //STEP 3: click on "create contact" button
		  driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		  
		  // STEp 4: enter all the details & Create new contact
		  driver.findElement(By.name("lastname")).sendKeys(lastName);
		  
		  driver.findElement(By.id("jscal_field_support_start_date")).clear();
		  driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(date.toString());
		  
		  driver.findElement(By.id("jscal_field_support_end_date")).clear();
		  driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate.toString());
		  
		  driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
	      
		  //Verify StartDate
		  String startDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		  if(startDate.equals(date.toString())) {
			  System.out.println(date +" Info Passed");
		  }
		  else {
			  System.out.println(date+ " Info Failed");
		  }
		  
		//Verify EndDate
		  String EndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		  if(EndDate.equals(endDate.toString())) {
			  System.out.println(endDate +" Info Pass");
		  }
		  else {
			  System.out.println(endDate +" Info Failed");
		  }
		  
		  //STEP 6: Logout
		  Actions action=new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		  driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		  
		  driver.quit();
	}
}
