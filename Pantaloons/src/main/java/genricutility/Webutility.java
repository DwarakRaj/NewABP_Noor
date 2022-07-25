package genricutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class Webutility extends Baseclass{
	
	public static void main(String[] args) throws Throwable {
		
	}
	
	//String sheetname,String cellvalue,int rownum, int cellnum
//	public static String ReadData() throws Throwable
//	{
//		
//		FileInputStream fis = new FileInputStream("/Users/liciouss/eclipse-workspace/Pantaloons/excel/Lighthouse data.xlsx");
//		ZipSecureFile.setMinInflateRatio(0.0009);
//		WorkbookFactory workbook=new WorkbookFactory();
//		Workbook abc = workbook.create(fis);
//		Sheet sheet = abc.getSheet("sheet1");
//		Row row = sheet.getRow(1);//zero row=heading rows,i++
//		Cell cell = row.getCell(3);
//		String celldata = cell.getStringCellValue();
//		return celldata ;
//		
//		
//	}
//	
//	
//	public static void writedataIS(int data ,String sheetname,int rownum,int columnnum) throws Throwable
//	{
//		
//		FileInputStream fis = new FileInputStream("/Users/liciouss/eclipse-workspace/Pantaloons/excel/Lighthouse data.xlsx");
//		ZipSecureFile.setMinInflateRatio(0.0009);
//		WorkbookFactory workbook=new WorkbookFactory();
//		Workbook abc = workbook.create(fis);
//		Sheet sheet = abc.getSheet(sheetname);
//		Row row = sheet.createRow(rownum);//zero row=heading rows,i++
//		Cell cell = row.createCell(columnnum);
//		cell.setCellType(Cell.CELL_TYPE_STRING);
//		cell.setCellValue(data);
//		FileOutputStream fos = new FileOutputStream("/Users/liciouss/eclipse-workspace/Pantaloons/excel/Lighthouse data.xlsx");
//		abc.write(fos);
//		fos.close();
//		System.out.println(data +" is entered successfully in excel");		
//		
//	}
//	
//	public static void writedataSS(String data ,String sheetname,int rownum,int columnnum) throws Throwable
//	{
//		
//		FileInputStream fis = new FileInputStream("/Users/liciouss/eclipse-workspace/Pantaloons/excel/Lighthouse data.xlsx");
//		ZipSecureFile.setMinInflateRatio(0.0009);
//		WorkbookFactory workbook=new WorkbookFactory();
//		Workbook abc = workbook.create(fis);
//		Sheet sheet = abc.getSheet(sheetname);
//		Row row = sheet.createRow(rownum);//zero row=heading rows,i++
//		Cell cell = row.createCell(columnnum);
//		cell.setCellType(Cell.CELL_TYPE_STRING);
//		cell.setCellValue(data);
//		FileOutputStream fos = new FileOutputStream("/Users/liciouss/eclipse-workspace/Pantaloons/excel/Lighthouse data.xlsx");
//		abc.write(fos);
//		fos.close();
//		System.out.println(data +" is entered successfully in excel");		
//		
//	}



	public void scrollTo(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int yaxis = element.getLocation().getY();
		js.executeScript("window.scrollBy(0," + yaxis + ")", element);
	}
	
	public static  String takeScreenShot(String screenshotName) throws IOException{


		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotpath = 
				System.getProperty("user.dir")+ 
				"/Reports/Screen-shots/" + screenshotName +"--.png";
		File destination = new File(screenshotpath);
		Files.copy(source, destination);
		return screenshotpath;
	}
	
	public void waitForElementtobeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
