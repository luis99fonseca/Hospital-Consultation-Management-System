package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.dto.SpecialtyPatientDTO;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.repos.PatientRepository;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialtyService specialtyService;

    @Test
    public void testGetTopSpecialties_success() throws Exception {
        // Mock data
        int minPatientCount = 2;

        // Prepare mock data (List<Object[]>)
        List<Object[]> mockResults = Arrays.asList(
            new Object[] {1, "Dermatology", 3},
            new Object[] {2, "Cardiology", 4}
        );

        // Mock repository method
        when(specialtyRepository.getTopSpecialtiesRaw(minPatientCount)).thenReturn(mockResults);


        // Call the method in the service
        List<SpecialtyPatientDTO> topSpecialties = specialtyService.getTopSpecialties(minPatientCount);

        // Verify service output
        assertThat(topSpecialties).hasSize(2);
        assertThat(topSpecialties.get(0).getSpecialty()).isEqualTo("Dermatology");
        assertThat(topSpecialties.get(1).getSpecialty()).isEqualTo("Cardiology");
        assertThat(topSpecialties.get(0).getNumberOfPatients()).isEqualTo(3);
        assertThat(topSpecialties.get(1).getNumberOfPatients()).isEqualTo(4);
    }
}
