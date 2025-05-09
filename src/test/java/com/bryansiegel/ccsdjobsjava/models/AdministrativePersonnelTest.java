package com.bryansiegel.ccsdjobsjava.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdministrativePersonnelTest {

    private AdministrativePersonnel personnel;
    private final LocalDateTime testDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);

    @BeforeEach
    public void setup() {
        personnel = new AdministrativePersonnel();
    }

    @Test
    public void testGetAndSetId() {
        Long id = 1L;
        personnel.setId(id);
        assertEquals(id, personnel.getId());
    }

    @Test
    public void testGetAndSetJobTitle() {
        String jobTitle = "School Principal";
        personnel.setJobTitle(jobTitle);
        assertEquals(jobTitle, personnel.getJobTitle());
    }

    @Test
    public void testGetAndSetJobCode() {
        String jobCode = "ADM123";
        personnel.setJobCode(jobCode);
        assertEquals(jobCode, personnel.getJobCode());
    }

    @Test
    public void testGetAndSetReferenceCode() {
        String referenceCode = "REF456";
        personnel.setReferenceCode(referenceCode);
        assertEquals(referenceCode, personnel.getReferenceCode());
    }

    @Test
    public void testGetAndSetDivisionUnit() {
        String divisionUnit = "Elementary Education";
        personnel.setDivisionUnit(divisionUnit);
        assertEquals(divisionUnit, personnel.getDivisionUnit());
    }

    @Test
    public void testGetAndSetClassification() {
        String classification = "Administrative";
        personnel.setClassification(classification);
        assertEquals(classification, personnel.getClassification());
    }

    @Test
    public void testGetAndSetTermsOfEmployment() {
        String termsOfEmployment = "Full-Time";
        personnel.setTermsOfEmployment(termsOfEmployment);
        assertEquals(termsOfEmployment, personnel.getTermsOfEmployment());
    }

    @Test
    public void testGetAndSetFlsaStatus() {
        String flsaStatus = "Exempt";
        personnel.setFlsaStatus(flsaStatus);
        assertEquals(flsaStatus, personnel.getFlsaStatus());
    }

    @Test
    public void testGetAndSetPositionSummary() {
        String positionSummary = "This is a position summary";
        personnel.setPositionSummary(positionSummary);
        assertEquals(positionSummary, personnel.getPositionSummary());
    }

    @Test
    public void testGetAndSetEssentialDutiesAndResponsibilities() {
        String duties = "These are the essential duties";
        personnel.setEssentialDutiesAndResponsibilities(duties);
        assertEquals(duties, personnel.getEssentialDutiesAndResponsibilities());
    }

    @Test
    public void testGetAndSetPositionExpectations() {
        String expectations = "These are the position expectations";
        personnel.setPositionExpectations(expectations);
        assertEquals(expectations, personnel.getPositionExpectations());
    }

    @Test
    public void testGetAndSetPositionRequirements() {
        String requirements = "These are the position requirements";
        personnel.setPositionRequirements(requirements);
        assertEquals(requirements, personnel.getPositionRequirements());
    }

    @Test
    public void testGetAndSetCreatedAt() {
        personnel.setCreatedAt(testDateTime);
        assertEquals(testDateTime, personnel.getCreatedAt());
    }

    @Test
    public void testGetAndSetUpdatedAt() {
        personnel.setUpdatedAt(testDateTime);
        assertEquals(testDateTime, personnel.getUpdatedAt());
    }

    @Test
    public void testToString() {
        // Set all fields
        personnel.setId(1L);
        personnel.setJobTitle("School Principal");
        personnel.setJobCode("ADM123");
        personnel.setReferenceCode("REF456");
        personnel.setDivisionUnit("Elementary Education");
        personnel.setClassification("Administrative");
        personnel.setTermsOfEmployment("Full-Time");
        personnel.setFlsaStatus("Exempt");
        personnel.setPositionSummary("Position summary");
        personnel.setEssentialDutiesAndResponsibilities("Essential duties");
        personnel.setPositionExpectations("Position expectations");
        personnel.setPositionRequirements("Position requirements");
        personnel.setCreatedAt(testDateTime);
        personnel.setUpdatedAt(testDateTime);

        // Verify toString contains all field values
        String toString = personnel.toString();
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("jobTitle='School Principal'"));
        assertTrue(toString.contains("jobCode='ADM123'"));
        assertTrue(toString.contains("referenceCode='REF456'"));
        assertTrue(toString.contains("divisionUnit='Elementary Education'"));
        assertTrue(toString.contains("classification='Administrative'"));
        assertTrue(toString.contains("termsOfEmployment='Full-Time'"));
        assertTrue(toString.contains("flsaStatus='Exempt'"));
        assertTrue(toString.contains("positionSummary='Position summary'"));
        assertTrue(toString.contains("essentialDutiesAndResponsibilities='Essential duties'"));
        assertTrue(toString.contains("positionExpectations='Position expectations'"));
        assertTrue(toString.contains("positionRequirements='Position requirements'"));
        assertTrue(toString.contains(testDateTime.toString()));
    }
}