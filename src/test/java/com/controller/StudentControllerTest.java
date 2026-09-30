package com.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.objects.Student;
import com.services.StudentService;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentControllerTest.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    void getStudentReturns200() throws Exception {
        Student student = new Student(
                "099",
                "John Doe",
                "john.doe@example.com",
                "123 Main St",
                "9876543211",
                "A+",
                LocalDate.parse("1990-01-01"));
        when(studentService.searchStudentById("099")).thenReturn(student);

        mockMvc.perform(get("/students/099"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("099"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

}
