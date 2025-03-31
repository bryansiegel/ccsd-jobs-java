package com.bryansiegel.ccsdjobsjava.services;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportPDFService {

    public ByteArrayInputStream exportToPdf(List<AdministrativePersonnel> personnelList) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Add title
        document.add(new Paragraph("Administrative Personnel").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)).setFontSize(18));

        // Create table with headers
        Table table = new Table(new float[]{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2});
        table.addHeaderCell("ID");
        table.addHeaderCell("Job Title");
        table.addHeaderCell("Job Code");
        table.addHeaderCell("Reference Code");
        table.addHeaderCell("Division Unit");
        table.addHeaderCell("Classification");
        table.addHeaderCell("Terms of Employment");
        table.addHeaderCell("FLSA Status");
        table.addHeaderCell("Position Summary");
        table.addHeaderCell("Essential Duties and Responsibilities");
        table.addHeaderCell("Position Expectations");
        table.addHeaderCell("Position Requirements");

        // Add rows to the table
        for (AdministrativePersonnel personnel : personnelList) {
            table.addCell(String.valueOf(personnel.getId()));
            table.addCell(personnel.getJobTitle());
            table.addCell(personnel.getJobCode());
            table.addCell(personnel.getReferenceCode());
            table.addCell(personnel.getDivisionUnit());
            table.addCell(personnel.getClassification());
            table.addCell(personnel.getTermsOfEmployment());
            table.addCell(personnel.getFlsaStatus());
            table.addCell(personnel.getPositionSummary());
            table.addCell(personnel.getEssentialDutiesAndResponsibilities());
            table.addCell(personnel.getPositionExpectations());
            table.addCell(personnel.getPositionRequirements());
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}