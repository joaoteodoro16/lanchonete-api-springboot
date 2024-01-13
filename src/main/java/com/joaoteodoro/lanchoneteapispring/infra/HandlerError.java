package com.joaoteodoro.lanchoneteapispring.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Toda vez que acontecer uma exception, essa classe é chamada, identifica se esta tratada e faz o tratamento
public class HandlerError {

    //404 - Quando não encontr
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handler404(){
        return ResponseEntity.notFound().build();
    }

    //400 - QUANDO UMA INFORMAÇÃO NÃO ESTA SENDO VALIDADA
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handler400(MethodArgumentNotValidException error){
        var errors = error.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorResponse::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handler400ConvertType(HttpMessageNotReadableException error){
        var errors = error.getLocalizedMessage();
        return ResponseEntity.badRequest().body(new ErrorResponse("",errors));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherExceptions(Exception ex) {
        // Lidar com outras exceções, se necessário.
        return ResponseEntity.status(500).body("Erro interno do servidor");
    }

    public record ErrorResponse(String field, String message){

        public ErrorResponse(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }
}
