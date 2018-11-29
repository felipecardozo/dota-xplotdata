package com.consumer.dota.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumer.dota.model.Hero;
import com.consumer.dota.repository.HeroRepository;
import com.google.gson.stream.JsonReader;

@Service
public class HeroService {
	
	@Autowired
	private HeroRepository heroRepository;
	
	public List<Hero> loadHeroes() {
		List<Hero> heroes = new ArrayList<>();
		try {
			JsonReader reader = new JsonReader(new FileReader("C:\\personal\\workspace\\dota-consumer\\src\\main\\resources\\json\\all_heroes.json"));
			Hero[] heroesArr = TransformerService.gson.fromJson(reader, Hero[].class);
			heroes.addAll(Arrays.asList(heroesArr));
			insertHeroes(heroes);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return heroes;
	}

	private void insertHeroes(List<Hero> heroes) {
		for(Hero hero : heroes) {
			heroRepository.save(hero);
		}
	}

}
