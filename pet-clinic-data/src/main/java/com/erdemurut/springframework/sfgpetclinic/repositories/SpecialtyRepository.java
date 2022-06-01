package com.erdemurut.springframework.sfgpetclinic.repositories;

import com.erdemurut.springframework.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
