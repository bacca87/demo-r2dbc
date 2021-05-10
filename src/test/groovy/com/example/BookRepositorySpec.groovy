package com.example

import io.micronaut.data.r2dbc.operations.R2dbcOperations
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class BookRepositorySpec extends Specification {

    @Shared @Inject R2dbcOperations operations
    @Shared @Inject BookRepository bookRepository

    def setupSpec() {
        // tag::programmatic-tx[]
        Mono.from(operations.withTransaction(status ->
                Flux.from(bookRepository.saveAll([
                        new Book(null, "The Stand", 1000),
                        new Book(null, "The Shining", 400),
                        new Book(null, "Along Came a Spider", 300),
                        new Book(null, "Jurassic Park", 300),
                        new Book(null, "Disclosure", 400)                        ]))
                        .then()
        )).block()
        // end::programmatic-tx[]
    }

    void "test list books"() {
        when:
        Flux<Book> books = bookRepository.findAll()
        List<Book> list = books.collectList().block()
        then:
        list.size() == 5
    }
}
