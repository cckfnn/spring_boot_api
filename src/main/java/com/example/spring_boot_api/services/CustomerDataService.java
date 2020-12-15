package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDataDto;
import com.example.spring_boot_api.exceptions.CustomerDataServiceException;

public interface CustomerDataService {
    CustomerDataDto getCustomerDataDto(Long customerId) throws CustomerDataServiceException;
}
