package com.example;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Singleton
public class BookService {

    @Inject
    private BookRepository bookRepository;

    public Publisher<Book> getAll() {
        return bookRepository.findAll();
    }

    public Mono<Book> get(int id) {
        return Mono.from(bookRepository.findById(id))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid book id!")));
    }

    @Transactional
    public Publisher<Book> add(@NotNull String title) {
        return Mono.just(new Book())
                .map(entity -> {
                    entity.setTitle(title);
                    return entity;
                })
                .flatMap(entity -> Mono.from(bookRepository.save(entity)));
    }

    public Mono<Void> setupData() {
        return Flux.just(new Book(1, "book 1"), new Book(2, "book 2"))
                .flatMap(bookRepository::save).then();
    }
}