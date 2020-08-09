package com.dota.team.pro.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dota.team.pro.model.matches.TeamMatches;
import com.dota.team.pro.model.teams.Team;
import com.dota.team.pro.repository.TeamMatchesRepository;
import com.dota.team.pro.repository.TeamRepository;
import com.dota.team.pro.service.util.ServiceUtils;

@Service
public class TeamMatchService {
	
	@Autowired
	private TeamMatchesRepository teamMatchesRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	private RestTemplate restTemplate;
	private HttpEntity<String> entity;
	private final String URL = "https://api.opendota.com/api/teams/%s/matches";
	
	public TeamMatchService() {
		restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<String>("parameters", headers);
	}
	
	public List<TeamMatches> insertMatches(){
		List<Team> teams = teamRepository.findAll();
		teams.stream().forEach( team -> {
			String urlFormated = String.format(URL, team.getTeamId());
			ResponseEntity<TeamMatches[]> response =  restTemplate.exchange(urlFormated, HttpMethod.GET, entity, TeamMatches[].class);
			if( response.getStatusCode().is2xxSuccessful() ) {
				List<TeamMatches> matches = Arrays.asList(response.getBody());
				matches.stream().forEach( match -> {
					match.setTeamId(team.getTeamId());
					match.setWin( teamWonGame(match) );
				});
				teamMatchesRepository.saveAll(matches);
			}
			ServiceUtils.sleep();
		} );
		
		return Collections.emptyList();
	}

	private Boolean teamWonGame(TeamMatches match) {
		return match.getRadiant() == match.getRadiantWin();
	}

	public Map<Integer, List<TeamMatches>> findAllMatches() {
		Map<Integer, List<TeamMatches>> allMatches = new HashMap<Integer, List<TeamMatches>>();
		List<Team> teams = teamRepository.findAll();
		
		List<Integer> teamIds = teams.stream().map(Team::getTeamId).collect(Collectors.toList());
		
		//teams.stream().forEach( team -> {teamIds.add(team.getTeamId());} );
		
		teamIds.stream().forEach( ID ->  allMatches.put(ID, teamMatchesRepository.findByTeamId(ID)) );
		
		return allMatches;
	}
	

}
