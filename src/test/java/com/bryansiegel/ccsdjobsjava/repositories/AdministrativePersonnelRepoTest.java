package com.bryansiegel.ccsdjobsjava.repositories;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AdministrativePersonnelRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AdministrativePersonnelRepo administrativePersonnelRepo;

    @Test
    public void testSaveAndFindById() {
        // Create and save a test entity
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setJobTitle("School Principal");
        personnel.setJobCode("ADM123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        // Find by ID
        Optional<AdministrativePersonnel> found = administrativePersonnelRepo.findById(personnel.getId());
        
        // Verify
        assertTrue(found.isPresent());
        assertEquals("School Principal", found.get().getJobTitle());
        assertEquals("ADM123", found.get().getJobCode());
    }

    @Test
    public void testFindAll() {
        // Create and save multiple test entities
        AdministrativePersonnel personnel1 = new AdministrativePersonnel();
        personnel1.setJobTitle("School Principal");
        personnel1.setJobCode("ADM123");
        
        AdministrativePersonnel personnel2 = new AdministrativePersonnel();
        personnel2.setJobTitle("Vice Principal");
        personnel2.setJobCode("ADM456");
        
        entityManager.persist(personnel1);
        entityManager.persist(personnel2);
        entityManager.flush();
        
        // Find all
        List<AdministrativePersonnel> allPersonnel = administrativePersonnelRepo.findAll();
        
        // Verify
        assertNotNull(allPersonnel);
        assertTrue(allPersonnel.size() >= 2);
        assertTrue(allPersonnel.stream().anyMatch(p -> p.getJobTitle().equals("School Principal")));
        assertTrue(allPersonnel.stream().anyMatch(p -> p.getJobTitle().equals("Vice Principal")));
    }

    @Test
    public void testUpdate() {
        // Create and save a test entity
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setJobTitle("School Principal");
        personnel.setJobCode("ADM123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        // Update the entity
        AdministrativePersonnel savedPersonnel = administrativePersonnelRepo.findById(personnel.getId()).get();
        savedPersonnel.setJobTitle("Senior School Principal");
        administrativePersonnelRepo.save(savedPersonnel);
        
        // Find by ID again
        AdministrativePersonnel updatedPersonnel = administrativePersonnelRepo.findById(personnel.getId()).get();
        
        // Verify
        assertEquals("Senior School Principal", updatedPersonnel.getJobTitle());
        assertEquals("ADM123", updatedPersonnel.getJobCode());
    }

    @Test
    public void testDelete() {
        // Create and save a test entity
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setJobTitle("School Principal");
        personnel.setJobCode("ADM123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        // Delete the entity
        administrativePersonnelRepo.deleteById(personnel.getId());
        
        // Try to find by ID
        Optional<AdministrativePersonnel> found = administrativePersonnelRepo.findById(personnel.getId());
        
        // Verify
        assertFalse(found.isPresent());
    }
}