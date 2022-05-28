package com.erdemurut.springframework.sfgpetclinic.services.map;

import com.erdemurut.springframework.sfgpetclinic.model.Speciality;
import com.erdemurut.springframework.sfgpetclinic.services.SpecialtiesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialtiesMapService extends AbstractMapService<Speciality, Long> implements SpecialtiesService {
	@Override
	public Set<Speciality> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Speciality object) {
		super.delete(object);
	}

	@Override
	public Speciality save(Speciality object) {
		return super.save(object);
	}

	@Override
	public Speciality findById(Long id) {
		return super.findById(id);
	}
}
