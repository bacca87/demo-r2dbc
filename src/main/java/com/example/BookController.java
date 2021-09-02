package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/")
public class BookController {

    @Inject
    private BookService bookService;

    @Get("/setup")
    Mono<Void> setupData() {
        return bookService.setupData();
    }

    @Get("/all")
    Publisher<Book> all() {
        return bookService.getAll();
    }

    @Get("/add")
    Publisher<Book> add(String title) {
        return bookService.add(title);
    }
}