package com.consumer.dota.model;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Player {
	
	@SerializedName("xp_start")
	private int xpStart;
	
	@SerializedName("xp_end")
	private int xpEnd;
	
	@SerializedName("killed")
	private Map<String, String> killed;
	
	

	public Map<String, String> getKilled() {
		return killed;
	}

	public void setKilled(Map<String, String> killed) {
		this.killed = killed;
	}

	public int getXpStart() {
		return xpStart;
	}

	public void setXpStart(int xpStart) {
		this.xpStart = xpStart;
	}

	public int getXpEnd() {
		return xpEnd;
	}

	public void setXpEnd(int xpEnd) {
		this.xpEnd = xpEnd;
	}

}
