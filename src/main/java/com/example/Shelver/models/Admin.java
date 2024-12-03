package com.example.Shelver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true,nullable = false)
    private String mobile;

    @CreationTimestamp
    private Date createdOn;



    @JoinColumn
    @OneToOne
    @JsonIgnoreProperties("admin")
    private User user;
}


