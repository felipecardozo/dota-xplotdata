package com.consumer.dota.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

public class TeamPlayer {
	
	@SerializedName("account_id")
	private String accountId;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("games_played")
	private Integer gamesPlayed;
	
	@SerializedName("wins")
	private int wins;
	
	@SerializedName("is_current_team_member")
	private Boolean isCurrentTeamMember;
	
	private Integer teamId;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(Integer gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public Boolean getIsCurrentTeamMember() {
		return isCurrentTeamMember;
	}

	public void setIsCurrentTeamMember(Boolean isCurrentTeamMember) {
		this.isCurrentTeamMember = isCurrentTeamMember;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
