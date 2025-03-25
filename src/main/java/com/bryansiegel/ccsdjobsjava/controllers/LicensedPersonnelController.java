package com.bryansiegel.ccsdjobsjava.controllers;

import com.bryansiegel.ccsdjobsjava.models.LicensedPersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.LicensedPersonnelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/licensed-personnel")
public class LicensedPersonnelController {

    @Autowired
    private LicensedPersonnelRepo licensedPersonnelRepo;

    @GetMapping
    public String getAllLicensedPersonnel(Model model) {
        List<LicensedPersonnel> licensedPersonnel = licensedPersonnelRepo.findAll();
        model.addAttribute("licensedPersonnel", licensedPersonnel);
        return "/admin/licensed-personnel/list";
    }

    @GetMapping("/{id}")
    public String getLicensedPersonnelById(@PathVariable Long id, Model model) {
        Optional<LicensedPersonnel> licensedPersonnel = licensedPersonnelRepo.findById(id);
        if (licensedPersonnel.isPresent()) {
            model.addAttribute("licensedPersonnel", licensedPersonnel.get());
            return "/admin/licensed-personnel/detail";
        } else {
            return "/admin/licensed-personnel/not-found";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("licensedPersonnel", new LicensedPersonnel());
        return "/admin/licensed-personnel/form";
    }

    @PostMapping
    public String createLicensedPersonnel(@ModelAttribute LicensedPersonnel licensedPersonnel) {
        licensedPersonnelRepo.save(licensedPersonnel);
        return "redirect:/admin/licensed-personnel";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<LicensedPersonnel> licensedPersonnel = licensedPersonnelRepo.findById(id);
        if (licensedPersonnel.isPresent()) {
            model.addAttribute("licensedPersonnel", licensedPersonnel.get());
            return "/admin/licensed-personnel/form";
        } else {
            return "/admin/licensed-personnel/not-found";
        }
    }

    @PostMapping("/update/{id}")
    public String updateLicensedPersonnel(@PathVariable Long id, @ModelAttribute LicensedPersonnel licensedPersonnelDetails) {
        Optional<LicensedPersonnel> licensedPersonnel = licensedPersonnelRepo.findById(id);
        if (licensedPersonnel.isPresent()) {
            LicensedPersonnel updatedLicensedPersonnel = licensedPersonnel.get();
            updatedLicensedPersonnel.setJobCode(licensedPersonnelDetails.getJobCode());
            updatedLicensedPersonnel.setClassification(licensedPersonnelDetails.getDivision());
            updatedLicensedPersonnel.setClassification(licensedPersonnelDetails.getClassification());
            updatedLicensedPersonnel.setTermsOfEmployment(licensedPersonnelDetails.getTermsOfEmployment());
            updatedLicensedPersonnel.setFlsaStatus(licensedPersonnelDetails.getFlsaStatus());
            updatedLicensedPersonnel.setPositionSummary(licensedPersonnelDetails.getPositionSummary());
            updatedLicensedPersonnel.setEssentialDutiesAndResponsibilities(licensedPersonnelDetails.getEssentialDutiesAndResponsibilities());
            updatedLicensedPersonnel.setPositionExpectations(licensedPersonnelDetails.getPositionExpectations());
            updatedLicensedPersonnel.setPositionRequirements(licensedPersonnelDetails.getPositionRequirements());
            licensedPersonnelRepo.save(updatedLicensedPersonnel);
            return "redirect:/admin/licensed-personnel";
        } else {
            return "/admin/licensed-personnel/not-found";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLicensedPersonnel(@PathVariable Long id) {
        Optional<LicensedPersonnel> licensedPersonnel = licensedPersonnelRepo.findById(id);
        if (licensedPersonnel.isPresent()) {
            licensedPersonnelRepo.delete(licensedPersonnel.get());
            return "redirect:/admin/licensed-personnel";
        } else {
            return "/admin/licensed-personnel/not-found";
        }
    }
}