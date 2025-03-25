package com.bryansiegel.ccsdjobsjava.controllers;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.AdministrativePersonnelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/administrative-personnel")
public class AdministrativePersonnelController {

    @Autowired
    private AdministrativePersonnelRepo administrativePersonnelRepo;

    @GetMapping
    public String getAllAdministrativePersonnel(Model model) {
        List<AdministrativePersonnel> administrativePersonnel = administrativePersonnelRepo.findAll();
        model.addAttribute("administrativePersonnel", administrativePersonnel);
        return "/admin/administrative-personnel/list";
    }

    @GetMapping("/{id}")
    public String getAdministrativePersonnelById(@PathVariable Long id, Model model) {
        Optional<AdministrativePersonnel> administrativePersonnel = administrativePersonnelRepo.findById(id);
        if (administrativePersonnel.isPresent()) {
            model.addAttribute("administrativePersonnel", administrativePersonnel.get());
            return "/admin/administrative-personnel/detail";
        } else {
            return "/admin/administrative-personnel/not-found";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("administrativePersonnel", new AdministrativePersonnel());
        return "/admin/administrative-personnel/form";
    }

    @PostMapping
    public String createAdministrativePersonnel(@ModelAttribute AdministrativePersonnel administrativePersonnel) {
        administrativePersonnelRepo.save(administrativePersonnel);
        return "redirect:/admin/administrative-personnel";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<AdministrativePersonnel> administrativePersonnel = administrativePersonnelRepo.findById(id);
        if (administrativePersonnel.isPresent()) {
            model.addAttribute("administrativePersonnel", administrativePersonnel.get());
            return "/admin/administrative-personnel/form";
        } else {
            return "/admin/administrative-personnel/not-found";
        }
    }

    @PostMapping("/update/{id}")
    public String updateAdministrativePersonnel(@PathVariable Long id, @ModelAttribute AdministrativePersonnel administrativePersonnelDetails) {
        Optional<AdministrativePersonnel> administrativePersonnel = administrativePersonnelRepo.findById(id);
        if (administrativePersonnel.isPresent()) {
            AdministrativePersonnel updatedAdministrativePersonnel = administrativePersonnel.get();
            updatedAdministrativePersonnel.setJobCode(administrativePersonnelDetails.getJobCode());
            updatedAdministrativePersonnel.setReferenceCode(administrativePersonnelDetails.getReferenceCode());
            updatedAdministrativePersonnel.setClassification(administrativePersonnelDetails.getClassification());
            updatedAdministrativePersonnel.setDivisionUnit(administrativePersonnelDetails.getDivisionUnit());
            updatedAdministrativePersonnel.setTermsOfEmployment(administrativePersonnelDetails.getTermsOfEmployment());
            updatedAdministrativePersonnel.setFlsaStatus(administrativePersonnelDetails.getFlsaStatus());
            updatedAdministrativePersonnel.setPositionSummary(administrativePersonnelDetails.getPositionSummary());
            updatedAdministrativePersonnel.setEssentialDutiesAndResponsibilities(administrativePersonnelDetails.getEssentialDutiesAndResponsibilities());
            updatedAdministrativePersonnel.setPositionExpectations(administrativePersonnelDetails.getPositionExpectations());
            updatedAdministrativePersonnel.setPositionRequirements(administrativePersonnelDetails.getPositionRequirements());
            administrativePersonnelRepo.save(updatedAdministrativePersonnel);
            return "redirect:/admin/administrative-personnel";
        } else {
            return "/admin/administrative-personnel/not-found";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAdministrativePersonnel(@PathVariable Long id) {
        Optional<AdministrativePersonnel> administrativePersonnel = administrativePersonnelRepo.findById(id);
        if (administrativePersonnel.isPresent()) {
            administrativePersonnelRepo.delete(administrativePersonnel.get());
            return "redirect:/admin/administrative-personnel";
        } else {
            return "/admin/administrative-personnel/not-found";
        }
    }
}