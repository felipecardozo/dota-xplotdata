package com.consumer.dota.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.consumer.dota.model.ProMatch;

public interface ProMatchRepository extends MongoRepository<ProMatch, String> {

}
