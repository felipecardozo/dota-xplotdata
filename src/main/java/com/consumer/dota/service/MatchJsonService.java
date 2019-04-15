package com.consumer.dota.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

	public MatchJsonService() {
		
		restTemplate = new RestTemplate();
	}

	public void loadMatches() {
		List<ProMatch> matches = proMatchRepository.findAll();
		List<Long> ids = retrieveMatchesId();
		
		for (ProMatch match : matches) {
			Long matchId = match.getMatchId();
			if( !isMatchInDB(matchId, ids) ) {
				System.out.println("processing match id " + matchId + " of player " + match.getPlayerId());
				HttpHeaders headers = new HttpHeaders();
	            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	            ResponseEntity<String> response = null;
	            try{
	            	//String response = restTemplate.getForObject(Constants.URL + Constants.PATH_MATCHES + "/" + matchId, String.class);
	            	response = restTemplate.exchange(Constants.URL + Constants.PATH_MATCHES + "/" + matchId, HttpMethod.GET, entity, String.class);
	            	matchJsonRepository.save(new MatchJson(response.getBody()));
	            }
	            catch(ResourceAccessException ex) {
	            	System.err.println(ex.getMessage());
	            }
	            catch(Exception ex) {
	            	System.err.println(ex.getMessage());
	            }finally {
	            	sleep();
	            }
				
			}
			
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	@Async
	public String getMatch() {
		List<TeamPlayerVanilla> proPlayers = transformerService.loadProPlayersIds();
		List<MatchJson> matches = matchJsonRepository.findAll();

		createFile();
		for (int i = 0; i < matches.size(); i++) {
			System.out.println("processing " + i + " of " + matches.size());
			String json = matches.get(i).getJson();

			Match singleMatch = gson.fromJson(json, Match.class);
			List<PlayerMatch> players = singleMatch.getPlayers();
			for (PlayerMatch player : players) {
				if (isProPlayer(player, proPlayers)) {
					try {
						writeLine(buildString(player));
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		}

		closeFile();
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

	private void createFile() {
		try {
			File file = new File("C:\\personal\\workspace\\dota-consumer\\src\\main\\resources\\output\\output.csv");
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
	public List<Long> retrieveMatchesId(List<MatchJson> matches) {
		List<Long> matchIds = new ArrayList<>();
		
		for( MatchJson match : matches ) {
			ProMatch proMatch = gson.fromJson(match.getJson(), ProMatch.class);
			matchIds.add(proMatch.getMatchId());
		}
		
		return matchIds;
	}

	public List<Long> retrieveMatchesId() {
		List<MatchJson> matches = matchJsonRepository.findAll();
		List<Long> matchIds = new ArrayList<>();
		
		for( MatchJson match : matches ) {
			ProMatch proMatch = gson.fromJson(match.getJson(), ProMatch.class);
			matchIds.add(proMatch.getMatchId());
		}
		
		return matchIds;
	}
	
	public Boolean isMatchInDB(Long id, List<Long> ids) {
		if(ids.contains(id)) return true;
		return Boolean.FALSE;
	}

}
