package com.sale.sale.presentation.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record SigninDto(String email, String password) {
}
