package com.atech.service;

import com.atech.entity.Vet;

import java.util.List;

public interface VetService {

    List<Vet> findAll();

    Vet findById(int vetId);

    void save(Vet vet);

    void delete(int vetId);
}
