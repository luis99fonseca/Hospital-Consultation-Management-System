package com.koerber.hospital.hospital_consult_management.entities;

import jakarta.persistence.Entity;

@Entity
public class Patient extends Person {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
