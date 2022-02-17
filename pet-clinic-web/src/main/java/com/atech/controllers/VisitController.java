package com.atech.controllers;

import com.atech.entity.Pet;
import com.atech.entity.Visit;
import com.atech.service.PetService;
import com.atech.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/addVisit")
    public String addNewVisit(Model model,
                              @PathVariable long ownerId,
                              @PathVariable long petId){

            model.addAttribute("owner", petService.findById(petId).getOwner());
            model.addAttribute("pet", petService.findById(petId));

            model.addAttribute("visit", new Visit());

        return "visits/createOrUpdateVisitForm";
    }

    @PostMapping("owners/{ownerId}/pets/{petId}/saveVisit")
    public String saveVisit(@PathVariable long ownerId,
                            @PathVariable long petId,
                            @RequestParam("id") String id,
                            @ModelAttribute("visit")Visit visit){

        if (id.equals("")) {

            Pet pet = petService.findById(petId);
            visit.setPet(pet);
            Visit savedVisit = visitService.save(visit);
            pet.getVisits().add(savedVisit);
            petService.save(pet);
            return "redirect:/owners/" + ownerId + "/detail";
        }
        else {

            Pet pet = petService.findById(petId);
            visit.setPet(pet);
            visit.setId(Long.parseLong(id));
            Visit savedVisit = visitService.save(visit);
            pet.getVisits().add(savedVisit);
            petService.save(pet);
            return "redirect:/owners/" + ownerId + "/detail";
        }

    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/updateVisit/{visitId}")
    public String updateVisit(@PathVariable long visitId,
                              @PathVariable long ownerId,
                              @PathVariable long petId,
                              Model model){

        log.debug("INSIDE UPDATE VISIT METHOD");

        model.addAttribute("owner", petService.findById(petId).getOwner());
        model.addAttribute("pet", petService.findById(petId));
        model.addAttribute("visit",visitService.findById(visitId));

        return "visits/createOrUpdateVisitForm";
    }

    @GetMapping("/pets/{petId}/deleteVisit/{visitId}")
    public String deleteVisit(@PathVariable long petId,
                              @PathVariable long visitId){

        Visit visit = visitService.findById(visitId);
        Pet pet = petService.findById(petId);

        log.debug("INSIDE THE VISIT DELETE METHOD");

        visitService.delete(visit);
        pet.getVisits().remove(visit);
        petService.save(pet);

        return "redirect:/owners/" + pet.getOwner().getId() + "/detail";
    }
}
