package com.comcast.crm.generic.webdriverutility;

import java.time.LocalDate;
import java.util.Random;

public class JavaUtility {
	public int  getRandomNum() {
		Random random=new Random();
		int randomNum= random.nextInt(2000);
		return randomNum;
	}
	public String Localdate() {
		LocalDate date=LocalDate.now();
		System.out.println(date);
		return date.toString();
	}
	
}
