package com.bryansiegel.ccsdjobsjava.controllers.api;

import com.bryansiegel.ccsdjobsjava.models.LicensedPersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.LicensedPersonnelRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licensed-personnel")
public class LicensedPersonnelApiController {

    private final LicensedPersonnelRepo licensedPersonnelRepo;

    public LicensedPersonnelApiController(LicensedPersonnelRepo licensedPersonnelRepo) {
        this.licensedPersonnelRepo = licensedPersonnelRepo;
    }

    @GetMapping
    public List<LicensedPersonnel> getAllLicensedPersonnel() {
        return licensedPersonnelRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LicensedPersonnel> getLicensedPersonnelById(@PathVariable Long id) {
        return licensedPersonnelRepo.findById(id);
    }

    @PostMapping
    public LicensedPersonnel createLicensedPersonnel(@RequestBody LicensedPersonnel licensedPersonnel) {
        return licensedPersonnelRepo.save(licensedPersonnel);
    }

    @PutMapping("/{id}")
    public Optional<LicensedPersonnel> updateLicensedPersonnel(@PathVariable Long id, @RequestBody LicensedPersonnel licensedPersonnelDetails) {
        return licensedPersonnelRepo.findById(id).map(licensedPersonnel -> {
            // Update fields as needed
            return licensedPersonnelRepo.save(licensedPersonnel);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteLicensedPersonnel(@PathVariable Long id) {
        licensedPersonnelRepo.deleteById(id);
    }
}