package com.erdemurut.springframework.sfgpetclinic.bootstrap;

import com.erdemurut.springframework.sfgpetclinic.model.Owner;
import com.erdemurut.springframework.sfgpetclinic.model.Pet;
import com.erdemurut.springframework.sfgpetclinic.model.PetType;
import com.erdemurut.springframework.sfgpetclinic.model.Speciality;
import com.erdemurut.springframework.sfgpetclinic.model.Vet;
import com.erdemurut.springframework.sfgpetclinic.services.OwnerService;
import com.erdemurut.springframework.sfgpetclinic.services.PetTypeService;
import com.erdemurut.springframework.sfgpetclinic.services.SpecialtyService;
import com.erdemurut.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;

	public DataLoader(OwnerService ownerService, VetService vetService,
					  PetTypeService petTypeService, SpecialtyService specialtyService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}

	@Override
	public void run(String... args) throws Exception {
		int count = petTypeService.findAll().size();

		if (count == 0) {
			loadData();
		}
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);

		Speciality raidology = new Speciality();
		raidology.setDescription("Radiology");
		Speciality savedRadiology = specialtyService.save(raidology);

		Speciality surgery = new Speciality();
		surgery.setDescription("Surgery");
		Speciality savedSurgery = specialtyService.save(surgery);

		Speciality dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		Speciality savedDentistry = specialtyService.save(dentistry);

		Owner owner1 = new Owner();
		owner1.setFirstName("erdem");
		owner1.setLastName("ürüt");
		owner1.setAddress("123 kağıthane");
		owner1.setCity("istanbul");
		owner1.setTelephone("12312312");

		Pet erdemsPet = new Pet();
		erdemsPet.setPetType(savedDogPetType);
		erdemsPet.setOwner(owner1);
		erdemsPet.setBirthDate(LocalDate.now());
		erdemsPet.setName("Rosco");
		owner1.getPets().add(erdemsPet);
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Betül");
		owner2.setLastName("ürüt");
		owner2.setAddress("456 şişli");
		owner2.setCity("istanbul");
		owner2.setTelephone("55555555");

		Pet betulsCat = new Pet();
		betulsCat.setPetType(savedCatPetType);
		betulsCat.setOwner(owner2);
		betulsCat.setBirthDate(LocalDate.now());
		betulsCat.setName("Pikachu");
		owner2.getPets().add(betulsCat);
		ownerService.save(owner2);

		System.out.println("Loaded Owners");

		Vet vet1 = new Vet();
		vet1.setFirstName("şeyma");
		vet1.setLastName("kızılkaya");
		vet1.getSpecialities().add(savedRadiology);

		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("ahmet");
		vet2.setLastName("mehmet");
		vet2.getSpecialities().add(savedSurgery);
		vetService.save(vet2);

		System.out.println("Loaded Vets");
	}
}
