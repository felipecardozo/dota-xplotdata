package com.consumer.dota.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.consumer.dota.model.team.TeamMatch;

@Repository
public interface TeamMatchRepository extends MongoRepository<TeamMatch, Long>{
	
}
