package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.HoldItemDto;

public interface HoldService {
   HoldItemDto getHoldItemDto(Long customerId);
}
