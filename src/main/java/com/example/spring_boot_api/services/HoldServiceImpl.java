package com.example.spring_boot_api.services;

import com.example.spring_boot_api.entities.HoldEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HoldServiceImpl implements HoldService {

    private final HoldEntity holdEntity;

    private final ModelMapper modelMapper;

    @Override
    public HoldEntity createHold(HoldEntity holdEntity) {

        return holdEntity;
    }
}
