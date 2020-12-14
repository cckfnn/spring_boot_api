package com.example.spring_boot_api.controllers;

import com.example.spring_boot_api.dto.CustomerDto;
import com.example.spring_boot_api.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers-info", produces = {MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class CustomerDtoController {
    private final CustomerService customerService;

    @GetMapping("{id}")
    public CustomerDto getCustomerInfoService(@PathVariable Long id) {
        return customerService.getCustomerDto(id);
    }
}
