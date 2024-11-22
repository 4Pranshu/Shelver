package com.example.Shelver.services;

import com.example.Shelver.dtos.GetAuthorDetailsReponse;
import com.example.Shelver.models.Author;
import com.example.Shelver.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public Author getOrCreateAuthor(Author author) {
        Author savedAuthor = authorRepository.findByEmail(author.getEmail());

        if(savedAuthor==null){
            savedAuthor = authorRepository.save(author);
        }

        return savedAuthor;
    }
}
