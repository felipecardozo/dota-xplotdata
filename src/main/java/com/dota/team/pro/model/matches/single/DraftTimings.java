package com.dota.team.pro.model.matches.single;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DraftTimings {
	
	private Integer order;
	
	private Boolean pick;
	
	@JsonProperty("active_team")
	private Integer active_team;
	
	@JsonProperty("hero_id")
	private Integer hero_id;
	
	@JsonProperty("player_slot")
	private Integer player_slot;
	
	@JsonProperty("extra_time")
	private Integer extra_time;
	
	@JsonProperty("total_time_taken")
	private Integer total_time_taken;

}
