package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import com.koerber.hospital.hospital_consult_management.services.SpecialtyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecialtyController {

    private static final Logger logger = LoggerFactory.getLogger(SpecialtyController.class);
    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/specialties")
    public List<Specialty> getAllSpecialties() {
        logger.info("Request GET at /specialties");
        return specialtyService.findAll();
    }

    @GetMapping("/specialties/no_patients")
    public List<SpecialtyPatientDTO> getTopSpecialties(@RequestParam(name = "minPatients") int minPatients) {
        logger.info("Request GET at /specialties/no_patients with minPatients='" + minPatients + "'");
        return specialtyService.getTopSpecialties(minPatients);
    }
}