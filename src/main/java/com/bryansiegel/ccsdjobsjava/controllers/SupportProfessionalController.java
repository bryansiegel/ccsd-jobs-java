package com.bryansiegel.ccsdjobsjava.controllers;

import com.bryansiegel.ccsdjobsjava.models.SupportProfessional;
import com.bryansiegel.ccsdjobsjava.repositories.SupportProfessionalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/support-professionals")
public class SupportProfessionalController {

    @Autowired
    private SupportProfessionalRepo supportProfessionalRepo;

    @GetMapping
    public String getAllSupportProfessionals(Model model) {
        List<SupportProfessional> supportProfessionals = supportProfessionalRepo.findAll();
        model.addAttribute("supportProfessionals", supportProfessionals);
        return "/admin/support-professionals/list";
    }

    @GetMapping("/{id}")
    public String getSupportProfessionalById(@PathVariable Long id, Model model) {
        Optional<SupportProfessional> supportProfessional = supportProfessionalRepo.findById(id);
        if (supportProfessional.isPresent()) {
            model.addAttribute("supportProfessional", supportProfessional.get());
            return "/admin/support-professionals/detail";
        } else {
            return "/admin/support-professionals/not-found";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("supportProfessional", new SupportProfessional());
        return "/admin/support-professionals/form";
    }

    @PostMapping
    public String createSupportProfessional(@ModelAttribute SupportProfessional supportProfessional) {
        supportProfessionalRepo.save(supportProfessional);
        return "redirect:/admin/support-professionals";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<SupportProfessional> supportProfessional = supportProfessionalRepo.findById(id);
        if (supportProfessional.isPresent()) {
            model.addAttribute("supportProfessional", supportProfessional.get());
            return "/admin/support-professionals/form";
        } else {
            return "/admin/support-professionals/not-found";
        }
    }

    @PostMapping("/update/{id}")
    public String updateSupportProfessional(@PathVariable Long id, @ModelAttribute SupportProfessional supportProfessionalDetails) {
        Optional<SupportProfessional> supportProfessional = supportProfessionalRepo.findById(id);
        if (supportProfessional.isPresent()) {
            SupportProfessional updatedSupportProfessional = supportProfessional.get();
            updatedSupportProfessional.setClassCode(supportProfessionalDetails.getClassCode());
            updatedSupportProfessional.setJobFamily(supportProfessionalDetails.getJobFamily());
            updatedSupportProfessional.setClassification(supportProfessionalDetails.getClassification());
            updatedSupportProfessional.setTermsOfEmployment(supportProfessionalDetails.getTermsOfEmployment());
            updatedSupportProfessional.setFlsaStatus(supportProfessionalDetails.getFlsaStatus());
            updatedSupportProfessional.setPositionSummary(supportProfessionalDetails.getPositionSummary());
            updatedSupportProfessional.setDutiesAndResponsibilities(supportProfessionalDetails.getDutiesAndResponsibilities());
            updatedSupportProfessional.setDistinguishingCharacteristics(supportProfessionalDetails.getDistinguishingCharacteristics());
            updatedSupportProfessional.setKnowledgeSkillsAbilities(supportProfessionalDetails.getKnowledgeSkillsAbilities());
            updatedSupportProfessional.setPositionRequirements(supportProfessionalDetails.getPositionRequirements());
            updatedSupportProfessional.setDocumentsRequiredAtTimeOfApplication(supportProfessionalDetails.getDocumentsRequiredAtTimeOfApplication());
            updatedSupportProfessional.setExamplesOfAssignedWorkAreas(supportProfessionalDetails.getExamplesOfAssignedWorkAreas());
            updatedSupportProfessional.setWorkEnvironment(supportProfessionalDetails.getWorkEnvironment());
            updatedSupportProfessional.setEquipmentSuppliesUsedToPerformTasks(supportProfessionalDetails.getEquipmentSuppliesUsedToPerformTasks());
            supportProfessionalRepo.save(updatedSupportProfessional);
            return "redirect:/admin/support-professionals";
        } else {
            return "/admin/support-professionals/not-found";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteSupportProfessional(@PathVariable Long id) {
        Optional<SupportProfessional> supportProfessional = supportProfessionalRepo.findById(id);
        if (supportProfessional.isPresent()) {
            supportProfessionalRepo.delete(supportProfessional.get());
            return "redirect:/admin/support-professionals";
        } else {
            return "/admin/support-professionals/not-found";
        }
    }
}