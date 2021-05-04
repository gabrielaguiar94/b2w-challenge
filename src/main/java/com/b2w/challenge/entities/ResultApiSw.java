package com.b2w.challenge.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultApiSw {

	private List<PlanetApiSw> results;

	public ResultApiSw(List<PlanetApiSw> results, String name) {
		this.results = results;
	}

}
