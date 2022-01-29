package com.atech.bootstrap;

import com.atech.entity.Owner;
import com.atech.entity.Vet;
import com.atech.service.OwnerService;
import com.atech.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;
    private final OwnerService ownerService;

    @Autowired
    public DataLoader(VetService vetService, OwnerService ownerService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("raed");
        owner1.setLastName("abu sada");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("george");
        owner2.setLastName("abu sada");
        ownerService.save(owner2);

        System.out.println("============");
        System.out.println("owners loaded");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("john");
        vet1.setLastName("doe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("alex");
        vet2.setLastName("show");
        vetService.save(vet2);

        System.out.println("vets loaded");
    }
}
