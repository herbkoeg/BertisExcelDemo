package de.hk.exceldemo.business.processor;

import de.hk.exceldemo.BaseTest;
import de.hk.exceldemo.business.service.ExcelAdapter;
import de.hk.exceldemo.business.service.SheetService;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;

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
        Sheet sheet = getBeitragsaenderungSheet();
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
