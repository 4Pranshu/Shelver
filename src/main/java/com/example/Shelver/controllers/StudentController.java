package com.example.Shelver.controllers;


import com.example.Shelver.dtos.GetStudentDetailsResponse;
import com.example.Shelver.models.Student;
import com.example.Shelver.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public GetStudentDetailsResponse getStudentDetails(@PathVariable("studentId") int studentId) {
        return studentService.getStudentDetails(studentId);
    }

}
