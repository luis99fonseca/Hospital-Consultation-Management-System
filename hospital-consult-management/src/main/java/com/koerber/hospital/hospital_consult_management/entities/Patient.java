package com.koerber.hospital.hospital_consult_management.entities;

import jakarta.persistence.Entity;

@Entity
public class Patient extends Person {

    private int age;

    public int getAge() {
        return age;
    }

    public Patient() {
    }

    public Patient(String name, int age) {
        super(name);
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + "{Patient{" +
                "age=" + age +
                '}' + '\'' +
                '}';
    }
}
