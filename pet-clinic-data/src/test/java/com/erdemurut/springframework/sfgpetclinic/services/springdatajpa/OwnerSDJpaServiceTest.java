package com.erdemurut.springframework.sfgpetclinic.services.springdatajpa;

import com.erdemurut.springframework.sfgpetclinic.model.Owner;
import com.erdemurut.springframework.sfgpetclinic.repositories.OwnerRepository;
import com.erdemurut.springframework.sfgpetclinic.repositories.PetRepository;
import com.erdemurut.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

	public static final String LAST_NAME = "Ürüt";
	@Mock
	OwnerRepository ownerRepository;

	@Mock
	PetRepository petRepository;

	@Mock
	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerSDJpaService service;

	Owner returnOwner;

	@BeforeEach
	void setUp() {
		returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
	}

	@Test
	void findAll() {
		Set<Owner> returnOwnersSet = new HashSet<>();
		returnOwnersSet.add(Owner.builder().id(1L).build());
		returnOwnersSet.add(Owner.builder().id(2L).build());

		when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

		Set<Owner> owners = service.findAll();

		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void findById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

		Owner owner = service.findById(1L);

		assertNotNull(owner);
	}

	@Test
	void findByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

		Owner owner = service.findById(1L);

		assertNull(owner);
	}

	@Test
	void save() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);

		verify(ownerRepository).save(any());
	}

	@Test
	void delete() {
		service.delete(returnOwner);

		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void deleteById() {
		service.deleteById(1L);

		verify(ownerRepository).deleteById(anyLong());
	}

	@Test
	void findByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

		Owner urut = service.findByLastName(LAST_NAME);

		assertEquals(LAST_NAME, urut.getLastName());
	}
}