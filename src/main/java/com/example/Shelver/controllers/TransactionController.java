package com.example.Shelver.controllers;

import com.example.Shelver.dtos.InitiateTransactionRequest;
import com.example.Shelver.models.Transaction;
import com.example.Shelver.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/initiate")
    public Transaction initiateTransaction(@RequestBody InitiateTransactionRequest transactionReq) throws Exception {
        return transactionService.initiateTransaction(transactionReq);
    }

}
