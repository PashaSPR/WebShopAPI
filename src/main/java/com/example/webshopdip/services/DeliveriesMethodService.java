package com.example.webshopdip.services;

import com.example.webshopdip.entities.DeliveriesMethodEntity;
import com.example.webshopdip.repositories.DeliveriesMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveriesMethodService {

    @Autowired
    private DeliveriesMethodRepository deliveriesMethodRepository;

    public DeliveriesMethodEntity createDeliveriesMethod(DeliveriesMethodEntity deliveriesMethodEntity) throws Exception {
        try {
            // Перевірка чи такий об'єкт вже існує
            if (deliveriesMethodRepository.findByMethod(deliveriesMethodEntity.getMethod()) != null) {
                throw new Exception("Такий метод доставки вже існує");
            }

            // Збереження переданого об'єкта
            return deliveriesMethodRepository.save(deliveriesMethodEntity);
        } catch (Exception e) {
            throw new Exception("Помилка при створенні об'єкта DeliveriesMethodEntity", e);
        }
    }
}
