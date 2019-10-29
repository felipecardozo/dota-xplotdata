package com.consumer.dota.model.team;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TeamMatch {
	
	private Long teamId;
	
	@SerializedName("match_id")
	private Long matchId;
	
	@SerializedName("radiant_win")
	private boolean radianWin;
	
	private boolean radian;
	
	private int duration;
	
	@SerializedName("start_time")
	private Long startTime;
	
	@SerializedName("leagueid")
	private int leagueId;
	
	@SerializedName("league_name")
	private String leagueName;
	
	@SerializedName("opposing_team_id")
	private Long opposingTeamId;
	
	@SerializedName("opposing_team_name")
	private String opposingTeamName;
	
	

}
