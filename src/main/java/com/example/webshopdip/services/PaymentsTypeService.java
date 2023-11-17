package com.example.webshopdip.services;

import com.example.webshopdip.entities.PaymentsTypeEntity;
import com.example.webshopdip.repositories.PaymentsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PaymentsTypeService {
    @Autowired
    PaymentsTypeRepository paymentsTypeRepository;

    public PaymentsTypeEntity createPaymentsType(PaymentsTypeEntity paymentsTypeEntity) throws Exception {
        try {
            // Перевірка чи такий об'єкт вже існує
            if (paymentsTypeRepository.findByPaymentType(paymentsTypeEntity.getPaymentType())) {
                throw new Exception("Такий тип оплати вже існує");
            }
            PaymentsTypeEntity entity=new PaymentsTypeEntity();
            entity.setPaymentType(paymentsTypeEntity.getPaymentType());

            return paymentsTypeRepository.save(entity);
        } catch (Exception e) {
            throw new Exception("Помилка при створенні об'єкта PaymentsTypeEntity", e);
        }
    }
}
