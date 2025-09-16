package com.example.demo.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @NotEmpty(message = "username Cannot be empty")
    private String username;

    @NotEmpty(message = "password Cannot be empty")
    private String password;
}
