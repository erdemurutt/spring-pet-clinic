package com.erdemurut.springframework.sfgpetclinic.repositories;

import com.erdemurut.springframework.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
