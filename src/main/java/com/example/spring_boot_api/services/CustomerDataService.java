package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDataDto;

import java.util.concurrent.CompletableFuture;

public interface CustomerDataService {
    CustomerDataDto getCustomerDataDto(Long customerId);
    CompletableFuture<CustomerDataDto> getCustomerDataDtoAsync(Long customerId);
}
