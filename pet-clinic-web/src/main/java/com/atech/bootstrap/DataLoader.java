package com.atech.bootstrap;

import com.atech.entity.Owner;
import com.atech.entity.PetType;
import com.atech.entity.Vet;
import com.atech.service.OwnerService;
import com.atech.service.PetTypeService;
import com.atech.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(VetService vetService,
                      OwnerService ownerService,
                      PetTypeService petTypeService) {

        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);


        Owner owner1 = new Owner();
        owner1.setFirstName("raed");
        owner1.setLastName("abu sada");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("george");
        owner2.setLastName("abu sada");
        ownerService.save(owner2);

        System.out.println("============");
        System.out.println("owners loaded");

        Vet vet1 = new Vet();
        vet1.setFirstName("john");
        vet1.setLastName("doe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("alex");
        vet2.setLastName("show");
        vetService.save(vet2);

        System.out.println("vets loaded");
        System.out.println("============");

    }
}
