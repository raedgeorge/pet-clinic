package com.atech.service;

import com.atech.entity.Owner;

public interface OwnerService extends CrudService<Owner, Integer> {

    Owner findByLastName(String lastName);

}
