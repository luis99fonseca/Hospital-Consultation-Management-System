package com.koerber.hospital.hospital_consult_management.repository;

import com.koerber.hospital.hospital_consult_management.controllers.ConsultController;
import com.koerber.hospital.hospital_consult_management.controllers.GenericController;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Doctor;
import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.entities.Person;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
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

@WebMvcTest(ConsultController.class)
public class ConsultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsultRepository consultRepository;

    @Test
    public void testGetAllConsults() throws Exception {
        // Mock data
        Specialty specialty1 = new Specialty("Dermatology");

        Doctor doctor = new Doctor("António", specialty1);
        Patient patient = new Patient("Manuel", 53);

        Pathology pathology = new Pathology("Pathology 1");

        Consult consult0 = new Consult(doctor, patient, pathology);
        Consult consult1 = new Consult(doctor, patient, new Pathology("Pathology 2"));

        // Mock repository method
        when(consultRepository.findAll()).thenReturn(List.of(consult0, consult1));

        // Perform GET request and validate the response
        mockMvc.perform(get("/consult"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].doctor.name").value("António"))
                .andExpect(jsonPath("$[0].doctor.specialty.name").value("Dermatology"))
                .andExpect(jsonPath("$[0].patient.name").value("Manuel"))
                .andExpect(jsonPath("$[0].patient.age").value(53))
                .andExpect(jsonPath("$[0].pathology.name").value("Pathology 1"))
                .andExpect(jsonPath("$[1].patient.name").value("Manuel"))
                .andExpect(jsonPath("$[1].pathology.name").value("Pathology 2"));

        // Verify repository interaction
        verify(consultRepository, times(1)).findAll();
    }

}
