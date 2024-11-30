package com.koerber.hospital.hospital_consult_management.helper;

import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Doctor;
import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import com.koerber.hospital.hospital_consult_management.entities.Patient;
import com.koerber.hospital.hospital_consult_management.entities.Specialty;
import com.koerber.hospital.hospital_consult_management.repos.ConsultRepository;
import com.koerber.hospital.hospital_consult_management.repos.SpecialtyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: for start of development only
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SpecialtyRepository repository) {

        Specialty specialty1 = new Specialty("Dermatology");

        Doctor doctor = new Doctor("António", specialty1);
        Patient patient = new Patient("Manuel", 53);

        Pathology pathology = new Pathology("Pathology 1");

        Consult consult0 = new Consult(doctor, patient, pathology);
        Consult consult1 = new Consult(doctor, patient, new Pathology("Pathology 2"));

      return args -> {
        log.info("Preloading " + repository.save(new Specialty("Dermatology")));
        log.info("Preloading " + repository.save(new Specialty("Ophthalmology")));
      };
    }
}
