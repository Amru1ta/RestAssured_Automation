package api.utilities;

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

public class ExcelUtilities {

public FileInputStream fi;
public FileOutputStream fo;
public XSSFWorkbook wb;
public XSSFSheet ws;
public XSSFRow row;
public XSSFCell cell;
public CellStyle style;
String path;
public ExcelUtilities(String path) {
    this.path = path;
}
// Get row count
public int getRowCount(String sheetName) throws IOException {
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    int rowcount = ws.getLastRowNum() + 1;
    wb.close();
    fi.close();
    return rowcount;
}
// Get cell count
public int getCellCount(String sheetName, int rownum) throws IOException {
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    row = ws.getRow(rownum);
    if (row == null) {
        wb.close();
        fi.close();
        return 0;
    }
    int cellcount = row.getLastCellNum();
    wb.close();
    fi.close();
    return cellcount;
}
// Get cell data
public String getCellData(String sheetName, int rownum, int colnum)
        throws IOException {
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    if (ws == null) {
        wb.close();
        fi.close();
        return "";
    }
    row = ws.getRow(rownum);
    if (row == null) {
        wb.close();
        fi.close();
        return "";
    }
    cell = row.getCell(colnum);
    if (cell == null) {
        wb.close();
        fi.close();
        return "";
    }
    DataFormatter formatter = new DataFormatter();
    String data = formatter.formatCellValue(cell);
    wb.close();
    fi.close();
    return data;
}
// Set cell data
public void setCellData(String sheetName, int rownum,
                        int colnum, String data) throws IOException {
    File xlfile = new File(path);
    if (!xlfile.exists()) {
        wb = new XSSFWorkbook();
        fo = new FileOutputStream(path);
        wb.write(fo);
        fo.close();
        wb.close();
    }
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    if (wb.getSheet(sheetName) == null) {
        wb.createSheet(sheetName);
    }
    ws = wb.getSheet(sheetName);
    if (ws.getRow(rownum) == null) {
        ws.createRow(rownum);
    }
    row = ws.getRow(rownum);
    cell = row.createCell(colnum);
    cell.setCellValue(data);
    fo = new FileOutputStream(path);
    wb.write(fo);
    wb.close();
    fi.close();
    fo.close();
}
// Fill cell with red color
public void fillRedColor(String sheetName,
                         int rownum,
                         int colnum) throws IOException {
    fi = new FileInputStream(path);
    wb = new XSSFWorkbook(fi);
    ws = wb.getSheet(sheetName);
    row = ws.getRow(rownum);
    if (row == null) {
        wb.close();
        fi.close();
        return;
    }
    cell = row.getCell(colnum);
    if (cell == null) {
        wb.close();
        fi.close();
        return;
    }
    style = wb.createCellStyle();
    style.setFillForegroundColor(
            IndexedColors.RED.getIndex());
    style.setFillPattern(
            FillPatternType.SOLID_FOREGROUND);
    cell.setCellStyle(style);
    fo = new FileOutputStream(path);
    wb.write(fo);
    wb.close();
    fi.close();
    fo.close();
}

}
