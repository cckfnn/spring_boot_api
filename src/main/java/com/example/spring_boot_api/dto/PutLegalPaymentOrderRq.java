package com.example.spring_boot_api.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutLegalPaymentOrderRq implements Serializable {

    private static final long serialVersionUID = 1383504060214961234L;

    private long id;

    protected String payerAccountInfoBankInfoVSPNum;

    protected String docRef;

    protected String operationType;

    protected String cmsBase64;

}