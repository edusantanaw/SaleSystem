package com.sale.sale.data.usecases;

import com.sale.sale.domain.entities.Customer;
import com.sale.sale.domain.usecases.CreateUsecase;
import com.sale.sale.infra.repositories.CustomerRepository;
import com.sale.sale.infra.security.TokenService;
import com.sale.sale.presentation.dtos.auth.AuthResponseDto;
import com.sale.sale.presentation.dtos.auth.SigninDto;
import com.sale.sale.utils.EmailAlreadyUsedException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SigninUsecase implements CreateUsecase<SigninDto, AuthResponseDto> {
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final TokenService tokenService;

    @Override
    public AuthResponseDto execute(SigninDto data) {
        var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(emailPassword);
        Customer customer = (Customer) auth.getPrincipal();
        var token = tokenService.generateToken(customer);
        return new AuthResponseDto(token, customer);
    }
}
