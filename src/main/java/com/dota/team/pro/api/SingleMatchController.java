package com.dota.team.pro.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dota.team.pro.model.matches.single.SingleMatch;
import com.dota.team.pro.service.CoreService;
import com.dota.team.pro.service.SingleMatchService;

@RestController
public class SingleMatchController {
	
	private SingleMatchService singleMatchService;
	private CoreService coreService;
	
	public SingleMatchController(SingleMatchService singleMatchService, CoreService coreService) {
		this.singleMatchService = singleMatchService;
		this.coreService = coreService;
	}
	
	@GetMapping("/match/{matchId}")
	public SingleMatch retrieveSingleMatch(@PathVariable Long matchId) {
		return singleMatchService.retrieveMatch(matchId);
	}

	/**
	 * This endpoint is key to retrieve the no processed matches of pro teams
	 * Later will retrieve matches 1x1 and after that will save that to SingleMatch collection
	 * After saved, will update that match into TeamMatch set to processed=true
	 * */
	@GetMapping("/insertandupdate")
	public List<List<SingleMatch>> test() {
		return coreService.insertSingleMatches();
	}

	@GetMapping("/hi")
	public String test2(){
		return "Hello";
	}

}
