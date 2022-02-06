package com.atech.controllers;

import com.atech.entity.Owner;
import com.atech.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/", "/index", "/index.html"})
    public String getOwnerList(Model model){

        List<Owner> ownerList = ownerService.findAll();

        model.addAttribute("owners", ownerList);

        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(Model theModel){

        theModel.addAttribute("owners", ownerService.findAll());

        return "notimplemented";
    }
}
