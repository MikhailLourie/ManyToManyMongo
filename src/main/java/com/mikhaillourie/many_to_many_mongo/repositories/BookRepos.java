package com.mikhaillourie.many_to_many_mongo.repositories;

import com.mikhaillourie.many_to_many_mongo.documents.BookDoc;
import com.mikhaillourie.many_to_many_mongo.dto.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepos extends MongoRepository<BookDoc, Long> {
}
