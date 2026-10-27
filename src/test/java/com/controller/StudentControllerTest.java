package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.config.SecurityConfig;
import com.exceptions.StudentNotFoundException;
import com.jayway.jsonpath.JsonPath;
import com.objects.Student;
import com.services.AuthService;
import com.services.JwtService;
import com.services.StudentService;

@WebMvcTest({StudentController.class, AuthController.class})
@Import({SecurityConfig.class, JwtService.class, AuthService.class})
public class StudentControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private JwtService jwtService;

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

        @Test
        void getUnknownStudentIdReturns404() throws Exception {

                when(studentService.searchStudentById("099"))
                                .thenThrow(new StudentNotFoundException("Student_NOT_FOUND"));

                mockMvc.perform(get("/students/099"))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.code").value("STUDENT_NOT_FOUND"));
        }

        @Test
        @WithMockUser
        void postInvalidBodyReturns400() throws Exception {

                mockMvc.perform(post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"\",\"email\":\"not-an-email\"}"))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
        }

        @Test
        void postWithoutCredentialsReturns401() throws Exception {
                mockMvc.perform(post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"Ada\",\"email\":\"ada@example.com\"}"))
                                .andExpect(status().isUnauthorized());
        }

        @Test
        void loginReturnsTokenForAdminAnd401ForWrongPassword() throws Exception {
                mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"username\":\"admin\",\"password\":\"wrong\"}"))
                                .andExpect(status().isUnauthorized());

                MvcResult result = mockMvc.perform(post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"username\":\"admin\",\"password\":\"password\"}"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.token").isNotEmpty())
                                .andReturn();

                String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
                assertEquals("admin", jwtService.parse(token).getSubject());
        }

}
