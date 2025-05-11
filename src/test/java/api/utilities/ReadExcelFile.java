package api.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	public static FileInputStream inputStream;
	public static XSSFWorkbook workBook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {

		try {
			inputStream = new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet = workBook.getSheet(sheetName);
			cell = excelSheet.getRow(rowNo).getCell(cellNo);

			DataFormatter formatter = new DataFormatter();
			// String val =
			// formatter.formatCellValue(excelSheet.getRow(rowNo).getCell(cellNo));

			// String str=formatter.formatCellValue(cell);
			workBook.close();
			// return cell.getStringCellValue();
			// return val;
//			System.out.println( "*****"+formatter.formatCellValue(cell));
			return formatter.formatCellValue(cell);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static int getRowCount(String fileName, String sheetName) {
		try {
			inputStream = new FileInputStream(fileName);
			// create XSSFWorkbook class object for excel file manipulation
			workBook = new XSSFWorkbook(inputStream);
			excelSheet = workBook.getSheet(sheetName);
			// Get total number of Rows
			int ttlRow = excelSheet.getLastRowNum() + 1;
			System.out.println("ttlRow " + ttlRow);
			workBook.close();
			return ttlRow;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getColCount(String fileName, String sheetName) {
		try {
			inputStream = new FileInputStream(fileName);
			// create XSSFWorkbook class object for excel file manipulation
			workBook = new XSSFWorkbook(inputStream);
			excelSheet = workBook.getSheet(sheetName);
			// Get total number of Columns
			int ttlCells = excelSheet.getRow(0).getLastCellNum();
			workBook.close();
			return ttlCells;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}