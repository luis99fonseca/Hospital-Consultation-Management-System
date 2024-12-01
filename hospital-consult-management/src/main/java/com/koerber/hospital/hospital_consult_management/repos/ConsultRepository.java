package com.koerber.hospital.hospital_consult_management.repos;

import com.koerber.hospital.hospital_consult_management.entities.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultRepository extends JpaRepository<Consult, Long> {

    @Query("SELECT c FROM Consult c WHERE c.patient.id = :patientId")
    List<Consult> findConsultsByPatientId(@Param("patientId") Long patientId);
}