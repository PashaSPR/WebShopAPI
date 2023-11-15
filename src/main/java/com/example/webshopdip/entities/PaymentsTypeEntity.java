package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Тип оплати за Товар".
 * Містить дані про Типи оплати за Товар.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "paymentstype")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class PaymentsTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Типу оплати
    private String payment_type; // Тип оплати

    /////////сутності що мають відношення One-to-Many з сутністю OrdersLists
    @OneToMany(mappedBy = "paymentsType")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Тип оплати за Товар може мати багато Переліків Товарів
    private List<OrdersListsEntity> ordersLists = new ArrayList<>();
    public PaymentsTypeEntity() {
    }
    public PaymentsTypeEntity(Long id, String payment_type, List<OrdersListsEntity> ordersLists) {
        this.id = id;
        this.payment_type = payment_type;
        this.ordersLists = ordersLists;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public List<OrdersListsEntity> getOrdersLists() {
        return ordersLists;
    }

    public void setOrdersLists(List<OrdersListsEntity> ordersLists) {
        this.ordersLists = ordersLists;
    }


}
