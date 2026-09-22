package com.controller;

@ControllerAdvice
public class GLobalExceptionHandler{
    @ExceptionHandler(StudentNotFoundException.class){
        public ResponseEntity<String> handle(StudentNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }
}