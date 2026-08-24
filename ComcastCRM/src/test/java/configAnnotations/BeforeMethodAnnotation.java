package configAnnotations;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BeforeMethodAnnotation {
	@BeforeMethod
	public void configBM() {
		System.out.println("BM executed");
	}
	
	@Test
	public void configTest() {
		System.out.println("test1 executed");
	}
	
	@Test
	public void configTest2() {
		System.out.println("test2 executed");
	}
}
