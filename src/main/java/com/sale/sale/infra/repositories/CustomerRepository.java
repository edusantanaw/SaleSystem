package com.sale.sale.infra.repositories;

import com.sale.sale.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
   UserDetails findByEmail(String email);
}
