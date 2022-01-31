package com.atech.entity;

import java.util.List;

public class Vet extends Person{

    private List<Speciality> specialities;

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
