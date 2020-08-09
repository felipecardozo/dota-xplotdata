package com.dota.team.pro.model.matches.single;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BenchMark {
	
	private Integer raw;
	
	private Float pct;

}
