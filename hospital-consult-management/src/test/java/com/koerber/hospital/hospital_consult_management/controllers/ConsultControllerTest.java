package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.ConsultDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Doctor;
import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.services.ConsultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsultController.class)
public class ConsultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsultService consultService;

    @Test
    public void testGetAllConsults_success() throws Exception {
        // Mock data
        Specialty specialty1 = new Specialty("Dermatology");

        Doctor doctor = new Doctor("António", specialty1);
        Patient patient = new Patient("Manuel", 53);

        Pathology pathology = new Pathology("Pathology 1");

        Consult consult0 = new Consult(doctor, patient, pathology);
        Consult consult1 = new Consult(doctor, patient, new Pathology("Pathology 2"));

        // Mock service method
        when(consultService.findAll()).thenReturn(List.of(consult0, consult1));

        // Perform GET request and validate the response
        mockMvc.perform(get("/consults"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].doctor.name").value("António"))
                .andExpect(jsonPath("$[0].doctor.specialty.name").value("Dermatology"))
                .andExpect(jsonPath("$[0].patient.name").value("Manuel"))
                .andExpect(jsonPath("$[0].patient.age").value(53))
                .andExpect(jsonPath("$[0].pathology.name").value("Pathology 1"))
                .andExpect(jsonPath("$[1].patient.name").value("Manuel"))
                .andExpect(jsonPath("$[1].pathology.name").value("Pathology 2"));

        // Verify service interaction
        verify(consultService, times(1)).findAll();
    }

    @Test
    public void testGetAllConsults_emptyList_success() throws Exception {
        // Mock service method
        when(consultService.findAll()).thenReturn(Collections.emptyList());

        // Perform GET request and validate the response
        mockMvc.perform(get("/consults"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        // Verify service interaction
        verify(consultService, times(1)).findAll();
    }

    @Test
    public void testPostConsult_success() throws Exception {
        // Mock data
        Specialty specialty1 = new Specialty("Dermatology");

        Doctor doctor = new Doctor("António", specialty1);
        Patient patient = new Patient("Manuel", 53);

        Pathology pathology = new Pathology("Pathology 1");

        Consult consult0 = new Consult(doctor, patient, pathology);

        String json_payload = new String(Files.readAllBytes(Paths.get("src/test/resources/files/postConsult_success.json")));

        // Mock service method
        when(consultService.createConsult(any(ConsultDTO.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(consult0));

        // Perform POST request and validate the response
        mockMvc.perform(post("/consults")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json_payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.doctor.name").value("António"))
                .andExpect(jsonPath("$.doctor.specialty.name").value("Dermatology"))
                .andExpect(jsonPath("$.patient.name").value("Manuel"))
                .andExpect(jsonPath("$.patient.age").value(53))
                .andExpect(jsonPath("$.pathology.name").value("Pathology 1"));

        // Verify service interaction
        verify(consultService, times(1)).createConsult(any(ConsultDTO.class));
    }

}
