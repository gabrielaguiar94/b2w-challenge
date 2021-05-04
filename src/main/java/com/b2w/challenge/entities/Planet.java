package com.b2w.challenge.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@Document
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "name", "climate", "terreain" })
//@Table(name = "tb_planets")
public class Planet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@JsonProperty(required = true)
	private String name;

	@JsonProperty(required = true)
	private String climate;

	@JsonProperty(required = true)
	private String terrain;

	public Planet(String name, String climate, String terrain) {
//		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getClimate() {
//		return climate;
//	}
//
//	public void setClimate(String climate) {
//		this.climate = climate;
//	}
//
//	public String getTerrain() {
//		return terrain;
//	}
//
//	public void setTerrain(String terrain) {
//		this.terrain = terrain;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Planet other = (Planet) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}

}