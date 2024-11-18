package com.example.Shelver.services;

import com.example.Shelver.models.Book;
import com.example.Shelver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    List<Book> getBookDetailsBuStudentId(Integer studentId){
        return bookRepository.findByStudentId(studentId);
    }
}
