package com.dota.team.pro.model.matches.single;

import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleMatch {
	
	@Id
	private String id;
	
	@JsonProperty("match_id")
	private Long matchId;
	
	@JsonProperty("barracks_status_dire")
	private Integer barracksStatusDire;
	
	@JsonProperty("barracks_status_radiant")
	private Integer barracksStatusRadiant;
	
	@JsonProperty("dire_score")
	private Integer direScore;
	
	@JsonProperty("dire_team_id")
	private Integer direTeamId;
	
	@JsonProperty("duration")
	private Integer duration;
	
	@JsonProperty("first_blood_time")
	private Integer firstBloodTime;
	
	@JsonProperty("game_mode")
	private Integer game_mode;
	
	@JsonProperty("leagueid")
	private Integer leagueId;
	
	@JsonProperty("lobby_type")
	private Integer lobbyType;
	
	@JsonProperty("radiant_gold_adv")
	private List<Integer> radiantGoldAdv;
	
	@JsonProperty("radiant_score")
	private Integer radiantScore;
	
	@JsonProperty("radiant_xp_adv")
	private List<Integer> radiantXpAdv;
	
	@JsonProperty("tower_status_dire")
	private Integer towerStatusDire;
	
	@JsonProperty("tower_status_radiant")
	private Integer towerStatusRadiant;
	
	@JsonProperty("version")
	private Integer version;
	
	@JsonProperty("radiant_team")
	private RadiantTeam radiantTeam;
	
	@JsonProperty("dire_team")
	private DiretTeam direTeam;
	
	@JsonProperty("patch")
	private Integer patch;
	
	@JsonProperty("comeback")
	private Integer comeback;
	
	@JsonProperty("stomp")
	private Integer stomp;
	
	@JsonProperty("draft_timings")
	private List<DraftTimings> draftTimings;
	
	@JsonProperty("picks_bans")
	private List<PicksBans> picksBans;
	
	@JsonProperty("teamfights")
	private List<TeamFights> teamfights;
	
	@JsonProperty("players")
	private List<Players> players;
	


}






