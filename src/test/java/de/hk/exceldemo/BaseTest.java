/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import de.hk.exceldemo.business.service.ExcelAdapter;
import de.hk.exceldemo.business.service.SheetService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author palmherby
 */
public class BaseTest {
    
    protected final String FILE_BEITRAGSAENDERUNG = "./src/test/resources/beitrag.xlsx";
    protected final String FILE_OLD_FORMAT = "./src/test/resources/beitrag97.xls";
    protected final String FILE_NOT_EXISTING = "notexisting.xlsx";
    protected final String FILE_GENERATE = "./src/test/resources/beitragGenerated.xlsx";

    private ExcelAdapter excelAdapter = new ExcelAdapter() ;
    private SheetService sheetService = new SheetService();

    
    protected Sheet getBeitragsaenderungSheet() throws FileNotFoundException, IOException, InvalidFormatException {
        FileInputStream fileInputStream = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        return excelAdapter.getSheet(fileInputStream, 0);
    }
    
}
