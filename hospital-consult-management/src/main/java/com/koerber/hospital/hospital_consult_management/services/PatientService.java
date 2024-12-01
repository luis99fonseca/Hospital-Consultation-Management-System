package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.dto.ConsultsSymptomsDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.entities.Symptom;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.repos.PatientRepository;
import com.koerber.hospital.hospital_consult_management.repos.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;
    @Autowired
    private SymptomRepository symptomRepository;

    public Page<Patient> getPatients(Pageable pageable){
        return patientRepository.findAll(pageable);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public ResponseEntity<ConsultsSymptomsDTO> getPatientHistory(Long id) {
        List<Consult> consults = consultRepository.findConsultsByPatientId(id);
        List<Symptom> symptoms = symptomRepository.findSymptomsByPatientId(id);
        return ResponseEntity.ok(new ConsultsSymptomsDTO(consults, symptoms));
    }
}
