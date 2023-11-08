package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Оцінка Товарів".
 * Містить дані Оцінку товарів, надану покупцями,які придбали даний товар.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "evaluationsgood")
public class EvaluationsGoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Оцінки товарів
    private Integer evaluation; // Оцінка Товару
    /////////сутності що мають відношення Many-to-One з сутністю Customers
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато оцінок може належати одному товару
    private GoodsEntity goods;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Покупців можуть поставити одну оцінку на різні товарів
    private CustomersEntity customers;

    public EvaluationsGoodEntity() {
    }

    public EvaluationsGoodEntity(Long id, Integer evaluation, GoodsEntity goods, CustomersEntity customers) {
        this.id = id;
        this.evaluation = evaluation;
        this.goods = goods;
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public CustomersEntity getCustomers() {
        return customers;
    }

    public void setCustomers(CustomersEntity customers) {
        this.customers = customers;
    }
}
