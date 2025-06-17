package miniProjectSelenium;import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static String[][] readExcelFile(String filename, String sheetName) throws Exception {
		FileInputStream file = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int colCount = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[1][2];
		DataFormatter formatter = new DataFormatter();
		
		for (int j = 0; j < colCount; j++) {
				Cell cell = sheet.getRow(1).getCell(j);
				data[0][j] = formatter.formatCellValue(cell);
			}
		
		workbook.close();
		file.close();
		return data;
		
	}
}