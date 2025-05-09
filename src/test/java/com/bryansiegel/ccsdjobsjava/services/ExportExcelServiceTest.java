package com.bryansiegel.ccsdjobsjava.services;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExportExcelServiceTest {

    @InjectMocks
    private ExportExcelService exportExcelService;

    private List<AdministrativePersonnel> personnelList;

    @BeforeEach
    public void setup() {
        // Create test data
        AdministrativePersonnel personnel1 = new AdministrativePersonnel();
        personnel1.setId(1L);
        personnel1.setJobTitle("School Principal");
        personnel1.setJobCode("ADM123");
        personnel1.setReferenceCode("REF456");
        personnel1.setDivisionUnit("Elementary Education");
        personnel1.setClassification("Administrative");
        personnel1.setTermsOfEmployment("Full-Time");
        personnel1.setFlsaStatus("Exempt");
        personnel1.setPositionSummary("Position summary 1");
        personnel1.setEssentialDutiesAndResponsibilities("Essential duties 1");
        personnel1.setPositionExpectations("Position expectations 1");
        personnel1.setPositionRequirements("Position requirements 1");
        personnel1.setCreatedAt(LocalDateTime.now());
        personnel1.setUpdatedAt(LocalDateTime.now());
        
        AdministrativePersonnel personnel2 = new AdministrativePersonnel();
        personnel2.setId(2L);
        personnel2.setJobTitle("Vice Principal");
        personnel2.setJobCode("ADM456");
        personnel2.setReferenceCode("REF789");
        personnel2.setDivisionUnit("Elementary Education");
        personnel2.setClassification("Administrative");
        personnel2.setTermsOfEmployment("Full-Time");
        personnel2.setFlsaStatus("Exempt");
        personnel2.setPositionSummary("Position summary 2");
        personnel2.setEssentialDutiesAndResponsibilities("Essential duties 2");
        personnel2.setPositionExpectations("Position expectations 2");
        personnel2.setPositionRequirements("Position requirements 2");
        personnel2.setCreatedAt(LocalDateTime.now());
        personnel2.setUpdatedAt(LocalDateTime.now());
        
        personnelList = Arrays.asList(personnel1, personnel2);
    }

    @Test
    public void testExportToExcel() throws IOException {
        // Simple test to verify the service returns a non-null stream
        ByteArrayInputStream excelStream = exportExcelService.exportToExcel(personnelList);
        assertNotNull(excelStream);
        
        // Verify the Excel file contains some data
        byte[] excelBytes = excelStream.readAllBytes();
        assertTrue(excelBytes.length > 0);
    }
    
    @Test
    public void testExportToExcelEmptyList() throws IOException {
        // Execute the service method with an empty list
        ByteArrayInputStream excelStream = exportExcelService.exportToExcel(List.of());
        
        // Verify the Excel file was generated
        assertNotNull(excelStream);
        
        // Verify the Excel file contains some data (at least headers)
        byte[] excelBytes = excelStream.readAllBytes();
        assertTrue(excelBytes.length > 0);
    }
}