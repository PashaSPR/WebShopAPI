package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Метод доставки Товарів".
 * Містить Методи доставки товарів покупцеві.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "deliveriesmethod")
public class DeliveriesMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Методу
    private String method; // Метод доставки Товарів

    /////////сутності що мають відношення One-to-Many з сутністю OrdersLists
    @OneToMany(mappedBy = "deliveriesMethod")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Метод доставки Товарів може мати багато Переліків Товарів
    private List<OrdersListsEntity> ordersLists = new ArrayList<>();

    public DeliveriesMethodEntity(Long id, String method, List<OrdersListsEntity> ordersLists) {
        this.id = id;
        this.method = method;
        this.ordersLists = ordersLists;
    }

    public DeliveriesMethodEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<OrdersListsEntity> getOrdersLists() {
        return ordersLists;
    }

    public void setOrdersLists(List<OrdersListsEntity> ordersLists) {
        this.ordersLists = ordersLists;
    }
}
