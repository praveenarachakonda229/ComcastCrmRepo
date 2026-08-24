package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName, int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testdata/Readdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		
		return data;
	}
	
	public int rowCount(String sheetName,int row) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testdata/Readdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName,int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testdata/Readdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(row).createCell(cell);
		
		FileOutputStream fos=new FileOutputStream("./testdata/Readdata.xlsx");
		wb.write(fos);
		wb.close();
	}
}
