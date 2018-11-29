package com.consumer.dota.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.consumer.dota.model.TeamPlayer;
import com.google.gson.Gson;

@Service
public class FindTeamMembersService {
	
	@Value("#{'${pro.teams.ids}'.split(',')}")
	public List<Integer> teamIds;
	
	private RestTemplate restTemplate;
	
	final static Gson gson = new Gson();
	
	public FindTeamMembersService() {
		restTemplate = new RestTemplate();
	}
	
	public List<TeamPlayer> findTeamMembersByTeamId(){
		List<TeamPlayer> players = new ArrayList<>();
		
		for( Integer id : teamIds ) {
			String URL  = "https://api.opendota.com/api/teams/"+id+"/players";
			String  json = restTemplate.getForObject(URL, String.class);
			TeamPlayer[] playersArr = gson.fromJson(json, TeamPlayer[].class);
			List<TeamPlayer> validPlayers = getValidTeamPlayers(playersArr);
			addTeamId(validPlayers, id);
			players.addAll(validPlayers);
			System.out.println(validPlayers);
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
		
		return players;
	}

	private void addTeamId(List<TeamPlayer> validPlayers, Integer id) {
		validPlayers.forEach( f -> f.setTeamId(id) );
	}

	private List<TeamPlayer> getValidTeamPlayers(TeamPlayer[] playersArr) {
		List<TeamPlayer> valid = new ArrayList<>();
		for( TeamPlayer player : playersArr ) {
			if( player.getIsCurrentTeamMember() == Boolean.TRUE  ) {
				valid.add(player);
			}
		}
		return valid;
	}

}
