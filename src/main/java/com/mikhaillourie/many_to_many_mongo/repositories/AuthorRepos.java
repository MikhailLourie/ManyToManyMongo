package com.mikhaillourie.many_to_many_mongo.repositories;

import com.mikhaillourie.many_to_many_mongo.documents.AuthorDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepos extends MongoRepository<AuthorDoc, Integer> {
}
