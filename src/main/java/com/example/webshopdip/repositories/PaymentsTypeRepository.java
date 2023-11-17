package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.PaymentsTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsTypeRepository extends JpaRepository<PaymentsTypeEntity, Long> {
    boolean findByPaymentType(String paymentType);
}
