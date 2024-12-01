package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.ConsultsSymptomsDTO;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.Optional;


@RestController
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public Page<Patient> getPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortByParam,
            @RequestParam(defaultValue = "true") boolean ascending) {
        logger.info("Request GET at /patients");
        Sort sort = ascending ? Sort.by(sortByParam).ascending() : Sort.by(sortByParam).descending();

        Pageable pageRequest = PageRequest.of(page, size, sort);
        return patientService.getPatients(pageRequest);
    }

    @GetMapping("/patients/{id}/history")
    public ResponseEntity<ConsultsSymptomsDTO> getPatientHistory(@PathVariable Long id) {
        logger.info("Request GET at /patients/" + id + "/history");
        Optional<Patient> patient = patientService.findById(id);

        if (patient.isEmpty()) {
            logger.error("Patient with ID='" + id + "' not found");
            return ResponseEntity.notFound().build();
        }

        return patientService.getPatientHistory(id);
    }
}
