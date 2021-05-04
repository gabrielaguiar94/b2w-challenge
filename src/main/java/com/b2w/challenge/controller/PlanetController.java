package com.b2w.challenge.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.b2w.challenge.dto.PlanetDTO;
import com.b2w.challenge.entities.PlanetApiSw;
import com.b2w.challenge.services.impl.PlanetsServiceImpl;
import com.b2w.challenge.services.impl.SwApiRestTemplate;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/planets")
public class PlanetController {

	@Autowired
	private PlanetsServiceImpl service;

	@Autowired
	private SwApiRestTemplate swapi;

	private Calendar startTime = Calendar.getInstance();

	private List<PlanetApiSw> result = new ArrayList<PlanetApiSw>();

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Returns a paginated list of planets, to search by name, inform with the name parameter "),
			@ApiResponse(code = 404, message = "Planet not Found")
	})
	@GetMapping
	public ResponseEntity<Page<PlanetDTO>> findAllPaged(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return ResponseEntity.ok().body(insertAppearances(service.findAllPlanets(name.trim(), pageRequest)));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PlanetDTO> findById(@PathVariable String id) {
		PlanetDTO planet = service.findById(id);
		this.result = cacheImplements(this.result, startTime);
		return ResponseEntity.ok().body(new PlanetDTO(planet.getId(), planet.getName(), planet.getClimate(),
				planet.getTerrain(), findAppearances(result, planet)));
	}

	@PostMapping
	public ResponseEntity<PlanetDTO> insert(@RequestBody PlanetDTO dto) {

		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PlanetDTO> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	private Page<PlanetDTO> insertAppearances(Page<PlanetDTO> list) {
		this.result = cacheImplements(this.result, startTime);

		return list.map(x -> (new PlanetDTO(x.getId(), x.getName(), x.getClimate(), x.getTerrain(),
				findAppearances(result, x))));
	}

	private int findAppearances(List<PlanetApiSw> result, PlanetDTO x) {
		for (PlanetApiSw y : result) {
			if (x.getName().equals(y.getName())) {
				return y.getFilms().size();
			}
		}
		return 0;
	}

	private List<PlanetApiSw> cacheImplements(List<PlanetApiSw> result, Calendar startTime) {
		Calendar atual = Calendar.getInstance();
		Calendar horaComparar = (Calendar) startTime.clone();
		horaComparar.add(Calendar.HOUR_OF_DAY, 1);
		if (result.isEmpty()) {
			result = swapi.returnAppearances().getBody().getResults();
		}
		if (atual.after(horaComparar)) {
			result = swapi.returnAppearances().getBody().getResults();
			startTime = Calendar.getInstance();
		}
		return result;
	}

}
