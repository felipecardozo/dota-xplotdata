package com.consumer.dota.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProMatch {
	
	@Id
    private String id;
	
	@SerializedName("match_id")
	private Long matchId;
	
	private Long playerId;
	
	private Boolean isProcessed;
	
	@SerializedName("radiant_win")
	private Boolean radiantWin;
	
	@SerializedName("duration")
	private Integer duration;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	public String toString() {
		return " id " + getId() + " matchID" + getMatchId() + " isPorcessed" + getIsProcessed() + " ";
	}
	
}
