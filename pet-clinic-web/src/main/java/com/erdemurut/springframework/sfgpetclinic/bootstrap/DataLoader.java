package com.erdemurut.springframework.sfgpetclinic.bootstrap;

import com.erdemurut.springframework.sfgpetclinic.model.Owner;
import com.erdemurut.springframework.sfgpetclinic.model.Vet;
import com.erdemurut.springframework.sfgpetclinic.services.OwnerService;
import com.erdemurut.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;

	public DataLoader(OwnerService ownerService, VetService vetService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner();
		owner1.setFirstName("erdem");
		owner1.setLastName("ürüt");

		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Betül");
		owner2.setLastName("ürüt");

		ownerService.save(owner2);

		System.out.println("Loaded Owners");

		Vet vet1 = new Vet();
		vet1.setFirstName("şeyma");
		vet1.setLastName("kızılkaya");

		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("ahmet");
		vet2.setLastName("mehmet");

		vetService.save(vet2);

		System.out.println("Loaded Vets");
	}
}
