package com.mikhaillourie.many_to_many_mongo.services;

import com.mikhaillourie.many_to_many_mongo.dto.Author;
import com.mikhaillourie.many_to_many_mongo.dto.Book;

import java.util.Set;

public interface ILibraryService {
    void addBook(Book bookDto);
    void addAuthor(Author authorDto);
    Book getBook(long isbn);
    Set<Author> getBookAuthors(long isbn);
    Set<Book> getAuthorBooks(int id);
}
