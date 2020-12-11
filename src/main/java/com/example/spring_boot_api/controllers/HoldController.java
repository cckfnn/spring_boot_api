package com.example.spring_boot_api.controllers;


import com.example.spring_boot_api.dto.HoldItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/holds", produces = {MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class HoldController {


    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HoldItemDto createHold(@RequestBody HoldItemDto holdItemDto) {
        return null;
    }
}
