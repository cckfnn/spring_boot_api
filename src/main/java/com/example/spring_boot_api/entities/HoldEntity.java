package com.example.spring_boot_api.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HoldEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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