package com.consumer.dota.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.consumer.dota.Constants;
import com.consumer.dota.model.Match;
import com.consumer.dota.model.MatchJson;
import com.consumer.dota.model.PlayerMatch;
import com.consumer.dota.model.ProMatch;
import com.consumer.dota.model.TeamPlayerVanilla;
import com.consumer.dota.repository.MatchJsonRepository;
import com.consumer.dota.repository.ProMatchRepository;
import com.consumer.dota.util.ServiceUtils;
import com.google.gson.Gson;

@Service
public class MatchJsonService {

	@Autowired
	private ProMatchRepository proMatchRepository;

	@Autowired
	private MatchJsonRepository matchJsonRepository;

	@Autowired
	private TransformerService transformerService;

	private BufferedWriter output = null;

	final static Gson gson = new Gson();

	private RestTemplate restTemplate;
	
	public static final int TO = 10000;

	public MatchJsonService() {
		restTemplate = new RestTemplate();
	}

	public void loadMatches() {
		List<ProMatch> matches = proMatchRepository.findProMatchByIsProcessedIsFalse();
//		List<Long> ids = retrieveMatchJsonIds();
		System.out.println("processing " + matches.size() + "matches");
		for (ProMatch match : matches) {
			Long matchId = match.getMatchId();
			System.out.println("processing match id " + matchId + " of player " + match.getPlayerId());
			ResponseEntity<String> response = null;
	            
            try{
            	//String response = restTemplate.getForObject(Constants.URL + Constants.PATH_MATCHES + "/" + matchId, String.class);
            	response = restTemplate.exchange(Constants.URL + Constants.PATH_MATCHES + "/" + matchId, HttpMethod.GET, ServiceUtils.getEntity(), String.class);
            	matchJsonRepository.save(new MatchJson(response.getBody()));
            	match.setIsProcessed(true);
            	proMatchRepository.save(match);
            }
            catch(ResourceAccessException ex) {
            	System.err.println(ex.getMessage());
            }
            catch(Exception ex) {
            	System.err.println(ex.getMessage());
            }finally {
            	ServiceUtils.sleep();
            }
			
			
		}
	}
	
	
	int count = 0;
	List<MatchJson> matches = new ArrayList<>();
	@Async
	public String getMatch() {
		List<TeamPlayerVanilla> proPlayers = transformerService.loadProPlayersIds();
		System.out.println("proPlayers size " + proPlayers.size());
		
		for( int i = 0 ; i<15; i++ ) {
			Pageable pageable = PageRequest.of(i, MatchJsonService.TO);
			matches = matchJsonRepository.findAll(pageable).getContent();
			createFile(i+"");
			matches.stream().forEach( f -> {
				System.out.println(count++);
				if( count == 29999 ) {
					System.out.println("parar aqui");
				}
				String json = f.getJson();
				Match singleMatch = gson.fromJson(json, Match.class);
				json = null;
				List<PlayerMatch> players = singleMatch.getPlayers();
				singleMatch = null;
				if(players!= null && players.size()>0) {
					players.stream().forEach( player -> {
						
						if (isProPlayer(player, proPlayers)) {
							try {
								writeLine(buildString(player));
							} catch (IOException e) {
								System.err.println(e.getMessage());
							}
						}
					} );
					
				}
				
				players= null;
			} );
			closeFile();
			matches = new ArrayList<>();	
		}
		
		return "Finished";
	}

	private String buildString(PlayerMatch player) {
		StringBuilder sb = new StringBuilder();
		
		if (player.getAccountId() != null) {
			sb.append(player.getAccountId());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getAssists() != null) {
			sb.append(player.getAssists());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getHeroId() != null) {
			sb.append(player.getHeroId());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getHeroKills() != null) {
			sb.append(player.getHeroKills());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getLane() != null) {
			sb.append(player.getLane());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getNeutralKills() != null) {
			sb.append(player.getNeutralKills());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getGameMode() != null) {
			sb.append(player.getGameMode());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getTotalXp() != null) {
			sb.append(player.getTotalXp());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getTotalGold() != null) {
			sb.append(player.getTotalGold());
		} else {
			sb.append(Constants.ZERO);
		}
		sb.append(Constants.COMA);
		if (player.getWin() != null) {
			sb.append(player.getWin());
		} else {
			sb.append(Constants.ZERO);
		}
		
		return sb.toString();
	}

	private boolean isProPlayer(PlayerMatch player, List<TeamPlayerVanilla> proPlayers) {
		for (TeamPlayerVanilla p : proPlayers) {
			if (player.getAccountId() != null && player.getAccountId().equals(p.getAccountId())) {
				return true;
			}
		}
		return false;
	}

	private void createFile(String id) {
		try {
			File file = new File("D:\\maestria\\workspace\\dota-xplotdata\\src\\main\\resources\\output\\output"+id+".csv");
			output = new BufferedWriter(new FileWriter(file));
			writeLine("accountId,assists,heroId,heroKills,lane,neutralKills,gameMode,totalXp,totalGold,win");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void writeLine(String line) throws IOException {
		try {
			output.write(line + "\n");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void closeFile() {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	//get all matches from DB to avoid call open dota
	public List<Long> retrieveMatchesId(List<ProMatch> proMatches) {
		List<Long> matchIds = new ArrayList<>();
		
		proMatches.forEach(f->{
			matchIds.add(f.getMatchId());
			System.out.println(f.getIsProcessed());
		});
		System.out.println(proMatches.size());
		
		return matchIds;
	}
	
	public List<Long> retrieveMatchJsonIds(){
		List<Long> matchIds = new ArrayList<>();
		int to=10000;
		for( int from = 0; from <=5; from ++ ) {
			Pageable pageable = PageRequest.of(from, to);
			List<MatchJson> matchJsons = matchJsonRepository.findAll(pageable).getContent();
			matchJsons.forEach(json -> {
				Match match = gson.fromJson(json.getJson(), Match.class);
				matchIds.add(match.getMatchId());
			});
		}
		System.out.println(matchIds.size());
		
		return matchIds;
	}
	
	public List<Long> retrieveMatchesId(){
		List<Long> matchIds = new ArrayList<>();
		
		List<ProMatch> proMatches = proMatchRepository.findProMatchByIsProcessedIsFalse();
		proMatches.forEach(f->{
			matchIds.add(f.getMatchId());
			System.out.println(f.getIsProcessed());
		});
		System.out.println(proMatches.size());
		
		return matchIds;
	}
	

	/**public List<Long> retrieveMatchesId() {
		List<MatchJson> matches = matchJsonRepository.findAll();
		List<Long> matchIds = new ArrayList<>();
		
		for( MatchJson match : matches ) {
			ProMatch proMatch = gson.fromJson(match.getJson(), ProMatch.class);
			matchIds.add(proMatch.getMatchId());
		}
		
		return matchIds;
	}*/
	
	public void reIndexMatchJson(Integer from, Integer to) {
		System.out.println("starting re matchId");
		Pageable pageable = PageRequest.of(from, to);
		List<MatchJson> matchJsons = matchJsonRepository.findAll(pageable).getContent();
		matchJsons.forEach( json -> { 
			Match match = gson.fromJson(json.getJson(), Match.class);
			Long matchId = match.getMatchId();
			System.out.println("processing matchId "+matchId);
			json.setMatchId(matchId);
			matchJsonRepository.save(json);
		} );
		System.out.println("finished re matchId");
	}
	
	

	public List<MatchJson> checkNulls() {
		List<MatchJson> nulls = new ArrayList<>();
		for( int i=0; i<=5; i++ ) {
			Pageable pageable = PageRequest.of(i, 10000);
			List<MatchJson> matchJsons = matchJsonRepository.findAll(pageable).getContent();
			nulls.addAll( matchJsons.stream().filter(filter -> filter == null ).collect(Collectors.toList()) );
		}
		return nulls;
	}

}
