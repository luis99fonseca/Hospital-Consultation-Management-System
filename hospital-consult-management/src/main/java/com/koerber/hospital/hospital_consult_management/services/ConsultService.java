package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.dto.ConsultDTO;
import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.ConsultSymptom;
import com.koerber.hospital.hospital_consult_management.entities.Doctor;
import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.entities.Symptom;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.repos.ConsultSymptomRepository;
import com.koerber.hospital.hospital_consult_management.repos.DoctorRepository;
import com.koerber.hospital.hospital_consult_management.repos.PathologyRepository;
import com.koerber.hospital.hospital_consult_management.repos.PatientRepository;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import com.koerber.hospital.hospital_consult_management.repos.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultService {

    @Autowired
    private ConsultRepository consultRepository;
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PathologyRepository pathologyRepository;
    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private ConsultSymptomRepository consultSymptomRepository;

    @Transactional(rollbackFor = Exception.class)  // Rollback on all exceptions
    public ResponseEntity<Consult> createConsult(ConsultDTO consultDTO) {

        Consult consult = new Consult();
        Specialty specialty;
        Doctor doctor;
        Patient patient;
        Pathology pathology;

        // Handle Specialty
        if (consultDTO.getDoctor().getSpecialty().getId() == null){
            Specialty newSpecialty = new Specialty();
            newSpecialty.setName(consultDTO.getDoctor().getSpecialty().getName());
            specialty = specialtyRepository.save(newSpecialty);
        } else {
            specialty = specialtyRepository.findById(consultDTO.getDoctor().getSpecialty().getId())
                    .orElseGet(() -> {
                        // If Specialty not found, create a new Specialty
                        Specialty newSpecialty = new Specialty();
                        newSpecialty.setName(consultDTO.getDoctor().getSpecialty().getName());
                        return specialtyRepository.save(newSpecialty); // Save the new specialty
                    });
        }

        // Handle Doctor
        if (consultDTO.getDoctor().getId() == null){
            Doctor newDoctor = new Doctor();
            newDoctor.setName(consultDTO.getDoctor().getName());
            newDoctor.setSpecialty(specialty);
            doctor = doctorRepository.save(newDoctor);
        } else {
            doctor = doctorRepository.findById(consultDTO.getDoctor().getId())
                    .orElseGet(() -> {
                        // If Doctor not found, create a new Doctor
                        Doctor newDoctor = new Doctor();
                        newDoctor.setName(consultDTO.getDoctor().getName());
                        newDoctor.setSpecialty(specialty);
                        return doctorRepository.save(newDoctor); // Save the new doctor
                    });
        }
        consult.setDoctor(doctor);

        // Handle Patient
        if (consultDTO.getPatient().getId() == null){
            Patient newPatient = new Patient();
            newPatient.setName(consultDTO.getPatient().getName());
            newPatient.setAge(consultDTO.getPatient().getAge());
            patient = patientRepository.save(newPatient);
        } else {
            patient = patientRepository.findById(consultDTO.getPatient().getId())
                    .orElseGet(() -> {
                        // If Patient not found, create a new Patient
                        Patient newPatient = new Patient();
                        newPatient.setName(consultDTO.getPatient().getName());
                        newPatient.setAge(consultDTO.getPatient().getAge());
                        return patientRepository.save(newPatient); // Save the new patient
                    });
        }
        consult.setPatient(patient);

        // Handle Pathology
        if (consultDTO.getPathology().getId() == null){
            Pathology newPathology = new Pathology();
            newPathology.setName(consultDTO.getPathology().getName());
            pathology = pathologyRepository.save(newPathology);
        } else {
            pathology = pathologyRepository.findById(consultDTO.getPathology().getId())
                   .orElseGet(() -> {
                       // If Pathology not found, create a new Pathology
                       Pathology newPathology = new Pathology();
                       newPathology.setName(consultDTO.getPathology().getName());
                       return pathologyRepository.save(newPathology); // Save the new pathology
                   });
        }

        consult.setPathology(pathology);
        Consult savedConsult = consultRepository.save(consult);

        // Handle Symptom
        for(Symptom actual_symptom : consultDTO.getSymptoms()){
            Symptom symptom;
            if (actual_symptom.getId() == null){
                Symptom newSymptom = new Symptom();
                newSymptom.setName(actual_symptom.getName());
                symptom = symptomRepository.save(newSymptom);
            } else {
                symptom = symptomRepository.findById(actual_symptom.getId())
                       .orElseGet(() -> {
                           // If Symptom not found, create a new Symptom
                           Symptom newSymptom = new Symptom();
                           newSymptom.setName(actual_symptom.getName());
                           return symptomRepository.save(newSymptom); // Save the new Symptom
                       });
            }

            // Handle ConsultSymptom
            ConsultSymptom consultSymptom = new ConsultSymptom(savedConsult, symptom);
            consultSymptomRepository.save(consultSymptom);
        }

        return ResponseEntity.ok(savedConsult);
    }

    public List<Consult> findAll() {
        return consultRepository.findAll();
    }
}
