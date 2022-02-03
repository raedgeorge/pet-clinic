package com.atech.service.jpadata;

import com.atech.entity.Owner;
import com.atech.repositories.OwnerRepository;
import com.atech.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("jpadata")
public class OwnerJPAService implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerJPAService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findAll() {

        List<Owner> ownerList = new ArrayList<>();
        List<Owner> finalOwnerList = ownerList;
        ownerRepository.findAll().forEach(finalOwnerList::add);

        return ownerList;
    }

    @Override
    public Owner findById(Long aLong) {

        Optional<Owner> owner = ownerRepository.findById(aLong);

        return owner.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
