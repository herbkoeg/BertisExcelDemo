/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business.service;

import de.hk.exceldemo.business.processor.RowProcessor;
import de.hk.exceldemo.exception.FileFormatException;
import de.hk.exceldemo.model.Auftrag;
import de.hk.exceldemo.model.GeVo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author palmherby
 */
public class BavCockpitService {
    private ExcelAdapter excelAdapter = new ExcelAdapter();
    private SheetService sheetService = new SheetService();
    
    public List<GeVo> createGevoList(FileInputStream fileInputStream) throws IOException, InvalidFormatException, FileFormatException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        XSSFSheet sheet = excelAdapter.getXSSFSheet(fileInputStream, 0);
        RowProcessor xssfRowProcessor = sheetService.getXSSFRowProcessor(sheet);
        xssfRowProcessor.validateHeadingRow(sheetService.getHeadingRow(sheet));
                
        Iterator<Row> itDataRows = sheetService.getDataRows(sheet).iterator();
        List<GeVo> geVoList = new ArrayList<>();
        
        while (itDataRows.hasNext()) {
            Row row = itDataRows.next();
           // xssfRowProcessor.validateAuftragRow(row);
            geVoList.add(xssfRowProcessor.createGeVo(row));
        }

        return geVoList;
    }
    
    public XSSFWorkbook createVertragInfoWorkbook(XSSFWorkbook workbook) {
        
        return null;
    }
    
}
