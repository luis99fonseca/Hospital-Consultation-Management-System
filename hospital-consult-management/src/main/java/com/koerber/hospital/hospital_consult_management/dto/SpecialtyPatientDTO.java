package com.koerber.hospital.hospital_consult_management.dto;

public class SpecialtyPatientDTO{

    private String specialtyName;
    private int numberOfPatients;

    public SpecialtyPatientDTO(String specialtyName, int numberOfPatients) {
        this.specialtyName = specialtyName;
        this.numberOfPatients = numberOfPatients;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }
}