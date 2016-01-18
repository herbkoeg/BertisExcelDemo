/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author palmherby
 */
public class ExcelAdapterTest {
    
    public ExcelAdapterTest() {
    }
    
    @Before
    public void setUp() {

    }
    
    @Test
    public void firstTest() throws IOException, InvalidFormatException {
        ExcelAdapter cut = new ExcelAdapter();
        String path = "/Users/palmherby/Entwickeln/netBeansProjects/ExcelDemo/beitrag.xlsx";
        FileInputStream file = new FileInputStream(new File(path));
        XSSFSheet sheet = cut.loadXSSFSheet(file, 0);
        assertNotNull(sheet);
        
        cut.getRelevantRowsOld(sheet);
    }
}
