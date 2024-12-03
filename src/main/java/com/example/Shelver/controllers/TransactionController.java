package com.example.Shelver.controllers;

import com.example.Shelver.dtos.InitiateTransactionRequest;
import com.example.Shelver.models.Student;
import com.example.Shelver.models.Transaction;
import com.example.Shelver.models.User;
import com.example.Shelver.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Transaction initiateTransaction(
            @RequestBody InitiateTransactionRequest transactionReq
    ) throws Exception {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();

        Student student = user.getStudent();

        Integer studentId= null;
        if(student!=null){
            studentId = student.getId();
        }
        else{
            throw new Exception("User is not a student");
        }
        return transactionService.initiateTransaction(studentId,transactionReq);
    }

}
