package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.OrdersListsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersListsRepository extends JpaRepository<OrdersListsEntity,Long> {
    OrdersListsEntity findByNumber (String number);//по номеру замовлення
    OrdersListsEntity findByNumberIsNullAndCustomersIsNull  ();//по id замовлення

    List<OrdersListsEntity> findByCustomers_Id(Long id);

}