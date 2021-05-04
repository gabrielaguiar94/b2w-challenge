package com.b2w.challenge.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.b2w.challenge.dto.PlanetDTO;
import com.b2w.challenge.entities.Planet;
import com.b2w.challenge.repositories.PlanetRepository;
import com.b2w.challenge.services.IPlanetsService;
import com.b2w.challenge.services.exceptions.BadRequest;
import com.b2w.challenge.services.exceptions.ObjectNotFoundException;

@Service
public class PlanetsServiceImpl implements IPlanetsService {

	@Autowired
	private PlanetRepository repository;

	@Autowired
	public PlanetsServiceImpl(PlanetRepository repo) {
		this.repository = repo;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<PlanetDTO> findAllPlanets(String name, PageRequest pageRequest) {

		Page<Planet> list = repository.findAllPlanets(name, pageRequest);
		if(list.getNumberOfElements() == 0) {
			throw new ObjectNotFoundException("Planet not Found");
		}
		return list.map(x -> new PlanetDTO(x));
	}

	@Transactional(readOnly = true)
	@Override
	public PlanetDTO findById(String id) {
		Optional<Planet> planets = repository.findById(id);
		Planet entity = planets.orElseThrow(() -> new ObjectNotFoundException("Entity not found"));

		return new PlanetDTO(entity);
	}

	@Transactional
	public PlanetDTO insert(PlanetDTO dto) {
		Planet entity = new Planet();
		copyDtoToEntity(dto, entity);
		verificaConteudo(entity);
		entity = repository.save(entity);

		return new PlanetDTO(entity);

	}

	public void delete(String id) {

		repository.deleteById(id);
	}

	public void copyDtoToEntity(PlanetDTO dto, Planet entity) {
		entity.setName(dto.getName());
		entity.setClimate(dto.getClimate());
		entity.setTerrain(dto.getTerrain());
	}

	public Planet verificaConteudo(Planet obj) {
		try {
			if (obj.getName().isEmpty() || obj.getName().equals(null)) {
				throw new BadRequest("Campo nome vazio");
			}
			if (obj.getClimate().isEmpty() || obj.getClimate().equals(null)) {
				throw new BadRequest("Campo clima vazio");
			}
			if (obj.getTerrain().isEmpty() || obj.getTerrain().equals(null)) {
				throw new BadRequest("Campo terreno vazio");
			}
		} catch (Exception e) {

			throw new BadRequest("Erro ao inserir null");
		}
		return obj;

	}

}