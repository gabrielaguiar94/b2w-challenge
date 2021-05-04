package com.b2w.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.b2w.challenge.entities.Planet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class B2wChallengeApplicationTests {

	protected final String BASE_URL = "http://localhost:";

	@LocalServerPort
	private int port;

	RestTemplate rest;

	@Before
	public void setUp() {
		rest = new RestTemplate();
	}

	@Test
	public void insertShouldReturnObjectPlanetWhenBodyRequestExists() {

		Planet planeta = new Planet("Luke", "temperate", "jungle");
		ResponseEntity<String> response = rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);//
		Assert.assertEquals(201, response.getStatusCodeValue());
		rest.delete(response.getHeaders().getLocation());

	}

	@Test
	public void findShouldThrowExceptionBadRequestWhenNameIsEmpty() {
		Planet planeta = new Planet("", "temperate", "airless asteroid");
		try {
			rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400", e.getMessage().substring(0, 3));
		}
	}

	@Test
	public void findShouldThrowExceptionBadRequestWhenClimateIsEmpty() {
		Planet planeta = new Planet("Mygeeto", "", "airless asteroid");
		try {
			rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400", e.getMessage().substring(0, 3));
		}
	}

	@Test
	public void findShouldThrowExceptionBadRequestWhenTerrainIsEmpty() {
		Planet planeta = new Planet("Yoda", "Teste", "");
		try {
			rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400", e.getMessage().substring(0, 3));
		}
	}

	@Test
	public void findShouldThrowExceptionBadRequestWhenNameIsNull() {
		Planet planeta = new Planet(null, "Teste", "Teste");
		try {
			rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400", e.getMessage().substring(0, 3));
		}
	}

	@Test
	public void findShouldThrowExceptionBadRequestWhenClimateIsNull() {
		Planet planeta = new Planet("Chewbacca", null, "Teste");
		try {
			rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400", e.getMessage().substring(0, 3));
		}
	}

	@Test
	public void findShouldThrowExceptionBadRequestWhenTerrainIsNull() {
		Planet planeta = new Planet("Han Solo", "Teste", null);
		try {
			rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400", e.getMessage().substring(0, 3));
		}
	}

	@Test
	public void findShouldReturnSearchById() {
		Planet planeta = new Planet("Léia", "Teste", "Teste");
		ResponseEntity<String> response = rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);

		ResponseEntity<String> respostaBusca = rest.getForEntity(response.getHeaders().getLocation(), String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());

		rest.delete(response.getHeaders().getLocation());
	}

	@Test
	public void findShouldThrowNotFoundWhenNameNotExists() {
		try {
			rest.getForEntity(BASE_URL + port + "planets?name=Teste", String.class);
		} catch (Exception e) {
			Assert.assertEquals("404", e.getMessage().substring(0, 3));
		}
	}

	@Test
	public void findShouldReturnSearchByName() {
		Planet planeta = new Planet("Kamino", "temperate, arid", "ock, desert, mountain, barren");

		ResponseEntity<String> response = rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);
		ResponseEntity<String> respostaBusca = rest.getForEntity(BASE_URL + port + "/planets?name=Kamino", String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());

		rest.delete(response.getHeaders().getLocation());

	}	

	@Test
	public void findShouldReturnListOfPlanets() {
		Planet planeta1 = new Planet("Utapau", "temperate, arid, windy", "scrublands, savanna, canyons, sinkholes");
		Planet planeta2 = new Planet("Mustafar", "hot", "volcanoes, lava rivers, mountains, caves");		
		
		List<Planet> planetas = new ArrayList<Planet>();		
		planetas.add(planeta1);
		planetas.add(planeta2);
		
		ResponseEntity<String> response = rest.getForEntity(BASE_URL + port + "/planets", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		Planet planeta = new Planet("Obi-Wan", "temperate", "jungle");
		ResponseEntity<String> response = rest.postForEntity(BASE_URL + port + "/planets/", planeta, String.class);

		ResponseEntity<String> respostaBusca = rest.exchange(response.getHeaders().getLocation().toString(),
				HttpMethod.DELETE, createHeader(), String.class, planeta);
		Assert.assertEquals(204, respostaBusca.getStatusCodeValue());
	}

	@Test
	public void deleteShouldThrowWhenIdNotExists() {
		try {
			rest.exchange(BASE_URL + port + "/planets/" + "Teste", HttpMethod.DELETE, createHeader(), String.class);
		} catch (Exception e) {
			Assert.assertEquals("404", e.getMessage().substring(0, 3));
		}
	}

	private HttpEntity<String> createHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
}
