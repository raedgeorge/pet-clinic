package com.atech.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String petTypeName;

    @Builder
    public PetType(Long id, String petTypeName) {
        super(id);
        this.petTypeName = petTypeName;
    }

    @Override
    public String toString() {
        return petTypeName;
    }
}
