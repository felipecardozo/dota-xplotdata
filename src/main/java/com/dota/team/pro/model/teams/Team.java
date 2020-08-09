package com.dota.team.pro.model.teams;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Team {
	
	@Id
	private String id;
	
	@JsonProperty("team_id")
	@Field("teamId")
	private Integer teamId;
	
	private Integer wins;
	
	private Integer losses;
	
	private String name;
	

}
