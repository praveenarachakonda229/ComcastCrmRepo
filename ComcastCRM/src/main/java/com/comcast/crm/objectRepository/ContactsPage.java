package com.comcast.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	@FindBy(xpath="//img[@alt=\"Create Contact...\"]")
	private WebElement newContact;

	public WebElement getNewContact() {
		return newContact;
	}
	
	//Rule 3: object initialization
		WebDriver driver;
		public ContactsPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
}
