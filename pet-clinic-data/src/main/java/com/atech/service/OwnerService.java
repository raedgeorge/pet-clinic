package com.atech.service;

import com.atech.entity.Owner;

import java.util.List;

public interface OwnerService {

    List<Owner> findAll();

    Owner findById(int ownerId);

    Owner findByLastName(String lastName);

    void save(Owner owner);

    void delete(int ownerId);

}
