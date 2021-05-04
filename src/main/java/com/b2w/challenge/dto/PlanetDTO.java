package com.b2w.challenge.dto;

import com.b2w.challenge.entities.Planet;

import lombok.Data;

@Data
public class PlanetDTO {

	private String id;
	private String name;
	private String climate;
	private String terrain;
	private Integer appearances;

	public PlanetDTO() {
	}

	public PlanetDTO(String id, String name, String climate, String terrain, Integer appearances) {
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.appearances = appearances;
	}

	public PlanetDTO(Planet entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.climate = entity.getClimate();
		this.terrain = entity.getTerrain();

	}

}
