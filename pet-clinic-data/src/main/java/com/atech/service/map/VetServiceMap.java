package com.atech.service.map;

import com.atech.entity.Vet;
import com.atech.service.VetService;

import java.util.List;

public class VetServiceMap extends AbstractMapService<Vet, Integer> implements VetService {

    @Override
    public List<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object, object.getId().intValue());
    }

    @Override
    public Vet findById(Integer id) {
        return super.findById(id);
    }
}
