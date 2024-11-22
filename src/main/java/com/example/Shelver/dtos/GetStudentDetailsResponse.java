package com.example.Shelver.dtos;


import com.example.Shelver.models.Book;
import com.example.Shelver.models.Student;
import lombok.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentDetailsResponse {

    private Student student;

//    private List<Book> bookList;
}
