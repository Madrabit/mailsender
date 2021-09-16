package ru.madrabit.mailsender.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.madrabit.mailsender.dto.EmployeeDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Slf4j
public class CreateExcel {

    private final String filePath;


    public CreateExcel() {
        log.info(System.getProperty("user.dir"));
        filePath = System.getProperty("user.dir")
                + File.separator + "downloads" + File.separator
                + "employees.xlsx";

    }

    private Integer objectId;
    private String name;
    private String surename;
    private String email;
    private String depName;
    private Integer departmentNumber;

    public void createExcel(List<EmployeeDTO> employeeDTOList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Emp sheet");

        int rowNumber = 0;
        Cell cell;
        Row row;
        //
        XSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rowNumber);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Имя");
        cell.setCellStyle(style);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Отчество");
        cell.setCellStyle(style);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Email");
        cell.setCellStyle(style);
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Название отдела");
        cell.setCellStyle(style);
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Номер отдела");
        cell.setCellStyle(style);

        // Data
        for (EmployeeDTO emp : employeeDTOList) {
            row = sheet.createRow(++rowNumber);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(emp.getName());
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getPatronymic());
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(emp.getEmail());
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(emp.getDepName());
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(emp.getDepartmentNumber());
        }
        createFile(workbook);
    }


    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private void createFile(XSSFWorkbook workbook) {
        File file = new File(filePath);
        boolean mkdirs = !file.exists() ? file.getParentFile().mkdirs() : true;
        if (mkdirs) {
            try (FileOutputStream outFile = new FileOutputStream(file)) {
                workbook.write(outFile);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            log.info("Created file: {}", file.getAbsolutePath());
        } else {
            log.error("Directory was not created. {}", false);
        }

    }
}
