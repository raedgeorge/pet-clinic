package com.atech.controllers;

import com.atech.entity.Owner;
import com.atech.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    @InjectMocks
    OwnerController ownerController;

    List<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new ArrayList<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void getOwnerList() throws Exception {

        Owner.builder().id(1l).build();

//        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(0)));
    }

    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));

      //  verifyNoInteractions(ownerService);

    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception{

        when(ownerService.findAllByLastNameLike(anyString())).
                thenReturn(Arrays.asList(Owner.builder().id(1l).build(),
                           Owner.builder().id(2l).build()));

        mockMvc.perform(get("/owners").
                param("lastName",""))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)));

    }

    @Test
    void processFindFormReturnMany() throws Exception{

        when(ownerService.findAllByLastNameLike(anyString())).
                thenReturn(Arrays.asList(Owner.builder().id(1l).build(),
                        Owner.builder().id(2l).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)));

    }

    @Test
    void processFindFormReturnOne() throws Exception{

        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1/detail"));


    }

}