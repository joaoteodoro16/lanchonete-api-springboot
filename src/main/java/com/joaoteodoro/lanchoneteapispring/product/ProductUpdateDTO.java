package com.joaoteodoro.lanchoneteapispring.product;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProductUpdateDTO(
        @NotNull
        Long id,
        String name,
        String price,
        String description,
        String sector,
        String stock
) {
}


//Long id,
//String name,
//double price,
//String description,
//String sector,
//int stock,
//LocalDate created_at