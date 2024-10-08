package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\Opecart_LoginData.xlsx"; //taking excel file from testData
		
		ExcelUtility excelUtility = new ExcelUtility(path);
		
		int totalRows = excelUtility.getRowCount("Data");
		int totalColumns = excelUtility.getCellCount("Data", 1);
		
		String loginData[][] = new String[totalRows][totalColumns];
		
		for(int i = 1; i <= totalRows; i++)
		{
			for(int j = 0; j < totalColumns; j++)
			{
				loginData[i - 1][j] = excelUtility.getCellData("Data", i, j);
			}
		}
		
		return loginData; //returning two dimensional array
	}
}
