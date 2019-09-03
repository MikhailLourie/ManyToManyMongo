package com.mikhaillourie.many_to_many_mongo.services;

import com.mikhaillourie.many_to_many_mongo.documents.AuthorDoc;
import com.mikhaillourie.many_to_many_mongo.documents.BookDoc;
import com.mikhaillourie.many_to_many_mongo.dto.Author;
import com.mikhaillourie.many_to_many_mongo.dto.Book;
import com.mikhaillourie.many_to_many_mongo.exceptions.DuplicateDataException;
import com.mikhaillourie.many_to_many_mongo.exceptions.NoDataFoundException;
import com.mikhaillourie.many_to_many_mongo.repositories.AuthorRepos;
import com.mikhaillourie.many_to_many_mongo.repositories.BookRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LibraryService implements ILibraryService{
    @Autowired
    BookRepos booksRepo;

    @Autowired
    AuthorRepos authorRepo;

    @Override
    public void addBook(Book bookDto) {
        if(booksRepo.existsById(bookDto.getIsbn()))
            throw new DuplicateDataException("Book with isbn " + bookDto.getIsbn()+ " already exist");
        for (Author authorDto : bookDto.getAuthors()) {
            AuthorDoc author = authorRepo.findById(authorDto.getId()).orElse(null);
            if(author == null){
                author = authorRepo.save(new AuthorDoc(authorDto));
            }
            author.getBooks().add(bookDto.getIsbn());
            authorRepo.save(author);
        }
        booksRepo.save(new BookDoc(bookDto));

    }

    @Override
    public void addAuthor(Author authorDto) {
        if(authorRepo.existsById(authorDto.getId()))
            throw new DuplicateDataException("Author with id " + authorDto.getId()+ " already exist");
        authorRepo.save(new AuthorDoc(authorDto));
    }

    @Override
    public Book getBook(long isbn) {
        BookDoc bookDoc = booksRepo.findById(isbn).orElseThrow(
                ()->new NoDataFoundException("No book with isbn "+isbn));
        return bookDoc.getBook(authorsIdToDoc(bookDoc.getAuthors()));
    }

    @Override
    public Set<Author> getBookAuthors(long isbn) {
        BookDoc bookDoc = booksRepo.findById(isbn).orElseThrow(
                ()->new NoDataFoundException("No book with isbn "+isbn));
        return authorsIdToDoc(bookDoc.getAuthors()).stream()
                .map(authorDoc -> authorDoc.getAuthor()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<Book> getAuthorBooks(int id) {
        AuthorDoc authorDoc = authorRepo.findById(id).orElseThrow(
                ()-> new NoDataFoundException("No author with id "+id));
        return booksIdToDoc(authorDoc.getBooks()).stream()
                .map(bookDoc->bookDoc.getBook(authorsIdToDoc(bookDoc.getAuthors())))
                .collect(Collectors.toSet());
    }

    private Set<AuthorDoc> authorsIdToDoc(Set<Integer> ids){
        Iterable<AuthorDoc> authorDocs = authorRepo.findAllById(ids);
        Set<AuthorDoc> result = new HashSet<>();
        for (AuthorDoc authorDoc : authorDocs) {
            result.add(authorDoc);
        }
        return result;
    }

    private Set<BookDoc> booksIdToDoc(Set<Long> isbn){
        Iterable<BookDoc> bookDocs = booksRepo.findAllById(isbn);
        Set<BookDoc> result = new HashSet<>();
        for (BookDoc bookDoc : bookDocs) {
            result.add(bookDoc);
        }
        return result;
    }
}
