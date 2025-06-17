package fileReading;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtils {
	public static String[][] readExcelFile(String filename, String sheetName) throws Exception {
		FileInputStream file = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int colCount = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[1][5];
		DataFormatter formatter = new DataFormatter();
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		
		for (int j = 0; j < colCount; j++) {
				Cell cell = sheet.getRow(1).getCell(j);
				data[0][j] = formatter.formatCellValue(cell,evaluator);
		}
		
		workbook.close();
		file.close();
		return data;
		
	}
	
	public static void writeExcelFile_1(List<WebElement> pricing, String lowestPrice) {
		Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("cab Pricing");

	    int rowNum = 0;
	    for (WebElement price : pricing) {
	        Row row = sheet.createRow(rowNum++);
	        Cell cell = row.createCell(0);
	        cell.setCellValue(price.getText());
	    }
	    
	    Row row = sheet.createRow(rowNum+2);
	    Cell cell = row.createCell(0);
	    cell.setCellValue(lowestPrice);
	    
	    
		File folder = new File(
				System.getProperty("user.dir") + File.separator + "Test-OutputFiles");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		

		File excelFile = new File(folder, "output_1.xlsx");

	    try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
	        workbook.write(fileOut);
	        workbook.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void writeExcelFile_2(String msg) {
		Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("ErrorMsg");

	    int rowNum = 0;
	    
	    Row row = sheet.createRow(rowNum+2);
	    Cell cell = row.createCell(0);
	    cell.setCellValue(msg);
	    
	    
		File folder = new File(
				System.getProperty("user.dir") + File.separator + "Test-OutputFiles");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		

		File excelFile = new File(folder, "output_2.xlsx");

	    try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
	        workbook.write(fileOut);
	        workbook.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void writeExcelFile_3(List<WebElement> adultOptions) {
		Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Adult Options");

	    int rowNum = 0;
	    for (WebElement option : adultOptions) {
	        Row row = sheet.createRow(rowNum++);
	        Cell cell = row.createCell(0);
	        cell.setCellValue(option.getText());
	    }
	    
		File folder = new File(
				System.getProperty("user.dir") + File.separator + "Test-OutputFiles");
		if (!folder.exists()) {
			folder.mkdirs();
		}
		

		File excelFile = new File(folder, "output_3.xlsx");

	    try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
	        workbook.write(fileOut);
	        workbook.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}