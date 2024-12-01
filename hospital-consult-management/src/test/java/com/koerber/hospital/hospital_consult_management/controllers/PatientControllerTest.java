package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.ConsultsSymptomsDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.ConsultSymptom;
import com.koerber.hospital.hospital_consult_management.entities.Doctor;
import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.entities.Symptom;
import com.koerber.hospital.hospital_consult_management.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

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
    public void testGetAllPatients_withPageAndSize_success() throws Exception {
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

    @Test
    public void testGetPatientHistory_success() throws Exception {
        // Mock data
        Specialty specialty1 = new Specialty("Dermatology");

        Doctor doctor = new Doctor("António", specialty1);
        Patient patient = new Patient("Manuel", 53);

        Pathology pathology = new Pathology("Pathology 1");

        Consult consult0 = new Consult(doctor, patient, pathology);
        Consult consult1 = new Consult(doctor, patient, new Pathology("Pathology 2"));

        Symptom symptom0 = new Symptom("Symptom 1 Description");
        Symptom symptom1 = new Symptom("Symptom 2 Description");

        ConsultSymptom consultSymptom = new ConsultSymptom(consult0, symptom1);

        List<Consult> consultList = List.of(consult0, consult1);
        List<Symptom> symptomList = List.of(symptom0, symptom1);

        // Mock service method
        when(patientService.findById(any(Long.class))).thenReturn(Optional.of(patient));
        when(patientService.getPatientHistory(any(Long.class))).thenReturn(ResponseEntity.ok(new ConsultsSymptomsDTO(consultList, symptomList)));

        // Perform GET request and validate the response
        mockMvc.perform(get("/patients/8/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.consults[0].doctor.name").value("António"))
                .andExpect(jsonPath("$.consults[1].doctor.name").value("António"))
                .andExpect(jsonPath("$.consults[1].doctor.specialty.name").value("Dermatology"))
                .andExpect(jsonPath("$.symptoms[0].name").value("Symptom 1 Description"))
                .andExpect(jsonPath("$.symptoms[1].name").value("Symptom 2 Description"));

        // Verify service interaction
        verify(patientService, times(1)).findById(any(Long.class));
        verify(patientService, times(1)).getPatientHistory(any(Long.class));
    }

}
