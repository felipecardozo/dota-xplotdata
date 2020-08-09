package com.dota.team.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dota.team.pro.model.teams.Team;

public interface TeamRepository extends MongoRepository<Team, String> {
	
	
}
