package configAnnotations;

import org.testng.annotations.Test;

public class CreateContact extends BaseClass{
	@Test
	public void createContact() {
		System.out.println("Create Contact");
	}
	
	@Test
	public void createContactWithdate() {
		System.out.println("Create Contact with date");
	}
}
