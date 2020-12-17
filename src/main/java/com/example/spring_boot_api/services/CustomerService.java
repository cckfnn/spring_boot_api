package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface CustomerService {
    CustomerDto getCustomerDto(Long customerId);
    CustomerDto getCustomerDtoAsync(Long customerId) throws ExecutionException, InterruptedException;
}
