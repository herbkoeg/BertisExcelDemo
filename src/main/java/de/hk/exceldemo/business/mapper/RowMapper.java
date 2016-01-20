/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business.mapper;

import de.hk.exceldemo.exception.FileFormatException;
import de.hk.exceldemo.model.Auftrag;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author palmherby
 */
public interface RowMapper {
    // produce Info Excel-Sheet
    abstract String validateInfoHeaderRow(Row row) throws FileFormatException;
    abstract Row createInfoHeaderRow();
    abstract Row createInfoRow(Row row);
    abstract XSSFWorkbook createInfoWorkbook(List<Row> rows);

    // handle Auftrag Excel-Sheet
    abstract String validateAuftragHeaderRow(Row row)throws FileFormatException;
    abstract String validateAuftragRow(Row row)throws FileFormatException;
    abstract Auftrag createAuftrag(Row row);
    
}
