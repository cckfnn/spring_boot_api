package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDataDto;
import com.example.spring_boot_api.dto.CustomerDto;
import com.example.spring_boot_api.dto.HoldItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final HoldItemService holdItemService;
    private final CustomerDataService customerDataService;

    @Override
    public CustomerDto getCustomerDto(Long customerId) {
        // API Call №1
        CustomerDataDto customerData = customerDataService.getCustomerDataDto(customerId);

        // API Call №2
        HoldItemDto holdItemDto = holdItemService.getHoldItemDto(customerId);

        return CustomerDto.builder().customerDataDto(customerData).holdItemDto(holdItemDto).build();
    }

    @Override
    public CustomerDto getCustomerDtoAsync(Long customerId) throws ExecutionException, InterruptedException {
        // API Call №1
        CompletableFuture<CustomerDataDto> customerDataFuture = customerDataService.getCustomerDataDtoAsync(customerId);

        // API Call №2
        CompletableFuture<HoldItemDto> holdItemDtoFuture = holdItemService.getHoldItemDtoAsync(customerId);

        CompletableFuture.allOf(customerDataFuture, holdItemDtoFuture);

        return CustomerDto.builder().customerDataDto(customerDataFuture.get()).holdItemDto(holdItemDtoFuture.get()).build();
    }
}
