package com.consumer.dota.model;

import org.springframework.data.annotation.Id;

public class MatchJson {
	
	@Id
	private String id;
	
	private String json;
	
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
	
}
