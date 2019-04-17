package com.consumer.dota.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.consumer.dota.model.ProMatch;

@Repository
public interface ProMatchRepository extends MongoRepository<ProMatch, String> {

}
