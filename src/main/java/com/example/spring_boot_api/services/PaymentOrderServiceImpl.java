package com.example.spring_boot_api.services;

import com.example.spring_boot_api.dto.ListPaymentOrder;
import com.example.spring_boot_api.dto.PutLegalPaymentOrderRq;
import com.example.spring_boot_api.entities.PaymentOrderEntity;
import com.example.spring_boot_api.repositories.PaymentOrderRepository;
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
                .payerAccountInfoBankInfoVSPNum(putLegalPaymentOrderRq.getPayerAccountInfoBankInfoVSPNum())
                .docRef(putLegalPaymentOrderRq.getDocRef())
                .operationType(putLegalPaymentOrderRq.getOperationType())
                .cmsBase64(putLegalPaymentOrderRq.getCmsBase64())
                .build();
    }

    private PutLegalPaymentOrderRq entityToDto(PaymentOrderEntity paymentOrderEntity){

        return new PutLegalPaymentOrderRq(
                paymentOrderEntity.getId(),
                paymentOrderEntity.getPayerAccountInfoBankInfoVSPNum(),
                paymentOrderEntity.getDocRef(),
                paymentOrderEntity.getOperationType(),
                paymentOrderEntity.getCmsBase64());
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
    public ResponseEntity<ListPaymentOrder>  getPaymentOrderByPaymentOrderNumber(String docRef) {
        List<PaymentOrderEntity> entityList = paymentOrderRepository.findByDocRef(docRef);
        if (!entityList.isEmpty()) {
            ListPaymentOrder listPaymentOrder = new ListPaymentOrder();
            listPaymentOrder.setPutLegalPaymentOrderRqList(entityList.stream()
                    .map(this::entityToDto)
                    .collect(Collectors.toList()));
            return ResponseEntity.ok(listPaymentOrder);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
