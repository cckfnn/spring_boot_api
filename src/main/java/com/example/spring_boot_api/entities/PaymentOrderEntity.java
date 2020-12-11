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

    protected String payerAccountInfoBankInfoVSPNum;

    protected String docRef;

    protected String operationType;

    protected String cmsBase64;


}
