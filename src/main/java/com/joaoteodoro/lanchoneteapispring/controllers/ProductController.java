package com.joaoteodoro.lanchoneteapispring.controllers;

import com.joaoteodoro.lanchoneteapispring.product.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {
    //DTO => DATA TRANSFER OBJECT
    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional //Faz o RollBack, a transação seja revertida
    public void register(@RequestBody @Valid ProductDTO product){
        repository.save(new Product(product));

    }

    @GetMapping
    public List<ProductResponseDTO> getAll(){
//        return repository.findAll().stream().map(product -> new ProductResponseDTO(product)).toList();
        return repository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid ProductUpdateDTO product){
        Product produto = repository.getReferenceById(product.id());
        produto.update(product);
    }





}
