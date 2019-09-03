package com.mikhaillourie.many_to_many_mongo.documents;

import com.mikhaillourie.many_to_many_mongo.dto.Author;
import com.mikhaillourie.many_to_many_mongo.dto.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "books")
@Document(collection = "author")
public class AuthorDoc {
    @Id
    private int id;
    private String name;
    private Set<Long> books = new HashSet<>();

public AuthorDoc(Author author){
    this.id=author.getId();
    this.name=author.getName();
}

public Author getAuthor(){
    return new Author(id, name);
}

}
