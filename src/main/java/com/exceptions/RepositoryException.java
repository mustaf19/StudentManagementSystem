package com.exceptions;

import lombok.Getter;

@Getter 
public class RepositoryException extends RuntimeException{
    private String code;

    public RepositoryException(String message, Throwable cause){
        super(message, cause);
    }

    public RepositoryException(String message, String code){
        super(message);
        this.code =code;
    }
}