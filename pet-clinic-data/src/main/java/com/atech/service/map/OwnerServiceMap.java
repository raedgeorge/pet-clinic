package com.atech.service.map;

import com.atech.entity.Owner;
import com.atech.service.CrudService;

import java.util.List;

public class OwnerServiceMap extends AbstractMapService<Owner, Integer> implements CrudService<Owner, Integer> {

    @Override
    public List<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object, object.getId().intValue());
    }

    @Override
    public Owner findById(Integer id) {
        return super.findById(id);
    }
}
