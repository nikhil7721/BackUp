package com.ondemandcarwash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ondemandcarwash.model.WashPacks;







public interface WashPackRepository extends MongoRepository<WashPacks, Integer> {



}