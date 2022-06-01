package com.erdemurut.springframework.sfgpetclinic.services.map;

import com.erdemurut.springframework.sfgpetclinic.model.Speciality;
import com.erdemurut.springframework.sfgpetclinic.model.Vet;
import com.erdemurut.springframework.sfgpetclinic.services.SpecialtyService;
import com.erdemurut.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
	private final SpecialtyService specialtyService;

	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}

	@Override
	public Vet save(Vet object) {
		if (!object.getSpecialities().isEmpty()) {
			object.getSpecialities().forEach(speciality -> {
				if (speciality.getId() == null) {
					Speciality savedSpecialty = specialtyService.save(speciality);
					speciality.setId(savedSpecialty.getId());
				}
			});
		}
		return super.save(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
}
