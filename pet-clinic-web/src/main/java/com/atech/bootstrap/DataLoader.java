package com.atech.bootstrap;

import com.atech.entity.*;
import com.atech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(VetService vetService,
                      OwnerService ownerService,
                      PetTypeService petTypeService,
                      SpecialityService specialityService,
                      VisitService visitService) {

        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
                loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("raed");
        owner1.setLastName("abu sada");
        owner1.setCity("Beit-Sahour");
        owner1.setAddress("Steh Street");
        owner1.setTelephone("0592756158");

        Pet raedPet = new Pet();
        raedPet.setName("raed dog");
        raedPet.setBirthDate( LocalDate.now());
        raedPet.setPetType(savedDogPetType);
        raedPet.setOwner(owner1);


        owner1.getPetList().add(raedPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("george");
        owner2.setLastName("abu sada");
        owner2.setCity("Beit-Sahour");
        owner2.setAddress("Steh Street");
        owner2.setTelephone("0595896841");

        Pet georgePet = new Pet();
        georgePet.setName("kitty cat");
        georgePet.setBirthDate(LocalDate.now());
        georgePet.setPetType(savedCatPetType);
        georgePet.setOwner(owner2);

        owner2.getPetList().add(georgePet);
        ownerService.save(owner2);

        System.out.println("============");
        System.out.println("owners loaded");

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery speciality");
        Speciality savedSurgery = specialityService.save(surgery);

        Vet vet1 = new Vet();
        vet1.setFirstName("john");
        vet1.setLastName("doe");

        vet1.getSpecialities().add(savedSurgery);
        vetService.save(vet1);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology Speciality");
        Speciality savedRadiology = specialityService.save(radiology);

        Vet vet2 = new Vet();
        vet2.setFirstName("alex");
        vet2.setLastName("show");

        vet2.getSpecialities().add(savedRadiology);
        vetService.save(vet2);

        System.out.println("vets loaded");
        System.out.println("============");

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("visit for surgery appointment");
        visit.setPet(raedPet);

        visitService.save(visit);
        System.out.println("visits loaded");
        System.out.println("============");

    }
}
