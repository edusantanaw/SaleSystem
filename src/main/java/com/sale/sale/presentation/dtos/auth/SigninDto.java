package com.sale.sale.presentation.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public class SigninDto {
    @NotBlank(message = "e-mail is required!")
    public String email;
    @NotBlank(message = "password is required!")
    public String password;
}
