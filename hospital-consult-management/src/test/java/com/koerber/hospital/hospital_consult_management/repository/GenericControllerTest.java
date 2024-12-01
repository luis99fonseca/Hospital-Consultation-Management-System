package com.koerber.hospital.hospital_consult_management.repository;

import com.koerber.hospital.hospital_consult_management.controllers.GenericController;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenericController.class)
public class GenericControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpecialtyRepository specialtyRepository;

    @Test
    public void testGetAllSpecialties() throws Exception {
        // Mock data
        Specialty specialty1 = new Specialty("Dermatology");
        Specialty specialty2 = new Specialty("Ophthalmology");
        List<Specialty> specialties = Arrays.asList(specialty1, specialty2);

        // Mock service method
        when(specialtyRepository.findAll()).thenReturn(specialties);

        // Perform GET request and validate the response
        mockMvc.perform(get("/specialty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dermatology"))
                .andExpect(jsonPath("$[1].name").value("Ophthalmology"));

        // Verify repository interaction
        verify(specialtyRepository, times(1)).findAll();
    }

}
