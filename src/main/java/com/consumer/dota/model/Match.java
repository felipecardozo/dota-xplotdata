package com.consumer.dota.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Match {
	
	@SerializedName("match_id")
	private String matchId;
	
	@SerializedName("players")
	private List<PlayerMatch> players;

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public List<PlayerMatch> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerMatch> players) {
		this.players = players;
	}
	
}
