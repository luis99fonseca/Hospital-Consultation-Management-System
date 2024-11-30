package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PatientController {

    @Autowired
    private ConsultRepository consultController;

    @GetMapping("/persons")
    public List<Consult> getAllPersons() {
        return consultController.findAll();
    }
}
