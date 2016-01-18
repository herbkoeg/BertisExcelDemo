/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import de.hk.exceldemo.business.AuftragHeader;
import de.hk.exceldemo.exception.FileFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author palmherby
 */
public class ExcelAdapterTest {

    private ExcelAdapter cut ;
    private final String FILE_VALID = "./src/test/resources/beitrag.xlsx";
    private final String FILE_OLD_FORMAT = "./src/test/resources/beitrag97.xls";
    private final String FILE_NOT_EXISTING = "notexisting.xlsx";
    
    @Before
    public void setUp() {
    cut = new ExcelAdapter();
    }
    
    @Test
    public void loadXSSFSheetTest() throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(FILE_VALID));
        XSSFSheet sheet = this.cut.loadXSSFSheet(file, 0);
        assertNotNull(sheet);
    }
    
    @Test(expected = IOException.class)
    public void loadXSSFSheetFailureTest() throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(FILE_NOT_EXISTING));
        XSSFSheet sheet = this.cut.loadXSSFSheet(file, 0);
    }
    
    @Test(expected = POIXMLException.class)
    public void loadXSSFSheetFailureOldFormatTest() throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(FILE_OLD_FORMAT));
        XSSFSheet sheet = this.cut.loadXSSFSheet(file, 0);
    }

    
    @Test
    public void getHeaderTest() throws FileNotFoundException, IOException, InvalidFormatException, FileFormatException {
        FileInputStream file = new FileInputStream(new File(FILE_VALID));
        XSSFSheet sheet = this.cut.loadXSSFSheet(file, 0);
        
        AuftragHeader auftragHeader = cut.getHeader(sheet);
        
        assertNotNull(auftragHeader);
        
    }
    
    
    
}
