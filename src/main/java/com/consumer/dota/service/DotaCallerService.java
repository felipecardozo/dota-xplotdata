package com.consumer.dota.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
	
	private static final String APP_KEY = "";
	
	private HttpEntity<String> entity;
	
	final static Gson gson = new Gson();
	
	public DotaCallerService() {
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<String>("parameters", headers);
		restTemplate = new RestTemplate();
	}
	
	public Match getMatch(String match) {
		ResponseEntity<String> response = restTemplate.getForEntity(Constants.URL+"/matches/"+match+"?"+DotaCallerService.APP_KEY, String.class);
		Match matchEntity = DotaCallerService.gson.fromJson(response.getBody(), Match.class);
		return matchEntity;
	}
	
	public List<ProPlayer> getProPlayers(){
		ResponseEntity<String> response = restTemplate.exchange(Constants.URL+"/proplayers/", HttpMethod.GET, entity, String.class);
		ProPlayer[] players = DotaCallerService.gson.fromJson(response.getBody(), ProPlayer[].class);
		return Arrays.asList(players);
	}

}
