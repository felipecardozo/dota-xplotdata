package com.dota.team.pro.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dota.team.pro.model.matches.TeamMatches;
import com.dota.team.pro.model.matches.single.SingleMatch;
import com.dota.team.pro.model.teams.Team;
import com.dota.team.pro.repository.SingleMatchRepository;
import com.dota.team.pro.repository.TeamMatchesRepository;
import com.dota.team.pro.repository.TeamRepository;
import com.dota.team.pro.service.util.ServiceUtils;

@Service
public class CoreService {
	
	private TeamRepository teamRepository;
	private TeamMatchesRepository teamMatchesRepository;
	private SingleMatchRepository singleMatchRepository;
	
	private RestTemplate restTemplate;
	private HttpEntity<String> entity;
	
	@SuppressWarnings("unused")
	private List<SingleMatch> matches;
	
	private String URL = "https://api.opendota.com/api/matches/%d";
	
	public CoreService(TeamRepository teamRepository, TeamMatchesRepository teamMatchesRepository, SingleMatchRepository singleMatchRepository) {
		this.teamMatchesRepository = teamMatchesRepository;
		this.teamRepository = teamRepository;
		this.singleMatchRepository = singleMatchRepository;
		restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<String>("parameters", headers);
        
        matches = new ArrayList<>();
	}
	
	public List<List<SingleMatch>> insertSingleMatches() {
		List<Team> teams = teamRepository.findAll();
		List<Integer> teamIds = teams.stream().map(Team::getTeamId).collect(Collectors.toList());
		
		//collect all matches to store in the collection
		return teamIds.stream()
				.map( id -> teamMatchesRepository.findByTeamIdAndByIsProcessed(id, false) )
				.collect(Collectors.toList())
				.stream()
				.map(this::processTeamMatches)
				.collect(Collectors.toList())
				.stream()
				.map(this::callEndPointAndInsertToCollection).collect(Collectors.toList());
		
		//System.out.println(teamIds);
		
	}
	
	public List<Long> processTeamMatches( List<TeamMatches> teamMatches ) {
		return teamMatches.stream().map(TeamMatches::getMatchId ).collect(Collectors.toList());
	}
	
	public List<SingleMatch> callEndPointAndInsertToCollection(List<Long> IDs) {
		return IDs.stream().map( matchId -> callOpenDota(matchId) ).collect(Collectors.toList());
	}
	
	public void callEndPointAndInsertToCollection2(List<List<Long>> IDs) {
		
		IDs.stream().forEach( team -> {
			team.stream().map( matchId -> callOpenDota(matchId) );
		} );
			
	}

	private SingleMatch callOpenDota(Long matchId) {
		ResponseEntity<SingleMatch> response = restTemplate.exchange(String.format(URL, matchId), HttpMethod.GET, entity, SingleMatch.class);
		ServiceUtils.sleep();
		if( response.hasBody() ) {
			SingleMatch singleMatch = response.getBody();
			singleMatchRepository.save(singleMatch);
			
			TeamMatches teamMatch = teamMatchesRepository.findByMatchIdAndIsProcessed(matchId, false)
														 .stream()
														 .findFirst()
														 .get();
			teamMatch.setProcessed(true);
			teamMatchesRepository.save(teamMatch);
			
			return singleMatch;
		}
		return new SingleMatch();
	}
	

}
