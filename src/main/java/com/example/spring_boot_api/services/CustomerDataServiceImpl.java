package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDataDto;
import com.example.spring_boot_api.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerDataServiceImpl implements CustomerDataService {

    @Value("${customerService.url}")
    private String customerService;

    private final RestTemplate restTemplate;

    @Override
    public CustomerDataDto getCustomerDataDto(Long customerId) {
        return restTemplate.getForObject(String.format("%s/customers/%s/account", customerService, customerId), CustomerDataDto.class);
    }

}
