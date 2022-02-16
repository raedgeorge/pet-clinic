package com.atech.service.map;

import com.atech.entity.Owner;
import com.atech.entity.Pet;
import com.atech.service.OwnerService;
import com.atech.service.PetService;
import com.atech.service.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    @Autowired
    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public List<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner save(Owner object) {

        if (object != null){
            if (object.getPetList() != null){
                object.getPetList().forEach(pet -> {

                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }
                         else {
                             throw new RuntimeException("Pet Type Is Required");
                        }

                        if (pet.getId() == null){
                            Pet savedPet = petService.save(pet);
                            pet.setId(savedPet.getId());
                        }
                });
            }
            return super.save(object);
        }
        else return null;
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {

        return this.findAll()
           .stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
           .findFirst()
           .orElse(null);

    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return null;
    }
}
