package com.b2w.challenge.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.b2w.challenge.dto.PlanetDTO;

public interface IPlanetsService {

//	List<PlanetDTO> findAllPlanets();

	Page<PlanetDTO> findAllPlanets(String name, PageRequest pageRequest);

	PlanetDTO findById(String id);

	PlanetDTO insert(PlanetDTO dto);

	void delete(String id);

}
