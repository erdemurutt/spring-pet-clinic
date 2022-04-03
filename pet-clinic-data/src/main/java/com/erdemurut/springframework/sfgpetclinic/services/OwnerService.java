package com.erdemurut.springframework.sfgpetclinic.services;

import com.erdemurut.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

	Owner findByLastName(String lastName);
}
