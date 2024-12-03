package com.example.Shelver.services;


import com.example.Shelver.dtos.InitiateTransactionRequest;
import com.example.Shelver.models.*;
import com.example.Shelver.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service

public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private BookService bookService;

    @Value("${student.max.allowed-books}")
    Integer maxAllowedBooks;

    @Value("${books.return.duration}")
    Integer returnDuration;

    @Value("${fina.per.day}")
    Integer finePerDay;


    public Transaction initiateTransaction(Integer studentId,InitiateTransactionRequest transactionReq) throws Exception {
        switch (transactionReq.getTransactionType()) {
            case RETURN:
                return initiateReturnTransaction(studentId, transactionReq.getBookId());
            case ISSUE:
                return initiateIssueTransaction(studentId, transactionReq.getBookId());
            default:
                throw new Exception("Invalid Transaction Type");
        }
    }

    private Transaction initiateIssueTransaction(Integer studentId, Integer bookId) throws Exception {
        Student student = this.studentService.getStudentDetails(studentId).getStudent();
        if (student == null) {
            throw new Exception("Student is not present");
        }
        Book book = this.bookService.getBookById(bookId);
        if (book == null || book.getStudent() != null) {
            throw new Exception("Book is not available");
        }

        List<Book> issuedBooks = student.getBookList();
        if (issuedBooks != null && issuedBooks.size() >= maxAllowedBooks) {
            throw new Exception("Student has issued maximum number of books");
        }

        Transaction transaction = Transaction.builder().book(book).student(student).externalTransactionId(UUID.randomUUID().toString()).transactionType(TransactionType.ISSUE).build();

        transaction = this.transactionRepository.save(transaction);

        try {
            book.setStudent(student);
            Book savedBook = this.bookService.updateOrCreate(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transaction = this.transactionRepository.save(transaction);
            return transaction;
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);
            if (book.getStudent() != null) {
                book.setStudent(null);
                this.bookService.updateOrCreate(book);
            }
            return null;
        }

    }

    private Transaction initiateReturnTransaction(Integer studentId, Integer bookId) throws Exception {

        Student student = this.studentService.getStudentDetails(studentId).getStudent();
        if (student == null) {
            throw new Exception("Student is not present");
        }
        Book book = this.bookService.getBookById(bookId);
        if (book == null || book.getStudent() == null || !Objects.equals(book.getStudent().getId(), student.getId())) {
            throw new Exception("Book is not available or someone issued it not you");
        }

//        List<Book> issuedBooks = student.getBookList();
//        if (issuedBooks != null && issuedBooks.size() >= maxAllowedBooks) {
//            throw new Exception("Student has issued maximum number of books");
//        }
        Integer fine = calculateFine(student, book);
        Transaction transaction = Transaction.builder().book(book).fine(fine).
                student(student).externalTransactionId(UUID.randomUUID().toString()).transactionType(TransactionType.RETURN).build();

        transaction = this.transactionRepository.save(transaction);

        try {
            book.setStudent(null);
            Book savedBook = this.bookService.updateOrCreate(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transaction = this.transactionRepository.save(transaction);
            return transaction;
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.transactionRepository.save(transaction);
            if (book.getStudent() != null) {
                book.setStudent(null);
                this.bookService.updateOrCreate(book);
            }
            return null;
        }


    }


    private Integer calculateFine(Student student, Book book) {
        Transaction issueTransaction = this.transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(student, book, TransactionType.ISSUE, TransactionStatus.SUCCESS);
        Long issuesTimeInMillis = issueTransaction.getUpdatedOn().getTime();
        Long timePassedInMillis = System.currentTimeMillis() - issuesTimeInMillis;
        Long daysPassed = TimeUnit.DAYS.convert(timePassedInMillis, TimeUnit.MILLISECONDS);

        if (daysPassed > returnDuration) {
            return (daysPassed.intValue() - returnDuration) * finePerDay;
        }
        return 0;
    }
}
