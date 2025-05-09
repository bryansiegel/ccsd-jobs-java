package com.bryansiegel.ccsdjobsjava.controllers.api;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.AdministrativePersonnelRepo;
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
public class AdministrativePersonnelApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AdministrativePersonnelRepo administrativePersonnelRepo;
    
    @InjectMocks
    private AdministrativePersonnelApiController administrativePersonnelApiController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(administrativePersonnelApiController).build();
    }

    @Test
    public void testGetAllAdministrativePersonnel() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel1 = new AdministrativePersonnel();
        personnel1.setId(1L);
        AdministrativePersonnel personnel2 = new AdministrativePersonnel();
        personnel2.setId(2L);
        
        when(administrativePersonnelRepo.findAll()).thenReturn(Arrays.asList(personnel1, personnel2));
        
        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.get("/api/administrative-personnel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findAll();
    }

    @Test
    public void testGetAdministrativePersonnelById() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.get("/api/administrative-personnel/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findById(1L);
    }

    @Test
    public void testCreateAdministrativePersonnel() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        when(administrativePersonnelRepo.save(any(AdministrativePersonnel.class))).thenReturn(personnel);
        
        String newPersonnelJson = "{\"jobCode\":\"1234\",\"referenceCode\":\"5678\",\"classification\":\"Admin\"}";

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.post("/api/administrative-personnel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPersonnelJson))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).save(any(AdministrativePersonnel.class));
    }

    @Test
    public void testUpdateAdministrativePersonnel() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));
        when(administrativePersonnelRepo.save(any(AdministrativePersonnel.class))).thenReturn(personnel);

        String updatedPersonnelJson = "{\"jobCode\":\"1234\",\"referenceCode\":\"5678\",\"classification\":\"Admin\"}";

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.put("/api/administrative-personnel/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPersonnelJson))
                .andExpect(status().isOk());
                
        // Verify the mocks were called
        verify(administrativePersonnelRepo).findById(1L);
        verify(administrativePersonnelRepo).save(any(AdministrativePersonnel.class));
    }

    @Test
    public void testDeleteAdministrativePersonnel() throws Exception {
        // Setup
        doNothing().when(administrativePersonnelRepo).deleteById(1L);
        
        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/administrative-personnel/1"))
                .andExpect(status().isOk());
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).deleteById(1L);
    }
}