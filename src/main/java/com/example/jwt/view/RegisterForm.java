package com.example.jwt.view;

import lombok.Data;

@Data
public class RegisterForm {
    private String username;
    private String password;
    private String confirmPassword;
}
