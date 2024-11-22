package com.example.Shelver.controllers;


import com.example.Shelver.dtos.CreateBookRequest;
import com.example.Shelver.dtos.GetBookDetailsResponse;
import com.example.Shelver.models.Book;
import com.example.Shelver.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public GetBookDetailsResponse createBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        return bookService.createBook(createBookRequest);
    }


    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") Integer bookId) {
        return bookService.getBookById(bookId);
    }

}
