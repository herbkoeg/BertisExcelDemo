package de.hk.exceldemo.business.service;

import de.hk.exceldemo.exception.FileFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import de.hk.exceldemo.business.processor.RowProcessor;
import org.apache.poi.ss.usermodel.Sheet;


/**
 *
 * @author palmherby
 */
public class SheetService {

    List<Row> getDataRows(Sheet sheet) throws IOException, InvalidFormatException {
        List<Row> relevantRows = new ArrayList<>();
        int counter = 0;
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            counter++;
            if (counter > 2) // ignore first Lines
            {
                relevantRows.add(row);
            }
        }
        return relevantRows;
    }

    public String getType(Sheet sheet) throws FileFormatException, IOException, InvalidFormatException {
        List<Row> rows = getHeaderRows(sheet, 0);
        Iterator<Row> rowIt = rows.iterator();
        return rowIt.next().getCell(0).getStringCellValue();
    }

    public Row getHeadingRow(Sheet sheet) throws FileFormatException, IOException, InvalidFormatException {
        List<Row> rows = getHeaderRows(sheet, 1);
        if(rows.size() < 2) throw new FileFormatException("Keine Ueberschrift gefunden");
        Iterator<Row> rowIt = rows.iterator();
        rowIt.next();
        return rowIt.next();
    }

    private List<Row> getHeaderRows(Sheet sheet, int sheetNr) throws FileFormatException, IOException, InvalidFormatException {
        List<Row> header = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {  // first row
            header.add(rowIterator.next());
            if (rowIterator.hasNext()) {  // second row
                header.add(rowIterator.next());
            } else {
                throw new FileFormatException("Ungueltiger Header");
            }
        } else {
            throw new FileFormatException("Ungueltiger Header");
        }
        return header;
    }

    public RowProcessor getRowProcessor(Sheet sheet) throws FileFormatException, IOException, InvalidFormatException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String type = getType(sheet);

        String className = "de.hk.exceldemo.business.processor." + type + "RowProcessor";
        Class c = Class.forName(className);
        return (RowProcessor) c.newInstance();
    }

}
