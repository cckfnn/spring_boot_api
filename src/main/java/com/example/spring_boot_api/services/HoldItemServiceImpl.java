package com.example.spring_boot_api.services;


import com.example.spring_boot_api.dto.HoldItemDto;
import com.example.spring_boot_api.exceptions.ExceptionDetails;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class HoldItemServiceImpl implements HoldItemService {

    @Value("${holdItemService.url}")
    private String holdItemServiceUrl;

    private final RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getHoldItemDtoReliable")
    public HoldItemDto getHoldItemDto(long customerId) {
        ResponseEntity<HoldItemDto> entity = restTemplate.getForEntity(String.format("%s/customers/%s/holds", holdItemServiceUrl, customerId), HoldItemDto.class);
        return entity.getBody();
    }

    @Async
    public CompletableFuture<HoldItemDto> getHoldItemDtoAsync(long customerId) {
       return CompletableFuture.completedFuture(getHoldItemDto(customerId));
    }

    public HoldItemDto getHoldItemDtoReliable(long customerId){
        return HoldItemDto.builder()
                .dateVal("hystryxDateval")
                .currency("hystryxCurrency")
                .summa(new BigDecimal(100))
                .registerType("hystyxRegisterType")
                .needReservation(true)
                .GUIDExternal("hystryxGuidEx")
                .GUIDRegister("hystryxGuidReg")
                .id(1L)
                .GUIDRequest("hystryxRequest")
                .priority(1)
                .build();
    }
}
