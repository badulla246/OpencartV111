package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	//DataProvide 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path = ".\\testData\\Dummy_Data.xlsx"; //Taking xlfile from testData Folder
		
		ExcelUtility xlUtil = new ExcelUtility(path);
		
		int totalrows = xlUtil.getRowCount("Data");
		int totalcols = xlUtil.getCellCount("Data", 1);
		
		//Create two dimensional array which can store data from Excel
		String logindata[][] = new String[totalrows][totalcols];
		
		for (int i=1; i<=totalrows; i++) //1
		{
			for (int j=0; j<totalcols; j++) //1 // i is rows and j is cols
			{
				logindata[i-1][j] = xlUtil.getCellData("Data", i, j); //0,0
			}
		}
		return logindata; //return two dimensional array
	}
	
	//DataProvider 2
	
	//DataProvider 3
}
