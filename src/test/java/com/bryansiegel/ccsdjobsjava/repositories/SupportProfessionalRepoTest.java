package com.bryansiegel.ccsdjobsjava.repositories;

import com.bryansiegel.ccsdjobsjava.models.SupportProfessional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SupportProfessionalRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SupportProfessionalRepo supportProfessionalRepo;

    @Test
    public void testSaveAndFindById() {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setJobTitle("Custodian");
        personnel.setJobCode("SUP123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        Optional<SupportProfessional> found = supportProfessionalRepo.findById(personnel.getId());
        
        assertTrue(found.isPresent());
        assertEquals("Custodian", found.get().getJobTitle());
        assertEquals("SUP123", found.get().getJobCode());
    }

    @Test
    public void testFindAll() {
        SupportProfessional personnel1 = new SupportProfessional();
        personnel1.setJobTitle("Custodian");
        personnel1.setJobCode("SUP123");
        
        SupportProfessional personnel2 = new SupportProfessional();
        personnel2.setJobTitle("Food Service");
        personnel2.setJobCode("SUP456");
        
        entityManager.persist(personnel1);
        entityManager.persist(personnel2);
        entityManager.flush();
        
        List<SupportProfessional> allPersonnel = supportProfessionalRepo.findAll();
        
        assertNotNull(allPersonnel);
        assertTrue(allPersonnel.size() >= 2);
        assertTrue(allPersonnel.stream().anyMatch(p -> p.getJobTitle().equals("Custodian")));
        assertTrue(allPersonnel.stream().anyMatch(p -> p.getJobTitle().equals("Food Service")));
    }

    @Test
    public void testUpdate() {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setJobTitle("Custodian");
        personnel.setJobCode("SUP123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        SupportProfessional savedPersonnel = supportProfessionalRepo.findById(personnel.getId()).get();
        savedPersonnel.setJobTitle("Head Custodian");
        supportProfessionalRepo.save(savedPersonnel);
        
        SupportProfessional updatedPersonnel = supportProfessionalRepo.findById(personnel.getId()).get();
        
        assertEquals("Head Custodian", updatedPersonnel.getJobTitle());
        assertEquals("SUP123", updatedPersonnel.getJobCode());
    }

    @Test
    public void testDelete() {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setJobTitle("Custodian");
        personnel.setJobCode("SUP123");
        
        entityManager.persist(personnel);
        entityManager.flush();
        
        supportProfessionalRepo.deleteById(personnel.getId());
        
        Optional<SupportProfessional> found = supportProfessionalRepo.findById(personnel.getId());
        
        assertFalse(found.isPresent());
    }
}