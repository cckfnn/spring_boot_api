package com.example.spring_boot_api.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@RequiredArgsConstructor
@ToString
@Getter
public class ExceptionDetails {
    private final Date timestamp;
    private final String message;
    private final String details;
    private final String path;
}