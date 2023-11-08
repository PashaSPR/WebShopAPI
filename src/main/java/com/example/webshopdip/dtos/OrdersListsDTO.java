package com.example.webshopdip.dtos;

import com.example.webshopdip.entities.CustomersEntity;
import com.example.webshopdip.entities.DeliveriesMethodEntity;
import com.example.webshopdip.entities.GoodsOrdersEntity;
import com.example.webshopdip.entities.PaymentsTypeEntity;

import java.util.ArrayList;
import java.util.List;

public class OrdersListsDTO {
    private Long id; // Унікальний ідентифікатор категорії товару
    private String number; //Номер Замовлення на Товари

// Зв'язок Many-to-One: Багато Замовлень на Товари може стосуватися одного Продавця
    private CustomersEntity customers;

// Зв'язок One-to-One: Багато Замовлень на Товари відповідає одному Типу оплати за товар
    private PaymentsTypeEntity paymentsType;

// Зв'язок One-to-One: Багато Замовлень на Товари відповідає одному Методу доставлення товару
    private DeliveriesMethodEntity deliveriesMethod;
    private String address_delivery;  // Адреса доставлення товару

    /////////сутності що мають відношення One-to-Many з сутністю OrdersLists

// Зв'язок One-to-Many: Одне Замовлення на Товари може мати багато Переліків Товарів
    private List<GoodsOrdersEntity> goodsOrders = new ArrayList<>();

    public OrdersListsDTO() {
    }

    public OrdersListsDTO(Long id, String number, CustomersEntity customers, PaymentsTypeEntity paymentsType, DeliveriesMethodEntity deliveriesMethod, String address_delivery, List<GoodsOrdersEntity> goodsOrders) {
        this.id = id;
        this.number = number;
        this.customers = customers;
        this.paymentsType = paymentsType;
        this.deliveriesMethod = deliveriesMethod;
        this.address_delivery = address_delivery;
        this.goodsOrders = goodsOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CustomersEntity getCustomers() {
        return customers;
    }

    public void setCustomers(CustomersEntity customers) {
        this.customers = customers;
    }

    public PaymentsTypeEntity getPaymentsType() {
        return paymentsType;
    }

    public void setPaymentsType(PaymentsTypeEntity paymentsType) {
        this.paymentsType = paymentsType;
    }

    public DeliveriesMethodEntity getDeliveriesMethod() {
        return deliveriesMethod;
    }

    public void setDeliveriesMethod(DeliveriesMethodEntity deliveriesMethod) {
        this.deliveriesMethod = deliveriesMethod;
    }

    public String getAddress_delivery() {
        return address_delivery;
    }

    public void setAddress_delivery(String address_delivery) {
        this.address_delivery = address_delivery;
    }

    public List<GoodsOrdersEntity> getGoodsOrders() {
        return goodsOrders;
    }

    public void setGoodsOrders(List<GoodsOrdersEntity> goodsOrders) {
        this.goodsOrders = goodsOrders;
    }
}
