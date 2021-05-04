package com.b2w.challenge.repository;

import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.b2w.challenge.entities.Planet;
import com.b2w.challenge.repositories.PlanetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetRepositoryTest {

	Planet planeta1, planeta2;

	@Autowired
	PlanetRepository repository;

	@Before
	public void setUp() {

		planeta1 = repository.save(new Planet("Boba Fett", "teste", "teste"));
		planeta2 = repository.save(new Planet("Lando", "teste", "teste"));
	}

	@After
	public void tearDown() {

		repository.delete(planeta1);
		repository.delete(planeta2);
	}

	@Test
	public void testa_criar_planeta() {

		Planet planeta = repository.save(new Planet("R2", "teste", "teste"));
		Assert.assertFalse(planeta.getId().isEmpty());
		repository.delete(planeta);
	}

	@Test
	public void testa_buscar_por_nome(Pageable pageable) {

		Page<Planet> result = repository.findAllPlanets("Boba Fett", pageable);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testa_buscar_por_id() {

		Optional<Planet> obj = repository.findById("Boba Fett");
		Assert.assertNotNull(obj);
	}

	@Test
	public void testa_buscar_todos_planetas(Pageable pageable) {

		Page<Planet> result = repository.findAllPlanets("", pageable);

		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testa_deletar_planeta(Pageable pageable) {

		Page<Planet> planeta = repository.findAllPlanets("Lando", pageable);
//		repository.delete(planeta.get(0));
		Page<Planet> response = repository.findAllPlanets("Lando", pageable);
		Assert.assertTrue(response.isEmpty());

		Assert.assertFalse(planeta.isEmpty());

	}
}