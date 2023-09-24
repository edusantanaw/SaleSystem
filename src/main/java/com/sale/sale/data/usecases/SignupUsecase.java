package com.sale.sale.data.usecases;

import com.sale.sale.data.repositories.CreateCustomerRepository;
import com.sale.sale.domain.entities.Customer;
import com.sale.sale.domain.usecases.CreateUsecase;
import com.sale.sale.infra.security.TokenService;
import com.sale.sale.presentation.dtos.auth.AuthResponseDto;
import com.sale.sale.presentation.dtos.auth.SignupDto;
import com.sale.sale.utils.EmailAlreadyUsedException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sale.sale.domain.enums.Role;
@AllArgsConstructor()
@Service()
public class SignupUsecase implements CreateUsecase<SignupDto, AuthResponseDto> {
    private final CreateCustomerRepository customerRepository;
    private final TokenService tokenService;

    public AuthResponseDto execute(SignupDto data) throws EmailAlreadyUsedException {
        var emailAlreadyBeingUsed = this.customerRepository.findByEmail(data.email());
        if(emailAlreadyBeingUsed != null) throw  new EmailAlreadyUsedException();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        var role = data.role() == "admin" ? Role.ADMIN : Role.USER;
        Customer customer = Customer
                .builder().email(data.email())
                .password(encryptedPassword).name(data.name())
                .role(role)
                .build();
        Customer newCustomer = this.customerRepository.save(customer);
        var token = tokenService.generateToken(customer);
        return new AuthResponseDto(token, newCustomer);
    }
}
