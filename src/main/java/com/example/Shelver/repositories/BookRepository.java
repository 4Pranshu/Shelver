package com.example.Shelver.repositories;

import com.example.Shelver.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByStudentId(Integer studentId);
}
