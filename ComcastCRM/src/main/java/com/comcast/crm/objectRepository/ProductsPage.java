package com.comcast.crm.objectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage {
	@FindBy(xpath="//input[@alt='Create Product']")
	private WebElement createProductImgBtn;
}
