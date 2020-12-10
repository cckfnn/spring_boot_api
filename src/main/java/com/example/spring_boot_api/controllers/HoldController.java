package com.example.spring_boot_api.controllers;

import com.example.spring_boot_api.dto.HoldItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/holds", produces = {MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class HoldController {

    private final HoldItem holdItem;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HoldItem createHold(@RequestBody HoldItem holdItem) {
        return null;
    }
}
