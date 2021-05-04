package com.b2w.challenge.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.b2w.challenge.entities.ResultApiSw;
import com.b2w.challenge.services.impl.SwApiRestTemplate;

public class SwApiRestTemplateTest {

	@Autowired
	SwApiRestTemplate rest;

	@Before
	public void setUp() {
		rest = new SwApiRestTemplate();
	}

	@Test
	public void testa_Retorna_Aparicoes() {
		ResponseEntity<ResultApiSw> result = rest.returnAppearances();
		Assert.assertNotNull(result);
	}
}