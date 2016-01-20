/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business.mapper;

import de.hk.exceldemo.exception.FileFormatException;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author palmherby
 */
abstract class AbstractRowMapper implements RowMapper {

    protected void checkStringCell(Cell cell, String value) throws FileFormatException, IllegalArgumentException {
        if (cell == null || cell.getStringCellValue() == null) {
            throw new IllegalArgumentException("Zelle '" + value + "' konnte nicht gefunden werden");
        } else if (!cell.getStringCellValue().trim().equalsIgnoreCase(value.trim())) {
            throw new FileFormatException(value + " erwartet statt " + cell.getStringCellValue());
        }
    }

    protected void checkNumericCell(Cell cell, String value) throws FileFormatException {

    }

}
