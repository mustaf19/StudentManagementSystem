package com.dto;

import java.util.List;

import com.objects.Student;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Paged list of students")
public class StudentPage {

    @Schema(description = "Students on the current page")
    private List<Student> content;

    @Schema(description = "Current page index (zero-based)", example = "0")
    private Integer page;

    @Schema(description = "Requested page size", example = "20")
    private Integer size;

    @Schema(description = "Total number of students across all pages", example = "42")
    private Integer totalElements;

    @Schema(description = "Total number of pages", example = "3")
    private Integer totalPages;

}
