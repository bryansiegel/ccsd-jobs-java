package com.bryansiegel.ccsdjobsjava.services;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ExportPDFService {

    public ByteArrayInputStream exportToPdf(AdministrativePersonnel personnel) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);

        // HTML content
        String htmlContent = "<h1>Administrative Personnel Details</h1>" +
                "<p><strong>ID:</strong> " + personnel.getId() + "</p>" +
                "<p><strong>Job Code:</strong> " + personnel.getJobCode() + "</p>" +
                "<p><strong>Division:</strong> " + personnel.getDivisionUnit() + "</p>" +
                "<p><strong>Classification:</strong> " + personnel.getClassification() + "</p>" +
                "<p><strong>Terms of Employment:</strong> " + personnel.getTermsOfEmployment() + "</p>" +
                "<p><strong>FLSA Status:</strong> " + personnel.getFlsaStatus() + "</p>" +
                "<p><strong>Position Summary:</strong> " + personnel.getPositionSummary() + "</p>" +
                "<p><strong>Essential Duties and Responsibilities:</strong> " + personnel.getEssentialDutiesAndResponsibilities() + "</p>" +
                "<p><strong>Position Expectations:</strong> " + personnel.getPositionExpectations() + "</p>" +
                "<p><strong>Position Requirements:</strong> " + personnel.getPositionRequirements() + "</p>";

        // Convert HTML to PDF
        HtmlConverter.convertToPdf(htmlContent, pdf.getWriter());

        return new ByteArrayInputStream(out.toByteArray());
    }
}