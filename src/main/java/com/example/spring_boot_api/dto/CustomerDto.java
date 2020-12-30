package com.example.spring_boot_api.dto;

import lombok.*;

import javax.validation.Valid;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -7738961298826188881L;
    @Valid
    private HoldItemDto holdItemDto;

    @Valid
    private CustomerDataDto customerDataDto;
}
