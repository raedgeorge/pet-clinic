package com.atech.controllers;

import com.atech.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"","/",  "/index", "/index.html"})
    public String getVetsList(Model model){

        model.addAttribute("vets", vetService.findAll());
        vetService.findAll().forEach(vet -> System.out.println(vet));

        return "vets/index";
    }
}
