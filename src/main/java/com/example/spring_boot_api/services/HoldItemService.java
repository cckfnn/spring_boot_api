package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.HoldItemDto;

public interface HoldItemService {
    HoldItemDto getHoldItemDto(long customerId);
}
