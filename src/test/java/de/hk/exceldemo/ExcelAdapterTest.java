/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import de.hk.exceldemo.business.mapper.BeitragsaenderungRowMapper;
import de.hk.exceldemo.business.mapper.XSSFRowMapper;
import de.hk.exceldemo.exception.FileFormatException;
import de.hk.exceldemo.model.Beitragsaenderung;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author palmherby
 */
public class ExcelAdapterTest {

    private ExcelAdapter cut;
    private final String FILE_BEITRAGSAENDERUNG = "./src/test/resources/beitrag.xlsx";
    private final String FILE_OLD_FORMAT = "./src/test/resources/beitrag97.xls";
    private final String FILE_NOT_EXISTING = "notexisting.xlsx";
    private final String FILE_GENERATE = "./src/test/resources/beitragGenerated.xlsx";


    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        cut = new ExcelAdapter();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loadXSSFSheetTest() throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        XSSFSheet sheet = this.cut.getXSSFSheet(file, 0);
        assertNotNull(sheet);
    }

    @Test(expected = IOException.class)
    public void loadXSSFSheetFailureTest() throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(FILE_NOT_EXISTING));
        this.cut.getXSSFSheet(file, 0);
    }

    @Test(expected = POIXMLException.class)
    public void loadXSSFSheetFailureOldFormatTest() throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(FILE_OLD_FORMAT));
        this.cut.getXSSFSheet(file, 0);
    }

    @Test
    public void getHeaderTest() throws FileNotFoundException, IOException, InvalidFormatException, FileFormatException {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        List<Row> auftragHeader = cut.getHeaderRows(fileInputStream);
        assertNotNull(auftragHeader);
        assertEquals("Beitragsaenderung",auftragHeader.get(0).getCell(0).getStringCellValue());
    }
    
    @Test
    public void getRelevantRows() throws FileNotFoundException, IOException, InvalidFormatException{
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
     
        List<Row> rows = cut.getEntityRows(fileInputStream);
        
        assertEquals(24, rows.size());
    }
    
    @Test
    public void createXSSFWorkbook() throws FileNotFoundException, IOException, InvalidFormatException
    {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        List<Row> rows = cut.getEntityRows(fileInputStream);        
        XSSFWorkbook wb = cut.createXSSFWorkbook(rows);
        try ( //write this workbook to an Outputstream.
                FileOutputStream fileOut = new FileOutputStream(FILE_GENERATE)) {
            wb.write(fileOut);
            fileOut.flush();
        }
    }
    
    @Test
    public void addCell()
    {
        XSSFWorkbook wb1 = new XSSFWorkbook();
        XSSFSheet sheet1 = wb1.createSheet("testSheet1");
        XSSFRow newRow1 = sheet1.createRow(0);
        Cell cell1 = newRow1.createCell(0);
        cell1.setCellValue("jawoi");
        
        XSSFWorkbook wb2 = new XSSFWorkbook();
        XSSFSheet sheet2 = wb2.createSheet("testSheet2");
        XSSFRow newRow2 = sheet2.createRow(0);
        
        cut.addCell(newRow2, 0, cell1);
        
        Iterator<Cell> celltIt = newRow2.cellIterator();
        assertEquals("jawoi", celltIt.next().getStringCellValue());
    }
    
    @Test
    public void getXSSFRowMapper() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        XSSFRowMapper mapper = cut.getXSSFRowMapper(fileInputStream);        
        
        assertTrue(mapper instanceof BeitragsaenderungRowMapper);
    }
}
