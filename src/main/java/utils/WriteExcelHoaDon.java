package utils;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.bean.OrderDetail;
import model.bean.Products;
import model.bean.order_inf;
import model.dao.OrderDetailDAO;
import model.dao.OrderInfoDAO;
import model.dao.ProductsDAO;

 
public class WriteExcelHoaDon {
    public static final int COLUMN_INDEX_ID         = 0;
    public static final int COLUMN_INDEX_MaDH      = 1;
    public static final int COLUMN_INDEX_Tensp      = 2;
    public static final int COLUMN_INDEX_NgayDat   = 3;
    public static final int COLUMN_INDEX_Soluong      = 4;
    public static final int COLUMN_INDEX_ChiPhi      = 5;
    public static final int COLUMN_INDEX_Sdt      = 6;
    private static CellStyle cellStyleFormatNumber = null;
    
    public static void main(String[] args) throws IOException {
    	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        final List<OrderDetail> listdh = orderDetailDAO.getListOrderDetail();
        final String excelFilePath = "C:/demo/dathang.xlsx";
        writeExcel(listdh, excelFilePath);
    }
 
    public static void writeExcel(List<OrderDetail> listdh, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
        
        // Create sheet
        Sheet sheet = workbook.createSheet("Donhang"); // Create sheet with sheet name
 
        int rowIndex = 0;
         
        // Write header
        writeHeader(sheet, rowIndex);
 
        // Write data
        rowIndex++;
        for (OrderDetail it : listdh) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(it, row);
            rowIndex++;
        }
         
        // Write footer
        writeFooter(sheet, rowIndex);
 
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
 
        cell = row.createCell(COLUMN_INDEX_MaDH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã hàng");
 
        cell = row.createCell(COLUMN_INDEX_Tensp);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên sản phẩm");
 
        cell = row.createCell(COLUMN_INDEX_Soluong);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số lượng");
 
        cell = row.createCell(COLUMN_INDEX_NgayDat);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Ngày đặt");
        
        cell = row.createCell(COLUMN_INDEX_ChiPhi);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Chi phí");
        
        cell = row.createCell(COLUMN_INDEX_Sdt);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");
    }
 
    // Write data
    private static void writeBook(OrderDetail dh, Row row) {
        
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");
             
            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        
        OrderInfoDAO orderInfoDAO =new OrderInfoDAO();
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(dh.getId());
        order_inf itdh = orderInfoDAO.getItemById(dh.getIdInfo());
        
        cell = row.createCell(COLUMN_INDEX_MaDH);
        cell.setCellValue(itdh.getCode());
        
        ProductsDAO menuDAO = new ProductsDAO();
        Products itsp = menuDAO.getProductsById(dh.getIdPro());
        cell = row.createCell(COLUMN_INDEX_Tensp);
        cell.setCellValue(itsp.getName());
 
        cell = row.createCell(COLUMN_INDEX_Soluong);
        cell.setCellValue(dh.getNumber());
        cell.setCellStyle(cellStyleFormatNumber);
        
        cell = row.createCell(COLUMN_INDEX_NgayDat);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        String nm = dateformat.format(itdh.getDate());
        cell.setCellValue(nm);
        
        
        cell = row.createCell(COLUMN_INDEX_ChiPhi);
        cell.setCellValue(dh.getPrice() * dh.getNumber());
        
        cell = row.createCell(COLUMN_INDEX_Sdt);
        cell.setCellValue(itdh.getPhone());
         
       
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
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(COLUMN_INDEX_ChiPhi);
        ArrayList<OrderDetail> listdh = new ArrayList<OrderDetail>();
        OrderDetailDAO chiTietDonHangDAO = new OrderDetailDAO();
        listdh = chiTietDonHangDAO.getListOrderDetail();
        int S = 0;
        for(OrderDetail it : listdh) {
        	S+=it.getPrice();
        }
        cell.setCellValue(S);
        
    }
     
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

