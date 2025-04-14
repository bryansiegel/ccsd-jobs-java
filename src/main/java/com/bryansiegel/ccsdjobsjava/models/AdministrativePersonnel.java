package com.bryansiegel.ccsdjobsjava.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class AdministrativePersonnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String jobCode;
    private String referenceCode;
    private String divisionUnit;
    private String classification;

    @Column(columnDefinition = "TEXT")
    private String termsOfEmployment;
    private String flsaStatus;

    @Column(columnDefinition = "TEXT")
    private String positionSummary;

    @Column(columnDefinition = "TEXT")
    private String essentialDutiesAndResponsibilities;

    @Column(columnDefinition = "TEXT")
    private String positionExpectations;

    @Column(columnDefinition = "TEXT")
    private String positionRequirements;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getDivisionUnit() {
        return divisionUnit;
    }

    public void setDivisionUnit(String divisionUnit) {
        this.divisionUnit = divisionUnit;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTermsOfEmployment() {
        return termsOfEmployment;
    }

    public void setTermsOfEmployment(String termsOfEmployment) {
        this.termsOfEmployment = termsOfEmployment;
    }

    public String getFlsaStatus() {
        return flsaStatus;
    }

    public void setFlsaStatus(String flsaStatus) {
        this.flsaStatus = flsaStatus;
    }

    public String getPositionSummary() {
        return positionSummary;
    }

    public void setPositionSummary(String positionSummary) {
        this.positionSummary = positionSummary;
    }

    public String getEssentialDutiesAndResponsibilities() {
        return essentialDutiesAndResponsibilities;
    }

    public void setEssentialDutiesAndResponsibilities(String essentialDutiesAndResponsibilities) {
        this.essentialDutiesAndResponsibilities = essentialDutiesAndResponsibilities;
    }

    public String getPositionExpectations() {
        return positionExpectations;
    }

    public void setPositionExpectations(String positionExpectations) {
        this.positionExpectations = positionExpectations;
    }

    public String getPositionRequirements() {
        return positionRequirements;
    }

    public void setPositionRequirements(String positionRequirements) {
        this.positionRequirements = positionRequirements;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "AdministrativePersonnel{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobCode='" + jobCode + '\'' +
                ", referenceCode='" + referenceCode + '\'' +
                ", divisionUnit='" + divisionUnit + '\'' +
                ", classification='" + classification + '\'' +
                ", termsOfEmployment='" + termsOfEmployment + '\'' +
                ", flsaStatus='" + flsaStatus + '\'' +
                ", positionSummary='" + positionSummary + '\'' +
                ", essentialDutiesAndResponsibilities='" + essentialDutiesAndResponsibilities + '\'' +
                ", positionExpectations='" + positionExpectations + '\'' +
                ", positionRequirements='" + positionRequirements + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
