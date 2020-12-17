package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.HoldItemDto;

import java.util.concurrent.CompletableFuture;

public interface HoldItemService {
    HoldItemDto getHoldItemDto(long customerId);
    CompletableFuture<HoldItemDto> getHoldItemDtoAsync(long customerId);
}
