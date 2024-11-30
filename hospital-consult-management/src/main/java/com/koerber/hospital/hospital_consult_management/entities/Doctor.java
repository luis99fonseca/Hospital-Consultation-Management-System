package com.koerber.hospital.hospital_consult_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Doctor extends Person {

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    public Doctor() {
    }

    public Doctor(String name, Specialty specialty) {
        super(name);
        this.specialty = specialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return super.toString() + "Doctor{" +
                "specialty=" + specialty +
                '}' + '\'' +
                '}';
    }
}
