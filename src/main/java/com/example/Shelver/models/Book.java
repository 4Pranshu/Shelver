package com.example.Shelver.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;


    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList","createdOn"})
    private Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList","transactionList","createdOn","updatedOn"})
    private Student student;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Transaction> transactionList;


}
