package com.consumer.dota.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerMatch {
	
	@SerializedName("total_gold")
	private Long totalGold;
	
	@SerializedName("total_xp")
	private Long totalXp;
	
	@SerializedName("win")
	private Long win;
	
	@SerializedName("game_mode")
	private Long gameMode;
	
	@SerializedName("neutral_kills")
	private Long neutralKills;
	
	@SerializedName("lane")
	private Long lane;
	
	@SerializedName("hero_kills")
	private Long heroKills;
	
	@SerializedName("hero_id")
	private Long heroId;
	
	@SerializedName("account_id")
	private Long accountId;
	
	@SerializedName("assists")
	private Long assists;

	public Long getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(Long totalGold) {
		this.totalGold = totalGold;
	}

	public Long getTotalXp() {
		return totalXp;
	}

	public void setTotalXp(Long totalXp) {
		this.totalXp = totalXp;
	}

	public Long getWin() {
		return win;
	}

	public void setWin(Long win) {
		this.win = win;
	}

	public Long getGameMode() {
		return gameMode;
	}

	public void setGameMode(Long gameMode) {
		this.gameMode = gameMode;
	}

	public Long getNeutralKills() {
		return neutralKills;
	}

	public void setNeutralKills(Long neutralKills) {
		this.neutralKills = neutralKills;
	}

	public Long getLane() {
		return lane;
	}

	public void setLane(Long lane) {
		this.lane = lane;
	}

	public Long getHeroKills() {
		return heroKills;
	}

	public void setHeroKills(Long heroKills) {
		this.heroKills = heroKills;
	}

	public Long getHeroId() {
		return heroId;
	}

	public void setHeroId(Long heroId) {
		this.heroId = heroId;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getAssists() {
		return assists;
	}

	public void setAssists(Long assists) {
		this.assists = assists;
	}
	
}
