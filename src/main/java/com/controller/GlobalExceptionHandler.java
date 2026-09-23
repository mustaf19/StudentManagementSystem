package com.controller;

import com.exceptions.RepositoryException;
import com.exceptions.StudentNotFoundException;
import com.exceptions.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handle(StudentNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handle(ValidationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<String> handle(RepositoryException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}