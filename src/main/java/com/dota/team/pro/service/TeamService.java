package com.dota.team.pro.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dota.team.pro.model.teams.Team;
import com.dota.team.pro.repository.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepository;
	
	private RestTemplate restTemplate;
	private HttpEntity<String> entity;
	private final String URL = "https://api.opendota.com/api/teams";
	
	public TeamService() {
		restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<String>("parameters", headers);
	}
	
	
	public List<Team> insertTeams() {
		ResponseEntity<Team[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Team[].class);
		if ( response.getStatusCode().is2xxSuccessful() ) {
			List<Team> teams = Arrays.asList(response.getBody());
			teamRepository.saveAll(teams);
			
			return teams;
		}
		return Collections.emptyList();
	}
	

}
