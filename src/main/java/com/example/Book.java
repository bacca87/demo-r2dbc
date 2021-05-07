package com.example;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public record Book(
        @Id @GeneratedValue
        Long id,
        String titles,
        int pages
) {

}
