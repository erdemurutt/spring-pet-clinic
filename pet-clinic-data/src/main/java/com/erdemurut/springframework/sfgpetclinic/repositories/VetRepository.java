package com.erdemurut.springframework.sfgpetclinic.repositories;

import com.erdemurut.springframework.sfgpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
