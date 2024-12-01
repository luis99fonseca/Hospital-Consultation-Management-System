package com.koerber.hospital.hospital_consult_management.repos;

import com.koerber.hospital.hospital_consult_management.entities.ConsultSymptom;
import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultSymptomRepository extends JpaRepository<ConsultSymptom, Long> {
}
