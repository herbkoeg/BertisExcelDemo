/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo;

import java.io.IOException;
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void firstTest() throws IOException {
        ExcelAdapter cut = new ExcelAdapter();
        
        String path = "/Users/palmherby/Entwickeln/netBeansProjects/ExcelDemo/beitrag.xlsx";
        
        cut.getRelevantRows(path);
        
    }
}
