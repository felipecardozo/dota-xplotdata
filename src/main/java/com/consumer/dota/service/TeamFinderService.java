package com.consumer.dota.service;

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

import com.consumer.dota.model.team.TeamMatch;
import com.consumer.dota.repository.TeamMatchRepository;
import com.google.gson.Gson;

@Service
public class TeamFinderService {

	private RestTemplate restTemplate;
	
	HttpEntity<String> entity;
	
	private final Gson gson = new Gson();
	
	private TeamMatchRepository repository;
	
	private List<TeamMatch> list;
	
	final static List<Long> teams = Arrays.asList(726228L, 2163L, 15L, 5228654L, 838315L,
				1883502L, 39L, 2586976L, 350190L, 36L,
				2672298L, 5027210L, 1375614L, 5066616L, 2108395L,
				543897L, 7118032L);
	
	@Autowired
	public TeamFinderService(TeamMatchRepository repository) {
		this.repository = repository;
		
		restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<String>("parameters", headers);
        
        list = Collections.emptyList();
	}
	
	public List<TeamMatch> findMatchesByTeam() {
		teams.stream().forEach(f->{
			String URL = "https://api.opendota.com/api/teams/"+f+"/matches";
			System.out.println(URL);
			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
			TeamMatch[] matches = gson.fromJson(response.getBody(), TeamMatch[].class);
			
			list = Arrays.asList(matches);
			list.stream().forEach(match -> { match.setTeamId(f); });
			
			repository.saveAll(list);
			
		});
		
		return list;
	}
	
}
