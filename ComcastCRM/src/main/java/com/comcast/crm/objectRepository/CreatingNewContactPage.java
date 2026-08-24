package com.comcast.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
	private WebElement save;

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getSave() {
		return save;
	}
	
	//business method
	public void createContact(String lastname) {
		lastName.sendKeys(lastname);
		save.click();
	}
}
