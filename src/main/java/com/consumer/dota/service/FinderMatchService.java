package com.consumer.dota.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.consumer.dota.Constants;
import com.consumer.dota.model.ProMatch;
import com.consumer.dota.model.TeamPlayerVanilla;
import com.consumer.dota.repository.ProMatchRepository;
import com.google.gson.Gson;

@Service
public class FinderMatchService {

	@Autowired
	private TransformerService transformerService;
	final static Gson gson = new Gson();	
	private RestTemplate restTemplate;
	
	@Autowired
	private ProMatchRepository proMatchRepository;
	
	public FinderMatchService() {
		restTemplate = new RestTemplate();
	}
	
	public List<ProMatch> findAndInsertmatches(){
		List<ProMatch> matches = new ArrayList<>();
		List<TeamPlayerVanilla> players = transformerService.loadProPlayersIds();
		
		for( TeamPlayerVanilla player : players ) {
			String response = restTemplate.getForObject(Constants.URL+Constants.PATH_PLAYERS+"/"+player.getAccountId()+Constants.PATH_MATCHES, String.class);
			ProMatch[] matchesArray = gson.fromJson(response, ProMatch[].class);
			addExtraProperties(matchesArray, player.getAccountId());
			insertMatches(matchesArray);
			matches.addAll(Arrays.asList(matchesArray));
			System.out.println("saved " + player + " total matches " + matchesArray.length);
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		
		return matches;
	}

	private void insertMatches(ProMatch[] matchesArray) {
		for(ProMatch match : matchesArray) {
			proMatchRepository.save(match);
		}
	}

	private void addExtraProperties(ProMatch[] matchesArray, Long accountId) {
		for( ProMatch match : matchesArray ) {
			match.setPlayerId(accountId);
			match.setIsProcessed(Boolean.FALSE);
		}
	}

}
