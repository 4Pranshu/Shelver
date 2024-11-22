package com.example.Shelver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String externalTransactionId;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"transactionList","student","createdOn","updatedOn"})
    private Book book;


    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"transactionList","createdOn","updatedOn","bookList"})
    private Student student;
    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    private Integer fine;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

}
