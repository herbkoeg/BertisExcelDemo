/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author palmherby
 */
public interface AuftragMapper {
    
    abstract Object createAufrag(XSSFSheet xssfSheet);
}
