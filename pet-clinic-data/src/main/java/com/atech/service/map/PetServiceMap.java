package com.atech.service.map;

import com.atech.entity.Pet;
import com.atech.service.CrudService;

import java.util.List;

public class PetServiceMap extends AbstractMapService<Pet, Integer> implements CrudService<Pet, Integer> {

    @Override
    public List<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object, object.getId().intValue());
    }

    @Override
    public Pet findById(Integer id) {
        return super.findById(id);
    }
}
