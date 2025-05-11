package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "allData")
	public String[][] allDataProvider() {
		String fName = System.getProperty("user.dir") + "/TestData/TestData.xlsx";

		int ttlRowCount = ReadExcelFile.getRowCount(fName, "Sheet1");
		int ttlColCnt = ReadExcelFile.getColCount(fName, "Sheet1");

		String userData[][] = new String[ttlRowCount - 1][ttlColCnt];
		for (int rowNo = 1; rowNo < ttlRowCount; rowNo++) {
			for (int colNo = 0; colNo < ttlColCnt; colNo++) {
				userData[rowNo - 1][colNo] = ReadExcelFile.getCellValue(fName, "Sheet1", rowNo, colNo);

			}
		}
		return userData;
	}

	@DataProvider(name = "userNamesData")
	public String[] userNamesDataProvider() {
		String fName = System.getProperty("user.dir") + "//TestData/TestData.xlsx";

		int ttlRowCount = ReadExcelFile.getRowCount(fName, "Sheet1");
		int ttlColCnt = ReadExcelFile.getColCount(fName, "Sheet1");

		String userNamesData[] = new String[ttlRowCount - 1];
		for (int rowNo = 1; rowNo < ttlRowCount; rowNo++) {
			userNamesData[rowNo - 1] = ReadExcelFile.getCellValue(fName, "Sheet1", rowNo, 1);
		}
		return userNamesData;
	}
}
