package com.comcast.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage { //rule 1: create a seperate java class
	//rule 2: object creation
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
    
	//Rule 3: object initialization
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//Rule 4: Object Encapsultion
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule 5: We can provide action
	public void login(String userName, String password) {
		driver.manage().window().maximize();
		usernameEdit.sendKeys(userName);
		passwordEdit.sendKeys(password);
		loginBtn.click();
		
	}
	
}
