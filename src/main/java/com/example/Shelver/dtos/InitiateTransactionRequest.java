package com.example.Shelver.dtos;


import com.example.Shelver.models.TransactionType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitiateTransactionRequest {
    private Integer studentId;

    private Integer bookId;

    private TransactionType transactionType;
}
