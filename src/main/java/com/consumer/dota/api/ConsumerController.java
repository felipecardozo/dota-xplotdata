package com.consumer.dota.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.dota.model.Hero;
import com.consumer.dota.model.Match;
import com.consumer.dota.model.MatchJson;
import com.consumer.dota.model.ProMatch;
import com.consumer.dota.model.ProPlayer;
import com.consumer.dota.model.TeamPlayer;
import com.consumer.dota.service.DotaCallerService;
import com.consumer.dota.service.FindTeamMembersService;
import com.consumer.dota.service.FinderMatchService;
import com.consumer.dota.service.HeroService;
import com.consumer.dota.service.MatchJsonService;
import com.consumer.dota.service.OperationsService;
import com.consumer.dota.service.TransformerService;

@RestController
public class ConsumerController {
	
	private DotaCallerService dotaCallerService;
	private TransformerService transformService;
	private FindTeamMembersService findTeamMembersService;
	private FinderMatchService finderMatchService;
	private HeroService heroService;
	private MatchJsonService matchJsonService;
	private OperationsService operationsService;

	@Autowired
	public ConsumerController(DotaCallerService dotaCallerService, TransformerService transformService, FindTeamMembersService findTeamMembersService,
			FinderMatchService finderMatchService, HeroService heroService, MatchJsonService matchJsonService, OperationsService operationsService) {
		this.dotaCallerService = dotaCallerService;
		this.transformService = transformService;
		this.findTeamMembersService = findTeamMembersService;
		this.finderMatchService = finderMatchService;
		this.heroService = heroService;
		this.matchJsonService = matchJsonService;
		this.operationsService = operationsService;
	}
	
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
	
	@GetMapping("/reindex/from/{from}/to/{to}")
	public String reIndexMatchJson(@PathVariable Integer from, @PathVariable Integer to) {
		matchJsonService.reIndexMatchJson(from, to);
		return ".... done";
	}
	
	@GetMapping("/checkMatchIdNull")
	public List<MatchJson> checkMatchIdNull() {
		return matchJsonService.checkNulls();
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "Change it  *****s!";
	}
	
	@GetMapping("/update/match/processed")
	public String updateMatchProcessed() {
		operationsService.updateMatchProcessed();
		return "updating ...";
	}
	
	@GetMapping("/matchjsonids")
	public List<Long> retrieveMatchJsonIds() {
		return matchJsonService.retrieveMatchJsonIds();
	}

}
