package com.atech.repositories;

import com.atech.entity.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Integer> {
}
