package com.example.Shelver.repositories;


import com.example.Shelver.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc(
            Student student,
            Book book,
            TransactionType transactionType,
            TransactionStatus transactionStatus);
}
