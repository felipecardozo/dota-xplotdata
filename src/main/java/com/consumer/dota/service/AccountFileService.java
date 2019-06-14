package com.consumer.dota.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.consumer.dota.model.TeamPlayerVanilla;
import com.google.gson.stream.JsonReader;

@Service
public class AccountFileService {
	
	public static final String PATH_FILE = "D:\\maestria\\workspace\\dota-xplotdata\\src\\main\\resources\\output\\output_full.csv";
	private BufferedWriter output;
	
	public String createFileByAccount() {
		List<TeamPlayerVanilla> players = new ArrayList<>();
		List<Long> playersId = new ArrayList<>();
		try {
			JsonReader reader = new JsonReader(new FileReader("D:\\maestria\\workspace\\dota-xplotdata\\src\\main\\resources\\json\\pro_players_ids.json"));
			TeamPlayerVanilla[] input = TransformerService.gson.fromJson(reader, TeamPlayerVanilla[].class);
			players = Arrays.asList(input);
			playersId = players.stream().map(f -> f.getAccountId()).collect(Collectors.toList());
			
			playersId.stream().forEach( playerId -> {
				createFile(playerId);
				iterateFile(playerId);
				closeFile();
			} );
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return playersId.toString();
	}
	
	private void iterateFile(Long playerId) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(AccountFileService.PATH_FILE));
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getMessage());
		}
		//ignore 1st line
		if( sc!=null & sc.hasNext() ) {
			sc.next();
		}
		System.out.println("create file for " + playerId);
		while( sc!=null && sc.hasNext() ) {
			String line = sc.next();
			String elements[] = line.split(",");
			if( elements[0].equals(Long.toString(playerId)) ) {
				String row = "";
				for( int i = 1; i<elements.length ; i++ ) {
					if( i == elements.length-1 ) {
						row+= elements[i];
					}else {
						row+= elements[i] + ",";
					}
				}
				try {
					writeLine(row);
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		sc.close();
		
	}

	private void createFile(Long idPlayer) {
		try {
			File file = new File("D:\\maestria\\workspace\\dota-xplotdata\\src\\main\\resources\\players\\output_"+idPlayer+".csv");
			output = new BufferedWriter(new FileWriter(file));
			writeLine("assists,heroId,heroKills,lane,neutralKills,gameMode,totalXp,totalGold,win");
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

}
