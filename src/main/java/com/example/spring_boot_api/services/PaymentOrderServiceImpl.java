package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.ListPaymentOrder;
import com.example.spring_boot_api.entities.PaymentOrderEntity;
import com.example.spring_boot_api.repositories.PaymentOrderRepository;
import com.sbt.pprb.dto.srvputlegalpaymentorder.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentOrderServiceImpl implements PaymentOrderService{

    private final PaymentOrderRepository paymentOrderRepository;

    private PaymentOrderEntity dtoToEntity(PutLegalPaymentOrderRq putLegalPaymentOrderRq) {
        return PaymentOrderEntity.builder()
                .transKind(putLegalPaymentOrderRq.getPaymentOrder().getTransKind())
                .paymentOrderNumber(putLegalPaymentOrderRq.getPaymentOrder().getPaymentOrderNumber())
                .date(putLegalPaymentOrderRq.getPaymentOrder().getDate())
                .paymentSum(putLegalPaymentOrderRq.getPaymentOrder().getPaymentSum())
                .commissionSum(putLegalPaymentOrderRq.getPaymentOrder().getCommissionSum())
                .payerDataName(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getName())
                .payerDataAccount(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getAccount())
                .payerDataInn(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getInn())
                .payerDataKpp(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getKpp())
                .payerDataClientID(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getClientID())
                .payerDataProductUID(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getProductUID())
                .payerDataRegisterUID(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getRegisterUID())
                .payerDataBankDataName(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getBankData().getName())
                .payerDataBankDataBic(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getBankData().getBic())
                .payerDataBankDataBankAccount(putLegalPaymentOrderRq.getPaymentOrder().getPayerData().getBankData().getBankAccount())
                .payeeDataName(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getName())
                .payeeDataAccount(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getAccount())
                .payeeDataInn(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getInn())
                .payeeDataKpp(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getKpp())
                .payeeDataType(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getType())
                .payeeDataClientID(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getClientID())
                .payeeDataProductUID(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getProductUID())
                .payeeDataRegisterUID(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getRegisterUID())
                .payeeDataBankDataName(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getBankData().getName())
                .payeeDataBankDataBic(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getBankData().getBic())
                .payeeDataBankDataBankAccount(putLegalPaymentOrderRq.getPaymentOrder().getPayeeData().getBankData().getBankAccount())
                .paymentPurpose(putLegalPaymentOrderRq.getPaymentOrder().getPaymentPurpose())
                .paymentPriority(putLegalPaymentOrderRq.getPaymentOrder().getPaymentPriority())
                .paymentPriorityAuto(putLegalPaymentOrderRq.getPaymentOrder().getPaymentPriorityAuto())
                .paymentSendType(putLegalPaymentOrderRq.getPaymentOrder().getPaymentSendType())
                .paymentPurposeCode(putLegalPaymentOrderRq.getPaymentOrder().getPaymentPurposeCode())
                .taxFieldsPayerStatus(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getPayerStatus())
                .taxFieldsUinCode(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getUinCode())
                .taxFieldsKbk(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getKbk())
                .taxFieldsOktmo(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getOktmo())
                .taxFieldsPaymentBasis(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getPaymentBasis())
                .taxFieldsTaxPeriod(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getTaxPeriod())
                .taxFieldsDocumentTaxNumber(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getDocumentTaxNumber())
                .taxFieldsDocumentTaxDate(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getDocumentTaxDate())
                .taxFieldsTaxPayKind(putLegalPaymentOrderRq.getPaymentOrder().getTaxFields().getTaxPayKind())
                .payerAccountInfoBankInfoVSPNum(putLegalPaymentOrderRq.getPayerAccountInfoBankInfoVSPNum())
                .docRef(putLegalPaymentOrderRq.getDocRef())
                .operationType(putLegalPaymentOrderRq.getOperationType())
                .cmsBase64(putLegalPaymentOrderRq.getCmsBase64())
                .FirstSignerIdFirstSigner(putLegalPaymentOrderRq.getFirstSigner().getIdFirstSigner())
                .FirstSignerDateTimeFirstSigner(putLegalPaymentOrderRq.getFirstSigner().getDateTimeFirstSigner())
                .FirstSignerFirstSignatureFio(putLegalPaymentOrderRq.getFirstSigner().getFirstSignatureFio())
                .build();
    }

    private PutLegalPaymentOrderRq entityToDto(PaymentOrderEntity paymentOrderEntity){

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setTransKind(paymentOrderEntity.getTransKind());
        paymentOrder.setPaymentOrderNumber(paymentOrderEntity.getPaymentOrderNumber());
        paymentOrder.setDate(paymentOrderEntity.getDate());
        paymentOrder.setPaymentSum(paymentOrderEntity.getPaymentSum());
        paymentOrder.setCommissionSum(paymentOrder.getCommissionSum());
        paymentOrder.setPayerData(new PayerData(paymentOrderEntity.getPayerDataName(),
                paymentOrderEntity.getPayerDataAccount(),
                paymentOrderEntity.getPayerDataInn(),
                paymentOrderEntity.getPayerDataKpp(),
                paymentOrderEntity.getPayerDataClientID(),
                paymentOrderEntity.getPayerDataProductUID(),
                paymentOrderEntity.getPayerDataRegisterUID(),
                new PayerBankData(paymentOrderEntity.getPayerDataBankDataName(),
                        paymentOrderEntity.getPayerDataBankDataBic(),
                        paymentOrderEntity.getPayerDataBankDataBankAccount())));
        paymentOrder.setPayeeData(new PayeeData(paymentOrderEntity.getPayeeDataName(),
                paymentOrderEntity.getPayeeDataAccount(),
                paymentOrderEntity.getPayeeDataInn(),
                paymentOrderEntity.getPayeeDataKpp(),
                paymentOrderEntity.getPayeeDataType(),
                paymentOrderEntity.getPayeeDataClientID(),
                paymentOrderEntity.getPayeeDataProductUID(),
                paymentOrderEntity.getPayeeDataRegisterUID(),
                new PayeeBankData(paymentOrderEntity.getPayeeDataBankDataName(),
                        paymentOrderEntity.getPayeeDataBankDataBic(),
                        paymentOrderEntity.getPayeeDataBankDataBankAccount())));
        paymentOrder.setPaymentPurpose(paymentOrderEntity.getPaymentPurpose());
        paymentOrder.setPaymentPriority(paymentOrderEntity.getPaymentPriority());
        paymentOrder.setPaymentPriorityAuto(paymentOrderEntity.getPaymentPriorityAuto());
        paymentOrder.setPaymentSendType(paymentOrderEntity.getPaymentSendType());
        paymentOrder.setPaymentPurposeCode(paymentOrderEntity.getPaymentPurposeCode());
        paymentOrder.setTaxFields(new TaxFields(paymentOrderEntity.getTaxFieldsPayerStatus(),
                paymentOrderEntity.getTaxFieldsUinCode(),
                paymentOrderEntity.getTaxFieldsKbk(),
                paymentOrderEntity.getTaxFieldsOktmo(),
                paymentOrderEntity.getTaxFieldsPaymentBasis(),
                paymentOrderEntity.getTaxFieldsTaxPeriod(),
                paymentOrderEntity.getTaxFieldsDocumentTaxNumber(),
                paymentOrderEntity.getTaxFieldsDocumentTaxDate(),
                paymentOrderEntity.getTaxFieldsTaxPayKind()));
        paymentOrder.setCommissionSum(paymentOrderEntity.getCommissionSum());

        PutLegalPaymentOrderRq putLegalPaymentOrderRq =
                new PutLegalPaymentOrderRq(paymentOrder,
                        paymentOrderEntity.getPayerAccountInfoBankInfoVSPNum(),
                        paymentOrderEntity.getDocRef(),
                        paymentOrderEntity.getOperationType(),
                        paymentOrderEntity.getCmsBase64(),
                        new FirstSigner(paymentOrderEntity.getFirstSignerIdFirstSigner(),
                                paymentOrderEntity.getFirstSignerDateTimeFirstSigner(),
                                paymentOrderEntity.getFirstSignerFirstSignatureFio()));
        return putLegalPaymentOrderRq;
    }

    @Override
    public PutLegalPaymentOrderRq createPaymentOrder(PutLegalPaymentOrderRq putLegalPaymentOrderRq){
        PaymentOrderEntity paymentOrderEntity = dtoToEntity(putLegalPaymentOrderRq);
        return entityToDto(paymentOrderRepository.save(paymentOrderEntity));
    }

    @Override
    public ResponseEntity<PutLegalPaymentOrderRq> getPaymentOrderById(long id){
        Optional<PaymentOrderEntity> entityOptional = paymentOrderRepository.findById(id);
        return entityOptional.map(paymentOrderEntity ->
                ResponseEntity.ok(entityToDto(paymentOrderEntity))).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<ListPaymentOrder>  getPaymentOrderByPaymentOrderNumber(String paymentOrderNumber) {
        List<PaymentOrderEntity> entityList = paymentOrderRepository.findByPaymentOrderNumber(paymentOrderNumber);
        if (!entityList.isEmpty()) {
            ListPaymentOrder listPaymentOrder = new ListPaymentOrder();
            listPaymentOrder.setPutLegalPaymentOrderRqList(entityList.stream()
                    .map(this::entityToDto)
                    .collect(Collectors.toList()));
            return ResponseEntity.ok(listPaymentOrder);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
