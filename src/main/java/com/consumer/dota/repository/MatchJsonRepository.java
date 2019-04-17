package com.consumer.dota.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.consumer.dota.model.MatchJson;

@Repository
public interface MatchJsonRepository extends MongoRepository<MatchJson, String> {

}
