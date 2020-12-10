package com.example.spring_boot_api.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

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

    
    protected BigDecimal paymentSum;
    
    protected BigDecimal commissionSum;
    
    protected String payerDataName;
    
    protected String payerDataAccount;
    
    protected String payerDataInn;
    
    protected String payerDataKpp;
    
    protected String payerDataClientID;
    
    protected String payerDataProductUID;
    
    protected String payerDataRegisterUID;
    
    protected String payerDataBankDataName;
    
    protected String payerDataBankDataBic;
    
    protected String payerDataBankDataBankAccount;
    
    protected String payeeDataName;
    
    protected String payeeDataAccount;
    
    protected String payeeDataInn;
    
    protected String payeeDataKpp;
    
    protected String payeeDataType;
    
    protected String payeeDataClientID;
    
    protected String payeeDataProductUID;
    
    protected String payeeDataRegisterUID;
    
    protected String payeeDataBankDataName;
    
    protected String payeeDataBankDataBic;
    
    protected String payeeDataBankDataBankAccount;
    
    protected String paymentPurpose;
    
    protected String paymentPriority;
    
    protected String paymentPriorityAuto;
    
    protected String paymentSendType;
    
    protected String paymentPurposeCode;
    
    protected String taxFieldsPayerStatus;
    
    protected String taxFieldsUinCode;
    
    protected String taxFieldsKbk;
    
    protected String taxFieldsOktmo;
    
    protected String taxFieldsPaymentBasis;
    
    protected String taxFieldsTaxPeriod;
    
    protected String taxFieldsDocumentTaxNumber;
    
    protected String taxFieldsDocumentTaxDate;
    
    protected String taxFieldsTaxPayKind;
    
    protected String payerAccountInfoBankInfoVSPNum;
    
    protected String docRef;
    
    protected String operationType;
    
    protected String cmsBase64;
    
    protected String FirstSignerIdFirstSigner;
    
    protected Calendar FirstSignerDateTimeFirstSigner;
    
    protected String FirstSignerFirstSignatureFio;

    
    private String transKind;
    
    private String paymentOrderNumber;
    @Temporal(TemporalType.DATE)
    
    private Calendar date;

}
