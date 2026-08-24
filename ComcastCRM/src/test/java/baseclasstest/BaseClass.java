package baseclasstest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.FileUtility;

public class BaseClass {
	
	DataBaseUtility dbLib=new DataBaseUtility();
	FileUtility fLib=new FileUtility();
	
	WebDriver driver=null;
	@BeforeSuite
	public void connectDB() {
		System.out.println("-----connect to DB-----");
		dbLib.getDbConnection();
	} 
	
	@BeforeClass
	public void launchBrowser() throws IOException {
		System.out.println("Launch the browser");
		String browser = fLib.getDataFromPropertiesFile("browser");
		
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		
	}
	@BeforeMethod
	public void login() {
		System.out.println("Login");
	}
	@AfterMethod
	public void logout() {
		System.out.println("Logout");
	}
	@AfterClass
	public void close() {
		System.out.println("Close the browser");
	}
	@AfterSuite
	public void closeDB() {
		System.out.println("-----close DB-----");
	}
}
