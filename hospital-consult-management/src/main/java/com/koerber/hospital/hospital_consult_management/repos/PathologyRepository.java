package com.koerber.hospital.hospital_consult_management.repos;

import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathologyRepository extends JpaRepository<Pathology, Long> {
}
