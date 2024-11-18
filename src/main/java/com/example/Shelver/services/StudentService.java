package com.example.Shelver.services;


import com.example.Shelver.dtos.GetStudentDetailsResponse;
import com.example.Shelver.models.Book;
import com.example.Shelver.models.Student;
import com.example.Shelver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookService bookService;

    public GetStudentDetailsResponse getStudentDetails(Integer studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        List<Book> bookList = bookService.getBookDetailsBuStudentId(studentId);
        return GetStudentDetailsResponse.builder()
                .bookList(bookList)
                .student(student)
                .build();
    }

}
