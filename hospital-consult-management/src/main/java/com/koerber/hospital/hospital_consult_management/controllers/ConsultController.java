package com.koerber.hospital.hospital_consult_management.controllers;

import com.koerber.hospital.hospital_consult_management.dto.ConsultDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.services.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @GetMapping("/consults")
    public List<Consult> getAllConsults() {
        return consultService.findAll();
    }

    @PostMapping("/consults")
    public ResponseEntity<Consult> createConsult(@RequestBody ConsultDTO consultDTO){
        return consultService.createConsult(consultDTO);
    }
}
