package com.bryansiegel.ccsdjobsjava.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class SupportProfessional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String classCode;
    private String jobFamily;
    private String classification;
    private String termsOfEmployment;
    private String flsaStatus;
    private String positionSummary;
    private String dutiesAndResponsibilities;
    private String distinguishingCharacteristics;
    private String knowledgeSkillsAbilities;
    private String positionRequirements;
    private String documentsRequiredAtTimeOfApplication;
    private String examplesOfAssignedWorkAreas;
    private String workEnvironment;
    private String equipmentSuppliesUsedToPerformTasks;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
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

    public String getDutiesAndResponsibilities() {
        return dutiesAndResponsibilities;
    }

    public void setDutiesAndResponsibilities(String dutiesAndResponsibilities) {
        this.dutiesAndResponsibilities = dutiesAndResponsibilities;
    }

    public String getDistinguishingCharacteristics() {
        return distinguishingCharacteristics;
    }

    public void setDistinguishingCharacteristics(String distinguishingCharacteristics) {
        this.distinguishingCharacteristics = distinguishingCharacteristics;
    }

    public String getKnowledgeSkillsAbilities() {
        return knowledgeSkillsAbilities;
    }

    public void setKnowledgeSkillsAbilities(String knowledgeSkillsAbilities) {
        this.knowledgeSkillsAbilities = knowledgeSkillsAbilities;
    }

    public String getPositionRequirements() {
        return positionRequirements;
    }

    public void setPositionRequirements(String positionRequirements) {
        this.positionRequirements = positionRequirements;
    }

    public String getDocumentsRequiredAtTimeOfApplication() {
        return documentsRequiredAtTimeOfApplication;
    }

    public void setDocumentsRequiredAtTimeOfApplication(String documentsRequiredAtTimeOfApplication) {
        this.documentsRequiredAtTimeOfApplication = documentsRequiredAtTimeOfApplication;
    }

    public String getExamplesOfAssignedWorkAreas() {
        return examplesOfAssignedWorkAreas;
    }

    public void setExamplesOfAssignedWorkAreas(String examplesOfAssignedWorkAreas) {
        this.examplesOfAssignedWorkAreas = examplesOfAssignedWorkAreas;
    }

    public String getWorkEnvironment() {
        return workEnvironment;
    }

    public void setWorkEnvironment(String workEnvironment) {
        this.workEnvironment = workEnvironment;
    }

    public String getEquipmentSuppliesUsedToPerformTasks() {
        return equipmentSuppliesUsedToPerformTasks;
    }

    public void setEquipmentSuppliesUsedToPerformTasks(String equipmentSuppliesUsedToPerformTasks) {
        this.equipmentSuppliesUsedToPerformTasks = equipmentSuppliesUsedToPerformTasks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SupportProfessional{" +
                "id=" + id +
                ", classCode='" + classCode + '\'' +
                ", jobFamily='" + jobFamily + '\'' +
                ", classification='" + classification + '\'' +
                ", termsOfEmployment='" + termsOfEmployment + '\'' +
                ", flsaStatus='" + flsaStatus + '\'' +
                ", positionSummary='" + positionSummary + '\'' +
                ", dutiesAndResponsibilities='" + dutiesAndResponsibilities + '\'' +
                ", distinguishingCharacteristics='" + distinguishingCharacteristics + '\'' +
                ", knowledgeSkillsAbilities='" + knowledgeSkillsAbilities + '\'' +
                ", positionRequirements='" + positionRequirements + '\'' +
                ", documentsRequiredAtTimeOfApplication='" + documentsRequiredAtTimeOfApplication + '\'' +
                ", examplesOfAssignedWorkAreas='" + examplesOfAssignedWorkAreas + '\'' +
                ", workEnvironment='" + workEnvironment + '\'' +
                ", equipmentSuppliesUsedToPerformTasks='" + equipmentSuppliesUsedToPerformTasks + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
