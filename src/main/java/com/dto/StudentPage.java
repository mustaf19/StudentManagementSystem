package com.dto;

import java.util.List;

import com.objects.Student;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class StudentPage {

    private List<Student> content;
    private Integer page;
    private Integer size;
    private Integer totalElements;
    private Integer totalPages; 
    
}
