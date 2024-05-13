package com.narainox.journalapplication.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "username is required parameter.")
    private String username;
    @NotBlank(message = "password is required parameter.")
    @Size(max = 10,min=4, message = "password Size min>4 and max<10")
    private String password;
    @NotBlank(message = "email is required parameter.")
    private String email;
}
