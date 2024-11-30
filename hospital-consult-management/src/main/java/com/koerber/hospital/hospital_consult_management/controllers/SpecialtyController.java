package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import com.koerber.hospital.hospital_consult_management.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/specialties")
    public List<Specialty> getAllSpecialties() {
        return specialtyService.findAll();
    }

    @GetMapping("/specialties/no_patients")
    public List<SpecialtyPatientDTO> getTopSpecialties(@RequestParam(name = "minPatients") int minPatients) {
        return specialtyService.getTopSpecialties(minPatients);
    }
}