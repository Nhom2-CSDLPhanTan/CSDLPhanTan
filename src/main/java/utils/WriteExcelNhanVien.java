package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.bean.Person;
import model.dao.PersonDAO;

	 
	public class WriteExcelNhanVien {
		public static final int COLUMN_INDEX_ID         = 0;
	    public static final int COLUMN_INDEX_Name      = 1;
	    public static final int COLUMN_INDEX_Email      = 2;
	    public static final int COLUMN_INDEX_Role      = 3;
	    private static CellStyle cellStyleFormatNumber = null;

	 
	    public static void main(String[] args) throws IOException {
	        final String excelFilePath = "C:/demo/Nhanvien.xlsx";
	        PersonDAO personDAO = new PersonDAO();
	        List<Person> listNV = personDAO.getListUserEmployee();
	         writeExcel(listNV,excelFilePath);
	        
	    }
	 
	    public static void writeExcel(List<Person> listNV, String excelFilePath) throws IOException {
	        // Create Workbook
	        Workbook workbook = getWorkbook(excelFilePath);
	        
	        // Create sheet
	        Sheet sheet = workbook.createSheet("nhanVien"); // Create sheet with sheet name
	 
	        int rowIndex = 0;
	         
	        // Write header
	        writeHeader(sheet, rowIndex);
	 
	        // Write data
	        rowIndex++;
	        for (Person it : listNV) {
	            // Create row
	            Row row = sheet.createRow(rowIndex);
	            // Write data on row
	            writeBook(it, row);
	            rowIndex++;
	        }
	         
	        // Write footer

	 
	        // Auto resize column witdth
	        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
	        autosizeColumn(sheet, numberOfColumn);
	 
	        // Create file excel
	        createOutputFile(workbook, excelFilePath);
	        System.out.println("Done!!!");
	    }
	 
	    // Create dummy data
	    
	 
	    // Create workbook
	    private static Workbook getWorkbook(String excelFilePath) throws IOException {
	        Workbook workbook = null;
	        
	        if (excelFilePath.endsWith("xlsx")) {
	            workbook = new XSSFWorkbook();
	        } else if (excelFilePath.endsWith("xls")) {
	            workbook = new HSSFWorkbook();
	        } else {
	            throw new IllegalArgumentException("The specified file is not Excel file");
	        }
	 
	        return workbook;
	    }
	 
	    // Write header with format
	    private static void writeHeader(Sheet sheet, int rowIndex) {
	        // create CellStyle
	        CellStyle cellStyle = createStyleForHeader(sheet);
	        
	        // Create row
	        Row row = sheet.createRow(rowIndex);
	         
	        // Create cells
	        Cell cell = row.createCell(COLUMN_INDEX_ID);
	        cell.setCellStyle(cellStyle);
	        cell.setCellValue("Id");
	 
	        cell = row.createCell(COLUMN_INDEX_Name);
	        cell.setCellStyle(cellStyle);
	        cell.setCellValue("Name");
	 
	        cell = row.createCell(COLUMN_INDEX_Email);
	        cell.setCellStyle(cellStyle);
	        cell.setCellValue("Email");
	        
	        cell = row.createCell(COLUMN_INDEX_Role);
	        cell.setCellStyle(cellStyle);
	        cell.setCellValue("Role");
	        
	       
	    }
	 
	    // Write data
	    private static void writeBook(Person itNV, Row row) {
	      
	            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");

	            //Create CellStyle
	            Workbook workbook = row.getSheet().getWorkbook();
	            cellStyleFormatNumber = workbook.createCellStyle();
	            cellStyleFormatNumber.setDataFormat(format);
	        
	        Cell cell = row.createCell(COLUMN_INDEX_ID);
	        cell.setCellValue(itNV.getUser_ID());
	        
	        cell = row.createCell(COLUMN_INDEX_Name);
	        cell.setCellValue(itNV.getUser_Name());
	        
	        cell = row.createCell(COLUMN_INDEX_Email);
	        cell.setCellValue(itNV.getUser_Email());
	 
	        cell = row.createCell(COLUMN_INDEX_Role);
	        cell.setCellValue(itNV.getUser_Role());
	       
	    }
	 
	    // Create CellStyle for header
	    private static CellStyle createStyleForHeader(Sheet sheet) {
	    	
	        // Create font
	        Font font = sheet.getWorkbook().createFont();
	        font.setFontName("Times New Roman"); 
	        font.setBold(true);
	        font.setFontHeightInPoints((short) 14); // font size
	        font.setColor(IndexedColors.WHITE.getIndex()); // text color
	 
	        // Create CellStyle
	        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	        cellStyle.setFont(font);
	        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
	        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        cellStyle.setBorderBottom(BorderStyle.THIN);
	        
	        return cellStyle;
	    }
	     
	    // Write footer
	     
	    // Auto resize column width
	    private static void autosizeColumn(Sheet sheet, int lastColumn) {
	        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
	            sheet.autoSizeColumn(columnIndex);
	        }
	    }
	     
	    // Create output file
	    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
	        try (OutputStream os = new FileOutputStream(excelFilePath)) {
	            workbook.write(os);
	        }
	    }
	}

