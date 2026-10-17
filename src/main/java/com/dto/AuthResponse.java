package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class AuthResponse {

    private String token;
    private long expiresInSeconds;
}
