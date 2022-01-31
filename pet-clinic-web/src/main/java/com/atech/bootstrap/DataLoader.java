package com.atech.bootstrap;

import com.atech.entity.Owner;
import com.atech.entity.Pet;
import com.atech.entity.PetType;
import com.atech.entity.Vet;
import com.atech.service.OwnerService;
import com.atech.service.PetTypeService;
import com.atech.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
