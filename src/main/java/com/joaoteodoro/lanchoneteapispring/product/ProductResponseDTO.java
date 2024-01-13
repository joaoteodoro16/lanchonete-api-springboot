package com.joaoteodoro.lanchoneteapispring.product;

import java.time.LocalDate;

public record ProductResponseDTO(
        Long id,
        String name,
        double price,
        String description,
        int sector,
        int stock,
        LocalDate created_at
) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice(),product.getDescription(),product.getSector(),product.getStock(),product.getCreated_at());
    }
}