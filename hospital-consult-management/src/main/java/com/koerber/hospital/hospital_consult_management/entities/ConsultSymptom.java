package com.koerber.hospital.hospital_consult_management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ConsultSymptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consult_id")
    private Consult consult;

    @ManyToOne
    @JoinColumn(name = "symptom_id")
    private Symptom symptom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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