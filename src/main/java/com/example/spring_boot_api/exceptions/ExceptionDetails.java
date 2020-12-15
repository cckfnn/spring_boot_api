package com.example.spring_boot_api.exceptions;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExceptionDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String path;
}