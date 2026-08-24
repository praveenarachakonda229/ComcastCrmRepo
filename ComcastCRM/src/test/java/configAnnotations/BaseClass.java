package configAnnotations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	@BeforeSuite
	public void connectDB() {
		System.out.println("-----connect to DB-----");
	}
	
	@BeforeClass
	public void launchBrowser() {
		System.out.println("Launch the browser");
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
