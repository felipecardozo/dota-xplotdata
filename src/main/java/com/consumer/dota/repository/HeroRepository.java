package com.consumer.dota.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.consumer.dota.model.Hero;

@Repository
public interface HeroRepository extends MongoRepository<Hero, String> {
	
	

}
