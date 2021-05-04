package com.b2w.challenge.services.impl;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.b2w.challenge.entities.ResultApiSw;
import com.b2w.challenge.services.exceptions.ServiceUnavailable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class SwApiRestTemplate {

	final static String url = "https://swapi.dev/api/planets/";

	RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<ResultApiSw> returnAppearances() {
		try {
			return restTemplate.exchange(url, HttpMethod.GET, getHeader(), ResultApiSw.class);
		} catch (Exception e) {
			throw new ServiceUnavailable("SWAPI out of service");
		}
	}

	public HttpEntity<String> getHeader() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		return entity;
	}

}
