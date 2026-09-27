package com.dto;

import com.objects.Student;

public class StudentMapper {

    private StudentMapper(){};

    public static Student toStudent(CreateStudentRequest req){
        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());
        student.setPhoneNo(req.getPhoneNo());
        student.setAddress(req.getAddress());
        student.setBloodGroup(req.getBloodGroup());
        student.setDob(req.getDob());
        return student;
    }

    public static StudentResponse toResponse(Student student){
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setPhoneNo(student.getPhoneNo());
        response.setAddress(student.getAddress());
        response.setBloodGroup(student.getBloodGroup());
        response.setDob(student.getDob());
        return response;
    }
}
