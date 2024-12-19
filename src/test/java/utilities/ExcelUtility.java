package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public FileInputStream fileinput;
	public FileOutputStream fileoutput;
	public XSSFWorkbook workbook;
	public XSSFSheet worksheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
    String path;
	
	public ExcelUtility(String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fileinput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileinput);
		worksheet = workbook.getSheet(sheetName);
		int rowcount = worksheet.getLastRowNum();
		workbook.close();
		fileinput.close();
		return rowcount;	
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fileinput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileinput);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fileinput.close();
		return cellcount;	
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fileinput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileinput);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		String data;
		try
		{
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell); //Returns the formatted of a cell value as a String 
			                                        //regardless excel type.
		}
		catch(Exception e)
		{
			data="";
		}

		workbook.close();
		fileinput.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		File xlfile = new File(path);
		if (!xlfile.exists()) //if file not exists then create new file
		{
			workbook = new XSSFWorkbook();
			fileoutput = new FileOutputStream(path);
			workbook.write(fileoutput);	
		}
		
		fileinput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileinput);
		
		if (workbook.getSheetIndex(sheetName)==-1)
		{
			workbook.createSheet(sheetName); //If sheet not exits then create new sheet.
		}
		worksheet = workbook.getSheet(sheetName);
		
		if (worksheet.getRow(rownum)==null)
		{
			worksheet.createRow(rownum); //If row not exits then create new row.
		}
		row = worksheet.getRow(rownum);
		
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fileoutput = new FileOutputStream(path);
		workbook.write(fileoutput);
		workbook.close();
		fileinput.close();
		fileoutput.close();
	}
	
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException
	{
		fileinput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileinput);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fileoutput = new FileOutputStream(path);
		workbook.write(fileoutput);
		workbook.close();
		fileinput.close();
		fileoutput.close();
	}
	
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException
	{
		fileinput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileinput);
		worksheet = workbook.getSheet(sheetName);
		row = worksheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fileoutput = new FileOutputStream(path);
		workbook.write(fileoutput);
		workbook.close();
		fileinput.close();
		fileoutput.close();
	}
}
