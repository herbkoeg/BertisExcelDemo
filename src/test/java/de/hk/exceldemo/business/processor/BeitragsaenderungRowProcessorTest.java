/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business.processor;

import de.hk.exceldemo.BaseTest;
import de.hk.exceldemo.business.service.ExcelAdapter;
import de.hk.exceldemo.business.service.SheetService;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author palmherby
 */
public class BeitragsaenderungRowProcessorTest extends BaseTest {
    
    public BeitragsaenderungRowProcessorTest() {
    }
    
    private BeitragsaenderungRowProcessor cut;
    private ExcelAdapter excelAdapter ;
    private SheetService sheetService;

    
    @Before
    public void setUp() {
        excelAdapter = new ExcelAdapter();
        sheetService  = new SheetService();
        cut = new BeitragsaenderungRowProcessor();
    }

    @Test
    public void testValidateInfoHeaderRow() throws Exception {
         FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        XSSFSheet sheet = excelAdapter.getXSSFSheet(fileInputStream, 0);
        cut.validateHeadingRow(sheetService.getHeadingRow(sheet));
    }

    @Test
    public void testCreateInfoHeaderRow() {
    }

    @Test
    public void testCreateInfoRow() {
    }

    @Test
    public void testCreateInfoWorkbook() {
    }

    @Test
    public void testValidateAuftragHeaderRow() throws Exception {
    }

    @Test
    public void testValidateAuftragRow() throws Exception {
    }

    @Test
    public void testCreateAuftrag() {
    }
    
}
