package com.mikhaillourie.many_to_many_mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private long isbn;
    private String title;
    private Set<Author> authors;
}
