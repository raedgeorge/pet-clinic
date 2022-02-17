package com.atech.controllers;

import com.atech.entity.Owner;
import com.atech.entity.Pet;
import com.atech.entity.PetType;
import com.atech.service.OwnerService;
import com.atech.service.PetService;
import com.atech.service.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService,
                         PetService petService,
                         PetTypeService petTypeService) {

        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/addPet")
    public String initAddPetForm(@PathVariable long ownerId, Model model){



    model.addAttribute("owner", ownerService.findById(ownerId));
    model.addAttribute("pet", Pet.builder().build());
    model.addAttribute("petTypes", petTypeService.findAll());

    return "pet/addOrUpdatePetForm";

    }

    @GetMapping("/editPet/{petId}")
    public String updatePet(@PathVariable long petId,
                            @PathVariable long ownerId,
                            Model model){

        model.addAttribute("owner", ownerService.findById(ownerId));
        model.addAttribute("petTypes", petTypeService.findAll());

        model.addAttribute("pet", petService.findById(petId));

        return "pet/addOrUpdatePetForm";
    }

    @PostMapping("/savePet")
    public String savePet(@PathVariable long ownerId,
                          @ModelAttribute("pet")Pet pet,
                          @ModelAttribute("petType") PetType petType,
                          @RequestParam("id") String id,
                          BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "pet/addOrUpdatePetForm";
        }

        if (id.equals("")) {
        Pet savedPet = petService.save(pet);
        Owner owner = ownerService.findById(ownerId);
        savedPet.setOwner(owner);
        owner.getPetList().add(savedPet);
        ownerService.save(owner);
        return "redirect:/owners/" + ownerId + "/detail";
        }

        else {
            pet.setId(Long.parseLong(id));
            Pet savedPet = petService.save(pet);
            Owner owner = ownerService.findById(ownerId);
            savedPet.setOwner(owner);
            owner.getPetList().add(savedPet);
            ownerService.save(owner);
            return "redirect:/owners/" + ownerId + "/detail";
        }
    }

}
