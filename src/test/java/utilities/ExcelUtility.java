package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	public static FileInputStream fileInput;
	public static FileOutputStream fileOutput;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String excelSheet) throws IOException
	{
		fileInput = new FileInputStream(path);
		
		workbook = new XSSFWorkbook(fileInput);
		
		sheet = workbook.getSheet(excelSheet);
		
		int rowCount = sheet.getLastRowNum();
		
		workbook.close();
		fileInput.close();
		
		return rowCount;
	}
	
	public int getCellCount(String excelSheet, int rowNum) throws IOException
	{
		fileInput = new FileInputStream(path);
		
		workbook = new XSSFWorkbook(fileInput);
		
		sheet = workbook.getSheet(excelSheet);
		
		row = sheet.getRow(rowNum);
		
		int cellCount = row.getLastCellNum();
		
		workbook.close();
		fileInput.close();
		
		return cellCount;
	}
	
	public String getCellData(String excelSheet, int rowNum, int colNum) throws IOException
	{
		fileInput = new FileInputStream(path);
		
		workbook = new XSSFWorkbook(fileInput);
		
		sheet = workbook.getSheet(excelSheet);
		
		row = sheet.getRow(rowNum);
		
		cell = row.getCell(colNum);
		
		String data;
		
		try
		{
			//data = cell.toString();
			
			DataFormatter formatter = new DataFormatter();
			
			data = formatter.formatCellValue(cell);//returns the formatted value of a cell as a String regardless of the cell type
		}
		catch(Exception e)
		{
			data = "";
		}
		
		workbook.close();
		fileInput.close();
		
		return data;
	}
	
	public String setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException
	{
		File excelFile = new File(path);
		
		if(!excelFile.exists())
		{
			workbook = new XSSFWorkbook();
			fileOutput = new FileOutputStream(path);
			workbook.write(fileOutput);
		}
		
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		
		if(workbook.getSheetIndex(sheetName) == -1)
		{
			workbook.createSheet(sheetName);
		}
		
		sheet = workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum) == null)
		{
			sheet.createRow(rowNum);
		}
		
		row = sheet.getRow(rowNum);
		
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		
		fileOutput = new FileOutputStream(path);
		
		workbook.write(fileOutput);
		
		workbook.close();
		fileInput.close();
		fileOutput.close();
		
		return data;
	}
	
	public void fillGreenColor(String excelSheet, int rowNum, int colNum) throws IOException
	{
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(excelSheet);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fileOutput = new FileOutputStream(path);
		
		workbook.write(fileOutput);
		
		workbook.close();
		fileInput.close();
		fileOutput.close();
	}
	
	public void fillRedColor(String excelSheet, int rowNum, int colNum) throws IOException
	{
		fileInput = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInput);
		sheet = workbook.getSheet(excelSheet);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		style = workbook.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		
		fileOutput = new FileOutputStream(path);
		
		workbook.write(fileOutput);
		
		workbook.close();
		fileInput.close();
		fileOutput.close();
	}
}
