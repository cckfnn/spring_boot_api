package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.CustomerDataDto;
import com.example.spring_boot_api.exceptions.CustomerDataServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CustomerDataServiceImpl implements CustomerDataService {

    @Value("${customerService.url}")
    private String customerService;

    private final RestTemplate restTemplate;

    @Override
    public CustomerDataDto getCustomerDataDto(Long customerId) {
        try{
            ResponseEntity<CustomerDataDto> responseEntity =
                    restTemplate.getForEntity(String.format("%s/customers/%s", customerService, customerId), CustomerDataDto.class);
            return responseEntity.getBody();
        } catch (HttpServerErrorException e){
            System.out.println(e.getStatusCode());
            throw new CustomerDataServiceException(String.format("CustomerDataService вернул ошибку %s", e.getStatusCode()));
        }
    }

    @Override
    @Async
    public CompletableFuture<CustomerDataDto> getCustomerDataDtoAsync(Long customerId) {
        return CompletableFuture.completedFuture(getCustomerDataDto(customerId));
    }
}
