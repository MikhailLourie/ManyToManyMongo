package com.mikhaillourie.many_to_many_mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {
    private int id;
    private String name;
}
