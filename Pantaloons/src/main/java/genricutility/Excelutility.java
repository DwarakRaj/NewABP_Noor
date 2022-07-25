package genricutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutility {
	
	
	
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	public String path = "/Users/liciouss/eclipse-workspace/Pantaloons/excel/Lighthouse data 1.xlsx";

	

	public Excelutility(String spath) {
		this.path = spath;
	}

	public int getRowCount(int sheetindex) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(sheetindex);

//		sheet = workbook.getSheet(sheetName);//stringtype
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fis.close();
		return data;

	}

	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);

//		formatter.formatCellValue(cell);
//		String data;
//		try {
//			data= 
//					formatter.formatCellValue(cell);
//		}
//		catch(Exception e) {
//			data="";
//		}

		fos = new FileOutputStream(path);
		workbook.write(fos);

		workbook.close();
		fis.close();
		fos.close();

	}

	public void writeDataInBlankcell(String sheetName, String data, int coloumNO) throws IOException {

		Excelutility eUtil = new Excelutility(path);

		for (int i = 1; i < 100; i++) {

			String presentval = eUtil.getCellData(sheetName, i, coloumNO);
			if (presentval == "") {
				eUtil.setCellData(sheetName, i, coloumNO, data);
				break;
			} else {
				i++;
			}
		}
		System.out.println(sheetName +" --- data entered.");
	}

	public  String getrefnumWhichisPending(String sheetname) throws IOException {


		String Refnumb = "";

		for (int i = 1; i < 100; i++) {

			String ScrutinyStatus = getCellData(sheetname, i, 1);
//			System.out.println("The Scrutiny stauts in the given row is --"+ScrutinyStatus);
			if (!ScrutinyStatus.isEmpty())
//					equalsIgnoreCase("Done")) 
			{
				i++;
			} else {
				String getCellData = getCellData(sheetname, i, 0);
				if (getCellData.isEmpty()) {
					i++;
				} else {
					String VehicleRefnum = getCellData(sheetname, i, 0);
					System.out.println(VehicleRefnum);
					Refnumb = VehicleRefnum;
					break;
				}

			}
		}

		return Refnumb;

	}
	
	public  void setScrutinyStatus(String sheetname, String data) throws IOException {


		String Refnumb = "";

		for (int i = 1; i < 100; i++) {

			String ScrutinyStatus = getCellData(sheetname, i, 1);
//			System.out.println("The Scrutiny stauts in the given row is --"+ScrutinyStatus);
			if (ScrutinyStatus.equalsIgnoreCase("Done")) {
				i++;
			} else {
				String getCellData = getCellData(sheetname, i, 0);
				if (getCellData.isEmpty()) {
					i++;
				} else {
					String VehicleRefnum = getCellData(sheetname, i, 0);
					System.out.println(VehicleRefnum);
					Refnumb = VehicleRefnum;
					setCellData(sheetname, i, 1, data);
					break;
				}

			}
		}

	}
	

}
