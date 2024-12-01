package com.koerber.hospital.hospital_consult_management.repos;

import com.koerber.hospital.hospital_consult_management.entities.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    @Query("SELECT DISTINCT s FROM Symptom s " +
           "JOIN ConsultSymptom cs ON cs.symptom = s " +
           "JOIN Consult c ON cs.consult = c " +
           "WHERE c.patient.id = :patientId")
    List<Symptom> findSymptomsByPatientId(@Param("patientId") Long patientId);
}
