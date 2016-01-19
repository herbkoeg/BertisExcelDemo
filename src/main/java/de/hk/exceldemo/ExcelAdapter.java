/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import de.hk.exceldemo.exception.FileFormatException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.examples.CellTypes;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author palmherby
 */
public class ExcelAdapter {

    List<Row> getEntityRows(FileInputStream fileInputStream) throws IOException, InvalidFormatException {
        return ExcelAdapter.this.getEntityRows(fileInputStream, 0);
    }

    List<Row> getEntityRows(FileInputStream fileInputStream, int sheetNr) throws IOException, InvalidFormatException {
        XSSFSheet sheet = getXSSFSheet(fileInputStream, sheetNr);

        List<Row> relevantRows = new ArrayList<>();
        int counter = 0;
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            counter++;
            if (counter > 2) // ignore first Lines
            {
                relevantRows.add(row);
            }
        }
        return relevantRows;
    }

    List<Row> getHeaderRows(FileInputStream fileInputStream) throws FileFormatException, IOException, InvalidFormatException {
        return getHeaderRows(fileInputStream, 0);
    }

    /**
     * Header consists of 2 rows. First row is the specification of the gevo,
     * second is the column description
     *
     * @param fileInputStream
     * @param sheetNr
     * @return
     * @throws FileFormatException
     * @throws IOException
     * @throws InvalidFormatException
     */
    List<Row> getHeaderRows(FileInputStream fileInputStream, int sheetNr) throws FileFormatException, IOException, InvalidFormatException {
        List<Row> header = new ArrayList<>();
        XSSFSheet sheet = getXSSFSheet(fileInputStream, sheetNr);
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {  // first row
            header.add(rowIterator.next());
            if (rowIterator.hasNext()) {  // second row
                header.add(rowIterator.next());
            } else {
                throw new FileFormatException("Ungueltiger Header");
            }
        } else {
            throw new FileFormatException("Ungueltiger Header");
        }
        return header;
    }

    XSSFSheet getXSSFSheet(FileInputStream file, int sheetNr) throws IOException, InvalidFormatException {

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(sheetNr);
        return sheet;
    }

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

    void addCell(XSSFRow newRow, int cellCount, Cell cell) {
        XSSFCell newCell = newRow.createCell(cellCount, cell.getCellType());
        
        if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
            newCell.setCellValue(cell.getStringCellValue());
        }
        else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            newCell.setCellValue(cell.getNumericCellValue());
        }
    }

}
