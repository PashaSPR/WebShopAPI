package com.example.webshopdip.dtos;

public class SellersDTO {
    private Long id; // Унікальний ідентифікатор Продавця Товарів
    private String name; // Ім'я(повне) Продавця Товарів

    public SellersDTO() {
    }

    public SellersDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
