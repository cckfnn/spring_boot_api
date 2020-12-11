package com.example.spring_boot_api.services;


import com.example.spring_boot_api.dto.ListPaymentOrder;
import com.example.spring_boot_api.dto.PutLegalPaymentOrderRq;
import org.springframework.http.ResponseEntity;


public interface PaymentOrderService {

    PutLegalPaymentOrderRq createPaymentOrder(PutLegalPaymentOrderRq putLegalPaymentOrderRq);
    ResponseEntity<PutLegalPaymentOrderRq> getPaymentOrderById(long id);
    ResponseEntity<ListPaymentOrder> getPaymentOrderByPaymentOrderNumber(String paymentOrderNumber);
}
