package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public List<Specialty> findAll(){
        return specialtyRepository.findAll();
    }

    public List<SpecialtyPatientDTO> getTopSpecialties(int minPatients) {
        return null;
    }
}
