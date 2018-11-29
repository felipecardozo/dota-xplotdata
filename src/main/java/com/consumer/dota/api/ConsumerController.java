package com.consumer.dota.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.dota.model.Hero;
import com.consumer.dota.model.Match;
import com.consumer.dota.model.ProMatch;
import com.consumer.dota.model.ProPlayer;
import com.consumer.dota.model.TeamPlayer;
import com.consumer.dota.service.DotaCallerService;
import com.consumer.dota.service.FindTeamMembersService;
import com.consumer.dota.service.FinderMatchService;
import com.consumer.dota.service.HeroService;
import com.consumer.dota.service.MatchJsonService;
import com.consumer.dota.service.TransformerService;

@RestController
public class ConsumerController {
	
	@Autowired
	private DotaCallerService dotaCallerService;
	
	@Autowired
	private TransformerService transformService;
	
	@Autowired
	private FindTeamMembersService findTeamMembersService;
	
	@Autowired
	private FinderMatchService finderMatchService;
	
	@Autowired
	private HeroService heroService;
	
	@Autowired
	private MatchJsonService matchJsonService;
	
	@GetMapping("/match/{match}")
	public Match getMatch(@PathVariable String match) {
		return dotaCallerService.getMatch(match);
	}
	
	@GetMapping("/transform")
	public String transform() {
		return transformService.transform();
	}
	
	@GetMapping("/teams")
	public List<TeamPlayer> findTeamMembers() {
		return findTeamMembersService.findTeamMembersByTeamId();
	}
	
	@GetMapping("/proplayers")
	public List<ProPlayer> getProPlayers() {
		List<ProPlayer> proplayers = dotaCallerService.getProPlayers();
		System.out.println(proplayers.get(2).getTeamName());
		return proplayers;
	}
	
	@GetMapping("/insertmatches")
	public List<ProMatch> insertMatches(){
		return finderMatchService.findAndInsertmatches();
	}
	
	@GetMapping("/loadheroes")
	public List<Hero> loadHeroes(){
		return heroService.loadHeroes();
	}
	
	@GetMapping("/runmatches")
	public String runMatches() {
		matchJsonService.loadMatches();
		return "in progress...";
	}
	
	@GetMapping("/getmatch")
	public String getMatch() {
		return matchJsonService.getMatch();
	}
	
	@GetMapping("/matchids")
	public List<Long> getMatchesFromDB() {
		return matchJsonService.retrieveMatchesId();
	}

}
