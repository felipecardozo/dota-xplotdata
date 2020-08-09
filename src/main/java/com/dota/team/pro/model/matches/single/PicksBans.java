package com.dota.team.pro.model.matches.single;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PicksBans {
	
	@JsonProperty("is_pick")
	private Boolean isPick;
	
	@JsonProperty("hero_id")
	private Integer hero_id;
	
	private Integer team;
	
	private Integer order;
	
	private Integer ord;

}
