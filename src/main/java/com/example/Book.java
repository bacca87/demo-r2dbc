package com.example;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
        @Id
        @GeneratedValue
        private int id;

        @NotNull
        private String title;
}