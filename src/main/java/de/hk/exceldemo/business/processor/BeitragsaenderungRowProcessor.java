package de.hk.exceldemo.business.processor;

import de.hk.exceldemo.exception.FileFormatException;
import de.hk.exceldemo.model.Auftrag;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author palmherby
 */
public class BeitragsaenderungRowProcessor extends AbstractRowProcessor{

    @Override
    public void validateInfoHeaderRow(Row row) throws FileFormatException {
        
        validateStringCell(row.getCell(0), "VNR");
        validateStringCell(row.getCell(1), "Stichtag");
        
    }
    
    @Override
    public Row createInfoHeaderRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Row createInfoRow(Row row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public XSSFWorkbook createInfoWorkbook(List<Row> rows) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validateAuftragHeaderRow(Row row) throws FileFormatException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validateAuftragRow(Row row) throws FileFormatException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Auftrag createAuftrag(Row row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
