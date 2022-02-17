package com.atech.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> petList = new ArrayList<>();

    @Builder
    public Owner(Long id, String firstName,
                 String lastName,
                 String address, String city,
                 String telephone, List<Pet> petList) {

        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (this.petList != null) {
            this.petList = petList;
        }
    }

    // Getters and Setters
}
