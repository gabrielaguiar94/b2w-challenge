package com.b2w.challenge.services;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.b2w.challenge.dto.PlanetDTO;
import com.b2w.challenge.entities.Planet;
import com.b2w.challenge.repositories.PlanetRepository;
import com.b2w.challenge.services.impl.PlanetsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetServiceTest {

	private PlanetsServiceImpl serv;

	@Mock
	private PlanetRepository repo;

	@Before
	public void setUp() {
		repo = Mockito.mock(PlanetRepository.class);
		serv = new PlanetsServiceImpl(repo);

	}

	@Test
	public void testa_Insere() {
		Planet planeta = new Planet("Bluf", "frozen", "tundra");
		PlanetDTO dto = new PlanetDTO();
		serv.copyDtoToEntity(dto, planeta);
		planeta.setId("Bluf");
		when(repo.save(planeta)).thenReturn(planeta);

		PlanetDTO planetaRetorno = serv.insert(dto);
		Assert.assertEquals(planetaRetorno.getName(), planeta.getName());
	}

//	@Test
//	public void testa_Listar_Todos(PageRequest pageRequest) {
//		Planet planeta1 = new Planet("Teste1", "Teste", "Teste");
//		Planet planeta2 = new Planet("Teste2", "Teste", "Teste");
//		Planet planeta3 = new Planet("Teste3", "Teste", "Teste");
//		Planet planeta4 = new Planet("Teste4", "Teste", "Teste");
//		List<Planet> planetas = new ArrayList<Planet>();
//		planetas.add(planeta1);
//		planetas.add(planeta2);
//		planetas.add(planeta3);
//		planetas.add(planeta4);
//
//		when(repo.findAll()).thenReturn(planetas);
//
//		Page<PlanetDTO> planetasRetorno = serv.findAllPlanets("", pageRequest);
//		Assert.assertEquals(planetasRetorno.get(0).getName(), planeta1.getName());
//	}
//
//	@Test
//	public void testa_Encontra_Por_ID() {
//		Planet planeta1 = new Planet("TesteNovo", "Teste", "Teste");
//		Optional<Planet> planetaOpt = Optional.of(planeta1);
//		planeta1.setId("Teste");
//		when(repo.findById(planeta1.getId())).thenReturn(planetaOpt);
//
//		Planet planetasRetorno = serv.encontraPorId(planeta1.getId());
//		Assert.assertEquals(planetaOpt.get(), planetasRetorno);
//	}
//
//	@Test
//	public void testa_Encontra_Por_ID_Nao_Existente() {
//		try {
//			serv.encontraPorId("");
//		} catch (Exception e) {
//			Assert.assertEquals("Id não encontrada!!", e.getMessage());
//		}
//	}
//
//	@Test
//	public void testa_Listar_Por_Nome() {
//		Planet planeta1 = new Planet("Star Destroyer 1", "Teste", "Teste");
//		Planet planeta2 = new Planet("Star Destroyer 2", "Teste", "Teste");
//		Planet planeta3 = new Planet("Star Destroyer 3", "Teste", "Teste");
//		Planet planeta4 = new Planet("Star Destroyer 4", "Teste", "Teste");
//		List<Planet> planetas = new ArrayList<Planet>();
//		planetas.add(planeta1);
//		planetas.add(planeta2);
//		planetas.add(planeta3);
//		planetas.add(planeta4);
//
//		when(repo.findByNomeContaining("Teste")).thenReturn(planetas);
//
//		List<Planet> planetasRetorno = serv.findByNome("Teste");
//		Assert.assertEquals(planetasRetorno.get(0).getName(), planeta1.getName());
//	}

}
