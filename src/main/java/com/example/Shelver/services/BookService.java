package com.example.Shelver.services;

import com.example.Shelver.dtos.CreateBookRequest;
import com.example.Shelver.dtos.GetBookDetailsResponse;
import com.example.Shelver.models.Author;
import com.example.Shelver.models.Book;
import com.example.Shelver.models.Student;
import com.example.Shelver.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    List<Book> getBookDetailsBuStudentId(Integer studentId){
        return bookRepository.findByStudentId(studentId);
    }

    public GetBookDetailsResponse createBook(@Valid CreateBookRequest createBookRequest) {
        Book book = createBookRequest.to();
        Author author = book.getAuthor();
        author = authorService.getOrCreateAuthor(author);
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        GetBookDetailsResponse getBookDetailsResponse = GetBookDetailsResponse.builder()
                .bookName(savedBook.getName())
                .author(author)
                .build();
        return getBookDetailsResponse;
    }

    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public Book updateOrCreate(Book book){
        return this.bookRepository.save(book);
    }
}
