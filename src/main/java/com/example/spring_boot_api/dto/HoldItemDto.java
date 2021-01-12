package com.example.spring_boot_api.dto;

import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoldItemDto implements Serializable {

    private static final long serialVersionUID = -7778857769963197745L;


    private Long id;

    @NotEmpty
    private String GUIDRegister;

    @NotEmpty
    private String GUIDExternal;

    @NotEmpty
    private String GUIDRequest;

    @NotNull
    private Boolean needReservation;

    @NotEmpty
    private String registerType;

    @NotEmpty
    @PositiveOrZero(message = "Значение summa не может быть отрицательным")
    private BigDecimal summa;

    @NotEmpty
    private String currency;

    @NotEmpty
    @Min(value = 1, message ="Значение priority не должно быть меньше 1")
    @Max(value = 3, message ="Значение priority не должно быть больше 3")
    private Integer priority;

    @NotEmpty
    private String dateVal;
}