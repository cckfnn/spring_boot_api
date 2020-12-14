package com.example.spring_boot_api.services;


import com.example.spring_boot_api.dto.HoldItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class HoldItemServiceImpl implements HoldItemService {

    @Value("${holdItemService.url}")
    private String holdItemServiceUrl;

    private final RestTemplate restTemplate;

    @Override
    public HoldItemDto getHoldItemDto(long customerId) {
        return restTemplate.getForObject(String.format("%s/customers/%s/account", holdItemServiceUrl, customerId), HoldItemDto.class);
    }
}
