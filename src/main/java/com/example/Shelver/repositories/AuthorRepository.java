package com.example.Shelver.repositories;

import com.example.Shelver.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    Author findByEmail(String email);
}
