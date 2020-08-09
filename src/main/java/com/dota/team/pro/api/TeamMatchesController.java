package com.dota.team.pro.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dota.team.pro.model.matches.TeamMatches;
import com.dota.team.pro.service.TeamMatchService;

@RestController
public class TeamMatchesController {

	public TeamMatchService teamMatchService;
	
	public TeamMatchesController(TeamMatchService teamMatchService) {
		this.teamMatchService = teamMatchService;
	}
	
	@GetMapping("/insert/teams/matches")
	public List<TeamMatches> insertMatches() {
		return teamMatchService.insertMatches();
	}
	
	@GetMapping("/find/teams/matches")
	public Map<Integer, List<TeamMatches>> findAllMatches() {
		return teamMatchService.findAllMatches();
	}
	
}
