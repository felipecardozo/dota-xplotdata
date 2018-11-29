package com.consumer.dota.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.consumer.dota.model.Hero;

public interface HeroRepository extends MongoRepository<Hero, String> {
	
	

}
