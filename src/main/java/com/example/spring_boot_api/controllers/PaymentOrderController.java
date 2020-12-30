package com.example.spring_boot_api.controllers;


import com.example.spring_boot_api.dto.ListPaymentOrder;
import com.example.spring_boot_api.dto.PutLegalPaymentOrderRq;
import com.example.spring_boot_api.services.PaymentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/payment-orders", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class PaymentOrderController {

    private final PaymentOrderService paymentOrderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PutLegalPaymentOrderRq createPaymentOrder(@RequestBody @Valid PutLegalPaymentOrderRq putLegalPaymentOrderRq) {
        return paymentOrderService.createPaymentOrder(putLegalPaymentOrderRq);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PutLegalPaymentOrderRq> getPaymentOrder(@PathVariable("id") long id) {
        return paymentOrderService.getPaymentOrderById(id);
    }

    @GetMapping("/doc-ref/{number}")
    public ResponseEntity<ListPaymentOrder>  getPaymentOrder(@PathVariable("number") String number) {
        return paymentOrderService.getPaymentOrderByPaymentOrderNumber(number);
    }
}