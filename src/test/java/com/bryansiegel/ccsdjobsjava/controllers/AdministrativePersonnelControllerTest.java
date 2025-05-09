package com.bryansiegel.ccsdjobsjava.controllers;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.AdministrativePersonnelRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AdministrativePersonnelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AdministrativePersonnelRepo administrativePersonnelRepo;

    @InjectMocks
    private AdministrativePersonnelController administrativePersonnelController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(administrativePersonnelController).build();
    }

    @Test
    public void testGetAllAdministrativePersonnel() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel1 = new AdministrativePersonnel();
        personnel1.setId(1L);
        personnel1.setJobTitle("Principal");
        
        AdministrativePersonnel personnel2 = new AdministrativePersonnel();
        personnel2.setId(2L);
        personnel2.setJobTitle("Vice Principal");
        
        when(administrativePersonnelRepo.findAll()).thenReturn(Arrays.asList(personnel1, personnel2));

        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/list"))
                .andExpect(model().attributeExists("administrativePersonnel"));
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findAll();
    }

    @Test
    public void testGetAdministrativePersonnelById() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        personnel.setJobTitle("Principal");
        
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/detail"))
                .andExpect(model().attributeExists("administrativePersonnel"));
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findById(1L);
    }

    @Test
    public void testGetAdministrativePersonnelByIdNotFound() throws Exception {
        // Setup test data
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/not-found"));
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findById(1L);
    }

    @Test
    public void testShowCreateForm() throws Exception {
        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/form"))
                .andExpect(model().attributeExists("administrativePersonnel"));
    }

    @Test
    public void testCreateAdministrativePersonnel() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setJobTitle("Principal");
        personnel.setJobCode("ADM123");
        
        when(administrativePersonnelRepo.save(any(AdministrativePersonnel.class))).thenReturn(personnel);
        
        // Execute and verify
        mockMvc.perform(post("/admin/administrative-personnel")
                        .param("jobTitle", "Principal")
                        .param("jobCode", "ADM123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/administrative-personnel"));
        
        // Verify the mock was called
        verify(administrativePersonnelRepo).save(any(AdministrativePersonnel.class));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        personnel.setJobTitle("Principal");
        
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/form"))
                .andExpect(model().attributeExists("administrativePersonnel"));
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findById(1L);
    }

    @Test
    public void testShowUpdateFormNotFound() throws Exception {
        // Setup test data
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/not-found"));
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findById(1L);
    }

    @Test
    public void testUpdateAdministrativePersonnel() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        personnel.setJobTitle("Principal");
        
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));
        when(administrativePersonnelRepo.save(any(AdministrativePersonnel.class))).thenReturn(personnel);

        // Execute and verify
        mockMvc.perform(post("/admin/administrative-personnel/update/1")
                        .param("jobTitle", "Senior Principal")
                        .param("jobCode", "ADM123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/administrative-personnel"));
        
        // Verify the mocks were called
        verify(administrativePersonnelRepo).findById(1L);
        verify(administrativePersonnelRepo).save(any(AdministrativePersonnel.class));
    }

    @Test
    public void testUpdateAdministrativePersonnelNotFound() throws Exception {
        // Setup test data
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        // Execute and verify
        mockMvc.perform(post("/admin/administrative-personnel/update/1")
                        .param("jobTitle", "Senior Principal")
                        .param("jobCode", "ADM123"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/not-found"));
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findById(1L);
    }

    @Test
    public void testDeleteAdministrativePersonnel() throws Exception {
        // Setup test data
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));
        doNothing().when(administrativePersonnelRepo).delete(any(AdministrativePersonnel.class));

        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/administrative-personnel"));
        
        // Verify the mocks were called
        verify(administrativePersonnelRepo).findById(1L);
        verify(administrativePersonnelRepo).delete(any(AdministrativePersonnel.class));
    }

    @Test
    public void testDeleteAdministrativePersonnelNotFound() throws Exception {
        // Setup test data
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        // Execute and verify
        mockMvc.perform(get("/admin/administrative-personnel/delete/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/administrative-personnel/not-found"));
                
        // Verify the mock was called
        verify(administrativePersonnelRepo).findById(1L);
    }
}