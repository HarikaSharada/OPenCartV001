package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	
  @DataProvider(name="LoginData")
  public String[][] getLoginData() throws IOException {
	  
	  
	  String loginDataFile=".\\testData\\Opencart_LoginData.xlsx";
	  ExcelUtility excelUtility = new ExcelUtility(loginDataFile);
	  
	  int noOfsheetRows=excelUtility.getRowCount("Sheet1");
	  int noOfSheetColumns=excelUtility.getCellCount("Sheet1",1);
	  
	  String getdata [][] = new String[noOfsheetRows][noOfSheetColumns];
	  for(int r=1;r<=noOfsheetRows;r++)
	  {
		  for(int c=0;c<noOfSheetColumns;c++)
		  {
			  getdata[r-1][c]= excelUtility.getCellData("Sheet1", r, c);
		  }
	  }
	  return getdata;
  }
}
