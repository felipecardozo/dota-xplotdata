package com.consumer.dota.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.consumer.dota.Constants;
import com.consumer.dota.model.Match;
import com.consumer.dota.model.ProPlayer;
import com.google.gson.Gson;

@Service
public class DotaCallerService {
	
	private RestTemplate restTemplate;
	
	private static final String APP_KEY = "api_key=279dfbb6-d331-4fd0-8257-edfaf80a6dbe";
	
	final static Gson gson = new Gson();
	
	public DotaCallerService() {
		restTemplate = new RestTemplate();
	}
	
	public Match getMatch(String match) {
		ResponseEntity<String> response = restTemplate.getForEntity(Constants.URL+"/matches/"+match+"?"+DotaCallerService.APP_KEY, String.class);
		Match matchEntity = DotaCallerService.gson.fromJson(response.getBody(), Match.class);
		return matchEntity;
	}
	
	public List<ProPlayer> getProPlayers(){
		String response = restTemplate.getForObject(Constants.URL+"/proplayers/", String.class);
		ProPlayer[] players = DotaCallerService.gson.fromJson(response, ProPlayer[].class);
		return Arrays.asList(players);
	}

}
