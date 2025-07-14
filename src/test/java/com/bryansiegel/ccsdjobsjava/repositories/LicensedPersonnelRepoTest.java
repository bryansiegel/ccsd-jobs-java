package com.bryansiegel.ccsdjobsjava.repositories;

import com.bryansiegel.ccsdjobsjava.models.LicensedPersonnel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LicensedPersonnelRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LicensedPersonnelRepo licensedPersonnelRepo;

    @Test
    public void testSaveAndFindById() {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setJobTitle("Teacher");
        personnel.setJobCode("LIC123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        Optional<LicensedPersonnel> found = licensedPersonnelRepo.findById(personnel.getId());
        
        assertTrue(found.isPresent());
        assertEquals("Teacher", found.get().getJobTitle());
        assertEquals("LIC123", found.get().getJobCode());
    }

    @Test
    public void testFindAll() {
        LicensedPersonnel personnel1 = new LicensedPersonnel();
        personnel1.setJobTitle("Teacher");
        personnel1.setJobCode("LIC123");
        
        LicensedPersonnel personnel2 = new LicensedPersonnel();
        personnel2.setJobTitle("Counselor");
        personnel2.setJobCode("LIC456");
        
        entityManager.persist(personnel1);
        entityManager.persist(personnel2);
        entityManager.flush();
        
        List<LicensedPersonnel> allPersonnel = licensedPersonnelRepo.findAll();
        
        assertNotNull(allPersonnel);
        assertTrue(allPersonnel.size() >= 2);
        assertTrue(allPersonnel.stream().anyMatch(p -> p.getJobTitle().equals("Teacher")));
        assertTrue(allPersonnel.stream().anyMatch(p -> p.getJobTitle().equals("Counselor")));
    }

    @Test
    public void testUpdate() {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setJobTitle("Teacher");
        personnel.setJobCode("LIC123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        LicensedPersonnel savedPersonnel = licensedPersonnelRepo.findById(personnel.getId()).get();
        savedPersonnel.setJobTitle("Senior Teacher");
        licensedPersonnelRepo.save(savedPersonnel);
        
        LicensedPersonnel updatedPersonnel = licensedPersonnelRepo.findById(personnel.getId()).get();
        
        assertEquals("Senior Teacher", updatedPersonnel.getJobTitle());
        assertEquals("LIC123", updatedPersonnel.getJobCode());
    }

    @Test
    public void testDelete() {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setJobTitle("Teacher");
        personnel.setJobCode("LIC123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        licensedPersonnelRepo.deleteById(personnel.getId());
        
        Optional<LicensedPersonnel> found = licensedPersonnelRepo.findById(personnel.getId());
        
        assertFalse(found.isPresent());
    }
}