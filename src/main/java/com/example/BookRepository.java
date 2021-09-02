package com.example;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Flux;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface BookRepository extends ReactiveStreamsCrudRepository<Book, Integer> {

    //@NonNull
    //@Override
    //Flux<Book> findAll();
}
