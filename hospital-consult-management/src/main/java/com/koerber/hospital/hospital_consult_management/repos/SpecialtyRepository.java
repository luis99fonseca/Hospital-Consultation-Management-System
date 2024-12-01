package com.koerber.hospital.hospital_consult_management.repos;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query(value = "SELECT s.id, s.name, COUNT(DISTINCT p.id) " +
                   "FROM specialty s " +
                   "JOIN doctor d ON d.specialty_id = s.id " +
                   "JOIN consult c ON c.doctor_id = d.id " +
                   "JOIN patient p ON p.id = c.patient_id " +
                   "GROUP BY s.id " +
                   "HAVING COUNT(DISTINCT p.id) > :minPatientCount", nativeQuery = true)
    List<Object[]> getTopSpecialtiesRaw(@Param("minPatientCount") int minPatientCount);
}
