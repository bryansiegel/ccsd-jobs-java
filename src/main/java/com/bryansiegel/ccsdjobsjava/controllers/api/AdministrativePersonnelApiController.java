package com.bryansiegel.ccsdjobsjava.controllers.api;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.AdministrativePersonnelRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrative-personnel")
public class AdministrativePersonnelApiController {

        private final AdministrativePersonnelRepo administrativePersonnelRepo;

    public AdministrativePersonnelApiController(AdministrativePersonnelRepo administrativePersonnelRepo) {
        this.administrativePersonnelRepo = administrativePersonnelRepo;
    }

    @GetMapping
        public List<AdministrativePersonnel> getAllAdministrativePersonnel() {
            return administrativePersonnelRepo.findAll();
        }

        @GetMapping("/{id}")
        public Optional<AdministrativePersonnel> getAdministrativePersonnelById(@PathVariable Long id) {
            return administrativePersonnelRepo.findById(id);
        }

        @PostMapping
        public AdministrativePersonnel createAdministrativePersonnel(@RequestBody AdministrativePersonnel administrativePersonnel) {
            return administrativePersonnelRepo.save(administrativePersonnel);
        }

        @PutMapping("/{id}")
        public Optional<AdministrativePersonnel> updateAdministrativePersonnel(@PathVariable Long id, @RequestBody AdministrativePersonnel administrativePersonnelDetails) {
            return administrativePersonnelRepo.findById(id).map(administrativePersonnel -> {
                administrativePersonnel.setJobCode(administrativePersonnelDetails.getJobCode());
                administrativePersonnel.setReferenceCode(administrativePersonnelDetails.getReferenceCode());
                administrativePersonnel.setClassification(administrativePersonnelDetails.getClassification());
                administrativePersonnel.setDivisionUnit(administrativePersonnelDetails.getDivisionUnit());
                administrativePersonnel.setTermsOfEmployment(administrativePersonnelDetails.getTermsOfEmployment());
                administrativePersonnel.setFlsaStatus(administrativePersonnelDetails.getFlsaStatus());
                administrativePersonnel.setPositionSummary(administrativePersonnelDetails.getPositionSummary());
                administrativePersonnel.setEssentialDutiesAndResponsibilities(administrativePersonnelDetails.getEssentialDutiesAndResponsibilities());
                administrativePersonnel.setPositionExpectations(administrativePersonnelDetails.getPositionExpectations());
                administrativePersonnel.setPositionRequirements(administrativePersonnelDetails.getPositionRequirements());
                return administrativePersonnelRepo.save(administrativePersonnel);
            });
        }

        @DeleteMapping("/{id}")
        public void deleteAdministrativePersonnel(@PathVariable Long id) {
            administrativePersonnelRepo.deleteById(id);
        }
    }
