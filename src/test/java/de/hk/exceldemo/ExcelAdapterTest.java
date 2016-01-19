/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import de.hk.exceldemo.exception.FileFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
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
    private final String FILE_VALID = "./src/test/resources/beitrag.xlsx";
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
        FileInputStream file = new FileInputStream(new File(FILE_VALID));
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
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_VALID));
        List<Row> auftragHeader = cut.getHeaderRows(fileInputStream);
        assertNotNull(auftragHeader);
        assertEquals("Beitragsaenderung",auftragHeader.get(0).getCell(0).getStringCellValue());
    }
    
    @Test
    public void getRelevantRows() throws FileNotFoundException, IOException, InvalidFormatException{
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_VALID));
     
        List<Row> rows = cut.getEntityRows(fileInputStream);
        
        assertEquals(24, rows.size());
    }
    
    @Test
    public void createXSSFWorkbook() throws FileNotFoundException, IOException, InvalidFormatException
    {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_VALID));
        List<Row> rows = cut.getEntityRows(fileInputStream);
        

        XSSFWorkbook wb = cut.createXSSFWorkbook(rows);
        
        //write this workbook to an Outputstream.
        FileOutputStream fileOut = new FileOutputStream(FILE_GENERATE);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();

    }
}
