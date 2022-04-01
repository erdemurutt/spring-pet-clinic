package com.erdemurut.springframework.sfgpetclinic.services;

import com.erdemurut.springframework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
	Pet findById(Long id);

	Pet save(Pet pet);

	Set<Pet> findAll();
}
