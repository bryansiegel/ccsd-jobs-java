package com.bryansiegel.ccsdjobsjava.controllers.api;

import com.bryansiegel.ccsdjobsjava.models.AdministrativePersonnel;
import com.bryansiegel.ccsdjobsjava.repositories.AdministrativePersonnelRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdministrativePersonnelApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdministrativePersonnelRepo administrativePersonnelRepo;

    @Test
    public void testGetAllAdministrativePersonnel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/administrative-personnel")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAdministrativePersonnelById() throws Exception {
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/administrative-personnel/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testCreateAdministrativePersonnel() throws Exception {
        String newPersonnelJson = "{\"jobCode\":\"1234\",\"referenceCode\":\"5678\",\"classification\":\"Admin\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/administrative-personnel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPersonnelJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateAdministrativePersonnel() throws Exception {
        AdministrativePersonnel personnel = new AdministrativePersonnel();
        personnel.setId(1L);
        when(administrativePersonnelRepo.findById(1L)).thenReturn(Optional.of(personnel));

        String updatedPersonnelJson = "{\"jobCode\":\"1234\",\"referenceCode\":\"5678\",\"classification\":\"Admin\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/administrative-personnel/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPersonnelJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteAdministrativePersonnel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/administrative-personnel/1"))
                .andExpect(status().isOk());
    }
}