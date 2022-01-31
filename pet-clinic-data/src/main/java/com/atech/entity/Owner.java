package com.atech.entity;

import java.util.List;

public class Owner extends Person{

    private List<Pet> petList;

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }
}
