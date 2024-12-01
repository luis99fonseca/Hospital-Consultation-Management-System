package com.koerber.hospital.hospital_consult_management.entities;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConsultSymptomId implements Serializable {
    private Long consultId;
    private Long symptomId;

    public ConsultSymptomId() {
    }

    public ConsultSymptomId(Long consultId, Long symptomId) {
        this.consultId = consultId;
        this.symptomId = symptomId;
    }

    public Long getConsultId() {
        return consultId;
    }

    public void setConsultId(Long consultId) {
        this.consultId = consultId;
    }

    public Long getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Long symptomId) {
        this.symptomId = symptomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultSymptomId that = (ConsultSymptomId) o;
        return Objects.equals(consultId, that.consultId) && Objects.equals(symptomId, that.symptomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultId, symptomId);
    }
}
