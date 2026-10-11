package com.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Student returned by the API")
public class StudentResponse {

    @Schema(description = "Unique student identifier", example = "STU-1001")
    private String id;

    @Schema(example = "Jane Doe")
    private String name;

    @Schema(example = "jane.doe@example.com")
    private String email;

    @Schema(example = "9876543210")
    private String phoneNo;

    @Schema(example = "123 Main St, Springfield")
    private String address;

    @Schema(example = "O+")
    private String bloodGroup;

    @Schema(example = "2001-05-15")
    private LocalDate dob;
}
