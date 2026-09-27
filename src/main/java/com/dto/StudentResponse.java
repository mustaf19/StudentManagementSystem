package com.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private String id;
    private String name;
    private String email;
    private String phoneNo;
    private String address;
    private String bloodGroup;
    private LocalDate dob;
}
