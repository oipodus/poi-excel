package ru.vlapin.experiments.poi.xlsx;

import org.apache.poi.ss.usermodel.Cell;

public class CantRecognizeCellTypeException extends RuntimeException {
    public CantRecognizeCellTypeException(Cell cell) {
        super("Can`t recognize cell type: " + cell);
    }
}
