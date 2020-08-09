package com.dota.team.pro.model.matches.single;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamFights {
	
	private Integer start;
	
	private Integer end;
	
	@JsonProperty("last_death")
	private Integer lastDeath;
	
	private Integer deaths;
	
	@JsonProperty("players")
	private List<TeamFightsPlayers> players;

}
