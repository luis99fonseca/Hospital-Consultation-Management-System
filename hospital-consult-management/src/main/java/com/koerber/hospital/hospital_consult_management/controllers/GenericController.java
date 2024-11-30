package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: remove/refactor later, so there are entity Specific Controllers
@RestController
public class GenericController {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping("/specialtyOld")
    public List<Specialty> getAllConsults() {
        return specialtyRepository.findAll();
    }
}
