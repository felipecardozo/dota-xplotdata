package com.dota.team.pro.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dota.team.pro.model.teams.Team;
import com.dota.team.pro.service.TeamService;

@RestController
public class TeamController {
	
	private TeamService teamService;
	
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}
	
	@GetMapping("/insert/teams")
	public List<Team> insertTeams(){
		return teamService.insertTeams();
	}

}
