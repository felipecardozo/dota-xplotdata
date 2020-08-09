package com.dota.team.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dota.team.pro.model.matches.single.SingleMatch;

public interface SingleMatchRepository extends MongoRepository<SingleMatch, String> {

	
}
