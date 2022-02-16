package com.atech.controllers;

import com.atech.entity.Owner;
import com.atech.service.OwnerService;
import com.atech.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final PetService petService;

    @Autowired
    public OwnerController(OwnerService ownerService,
                           PetService petService) {

        this.ownerService = ownerService;
        this.petService = petService;
    }

    @RequestMapping({"","/", "/find", "/ownersList.html"})
    public String getOwnerList(Model model){

        List<Owner> ownerList = ownerService.findAll();

        model.addAttribute("owners", ownerList);

        return "owners/ownersList";
    }

    // owner detail. implementation 1
//    @RequestMapping("/{ownerId}/detail")
//    public String ownerDetail(
//            Model theModel,
//            @PathVariable("ownerId") long ownerId){
//
//        Owner owner = ownerService.findById(ownerId);
//
//        theModel.addAttribute("owner", owner);
//
//        return "owners/ownerDetails";
//    }

    // owner detail implementation 2
    @RequestMapping("/{ownerId}/detail")
    public ModelAndView ownerDetail2(@PathVariable("ownerId") long ownerId){

        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");

        modelAndView.addObject(ownerService.findById(ownerId));

        return modelAndView;
    }
}
