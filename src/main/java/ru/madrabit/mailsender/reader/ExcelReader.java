package ru.madrabit.mailsender.reader;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Getter
public class ExcelReader {

    private final List<String> emails = new ArrayList<>();

    public ExcelReader() {
        readFile();
    }

    public void readFile() {
        File excelFile = new File("emails.xlsx");
        try (FileInputStream fis = new FileInputStream(excelFile)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    emails.add(cell.toString());
                }
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
