package com.example.Shelver.dtos;


import com.example.Shelver.models.Admin;
import com.example.Shelver.models.Student;
import com.example.Shelver.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String name;

    private String email;

    @NotBlank
    private String mobile;

    public Admin to() {
        return Admin.builder()
                .name(this.name)
                .email(this.email)
                .mobile(this.mobile)
                .user(User.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }
}
