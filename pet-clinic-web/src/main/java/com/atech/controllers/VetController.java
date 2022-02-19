package com.atech.controllers;

import com.atech.entity.Vet;
import com.atech.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets",  "vets/index", "vets/index.html", "/vets.html"})
    public String getVetsList(Model model){

        model.addAttribute("vets", vetService.findAll());
        vetService.findAll().forEach(vet -> System.out.println(vet));

        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody List<Vet> getVetJson(){
        return vetService.findAll();
    }
}
