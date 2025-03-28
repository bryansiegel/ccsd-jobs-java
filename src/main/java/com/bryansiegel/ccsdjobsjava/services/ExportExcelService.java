package com.bryansiegel.ccsdjobsjava.services;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportExcelService {

    public ByteArrayInputStream exportToExcel(List<AdministrativePersonnel> personnelList) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Administrative Personnel");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Job Title");
            headerRow.createCell(2).setCellValue("Job Code");
            headerRow.createCell(3).setCellValue("Reference Code");
            headerRow.createCell(4).setCellValue("Division Unit");
            headerRow.createCell(5).setCellValue("Classification");
            headerRow.createCell(6).setCellValue("Terms of Employment");
            headerRow.createCell(7).setCellValue("FLSA Status");
            headerRow.createCell(8).setCellValue("Position Summary");
            headerRow.createCell(9).setCellValue("Essential Duties and Responsibilities");
            headerRow.createCell(10).setCellValue("Position Expectations");
            headerRow.createCell(11).setCellValue("Position Requirements");

            int rowIdx = 1;
            for (AdministrativePersonnel personnel : personnelList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(personnel.getId());
                row.createCell(1).setCellValue(personnel.getJobTitle());
                row.createCell(2).setCellValue(personnel.getJobCode());
                row.createCell(3).setCellValue(personnel.getReferenceCode());
                row.createCell(4).setCellValue(personnel.getDivisionUnit());
                row.createCell(5).setCellValue(personnel.getClassification());
                row.createCell(6).setCellValue(personnel.getTermsOfEmployment());
                row.createCell(7).setCellValue(personnel.getFlsaStatus());
                row.createCell(8).setCellValue(personnel.getPositionSummary());
                row.createCell(9).setCellValue(personnel.getEssentialDutiesAndResponsibilities());
                row.createCell(10).setCellValue(personnel.getPositionExpectations());
                row.createCell(11).setCellValue(personnel.getPositionRequirements());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}