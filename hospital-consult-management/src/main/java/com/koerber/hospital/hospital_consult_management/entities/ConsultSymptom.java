package com.koerber.hospital.hospital_consult_management.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class ConsultSymptom {

    @EmbeddedId
    private ConsultSymptomId id;

    @ManyToOne
    @MapsId("consultId")
    @JoinColumn(name = "consult_id")
    private Consult consult;

    @ManyToOne
    @MapsId("symptomId")
    @JoinColumn(name = "symptom_id")
    private Symptom symptom;

    public ConsultSymptom() {
    }

    public ConsultSymptom(Consult consult, Symptom symptom) {
        this.consult = consult;
        this.symptom = symptom;
        this.id = new ConsultSymptomId(consult.getId(), symptom.getId());
    }

    public ConsultSymptomId getId() {
        return id;
    }

    public void setId(ConsultSymptomId id) {
        this.id = id;
    }

    public Consult getConsult() {
        return consult;
    }

    public void setConsult(Consult consult) {
        this.consult = consult;
    }

    public Symptom getSymptom() {
        return symptom;
    }

    public void setSymptom(Symptom symptom) {
        this.symptom = symptom;
    }
}