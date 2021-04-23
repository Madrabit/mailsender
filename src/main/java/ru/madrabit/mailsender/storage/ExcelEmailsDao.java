package ru.madrabit.mailsender.storage;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.madrabit.mailsender.config.StorageProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Getter
public class ExcelEmailsDao implements EmailsDAO {

    private List<String> emails;
    private final Path rootLocation;

    public ExcelEmailsDao(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void readEmails() {
        emails = new ArrayList<>();
        File excelFile = new File(rootLocation + File.separator + "emails.xlsx");
        try (FileInputStream fis = new FileInputStream(excelFile)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    String cell = cellIterator.next().toString();
                    if (!cell.isEmpty()) {
                        emails.add(cell);
                    }
                }
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setEmails() {

    }
}
