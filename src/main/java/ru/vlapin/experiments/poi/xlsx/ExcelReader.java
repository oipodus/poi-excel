package ru.vlapin.experiments.poi.xlsx;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ExcelReader {

    @SneakyThrows
    public static Map<String, Double> toMap(String file, String sheetName) {

        val result = new HashMap<String, Double>();
        try (InputStream resource = ExcelReader.class.getResourceAsStream(file);
             val myExcelBook = new XSSFWorkbook(resource)) {

            XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);
            for (Row row : myExcelSheet) {
                Cell cell1 = row.getCell(1);
                Cell cell2 = row.getCell(2);
                if (cell1.getCellTypeEnum() == CellType.STRING &&
                        cell2.getCellTypeEnum() == CellType.NUMERIC)
                    result.put(cell1.getStringCellValue().trim(), cell2.getNumericCellValue());
            }
        }

        return result;
    }
}
