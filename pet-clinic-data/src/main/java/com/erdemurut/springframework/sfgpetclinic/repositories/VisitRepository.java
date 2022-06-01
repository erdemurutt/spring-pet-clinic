package com.erdemurut.springframework.sfgpetclinic.repositories;

import com.erdemurut.springframework.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
