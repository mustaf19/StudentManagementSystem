package com.exceptions;

import lombok.Getter;

@Getter 
public class ValidationException extends RuntimeException{
    private String code;

    public ValidationException(String message){
        super(message);
    }

    public ValidationException(String message, String code){
        super(message);
        this.code = code;
    }
}