package com.atech.controllers;

import com.atech.entity.Owner;
import com.atech.service.OwnerService;
import com.atech.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
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

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model){

        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping
    public String getOwnerList(Owner owner, BindingResult bindingResult, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> ownerList = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");

        if (ownerList.isEmpty()) {

            bindingResult.rejectValue(
                    "lastName", "not found", "not found");

        }

        if (ownerList.size() == 1) {

            owner = ownerList.iterator().next();
            return "redirect:/owners/" + owner.getId() + "/detail";
        }

        else {
            model.addAttribute("owners", ownerList);
            return "owners/ownersList";
        }
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

    @GetMapping("/addOwner")
    public String addNewOwner(Model model){

        model.addAttribute("owner", new Owner());
        return "owners/createOrUpdateOwnerForm";
    }

    @GetMapping("/{ownerId}/updateOwner")
    public String updateOwner(Model model, @PathVariable("ownerId") long ownerId){

        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/saveOwner")
    public String saveOwner(@ModelAttribute("owner")Owner owner, @RequestParam("id")String id){

        if (id.equals("")) {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+ savedOwner.getId() + "/detail";
        }

        else {
            owner.setId(Long.parseLong(id));
            Owner updatedOwner = ownerService.save(owner);
            return "redirect:/owners/"+ updatedOwner.getId() + "/detail";
        }

    }

}
