package com.consumer.dota.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProPlayer {
	
	@SerializedName("account_id")
	private Double accountId;
	
	@SerializedName("steamid")
	private String steamId;
	
	@SerializedName("personaname")
	private String personaName;
	
	@SerializedName("loccountrycode")
	private String locCountryCode;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("country_code")
	private String countryCode;
	
	@SerializedName("team_id")
	private Integer teamId;
	
	@SerializedName("team_name")
	private String teamName;
	
	@SerializedName("team_tag")
	private String teamTag;
	
	@SerializedName("is_pro")
	private Boolean isPro;

	public Double getAccountId() {
		return accountId;
	}

	public void setAccountId(Double accountId) {
		this.accountId = accountId;
	}

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}

	public String getPersonaName() {
		return personaName;
	}

	public void setPersonaName(String personaName) {
		this.personaName = personaName;
	}

	public String getLocCountryCode() {
		return locCountryCode;
	}

	public void setLocCountryCode(String locCountryCode) {
		this.locCountryCode = locCountryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamTag() {
		return teamTag;
	}

	public void setTeamTag(String teamTag) {
		this.teamTag = teamTag;
	}

	public Boolean getIsPro() {
		return isPro;
	}

	public void setIsPro(Boolean isPro) {
		this.isPro = isPro;
	}
	

}
