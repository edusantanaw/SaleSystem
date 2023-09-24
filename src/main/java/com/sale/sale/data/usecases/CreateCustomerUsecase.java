package com.sale.sale.data.usecases;

import com.sale.sale.data.repositories.CreateCustomerRepository;
import com.sale.sale.domain.entities.Customer;
import com.sale.sale.domain.usecases.CreateUsecase;
import com.sale.sale.utils.EmailAlreadyUsedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor()
public class CreateCustomerUsecase implements CreateUsecase<Customer, Customer> {
    private final CreateCustomerRepository customerRepository;

    public Customer execute(Customer data) throws EmailAlreadyUsedException {
        Optional<Customer> emailAlreadyUsed = customerRepository.loadByEmail(data.getEmail());
        if(emailAlreadyUsed.isPresent()) throw  new EmailAlreadyUsedException();
        return customerRepository.create(data);
    }
}
