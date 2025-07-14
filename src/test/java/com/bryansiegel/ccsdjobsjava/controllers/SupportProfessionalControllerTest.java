package com.bryansiegel.ccsdjobsjava.controllers;

import com.bryansiegel.ccsdjobsjava.models.SupportProfessional;
import com.bryansiegel.ccsdjobsjava.repositories.SupportProfessionalRepo;
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
public class SupportProfessionalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SupportProfessionalRepo supportProfessionalRepo;

    @InjectMocks
    private SupportProfessionalController supportProfessionalController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(supportProfessionalController).build();
    }

    @Test
    public void testGetAllSupportProfessionals() throws Exception {
        SupportProfessional personnel1 = new SupportProfessional();
        personnel1.setId(1L);
        personnel1.setJobTitle("Custodian");
        
        SupportProfessional personnel2 = new SupportProfessional();
        personnel2.setId(2L);
        personnel2.setJobTitle("Food Service");
        
        when(supportProfessionalRepo.findAll()).thenReturn(Arrays.asList(personnel1, personnel2));

        mockMvc.perform(get("/admin/support-professionals"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/list"))
                .andExpect(model().attributeExists("supportProfessionals"));
                
        verify(supportProfessionalRepo).findAll();
    }

    @Test
    public void testGetSupportProfessionalById() throws Exception {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setId(1L);
        personnel.setJobTitle("Custodian");
        
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.of(personnel));

        mockMvc.perform(get("/admin/support-professionals/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/detail"))
                .andExpect(model().attributeExists("supportProfessional"));
                
        verify(supportProfessionalRepo).findById(1L);
    }

    @Test
    public void testGetSupportProfessionalByIdNotFound() throws Exception {
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/support-professionals/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/not-found"));
                
        verify(supportProfessionalRepo).findById(1L);
    }

    @Test
    public void testShowCreateForm() throws Exception {
        mockMvc.perform(get("/admin/support-professionals/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/form"))
                .andExpect(model().attributeExists("supportProfessional"));
    }

    @Test
    public void testCreateSupportProfessional() throws Exception {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setJobTitle("Custodian");
        personnel.setJobCode("SUP123");
        
        when(supportProfessionalRepo.save(any(SupportProfessional.class))).thenReturn(personnel);
        
        mockMvc.perform(post("/admin/support-professionals")
                        .param("jobTitle", "Custodian")
                        .param("jobCode", "SUP123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/support-professionals"));
        
        verify(supportProfessionalRepo).save(any(SupportProfessional.class));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setId(1L);
        personnel.setJobTitle("Custodian");
        
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.of(personnel));

        mockMvc.perform(get("/admin/support-professionals/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/form"))
                .andExpect(model().attributeExists("supportProfessional"));
                
        verify(supportProfessionalRepo).findById(1L);
    }

    @Test
    public void testShowUpdateFormNotFound() throws Exception {
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/support-professionals/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/not-found"));
                
        verify(supportProfessionalRepo).findById(1L);
    }

    @Test
    public void testUpdateSupportProfessional() throws Exception {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setId(1L);
        personnel.setJobTitle("Custodian");
        
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.of(personnel));
        when(supportProfessionalRepo.save(any(SupportProfessional.class))).thenReturn(personnel);

        mockMvc.perform(post("/admin/support-professionals/update/1")
                        .param("jobTitle", "Head Custodian")
                        .param("jobCode", "SUP123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/support-professionals"));
        
        verify(supportProfessionalRepo).findById(1L);
        verify(supportProfessionalRepo).save(any(SupportProfessional.class));
    }

    @Test
    public void testUpdateSupportProfessionalNotFound() throws Exception {
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/admin/support-professionals/update/1")
                        .param("jobTitle", "Head Custodian")
                        .param("jobCode", "SUP123"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/not-found"));
                
        verify(supportProfessionalRepo).findById(1L);
    }

    @Test
    public void testDeleteSupportProfessional() throws Exception {
        SupportProfessional personnel = new SupportProfessional();
        personnel.setId(1L);
        
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.of(personnel));
        doNothing().when(supportProfessionalRepo).delete(any(SupportProfessional.class));

        mockMvc.perform(get("/admin/support-professionals/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/support-professionals"));
        
        verify(supportProfessionalRepo).findById(1L);
        verify(supportProfessionalRepo).delete(any(SupportProfessional.class));
    }

    @Test
    public void testDeleteSupportProfessionalNotFound() throws Exception {
        when(supportProfessionalRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/admin/support-professionals/delete/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/support-professionals/not-found"));
                
        verify(supportProfessionalRepo).findById(1L);
    }
}