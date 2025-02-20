package com.max_num_search.max_num_search.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class XlsxFileReaderService {

    List<Integer> readNumbersFromFile(String filePath) throws IOException {
        List<Integer> numList = new ArrayList<>();

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row != null) {
                Cell cell = row.getCell(0); // Нулевой столбец
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    int cellValue = (int) cell.getNumericCellValue();
                    log.info("Строка {}: {}", rowIndex + 1, cellValue);
                    numList.add(cellValue);
                }
            }
        }
        return numList;
    }

}
