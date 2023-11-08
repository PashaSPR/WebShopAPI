package com.example.webshopdip.dtos;



public class EvaluationsGoodDTO {
    private Long id; // Унікальний ідентифікатор Оцінки товарів
    private Long customerId; // ID покупця, який поставив оцінку
    private Long goodsId; // ID товару, який оцінюють
    private Integer evaluation; // Оцінка Товару

    public EvaluationsGoodDTO() {
        // Порожній конструктор (зазвичай потрібен для серіалізації)
    }

    // Геттери та сеттери для полів

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }
}