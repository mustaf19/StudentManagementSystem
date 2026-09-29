package com.exceptions;

import lombok.Getter;

@Getter 
public class StudentNotFoundException extends RuntimeException{
    private String code;

    public StudentNotFoundException(String message){
        super(message);
    }

    public StudentNotFoundException(String message, String code){
        super(message);
        this.code = code;
    }
}