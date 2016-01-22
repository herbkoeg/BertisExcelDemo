package de.hk.exceldemo.business.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author palmherby
 */
public class ExcelAdapter {


    public XSSFWorkbook createXSSFWorkbook(List<Row> rows) throws IOException {

        String sheetName = "baVormer";

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        Iterator<Row> rowIt = rows.iterator();

        int rowCount = 0;
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            XSSFRow newRow = sheet.createRow(row.getRowNum());
            rowCount++;
            newRow.setRowStyle(row.getRowStyle());

            Iterator<Cell> cellIt = row.cellIterator();
            int cellCount = 0;
            while (cellIt.hasNext()) {
                Cell cell = cellIt.next();
                cellCount++;
                addCell(newRow, cellCount, cell);
            }
        }

        return wb;
    }

    void addCell(Row newRow, int cellCount, Cell cell) {
        Cell newCell = newRow.createCell(cellCount, cell.getCellType());

        if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
            newCell.setCellValue(cell.getStringCellValue());
        } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            newCell.setCellValue(cell.getNumericCellValue());
        }
    }

    public Sheet getSheet(FileInputStream fileInputStream, int sheetNr) throws IOException, InvalidFormatException {

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        return workbook.getSheetAt(sheetNr);
    }

}
