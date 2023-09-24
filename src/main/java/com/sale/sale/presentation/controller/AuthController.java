package com.sale.sale.presentation.controller;

import com.sale.sale.data.usecases.SigninUsecase;
import com.sale.sale.data.usecases.SignupUsecase;
import com.sale.sale.domain.entities.Customer;
import com.sale.sale.infra.repositories.CustomerRepository;
import com.sale.sale.infra.security.TokenService;
import com.sale.sale.presentation.dtos.auth.AuthResponseDto;
import com.sale.sale.presentation.dtos.auth.SigninDto;
import com.sale.sale.presentation.dtos.auth.SignupDto;
import com.sale.sale.utils.EmailAlreadyUsedException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
@RequestMapping("/api/auth")
@AllArgsConstructor()
public class AuthController {
  private final SignupUsecase signupUsecase;
  private final SigninUsecase signinUsecase;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid SigninDto data){
        var response = signinUsecase.execute(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupDto data){
        try {
            var response = signupUsecase.execute(data);
            return new ResponseEntity<AuthResponseDto>(response,null, 201);
        } catch (EmailAlreadyUsedException err) {
            return new ResponseEntity(err.getMessage(), null, 400);
        }
    }
}
