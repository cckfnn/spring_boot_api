package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDto;

public interface CustomerService {
    CustomerDto getCustomerDto(Long customerId);
}
