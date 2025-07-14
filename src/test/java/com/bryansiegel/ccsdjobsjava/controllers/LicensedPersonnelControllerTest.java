package com.bryansiegel.ccsdjobsjava.controllers;

import com.bryansiegel.ccsdjobsjava.models.LicensedPersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.LicensedPersonnelRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LicensedPersonnelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LicensedPersonnelRepo licensedPersonnelRepo;

    @InjectMocks
    private LicensedPersonnelController licensedPersonnelController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(licensedPersonnelController).build();
    }

    @Test
    public void testGetAllLicensedPersonnel() throws Exception {
        LicensedPersonnel personnel1 = new LicensedPersonnel();
        personnel1.setId(1L);
        personnel1.setJobTitle("Teacher");
        
        LicensedPersonnel personnel2 = new LicensedPersonnel();
        personnel2.setId(2L);
        personnel2.setJobTitle("Counselor");
        
        when(licensedPersonnelRepo.findAll()).thenReturn(Arrays.asList(personnel1, personnel2));

        mockMvc.perform(get("/admin/licensed-personnel"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/list"))
                .andExpect(model().attributeExists("licensedPersonnel"));
                
        verify(licensedPersonnelRepo).findAll();
    }

    @Test
    public void testGetLicensedPersonnelById() throws Exception {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setId(1L);
        personnel.setJobTitle("Teacher");
        
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        mockMvc.perform(get("/admin/licensed-personnel/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/detail"))
                .andExpect(model().attributeExists("licensedPersonnel"));
                
        verify(licensedPersonnelRepo).findById(1L);
    }

    @Test
    public void testGetLicensedPersonnelByIdNotFound() throws Exception {
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/licensed-personnel/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/not-found"));
                
        verify(licensedPersonnelRepo).findById(1L);
    }

    @Test
    public void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/admin/licensed-personnel/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/form"))
                .andExpect(model().attributeExists("licensedPersonnel"));
    }

    @Test
    public void testCreateLicensedPersonnel() throws Exception {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setJobTitle("Teacher");
        personnel.setJobCode("LIC123");
        
        when(licensedPersonnelRepo.save(any(LicensedPersonnel.class))).thenReturn(personnel);
        
        mockMvc.perform(post("/admin/licensed-personnel")
                        .param("jobTitle", "Teacher")
                        .param("jobCode", "LIC123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/licensed-personnel"));
        
        verify(licensedPersonnelRepo).save(any(LicensedPersonnel.class));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setId(1L);
        personnel.setJobTitle("Teacher");
        
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        mockMvc.perform(get("/admin/licensed-personnel/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/form"))
                .andExpect(model().attributeExists("licensedPersonnel"));
                
        verify(licensedPersonnelRepo).findById(1L);
    }

    @Test
    public void testShowUpdateFormNotFound() throws Exception {
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/licensed-personnel/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/not-found"));
                
        verify(licensedPersonnelRepo).findById(1L);
    }

    @Test
    public void testUpdateLicensedPersonnel() throws Exception {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setId(1L);
        personnel.setJobTitle("Teacher");
        
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));
        when(licensedPersonnelRepo.save(any(LicensedPersonnel.class))).thenReturn(personnel);

        mockMvc.perform(post("/admin/licensed-personnel/update/1")
                        .param("jobTitle", "Senior Teacher")
                        .param("jobCode", "LIC123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/licensed-personnel"));
        
        verify(licensedPersonnelRepo).findById(1L);
        verify(licensedPersonnelRepo).save(any(LicensedPersonnel.class));
    }

    @Test
    public void testUpdateLicensedPersonnelNotFound() throws Exception {
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/admin/licensed-personnel/update/1")
                        .param("jobTitle", "Senior Teacher")
                        .param("jobCode", "LIC123"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/not-found"));
                
        verify(licensedPersonnelRepo).findById(1L);
    }

    @Test
    public void testDeleteLicensedPersonnel() throws Exception {
        LicensedPersonnel personnel = new LicensedPersonnel();
        personnel.setId(1L);
        
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));
        doNothing().when(licensedPersonnelRepo).delete(any(LicensedPersonnel.class));

        mockMvc.perform(get("/admin/licensed-personnel/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/licensed-personnel"));
        
        verify(licensedPersonnelRepo).findById(1L);
        verify(licensedPersonnelRepo).delete(any(LicensedPersonnel.class));
    }

    @Test
    public void testDeleteLicensedPersonnelNotFound() throws Exception {
        when(licensedPersonnelRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/licensed-personnel/delete/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/licensed-personnel/not-found"));
                
        verify(licensedPersonnelRepo).findById(1L);
    }
}