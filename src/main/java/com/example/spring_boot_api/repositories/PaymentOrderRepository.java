package com.example.spring_boot_api.repositories;

import com.example.spring_boot_api.entities.PaymentOrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentOrderRepository extends CrudRepository<PaymentOrderEntity, Long> {
    List<PaymentOrderEntity> findByDocRef(String paymentOrderNumber);
}
