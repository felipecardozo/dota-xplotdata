package com.dota.team.pro.model.matches.single;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamFightsPlayers {
	
	@JsonProperty("deaths_pos")
	private Map<String, Map<String, Integer>> deathsPos;
	
	@JsonProperty("ability_uses")
	private Map<String, Integer> abilityUses;
	
	@JsonProperty("ability_targets")
	private Map<String, Map<String, Integer>> abilityTargets;
	
	@JsonProperty("item_uses")
	private Map<String, Integer> itemUses;
	
	@JsonProperty("killed")
	private Map<String, Integer> killed;
	
	private Integer deaths;
	
	private Integer buybacks;
	
	private Integer damage;
	
	private Integer healing;
	
	@JsonProperty("gold_delta")
	private Integer goldDelta;
	
	@JsonProperty("xp_delta")
	private Integer xpDelta;
	
	@JsonProperty("xp_start")
	private Integer xpStart;
	
	@JsonProperty("xp_end")
	private Integer xpEnd;
	
}
