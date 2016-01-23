/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business.service;

import de.hk.exceldemo.BaseTest;
import de.hk.exceldemo.model.BeitragsaenderungGeVo;
import de.hk.exceldemo.model.GeVo;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author palmherby
 */
public class BavCockpitServiceTest extends BaseTest {

    private BavCockpitService cut;
    
    public BavCockpitServiceTest() {
    }
    
    
    
    @Before
    public void setUp() {
        cut = new BavCockpitService();
    }

    @Test
    public void testCreateBeitragsaenderungGevoList() throws Exception {
        FileInputStream file = new FileInputStream(new File(FILE_BEITRAGSAENDERUNG));
        
        List<GeVo> gevoList = cut.createGevoList(file);
        
        assertEquals(24,gevoList.size());
        
        BeitragsaenderungGeVo gevo = (BeitragsaenderungGeVo) gevoList.get(0);
        
        assertEquals(70123456l,gevo.getVnr().longValue());
    }

    @Test
    public void testCreateVertragInfoWorkbook() {
    }
    
}
