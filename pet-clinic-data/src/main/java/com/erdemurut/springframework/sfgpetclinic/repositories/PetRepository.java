package com.erdemurut.springframework.sfgpetclinic.repositories;

import com.erdemurut.springframework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
