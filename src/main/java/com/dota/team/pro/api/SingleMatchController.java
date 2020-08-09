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
	
	@GetMapping("/test")
	public List<List<SingleMatch>> test() {
		return coreService.insertSingleMatches();
	}

	@GetMapping("/hi")
	public String test2(){
		return "Hello";
	}

}
