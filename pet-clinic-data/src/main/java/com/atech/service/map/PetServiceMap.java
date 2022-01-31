package com.atech.service.map;

import com.atech.entity.Pet;
import com.atech.service.PetService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {


    @Override
    public List<Pet> findAll() {
        return null;
    }

    @Override
    public void delete(Pet object) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public Pet findById(Long id) {
        return null;
    }
}
