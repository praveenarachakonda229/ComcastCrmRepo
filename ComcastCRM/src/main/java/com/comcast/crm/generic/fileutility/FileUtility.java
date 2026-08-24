package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream("./configAppData/commonData.properties");
		Properties proObject=new Properties();
		proObject.load(fis);
		String data=proObject.getProperty(key);
		return data;
	}
}
