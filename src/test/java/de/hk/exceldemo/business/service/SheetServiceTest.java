/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business.service;

import de.hk.exceldemo.BaseTest;
import de.hk.exceldemo.business.processor.BeitragsaenderungRowProcessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author palmherby
 */
public class SheetServiceTest extends BaseTest{
    
    public SheetServiceTest() {
    }

    SheetService cut;
    ExcelAdapter excelAdapter ;

    @Before
    public void init() {
        excelAdapter = new ExcelAdapter();
        cut = new SheetService();
    }
            
    
    @Test
    public void testGetDataRows() throws FileNotFoundException, IOException, InvalidFormatException {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        XSSFSheet sheet = excelAdapter.getXSSFSheet(fileInputStream, 0);
        assertEquals(24,cut.getDataRows(sheet).size());
    }
    
    @Test
    public void testGetXSSFRowMapper() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        XSSFSheet sheet = excelAdapter.getXSSFSheet(fileInputStream, 0);
        assertTrue(cut.getXSSFRowProcessor(sheet) instanceof BeitragsaenderungRowProcessor);    
    }
    
    @Test
    public void testType() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        XSSFSheet sheet = excelAdapter.getXSSFSheet(fileInputStream, 0);
        assertEquals("Beitragsaenderung", cut.getType(sheet));
    }
    
}
