package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.services.SpecialtyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpecialtyController.class)
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SpecialtyService specialtyService;

    @Test
    public void testGetAllSpecialties_success() throws Exception {
        // Mock data
        Specialty specialty1 = new Specialty("Dermatology");
        Specialty specialty2 = new Specialty("Ophthalmology");
        List<Specialty> specialties = Arrays.asList(specialty1, specialty2);

        // Mock service method
        when(specialtyService.findAll()).thenReturn(specialties);

        // Perform GET request and validate the response
        mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dermatology"))
                .andExpect(jsonPath("$[1].name").value("Ophthalmology"));

        // Verify service interaction
        verify(specialtyService, times(1)).findAll();
    }

    @Test
    public void testGetTopSpecialties_success() throws Exception {
        // Mock data
        SpecialtyPatientDTO specialtyPatientDTO = new SpecialtyPatientDTO("Ophthalmology", 3);

        // Mock service method
        when(specialtyService.getTopSpecialties(3)).thenReturn(List.of(specialtyPatientDTO));

        // Perform GET request and validate the response
        mockMvc.perform(get("/specialties/no_patients?minPatients=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].specialtyName").value("Ophthalmology"))
                .andExpect(jsonPath("$[0].numberOfPatients").value(3));

        // Verify service interaction
        verify(specialtyService, times(1)).getTopSpecialties(3);
    }

}
