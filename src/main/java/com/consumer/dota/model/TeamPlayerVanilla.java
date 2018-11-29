package com.consumer.dota.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TeamPlayerVanilla {

	private Long accountId;

	private String name;

	private Integer gamesPlayed;

	private Integer wins;

	private Boolean isCurrentTeamMember;

	private Integer teamId;

	private Boolean isProcessed;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
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

	public Integer getWins() {
		return wins;
	}

	public void setWins(Integer wins) {
		this.wins = wins;
	}

	public Boolean getIsCurrentTeamMember() {
		return isCurrentTeamMember;
	}

	public void setIsCurrentTeamMember(Boolean isCurrentTeamMember) {
		this.isCurrentTeamMember = isCurrentTeamMember;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
