package com.sale.sale.presentation.dtos.auth;

import com.sale.sale.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignupDto {
    @Email(message = "e-mail is invalid!")
    @NotBlank(message = "e-mail is required!")
    public String email;
    @NotBlank(message = "password is required!!")
    public String password;
    @NotBlank(message = "name is required1")
    public String name;
    @NotBlank(message = "role is required!")
    public Role role;
}
