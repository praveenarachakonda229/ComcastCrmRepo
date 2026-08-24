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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.comcast.crm.objectRepository.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepository.HomePage;
import com.comcast.crm.objectRepository.LoginPage;
import com.comcast.crm.objectRepository.OrganizationInfoPage;
import com.comcast.crm.objectRepository.OrganizationsPage;

public class DeleteOrg {
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
		Row row=sh.getRow(10);
		String orgName=row.getCell(2).toString()+randomInt;
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
		
		  //STEP 1: Login to App
		   LoginPage loginPage=new LoginPage(driver);
		   loginPage.login("admin", "admin");
		
		  //STEP 2: Navigate to Organizations Module
		   HomePage homePage=new HomePage(driver);
		   homePage.getOrgLink().click();
		  
		  //STEP 3: click on "create organization" button
		   OrganizationsPage orgPage=new OrganizationsPage(driver);
		   orgPage.getNewOrg().click();
		  
		  // STEp 4: enter all the details & Create new Organization
		   CreatingNewOrganizationPage newOrg=new CreatingNewOrganizationPage(driver);
		   newOrg.createOrg(orgName);
		 
		  // STEP 5: Verify the heading
		   OrganizationInfoPage infoPage=new OrganizationInfoPage(driver);
		   String actualText=infoPage.getHeader().getText();
		   if(actualText.contains(orgName)) {
			   System.out.println(orgName+" Verified--->Pass");
		   }
		   else {
			   System.out.println("failed");
		   }
		   
		   //go back to organization page
		   homePage.getOrgLink().click();
		    
		   
		   //Search for organization
		   orgPage.getSearchText().sendKeys(orgName);
		   WebElement org=orgPage.getSearchField();
		   Select select =new Select(org);
		   select.selectByVisibleText("Organization Name");
		   orgPage.getSearch().click();
		   
		   
		   driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		   
		   //Handling Alert
		   Alert alert=driver.switchTo().alert();
		   alert.accept();
		   
		  //STEP 6: Logout
//		  homePage.logout();
	}
}
