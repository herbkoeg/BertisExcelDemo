/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business.mapper;

import de.hk.exceldemo.exception.FileFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 *
 * @author palmherby
 */
abstract class AbstractRowProcessor implements RowProcessor {

    protected void validateStringCell(Cell cell, String value) throws FileFormatException, IllegalArgumentException {
        if (cell == null || cell.getStringCellValue() == null) {
            throw new IllegalArgumentException("Zelle '" + value + "' konnte nicht gefunden werden");
        } else if(cell.getCellType() != XSSFCell.CELL_TYPE_STRING) 
        {
            throw new FileFormatException("Ungueltiger CellType " + cell.getCellType() + " fuer " + value);
        }
        else if (!cell.getStringCellValue().trim().equalsIgnoreCase(value.trim())) {
            throw new FileFormatException(value + " erwartet statt " + cell.getStringCellValue());
        }
    }

    protected void checkNumericCell(Cell cell, String value) throws FileFormatException {
        if (cell == null || cell.getStringCellValue() == null) {
            throw new IllegalArgumentException("Zelle '" + value + "' konnte nicht gefunden werden");
        } else if(cell.getCellType() != XSSFCell.CELL_TYPE_NUMERIC) 
        {
            throw new FileFormatException("Ungueltiger CellType " + cell.getCellType() + " fuer " + value);
        }
        else if (!cell.getStringCellValue().trim().equalsIgnoreCase(value.trim())) {
            throw new FileFormatException(value + " erwartet statt " + cell.getStringCellValue());
        }
    }

}
