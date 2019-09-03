package com.mikhaillourie.many_to_many_mongo.documents;


import com.mikhaillourie.many_to_many_mongo.dto.Author;
import com.mikhaillourie.many_to_many_mongo.dto.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "authors")
@Document(collection = "book")
public class BookDoc {
    @Id
    private long isbn;
    private String title;
    private Set<Integer> authors;

    public BookDoc(Book book){
        this.isbn=book.getIsbn();
        this.title=book.getTitle();
        this.authors=book.getAuthors().stream()
                .map(author -> author.getId())
                .collect(Collectors.toSet());
    }

    public Book getBook(Set<AuthorDoc> authorDoc){
        Set<Author> authors = authorDoc.stream()
                .map(AuthorDoc::getAuthor)
                .collect(Collectors.toSet());
        return new Book(isbn, title, authors);

    }




}
