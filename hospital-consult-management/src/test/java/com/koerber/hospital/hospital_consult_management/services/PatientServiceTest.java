package com.koerber.hospital.hospital_consult_management.services;

import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.repos.PatientRepository;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testGetAllPatients_withPageAndSize_success() throws Exception {
        // Mock data
        Patient patient0 = new Patient("Manuel", 53);
        Patient patient1 = new Patient("Joana", 32);
        Patient patient2 = new Patient("Ana", 25);
        Patient patient3 = new Patient("Diogo", 33);
        Page<Patient> page = new PageImpl<>(List.of(patient0, patient1, patient2, patient3));

        // Mock repository method
        when(patientRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Call the method in the service
        Sort sort = Sort.by("name").ascending();
        Pageable pageRequest = PageRequest.of(1, 1, sort);

        Page<Patient> response_page = patientService.getPatients(pageRequest);

        // Verify service output
        List<Patient> response_list = response_page.get().toList();
        assertThat(response_list).hasSize(4);
        assertThat(response_list).contains(patient0, patient1, patient2, patient3);
        verify(patientRepository, times(1)).findAll(any(Pageable.class));
    }
}
