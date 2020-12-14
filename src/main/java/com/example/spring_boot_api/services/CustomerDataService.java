package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDataDto;

public interface CustomerDataService {
    CustomerDataDto getCustomerDataDto(Long customerId);
}
