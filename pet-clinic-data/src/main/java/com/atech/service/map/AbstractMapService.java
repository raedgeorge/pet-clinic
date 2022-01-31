package com.atech.service.map;

import com.atech.entity.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){

        if(object != null){
            if (object.getId() == null){
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        }
        else {
            throw new RuntimeException();
        }

        return object;
    }

    void delete(T object){
       map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(ID id){
        map.remove(id);
    }

    private Long getNextId(){

        long nextId = 0;

        try {

          nextId = Collections.max(map.keySet()) + 1;
        }
        catch (RuntimeException exception){
            nextId = 1;
        }
        return nextId;
    }
}
