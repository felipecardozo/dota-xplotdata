package com.dota.team.pro.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.dota.team.pro.model.matches.single.SingleMatch;

@Service
public class SingleMatchService {
	
	private RestTemplate restTemplate;
	
	private HttpEntity<String> entity;
	private final String URL = "https://api.opendota.com/api/matches/%d";
	
	public SingleMatchService() {
		restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<String>("parameters", headers);
	}
	
	public SingleMatch retrieveMatch(Long matchId) {
		String url = String.format(URL, matchId);
		try{
			ResponseEntity<SingleMatch> response = restTemplate.exchange(url, HttpMethod.GET, entity, SingleMatch.class);
			if( response.hasBody() ) {
				return response.getBody();
			}
		}catch(HttpClientErrorException ex) {
			System.err.println(String.format("error at matchId %d message %s", matchId, ex.getMessage() ));
		}catch(Exception ex) {
			System.err.println(String.format("something bad happended %s", ex.getMessage() ));
		}
		
		
		return new SingleMatch();
	}
	
}
