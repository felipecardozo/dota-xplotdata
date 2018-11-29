package com.consumer.dota.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.consumer.dota.model.MatchJson;

public interface MatchJsonRepository extends MongoRepository<MatchJson, String> {

}
