package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.HoldItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HoldServiceImpl implements HoldService {


    @Override
    public HoldItemDto getHoldItemDto(Long customerId) {
        return null;
    }
}

