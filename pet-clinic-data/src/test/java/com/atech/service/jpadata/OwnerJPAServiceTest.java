package com.atech.service.jpadata;

import com.atech.entity.Owner;
import com.atech.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerJPAService ownerJPAService;

    final String lastName = "george";

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName("george").build();
        ownerJPAService.save(returnedOwner);
      }

    @Test
    void findAll() {

        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(Owner.builder().id(2L).build());
        ownerList.add(Owner.builder().id(3L).build());

        when(ownerJPAService.findAll()).thenReturn(ownerList);

        assertNotNull(ownerList);
        assertEquals(2, ownerJPAService.findAll().size());
    }

    @Test
    void findById() {

        when(ownerRepository.findById(any())).
                thenReturn(Optional.of(returnedOwner));

        Owner owner = ownerJPAService.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void save() {

        Owner owner = Owner.builder().id(3L).build();

        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        Owner savedOwner = ownerJPAService.save(owner);
        assertNotNull(savedOwner);
        assertEquals(1, savedOwner.getId());
    }

    @Test
    void deleteById() {

        ownerJPAService.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void delete() {
        ownerJPAService.delete(returnedOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);

        Owner owner1 = ownerJPAService.findByLastName(lastName);

        assertEquals(lastName, owner1.getLastName());
        verify(ownerRepository).findByLastName(any());

    }
}