package ru.vlapin.experiments.poi.xlsx;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

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

    @SneakyThrows
    public static Map<String, List<String>> toListMap(String file, String sheetName) {

        @Cleanup InputStream resource = ExcelReader.class.getResourceAsStream(file);
        @Cleanup val myExcelBook = new XSSFWorkbook(resource);

        XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);

        return getColumns(myExcelSheet).stream()
                .map(cells -> cells.stream()
                        .map(ExcelReader::getStringCellValue)
                        .collect(Collectors.toList()))
                .map(cells -> Tuple.of(cells.get(0), cells.subList(1, cells.size())))
                .collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));
    }

    private static List<List<Cell>> getColumns(XSSFSheet excelSheet) {
        List<List<Cell>> columns = new ArrayList<>();
        for (Row cells : excelSheet) {
            Iterator<Cell> cellIterator = cells.iterator();
            for (int i = 0; cellIterator.hasNext(); i++) {
                if (columns.size() <= i)
                    columns.add(new ArrayList<>());
                columns.get(i).add(cellIterator.next());
            }
        }
        return columns;
    }

    private static String getStringCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return Double.toString(cell.getNumericCellValue());
            case BOOLEAN: return Boolean.toString(cell.getBooleanCellValue());
            default: throw new CantRecognizeCellTypeException(cell);
        }
    }
}
