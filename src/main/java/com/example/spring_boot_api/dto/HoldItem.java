package com.example.spring_boot_api.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoldItem implements Serializable {

    private static final long serialVersionUID = 7289282703374645131L;

    private Long id;
    private String GUIDRegister;
    private String GUIDExternal;
    private String GUIDRequest;
    private Boolean needReservation;
    private String registerType;
    private BigDecimal summa;
    private String currency;
    private Integer priority;
    private String dateVal;
}