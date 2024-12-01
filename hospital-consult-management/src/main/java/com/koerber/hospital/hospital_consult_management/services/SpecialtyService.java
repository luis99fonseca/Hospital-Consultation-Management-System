package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    private int lastMinPatientCount = 0;
    private List<SpecialtyPatientDTO> cache_specialties;

    public List<Specialty> findAll(){
        return specialtyRepository.findAll();
    }

    public List<SpecialtyPatientDTO> getTopSpecialties(int minPatientCount) {
        if (minPatientCount == lastMinPatientCount && !cache_specialties.isEmpty()){
            System.out.println("CACHED");
            return cache_specialties;
        }
        System.out.println("NOT CACHED");

        List<Object[]> results = specialtyRepository.getTopSpecialtiesRaw(minPatientCount);

        cache_specialties = new ArrayList<>();
        lastMinPatientCount = minPatientCount;

        for (Object[] result : results) {
            Integer id = (Integer) result[0];
            String specialtyName = (String) result[1];
            Long numberOfPatients = ((Number) result[2]).longValue();

            SpecialtyPatientDTO dto = new SpecialtyPatientDTO(id, specialtyName, numberOfPatients);
            cache_specialties.add(dto);
        }

        return cache_specialties;
    }
}
