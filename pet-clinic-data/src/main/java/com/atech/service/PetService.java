package com.atech.service;

import com.atech.entity.Pet;

import java.util.List;

public interface PetService {

    List<Pet> findAll();

    Pet findById(int petId);

    void save(Pet pet);

    void delete(int petId);
}
