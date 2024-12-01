package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getPatients(Pageable pageable){
        return null;
        //return patientRepository.findAll(pageable);
/*        Page<Patient> patientPage = patientRepository.findAll(pageable);
        return patientPage.map(patient -> new Patient(patient));*/
    }
}
