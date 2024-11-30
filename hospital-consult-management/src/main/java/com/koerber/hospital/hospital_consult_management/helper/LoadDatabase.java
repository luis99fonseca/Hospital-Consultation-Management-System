package com.koerber.hospital.hospital_consult_management.helper;

import com.koerber.hospital.hospital_consult_management.entities.Specialty;
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

      return args -> {
        log.info("Preloading " + repository.save(new Specialty("Dermatology")));
        log.info("Preloading " + repository.save(new Specialty("Ophthalmology")));
      };
    }
}
