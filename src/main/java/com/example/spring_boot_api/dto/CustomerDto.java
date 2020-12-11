package com.example.spring_boot_api.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -7738961298826188881L;
    private HoldItemDto holdItemDto;
    private CustomerDataDto customerDataDto;
}
