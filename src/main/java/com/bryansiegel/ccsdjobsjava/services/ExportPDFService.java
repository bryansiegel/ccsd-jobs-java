package com.bryansiegel.ccsdjobsjava.services;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class ExportPDFService {

    public ByteArrayInputStream exportToPdf(AdministrativePersonnel personnel) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);

        // Load and encode the image as Base64
        String logoPath = "src/main/resources/static/img/ccsd-email-logo.png"; // Adjust the path as needed
        byte[] logoBytes = Files.readAllBytes(Paths.get(logoPath));
        String base64Logo = Base64.getEncoder().encodeToString(logoBytes);

        // Format dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a");
        String formattedCreatedAt = personnel.getCreatedAt().format(formatter);
        String formattedUpdatedAt = personnel.getUpdatedAt().format(formatter);

        // HTML content
        String htmlContent = "<div class=\"container\">\n" +
                "<div class=\"row\">\n" +
                "<div class=\"col-md-12 mb-5\">\n" +
                "<div style=\"margin-bottom: 20px;\">" +
                "<img src='data:image/png;base64," + base64Logo + "' alt='Logo' width='120' height='80'></div>" +
                "<div'>" +
                "<div'>" +
                "<h1 style=\"color: #1771B7;font-size: 32px;font-weight:bold;padding-bottom: 25px;\">" + personnel.getJobTitle() + "</h1>" +
                "<h2 style=\"color: #214A7E;font-size: 20px;font-weight:bold;\">Position Details</h2>" +
                "Job Code: " + personnel.getJobCode() + "<br>" +
                "Reference Code: " + personnel.getReferenceCode() + "<br>" +
                "Division/Unit: " + personnel.getDivisionUnit() + "<br>" +
                "Classification: " + personnel.getClassification() + "<br>" +
                "Terms of Employment: " + personnel.getTermsOfEmployment() + "<br>" +
                "FLSA Status: " + personnel.getTermsOfEmployment() + "<br>" +
                "<h3  style=\"color: #214A7E;font-size: 20px;font-weight:bold;\">Position Summary:</h3>\n" +
                personnel.getPositionSummary() + "<hr>" +
                "<h3  style=\"color: #214A7E;font-size: 20px;font-weight:bold;\">Essential Duties and Responsibilities:</h3>\n" +
                personnel.getEssentialDutiesAndResponsibilities() + "<hr>" +
                "<h3  style=\"color: #214A7E;font-size: 20px;font-weight:bold;\">Position Expectations:</h3>\n" +
                personnel.getPositionExpectations() + "<hr>" +
                "<h3 style=\"color: #214A7E;font-size: 20px;font-weight:bold;\">Position Requirements:</h3>\n" +
                personnel.getPositionRequirements() + "<hr>" +
                "<h3 style=\"color: #214A7E;font-size: 20px;font-weight:bold;\">AA/EOE Statement</h3>\n" +
                "<p>The Clark County School District is proud to be an equal opportunity employer. The\n" +
                "Clark County School District is committed to providing all applicants and employees\n" +
                "equal employment opportunities without regard to race, color, religion, sex, gender\n" +
                "identity or expression, sexual orientation, national origin, genetics, disability, age, military\n" +
                "status, or other characteristics protected by applicable law. Here at Clark County School\n" +
                "District, we are a diverse group of people who honor the differences that drive innovative\n" +
                "solutions to meet the needs of our students and employees. We believe that through a\n" +
                "culture of inclusivity, we have the power to reflect the community we serve.</p>\n" +
                "<hr>\n" +
                "<h3 style=\"color: #214A7E;font-size: 20px;font-weight:bold;\">Job Revision Information</h3>" +
                "Created At: " + formattedCreatedAt + "<br>" +
                "Updated At: " + formattedUpdatedAt + "</div></div></div></div>"
                ;

        // Convert HTML to PDF
        HtmlConverter.convertToPdf(htmlContent, pdf.getWriter());

        return new ByteArrayInputStream(out.toByteArray());
    }
}