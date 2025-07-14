package com.bryansiegel.ccsdjobsjava;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.bryansiegel.ccsdjobsjava.models.LicensedPersonnel;
import com.bryansiegel.ccsdjobsjava.models.SupportProfessional;
import com.bryansiegel.ccsdjobsjava.repositories.AdministrativePersonnelRepo;
import com.bryansiegel.ccsdjobsjava.repositories.LicensedPersonnelRepo;
import com.bryansiegel.ccsdjobsjava.repositories.SupportProfessionalRepo;
import com.bryansiegel.ccsdjobsjava.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class CcsdJobsJavaApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdministrativePersonnelRepo administrativePersonnelRepo;

    @Autowired
    private LicensedPersonnelRepo licensedPersonnelRepo;

    @Autowired
    private SupportProfessionalRepo supportProfessionalRepo;

    @Test
    public void testAdministrativePersonnelEndToEnd() throws Exception {
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setJobTitle("Integration Test Principal");
        personnel.setJobCode("INT123");
        personnel.setReferenceCode("REF456");
        personnel.setClassification("Administrative");
        
        AdministrativePersonnel saved = administrativePersonnelRepo.save(personnel);
        
        mockMvc.perform(get("/admin/administrative-personnel/" + saved.getId())
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/detail"))
                .andExpect(model().attributeExists("administrativePersonnel"));
    }

    @Test
    public void testLicensedPersonnelEndToEnd() throws Exception {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setJobTitle("Integration Test Teacher");
        personnel.setJobCode("LIC123");
        personnel.setReferenceCode("REF789");
        personnel.setClassification("Licensed");
        
        LicensedPersonnel saved = licensedPersonnelRepo.save(personnel);
        
        mockMvc.perform(get("/admin/licensed-personnel/" + saved.getId())
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/detail"))
                .andExpect(model().attributeExists("licensedPersonnel"));
    }

    @Test
    public void testSupportProfessionalEndToEnd() throws Exception {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setJobTitle("Integration Test Custodian");
        personnel.setJobCode("SUP123");
        personnel.setReferenceCode("REF101");
        personnel.setClassification("Support");
        
        SupportProfessional saved = supportProfessionalRepo.save(personnel);
        
        mockMvc.perform(get("/admin/support-professionals/" + saved.getId())
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/detail"))
                .andExpect(model().attributeExists("supportProfessional"));
    }

    @Test
    public void testPublicIndexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/public/index.html"));
    }

    @Test
    public void testDashboardAccess() throws Exception {
        mockMvc.perform(get("/admin/dashboard/")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/dashboard"));
    }
}