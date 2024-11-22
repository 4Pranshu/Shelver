package com.example.Shelver.dtos;

import com.example.Shelver.models.Author;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBookDetailsResponse {

    private String bookName;

    private Author author;
}
