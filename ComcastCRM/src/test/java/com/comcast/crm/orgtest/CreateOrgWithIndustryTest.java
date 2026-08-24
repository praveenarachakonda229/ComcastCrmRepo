package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CreateOrgWithIndustryTest {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
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
		Row row=sh.getRow(4);
		String orgName=row.getCell(2).toString()+randomInt;
		String Industry=row.getCell(3).toString();
		String Type=row.getCell(4).toString();
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
		  
		  WebElement industry=driver.findElement(By.name("industry"));
		  Select select=new Select(industry);
		  select.selectByVisibleText(Industry);
		  
		  WebElement type=driver.findElement(By.name("accounttype"));
		  Select select2=new Select(type);
		  select2.selectByVisibleText(Type);
		  
		  driver.findElement(By.xpath("(//input[@name=\"button\"])[3]")).click();
		  //Verify dropdown (industry and type)
		  String actualIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
		  if(actualIndustry.equals(Industry)) {
			  System.out.println("Industry Info Pass");
		  }
		  else {
			  System.out.println("Industry Info Fail");
		  }
		  //TYPE
		  String actualType=driver.findElement(By.id("dtlview_Type")).getText();
		  if(actualType
				  .equals(Type)) {
			  System.out.println("Type Info Pass");
		  }
		  else {
			  System.out.println("Type Info Fail");
		  }
		  
		  driver.quit();
	}
}
