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

    public ByteArrayInputStream exportToPdf(AdministrativePersonnel personnel) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Add title
        document.add(new Paragraph("Administrative Personnel Details").setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)).setFontSize(18));

        // Add details
        document.add(new Paragraph("ID: " + personnel.getId()));
        document.add(new Paragraph("Job Code: " + personnel.getJobCode()));
        document.add(new Paragraph("Division: " + personnel.getDivisionUnit()));
        document.add(new Paragraph("Classification: " + personnel.getClassification()));
        document.add(new Paragraph("Terms of Employment: " + personnel.getTermsOfEmployment()));
        document.add(new Paragraph("FLSA Status: " + personnel.getFlsaStatus()));
        document.add(new Paragraph("Position Summary: " + personnel.getPositionSummary()));
        document.add(new Paragraph("Essential Duties and Responsibilities: " + personnel.getEssentialDutiesAndResponsibilities()));
        document.add(new Paragraph("Position Expectations: " + personnel.getPositionExpectations()));
        document.add(new Paragraph("Position Requirements: " + personnel.getPositionRequirements()));

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}