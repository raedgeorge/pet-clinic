package com.atech.service.map;

import com.atech.entity.Visit;
import com.atech.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public List<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Visit save(Visit object) {

        if (object.getPet() == null ||
            object.getPet().getId() == null ||
            object.getPet().getOwner() == null ||
            object.getPet().getOwner().getId() == null){

            throw new IllegalStateException("data in not complete");
        }

        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
