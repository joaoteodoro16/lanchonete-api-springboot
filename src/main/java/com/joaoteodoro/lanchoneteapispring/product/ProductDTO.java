package com.joaoteodoro.lanchoneteapispring.product;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProductDTO(

        @NotBlank //Não deixa passar ""
        String name,
        double price,
        String description,
        int sector,
        int stock,
        @Future //Não permite datas antigas
        LocalDate created_at

        //    Quando o atributo for um Enum, colocar dessa forma
        //    @Enumerated(EnumType.STRING)
        //    private Enum enum;
) {



}
