package com.atech.service.map;

import com.atech.entity.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    Long ownerId = 5L;
    final String lastName = "george";

    @BeforeEach
    void setUp() {

        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(),
                                              new PetServiceMap());

        ownerServiceMap.save(Owner.builder()
                       .id(ownerId)
                       .lastName(lastName)
                       .build());
    }

    @Test
    void findAll() {

        assertEquals(1, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(5, ownerServiceMap.findById(ownerId).getId());
    }

    @Test
    void save() {

        Owner owner = new Owner();
        owner.setId(6L);
        owner.setFirstName("raed");

        ownerServiceMap.save(owner);

        assertEquals(2, ownerServiceMap.findAll().size());
    }

    @Test
    void saveNoId() {

        Owner owner = Owner.builder().build();
        ownerServiceMap.save(owner);

        assertNotNull(owner);
        assertNotNull(owner.getId());

    }

    @Test
    void delete() {

        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {

        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {

        Owner owner = ownerServiceMap.findByLastName(lastName);

        assertNotNull(owner);

        assertEquals(lastName,owner.getLastName());

        assertEquals(ownerId, ownerServiceMap.findByLastName(lastName).getId());

    }

    @Test
    void findByLastNameNotFound() {

        Owner owner = ownerServiceMap.findByLastName("john");

        assertNull(owner);

    }
}