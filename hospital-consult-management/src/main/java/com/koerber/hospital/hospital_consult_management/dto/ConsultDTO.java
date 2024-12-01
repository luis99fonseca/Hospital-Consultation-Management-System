package com.koerber.hospital.hospital_consult_management.dto;

import com.koerber.hospital.hospital_consult_management.entities.Doctor;
import com.koerber.hospital.hospital_consult_management.entities.Pathology;
import com.koerber.hospital.hospital_consult_management.entities.Patient;

public class ConsultDTO {

    private Doctor doctor;

    private Patient patient;

    private Pathology pathology;


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Pathology getPathology() {
        return pathology;
    }

    public void setPathology(Pathology pathology) {
        this.pathology = pathology;
    }

    @Override
    public String toString() {
        return "ConsultDTO{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", pathology=" + pathology +
                '}';
    }
}
