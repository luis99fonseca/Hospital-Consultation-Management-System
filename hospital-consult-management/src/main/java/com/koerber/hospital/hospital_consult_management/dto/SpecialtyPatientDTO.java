package com.koerber.hospital.hospital_consult_management.dto;

public class SpecialtyPatientDTO{

    private String specialtyName;
    private int id;
    private long numberOfPatients;

    public SpecialtyPatientDTO(String specialtyName, int numberOfPatients) {
        this.specialtyName = specialtyName;
        this.numberOfPatients = numberOfPatients;
    }

    public SpecialtyPatientDTO(int id, String specialtyName, Long numberOfPatients) {
        this.id = id;
        this.specialtyName = specialtyName;
        this.numberOfPatients = numberOfPatients;
    }

    public String getSpecialty() {
        return specialtyName;
    }

    public void setSpecialty(String specialty) {
        this.specialtyName = specialty;
    }

    public long getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(long numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}