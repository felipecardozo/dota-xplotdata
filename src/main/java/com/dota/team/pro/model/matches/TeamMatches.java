package com.dota.team.pro.model.matches;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamMatches {
	
	@Id
	private String id;
	
	@JsonProperty("match_id")
	//@Field("matchId")
	private Long matchId;
	
	//@Field("radiantWin")
	@JsonProperty("radiant_win")
	private Boolean radiantWin;
	
	private Boolean radiant;
	
	//@Field("opposingTeamId")
	@JsonProperty("opposing_team_id")
	private Long opposingTeamId;
	
	private Boolean win;
	
	private Integer teamId;
	
	private Boolean processed;
	

}
