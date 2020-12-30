package com.example.spring_boot_api.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String payerAccountInfoBankInfoVSPNum;

    private String docRef;

    private String operationType;

    private String cmsBase64;

    private String bankAccount;

    private int percent;
}
