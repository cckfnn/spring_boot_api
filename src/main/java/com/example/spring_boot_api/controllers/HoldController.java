package com.example.spring_boot_api.controllers;


import com.example.spring_boot_api.dto.HoldItemDto;
import com.example.spring_boot_api.services.HoldItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/holds", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class HoldController {

    private final HoldItemService holdItemService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HoldItemDto getHoldItem(@PathVariable("id") long id) {
        return holdItemService.getHoldItemDto(id);
    }
}
