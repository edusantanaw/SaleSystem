package com.sale.sale.data.repositories;

import com.sale.sale.domain.entities.Customer;

import java.util.Optional;

public interface CreateCustomerRepository {
    Optional<Customer> loadByEmail(String email);
    Customer create(Customer data);
}
