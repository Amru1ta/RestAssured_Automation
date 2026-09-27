package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderMultiple {

	@DataProvider (name="dp")
	public String[][] getAllData() throws IOException{
		String path = ".\\testData\\petStoreData.xlsx";
		ExcelUtilities excel = new ExcelUtilities(path);
		int numberofrows = excel.getRowCount("sheet1");
		int numberofcol = excel.getCellCount("sheet1", 1);
		String data[] [] = new String [numberofrows-1][numberofcol];
		
		for (int i=1; i<numberofrows; i++) {
			for (int j=0; j<numberofcol; j++) {
				data [i-1][j] =  excel.getCellData("sheet1", i, j);
			}
		}
		return data;
		
	}
	
	@DataProvider (name="userNames")
	public String[] getUserNames() throws IOException{
		String path = ".\\testData\\petStoreData.xlsx";
		ExcelUtilities excel = new ExcelUtilities(path);
		int numberofrows = excel.getRowCount("sheet1");
		String data[]  = new String [numberofrows-1];
		
		for (int i=1; i<numberofrows; i++) {
			
				data [i-1] =  excel.getCellData("sheet1", i, 1);
			
		}
		return data;
		
	}
}
