package com.joaoteodoro.lanchoneteapispring.product;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private int sector;
    private int stock;
    private LocalDate created_at;

//    Quando o atributo for um Enum, colocar dessa forma
//    @Enumerated(EnumType.STRING)
//    private Enum enum;

    public Product(ProductDTO product){
        this.name = product.name();
        this.price = product.price();
        this.description = product.description();
        this.sector = product.sector();
        this.stock = product.stock();
        this.created_at = product.created_at();
    }

    public void update(ProductUpdateDTO product) {
        try {
            if(product.name() != null){
                this.name = product.name();
            }

            if(product.description() != null){
                this.description = product.description();
            }

            if(product.price() != null){
                this.price = Double.parseDouble(product.price());
            }

            if(product.sector() != null){
                this.sector = Integer.parseInt(product.sector());
            }

            if(product.stock() != null){
                this.stock = Integer.parseInt(product.stock());
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
