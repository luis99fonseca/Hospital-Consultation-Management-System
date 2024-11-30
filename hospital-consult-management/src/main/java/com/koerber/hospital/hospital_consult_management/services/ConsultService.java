package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.dto.ConsultDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {

    @Autowired
    private ConsultRepository consultRepository;


    public ResponseEntity<Consult> createConsult(ConsultDTO consultDTO) {
        return null;
    }

    public List<Consult> findAll() {
        return consultRepository.findAll();
    }
}
