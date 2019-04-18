package com.consumer.dota.model;

import org.springframework.data.annotation.Id;

import com.google.gson.annotations.SerializedName;

public class MatchJson {
	
	@Id
	private String id;
	
	private String json;
	
	@SerializedName("match_id")
	private Long matchId;
	
	public MatchJson() {}
	
	public MatchJson(String json) {
		this.id = null;
		this.json = json;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
}
