package com.sale.sale.presentation.dtos.auth;

import com.sale.sale.domain.entities.Customer;

public record AuthResponseDto(String token, Customer customer) {
}
