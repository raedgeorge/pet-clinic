package com.atech.service;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void save(T object);

    void deleteById(ID id);

    void delete(T object);



}
