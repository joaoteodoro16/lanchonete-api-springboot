package com.joaoteodoro.lanchoneteapispring.controllers;

import com.joaoteodoro.lanchoneteapispring.product.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {
    //DTO => DATA TRANSFER OBJECT
    @Autowired
    private ProductRepository repository;

    @PostMapping
    @Transactional //Faz o RollBack, a transação seja revertida
    public ResponseEntity<ProductResponseDTO> register(@RequestBody @Valid ProductDTO product, UriComponentsBuilder uriBuilder){
        var produto = new Product(product);
        repository.save(produto);
        //var uri =  uriBuilder.path("/produtos/${id}").buildAndExpand(produto.getId()).toUri();
        var uri = uriBuilder.path("/produtos/{id}")
                .buildAndExpand(produto.getId())
                .encode()
                .toUri();

        return ResponseEntity.created(uri).body(new ProductResponseDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
//        return repository.findAll().stream().map(product -> new ProductResponseDTO(product)).toList();
        var lista = repository.findAllByActiveTrue().stream().map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProductResponseDTO> update(@RequestBody @Valid ProductUpdateDTO product){
        Product produto = repository.getReferenceById(product.id());
        produto.update(product);
        return ResponseEntity.ok(new ProductResponseDTO(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("inactive/{id}")
    @Transactional //Identica que ouve uma alteração no registro e salva
    public ResponseEntity<Void> inactive(@PathVariable Long id){
        Product produto = repository.getReferenceById(id);
        produto.inactive();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id){
        var product = repository.getReferenceById(id);
        return ResponseEntity.ok(new ProductResponseDTO(product));
    }
}
