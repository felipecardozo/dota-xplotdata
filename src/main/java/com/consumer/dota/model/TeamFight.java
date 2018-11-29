package com.consumer.dota.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TeamFight {
	
	@SerializedName("deaths")
	private int deaths;
	
	@SerializedName("players")
	private List<Player> players;

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	
}
