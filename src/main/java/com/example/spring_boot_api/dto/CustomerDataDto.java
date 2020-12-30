package com.example.spring_boot_api.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDataDto implements Serializable  {

    private static final long serialVersionUID = -7460859531720528159L;

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Поле firstName должно содержать от 2 до 20 символов")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Поле lastName должно содержать от 2 до 20 символов")
    private String lastName;

    @NotEmpty
    private Boolean isCorporateClient;

    @NotEmpty
    private Integer serviceLevel;
}
