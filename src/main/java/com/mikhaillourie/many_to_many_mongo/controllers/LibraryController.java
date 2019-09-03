package com.mikhaillourie.many_to_many_mongo.controllers;

import com.mikhaillourie.many_to_many_mongo.dto.Author;
import com.mikhaillourie.many_to_many_mongo.dto.Book;
import com.mikhaillourie.many_to_many_mongo.services.ILibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class LibraryController {

    @Autowired
    ILibraryService service;

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){
        service.addBook(book);
        return "OK";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody Author author){
        service.addAuthor(author);
        return "OK";
    }

    @GetMapping("/getBook")
   public Book getBook(@RequestParam long isbn){
        return service.getBook(isbn);
    }

    @GetMapping("/getAuthorsOfBook")
    public Set<Author> getBookAuthors(@RequestParam long isbn){
        return service.getBookAuthors(isbn);
    }

    @GetMapping("/getBooksOfAuthor")
    public Set<Book> getAuthorBooks(@RequestParam int id){
        return service.getAuthorBooks(id);
    }
}
