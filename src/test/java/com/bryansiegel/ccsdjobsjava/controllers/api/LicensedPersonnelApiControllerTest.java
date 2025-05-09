package com.bryansiegel.ccsdjobsjava.controllers.api;

import com.bryansiegel.ccsdjobsjava.models.LicensedPersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.LicensedPersonnelRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LicensedPersonnelApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LicensedPersonnelRepo licensedPersonnelRepo;
    
    @InjectMocks
    private LicensedPersonnelApiController licensedPersonnelApiController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(licensedPersonnelApiController).build();
    }

    @Test
    public void testGetAllLicensedPersonnel() throws Exception {
        // Setup test data
        LicensedPersonnel personnel1 = new LicensedPersonnel();
        personnel1.setId(1L);
        LicensedPersonnel personnel2 = new LicensedPersonnel();
        personnel2.setId(2L);
        
        when(licensedPersonnelRepo.findAll()).thenReturn(Arrays.asList(personnel1, personnel2));
        
        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.get("/api/licensed-personnel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(licensedPersonnelRepo).findAll();
    }

    @Test
    public void testGetLicensedPersonnelById() throws Exception {
        // Setup test data
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setId(1L);
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.get("/api/licensed-personnel/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(licensedPersonnelRepo).findById(1L);
    }

    @Test
    public void testGetLicensedPersonnelByIdNotFound() throws Exception {
        // Setup test data
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.get("/api/licensed-personnel/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(licensedPersonnelRepo).findById(1L);
    }

    @Test
    public void testCreateLicensedPersonnel() throws Exception {
        // Setup test data
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setId(1L);
        when(licensedPersonnelRepo.save(any(LicensedPersonnel.class))).thenReturn(personnel);
        
        String newPersonnelJson = "{\"jobCode\":\"LP123\"}";

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.post("/api/licensed-personnel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPersonnelJson))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(licensedPersonnelRepo).save(any(LicensedPersonnel.class));
    }

    @Test
    public void testUpdateLicensedPersonnel() throws Exception {
        // Setup test data
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setId(1L);
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));
        when(licensedPersonnelRepo.save(any(LicensedPersonnel.class))).thenReturn(personnel);

        String updatedPersonnelJson = "{\"jobCode\":\"LP123\"}";

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.put("/api/licensed-personnel/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPersonnelJson))
                .andExpect(status().isOk());
                
        // Verify the mocks were called
        verify(licensedPersonnelRepo).findById(1L);
        verify(licensedPersonnelRepo).save(any(LicensedPersonnel.class));
    }

    @Test
    public void testDeleteLicensedPersonnel() throws Exception {
        // Setup test data
        doNothing().when(licensedPersonnelRepo).deleteById(1L);
        
        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/licensed-personnel/1"))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(licensedPersonnelRepo).deleteById(1L);
    }
}