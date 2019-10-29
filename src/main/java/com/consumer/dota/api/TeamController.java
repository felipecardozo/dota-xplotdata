package com.consumer.dota.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.dota.model.team.TeamMatch;
import com.consumer.dota.service.TeamFinderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TeamController {

	private TeamFinderService teamFinderService;
	
	@Autowired
	public TeamController(TeamFinderService teamFinderService) {
		this.teamFinderService = teamFinderService;
	}
	
	
	@GetMapping("/team")
	public List<TeamMatch> getTeams() {
		return teamFinderService.findMatchesByTeam();
	}

}
