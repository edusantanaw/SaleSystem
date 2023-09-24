package com.sale.sale.data.repositories;

import com.sale.sale.domain.entities.Customer;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface CreateCustomerRepository {
    Customer findByEmail(String email);

    Customer save(Customer data);
}
