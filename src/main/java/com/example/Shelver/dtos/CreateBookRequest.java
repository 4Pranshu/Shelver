package com.example.Shelver.dtos;


import com.example.Shelver.models.Author;
import com.example.Shelver.models.Book;
import com.example.Shelver.models.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String bookName;

    private Genre genre;

    private String authorName;

    @NotBlank
    private String authorEmail;


    public Book to() {
        return Book.builder()
                .name(this.bookName)
                .genre(this.genre)
                .author(Author.builder().name(this.authorName).email(this.authorEmail).build())
                .build();
    }
}
