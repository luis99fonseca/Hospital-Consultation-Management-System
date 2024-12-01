package com.koerber.hospital.hospital_consult_management.repository;

import com.koerber.hospital.hospital_consult_management.controllers.PatientController;
import com.koerber.hospital.hospital_consult_management.controllers.SpecialtyController;
import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;

    @Test
    public void testGetAllSpecialties_withPageAndSize_success() throws Exception {
        // Mock data
        Patient patient0 = new Patient("Manuel", 53);
        Patient patient1 = new Patient("Joana", 32);
        Patient patient2 = new Patient("Ana", 25);
        Patient patient3 = new Patient("Diogo", 33);
        Page<Patient> page = new PageImpl<>(List.of(patient0, patient1, patient2, patient3));

        // Mock service method
        when(patientService.getPatients(any(Pageable.class))).thenReturn(page);

        // Perform GET request and validate the response
        mockMvc.perform(get("/patients?page=0&size=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Manuel"))
                .andExpect(jsonPath("$.content[1].name").value("Joana"))
                .andExpect(jsonPath("$.content[2].name").value("Ana"))
                .andExpect(jsonPath("$.last").value("true"))
                .andExpect(jsonPath("$.size").value("4"));

        // Verify service interaction
        verify(patientService, times(1)).getPatients(any(Pageable.class));
    }

}
