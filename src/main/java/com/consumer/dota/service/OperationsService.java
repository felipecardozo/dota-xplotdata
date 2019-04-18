package com.consumer.dota.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.consumer.dota.model.MatchJson;
import com.consumer.dota.model.ProMatch;
import com.consumer.dota.repository.MatchJsonRepository;
import com.consumer.dota.repository.ProMatchRepository;

@Service
public class OperationsService {
	
	private ProMatchRepository proMatchRepository;
	
	private MatchJsonRepository matchJsonRepository;
	
	public final int TO = 10000;

	@Autowired
	public OperationsService(ProMatchRepository proMatchRepository, MatchJsonRepository matchJsonRepository) {
		this.proMatchRepository = proMatchRepository;
		this.matchJsonRepository = matchJsonRepository;
	}
	
	public void updateMatchProcessed() {
		List<ProMatch> proMatch = new ArrayList<>();
		for(int from=0; from<5; from++) {
			Pageable pageable = PageRequest.of(from, TO);
			List<MatchJson> matchJson = matchJsonRepository.findAll(pageable).getContent();
			matchJson.forEach(f -> {
				proMatch.addAll(proMatchRepository.findProMatchByMatchId(f.getMatchId()));
			});
		}
		
		proMatch.forEach( f -> updateProcessed(f) );
		
	}
	
	private void updateProcessed(ProMatch proMatch) {
		proMatch.setIsProcessed(true);
		proMatchRepository.save(proMatch);
		System.out.println("updated " + proMatch.getMatchId());
	}

}
