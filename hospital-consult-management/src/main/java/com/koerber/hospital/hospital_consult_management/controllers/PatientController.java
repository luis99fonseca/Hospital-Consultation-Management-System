package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.ConsultsSymptomsDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public Page<Patient> getPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortByParam,
            @RequestParam(defaultValue = "true") boolean ascending) {

        Sort sort = ascending ? Sort.by(sortByParam).ascending() : Sort.by(sortByParam).descending();

        Pageable pageRequest = PageRequest.of(page, size, sort);
        return patientService.getPatients(pageRequest);
    }

    @GetMapping("/patients/{id}/history")
    public ResponseEntity<ConsultsSymptomsDTO> getPatientHistory(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);

        if (patient.isEmpty()) {
            System.out.println("LUIS");
            return ResponseEntity.notFound().build();
        }

        return patientService.getPatientHistory(id);
        //return ResponseEntity.ok(patient);
    }
}
