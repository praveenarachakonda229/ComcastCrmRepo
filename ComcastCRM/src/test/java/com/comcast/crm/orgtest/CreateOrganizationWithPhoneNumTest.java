package com.comcast.crm.orgtest;

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

public class CreateOrganizationWithPhoneNumTest {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\prave\\OneDrive\\Desktop\\commonData.properties");
		Properties proObject=new Properties();
		proObject.load(fis);
		
		String browser=proObject.getProperty("Browser");
		String url=proObject.getProperty("URL");
		String username=proObject.getProperty("username");
		String password=proObject.getProperty("password");
		
		//generate random number
		Random random=new Random();
		int randomInt=random.nextInt();
		
		//Read data from excel file
		FileInputStream fis1=new FileInputStream("C:\\Users\\prave\\OneDrive\\Desktop\\ExcelFiles\\Readdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("Readdata");
		Row row=sh.getRow(7);
		String orgName=row.getCell(2).toString()+randomInt;
		String phn=row.getCell(3).getStringCellValue();
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
		
		driver.findElement(By.xpath("//a[text()='Organizations']")).click(); 
		  
		  //STEP 3: click on "create organization" button
		  driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		  
		  // STEp 4: enter all the details & Create new Organization
		  driver.findElement(By.name("accountname")).sendKeys(orgName);
		  
		  driver.findElement(By.id("phone")).sendKeys(phn);
		  
		  driver.findElement(By.xpath("(//input[@name=\"button\"])[3]")).click();
		 
	      //Verify Phn Num
		  String phnNum=driver.findElement(By.id("dtlview_Phone")).getText();
		  if(phnNum.equals(phn)) {
			  System.out.println("Pass");
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
