package com.dota.team.pro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.dota.team.pro.model.matches.TeamMatches;

public interface TeamMatchesRepository extends MongoRepository<TeamMatches, String> {

	public List<TeamMatches> findByTeamId(Integer teamId);
	
	public List<TeamMatches> findByMatchId(Long matchId);
	
	@Query( value="{ 'matchId' : ?0 , processed : ?1}")
	public List<TeamMatches> findByMatchIdAndIsProcessed(Long matchId, Boolean isProcessed);
	
	@Query( value="{ 'teamId' : ?0 , processed : ?1}")
	public List<TeamMatches> findByTeamIdAndByIsProcessed(Integer teamId, Boolean isProcessed);
	
}
