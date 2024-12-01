package com.koerber.hospital.hospital_consult_management.dto;

import com.koerber.hospital.hospital_consult_management.entities.Consult;
import com.koerber.hospital.hospital_consult_management.entities.Symptom;

import java.util.List;

public class ConsultsSymptomsDTO {
    private List<Consult> consults;
    private List<Symptom> symptoms;

    public ConsultsSymptomsDTO(List<Consult> consults, List<Symptom> symptoms) {
        this.consults = consults;
        this.symptoms = symptoms;
    }

    public List<Consult> getConsults() {
        return consults;
    }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }
}
