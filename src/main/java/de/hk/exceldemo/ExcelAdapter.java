/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import de.hk.exceldemo.exception.FileFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author palmherby
 */
public class ExcelAdapter {

    /**
     * Returns the relevant Rows of the Excel-File, e.g. all rows needed to
     * create an Aufrag
     *
     * @param sheet
     * @throws FileNotFoundException
     * @throws IOException
     * @deprecated
     */
    public void getRelevantRowsOld(XSSFSheet sheet) throws FileNotFoundException, IOException {

        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "t");
                        break;
                }
            }
            System.out.println("");
        }

    }

    List<Row> getRelevantRows(FileInputStream fileInputStream) throws IOException, InvalidFormatException {
        return getRelevantRows(fileInputStream, 0);
    }

    List<Row> getRelevantRows(FileInputStream fileInputStream, int sheetNr) throws IOException, InvalidFormatException {
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

    List<Row> getHeader(FileInputStream fileInputStream) throws FileFormatException, IOException, InvalidFormatException {
        return getHeader(fileInputStream, 0);
    }

    /**
     * Header consists of 2 rows.
     * First row is the specification of the gevo, second is the colomn
     * description
     * @param fileInputStream
     * @param sheetNr
     * @return
     * @throws FileFormatException
     * @throws IOException
     * @throws InvalidFormatException 
     */
    List<Row> getHeader(FileInputStream fileInputStream, int sheetNr) throws FileFormatException, IOException, InvalidFormatException {
        List<Row> header = new ArrayList<>();
        XSSFSheet sheet = getXSSFSheet(fileInputStream, sheetNr);
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            header.add(rowIterator.next());
            if (rowIterator.hasNext()) {
                header.add(rowIterator.next());
            }
            else {
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

}
