package com.b2w.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.b2w.challenge.entities.Planet;
import com.b2w.challenge.repositories.PlanetRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class B2wChallengeApplication implements CommandLineRunner {

	@Autowired
	private PlanetRepository planetrepo;

	public static void main(String[] args) {
		SpringApplication.run(B2wChallengeApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		Planet planeta;
		log.info("Encontrando planetas ou gerando uma galáxia muito, muito distante...");
		log.info("-------------------------------");
		if (planetrepo.count() == 0) {
			planeta = planetrepo.save(new Planet());
			planetrepo.deleteById(planeta.getId());
		}

	}

}
