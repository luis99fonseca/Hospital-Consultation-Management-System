package com.koerber.hospital.hospital_consult_management.entities;

import jakarta.persistence.Entity;

@Entity
public class Doctor extends Person {

    private Specialty specialty;

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
