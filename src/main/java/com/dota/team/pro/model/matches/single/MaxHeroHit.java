package com.dota.team.pro.model.matches.single;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaxHeroHit {
	
	private String type;
	
	private Integer time;
	
	private Boolean max;
	
	private String inflictor;
	
	private String unit;
	
	private String key;
	
	private Integer value;
	
	private Integer slot;
	
	@JsonProperty("player_slot")
	private Integer playerSlot;

}
