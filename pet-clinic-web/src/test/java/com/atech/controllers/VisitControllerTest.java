package com.atech.controllers;

import com.atech.service.PetService;
import com.atech.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

class VisitControllerTest {

    VisitController visitController;

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @Mock
    Model model;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        visitController = new VisitController(visitService, petService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void addNewVisit() throws Exception {

//        when(petService.findById(anyLong()).getOwner()).thenReturn()
//
//        mockMvc.perform(get("/owners/1/pets/1/addVisit"))
//                .andExpect(status().isOk())
//                .andExpect(view()
//                        .name("visits/createOrUpdateVisitForm"));
    }

    @Test
    void saveVisit() {
    }

    @Test
    void updateVisit() {
    }

    @Test
    void deleteVisit() {
    }
}