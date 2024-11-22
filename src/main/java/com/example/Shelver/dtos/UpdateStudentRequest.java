package com.example.Shelver.dtos;


import com.example.Shelver.models.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateStudentRequest {

    private String name;

    private String email;

    private String mobile;

    public Student to() {
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .mobile(this.mobile)
                .build();
    }

}
