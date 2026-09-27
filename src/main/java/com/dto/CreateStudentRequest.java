package com.dto;

import java.time.LocalDate;
import java.lang.String;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class CreateStudentRequest {
    
    @NotBlank 
    private String name;

    @NotBlank 
    @Email 
    private String Email;

    @Pattern(regexp = "\\d{10}")
    private String phoneNo;
    
    private String address;
    private String bloodGroup;
    private LocalDate dob;

}