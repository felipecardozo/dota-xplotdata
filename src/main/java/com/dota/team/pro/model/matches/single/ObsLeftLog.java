package com.dota.team.pro.model.matches.single;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObsLeftLog {
	
	private Integer time;
	
	private String type;
	
	private Integer slot;
	
	@JsonProperty("attackername")
	private String attackerName;
	
	private Integer x;
	
	private Integer y;
	
	private Integer z;
	
	@JsonProperty("entityleft")
	private Boolean entityLeft;
	
	private Long ehandle;
	
	@JsonProperty("player_slot")
	private Integer playerSlot;

}
