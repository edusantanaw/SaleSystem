package com.sale.sale.presentation.controller;

import com.sale.sale.domain.entities.Customer;
import com.sale.sale.infra.repositories.CustomerRepository;
import com.sale.sale.infra.security.TokenService;
import com.sale.sale.presentation.dtos.auth.AuthResponseDto;
import com.sale.sale.presentation.dtos.auth.SigninDto;
import com.sale.sale.presentation.dtos.auth.SignupDto;
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

@RestController()
@RequestMapping("api/auth")
@AllArgsConstructor()
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody @Valid SigninDto data){
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email, data.password);
        var auth = authenticationManager.authenticate(emailPassword);
        Customer customer = (Customer) auth.getPrincipal();
        var token = tokenService.generateToken(customer);
        return ResponseEntity.ok(new AuthResponseDto(token,customer));
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupDto data){
        var emailAlreadyBeingUsed = this.customerRepository.findByEmail(data.email);
        if(emailAlreadyBeingUsed != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password);
        Customer customer = Customer
                .builder().email(data.email)
                .password(encryptedPassword).name(data.name)
                .role(data.role)
                .build();
        this.customerRepository.save(customer);
        var token = tokenService.generateToken(customer);
        return ResponseEntity.ok(new AuthResponseDto(token, customer));
    }
}
