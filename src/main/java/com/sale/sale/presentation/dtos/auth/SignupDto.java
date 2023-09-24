package com.sale.sale.presentation.dtos.auth;

import com.sale.sale.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record SignupDto(String email, String password, String name, String role) {
}
