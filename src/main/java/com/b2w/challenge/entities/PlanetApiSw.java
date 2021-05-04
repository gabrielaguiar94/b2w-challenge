package com.b2w.challenge.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetApiSw {

	private String name;

	private List<String> films;

	public PlanetApiSw(String name, List<String> films) {
		this.name = name;
		this.films = films;
	}

}
