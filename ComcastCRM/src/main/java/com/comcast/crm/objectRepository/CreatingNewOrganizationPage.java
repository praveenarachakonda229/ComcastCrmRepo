package com.comcast.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {
	@FindBy(name="accountname")
	private WebElement orgName;
     
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	public WebElement getOrgName() {
		return orgName;
	}
	
    public WebElement getSaveBtn() {
		return saveBtn;
	}

	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void createOrg(String orgname) {
		orgName.sendKeys(orgname);
		saveBtn.click();
	}
	
}
