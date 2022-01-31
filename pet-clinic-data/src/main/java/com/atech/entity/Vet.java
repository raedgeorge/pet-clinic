package com.atech.entity;

import java.util.ArrayList;
import java.util.List;

public class Vet extends Person{

    private List<Speciality> specialities = new ArrayList<>();

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
