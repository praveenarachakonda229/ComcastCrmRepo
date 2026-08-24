package com.comcast.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	@FindBy(xpath="//img[@alt=\"Create Organization...\"]")
	private WebElement newOrg;
	
	@FindBy(name="search_text")
	private WebElement searchText;
	
	@FindBy(name="search_field")
	private WebElement searchField;
	
	@FindBy(name="submit")
	private WebElement search; 
	
	public WebElement getNewOrg() {
		return newOrg;
	}
	
	public WebElement getSearchText() {
		return searchText;
	}
	public WebElement getSearchField() {
		return searchField;
	}
	public WebElement getSearch() {
		return search;
	}
	
	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
}
