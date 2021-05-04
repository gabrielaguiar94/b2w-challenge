package com.b2w.challenge.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.b2w.challenge.entities.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String> {

//	@Query(value = "SELECT obj FROM Planet obj WHERE (LOWER(obj.name) LIKE LOWER(CONCAT('.*', :name, '.*')) ) ")
//	Page<Planet> findAllPlanets(String name, Pageable pageable);

	@Query("{ 'name' : /.*?0.*/ }")
	Page<Planet> findAllPlanets(String name, Pageable pageable);

	Page<Planet> findByName(String name, Pageable pageable);
}