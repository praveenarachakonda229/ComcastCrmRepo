package com.comcast.crm.contacttest;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.objectRepository.ContactsInfoPage;
import com.comcast.crm.objectRepository.ContactsPage;
import com.comcast.crm.objectRepository.CreatingNewContactPage;
import com.comcast.crm.objectRepository.HomePage;
import com.comcast.crm.objectRepository.LoginPage;

public class CreateContactTestWithPom {
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
        
		//STEP 1: Login to app
		LoginPage loginpage=new LoginPage(driver);
		//utilization
		loginpage.login("admin", "admin");
		
		//STEP 2: Navigate to Contacts Module
		 HomePage homePage=new HomePage(driver);
		 homePage.getContactLink().click();
		  
		  //STEP 3: click on "create contact" button
		  ContactsPage contactPage=new ContactsPage(driver);
		  contactPage.getNewContact().click();
		  
		  // STEp 4: enter all the details & Create new contact
		  CreatingNewContactPage newContact=new CreatingNewContactPage(driver);
		  newContact.createContact(lastName);
	      
		  //Verify
		  ContactsInfoPage infoPage=new ContactsInfoPage(driver);
		  String actualContactName=infoPage.getHeader().getText();
		  if(actualContactName.contains(lastName)) {
			  System.out.println(lastName+" Verified--->Pass");
		  }
		  else {
			  System.out.println("Failed");
		  }
		  
		  //STEP 6: Logout
		  homePage.logout();
		  
		  driver.quit();
	}
}
