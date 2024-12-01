package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.cache.SpecialtiesCache;
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
    @Autowired
    private SpecialtiesCache specialtiesCache;

    public List<Specialty> findAll(){
        return specialtyRepository.findAll();
    }

    public List<SpecialtyPatientDTO> getTopSpecialties(int minPatientCount) {
        List<SpecialtyPatientDTO> specialtyPatientDTOList = specialtiesCache.getCachedSpecialties(minPatientCount);
        if (specialtyPatientDTOList != null){
            return specialtyPatientDTOList;
        }

        List<Object[]> results = specialtyRepository.getTopSpecialtiesRaw(minPatientCount);

        specialtyPatientDTOList = new ArrayList<>();

        for (Object[] result : results) {
            Integer id = (Integer) result[0];
            String specialtyName = (String) result[1];
            Long numberOfPatients = ((Number) result[2]).longValue();

            SpecialtyPatientDTO dto = new SpecialtyPatientDTO(id, specialtyName, numberOfPatients);
            specialtyPatientDTOList.add(dto);
        }
        specialtiesCache.updateCachedSpecialties(specialtyPatientDTOList, minPatientCount);

        return specialtyPatientDTOList;
    }
}
