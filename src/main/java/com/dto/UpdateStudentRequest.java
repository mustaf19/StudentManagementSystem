package com.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for updating an existing student")
public class UpdateStudentRequest {

    @NotBlank
    @Schema(description = "Full name", example = "Jane Doe")
    private String name;

    @NotBlank
    @Email
    @Schema(description = "Valid email address", example = "jane.doe@updated.example.com")
    private String Email;

    @Pattern(regexp = "\\d{10}")
    @Schema(description = "10-digit phone number", example = "9876543210")
    private String phoneNo;

    @Schema(description = "Residential address", example = "456 Oak Ave, Springfield")
    private String address;

    @Schema(description = "Blood group", example = "O+")
    private String bloodGroup;

    @Schema(description = "Date of birth (ISO-8601)", example = "2001-05-15")
    private LocalDate dob;

}