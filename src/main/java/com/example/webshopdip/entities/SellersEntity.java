package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Продавці Товарів".
 * Містить дані та поведінку, пов'язані з Продавцями Товарів.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "sellers")
public class SellersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Продавця Товарів
    private String name; // Ім'я(повне) Продавця Товарів
    private String address; // Адреса Продавця Товарів
    private String phone; // Телефон Продавця Товарів
    private String email; // Електронна скринька Продавця Товарів
    @OneToOne
    @JoinColumn(name = "usersLists_id", referencedColumnName = "id")
    // Зв'язок One-to-One: Один Продавець Товарів відповідає одному Користувачу
    private UsersListsEntity usersLists;

    /////////сутності що мають відношення One-to-Many з сутністю Sellers
    @OneToMany(mappedBy = "sellers")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Продавець Товарів може мати багато Прибуткових переліків Товарів до магазину
    private List<GoodsInvoicesEntity> goodsInvoices = new ArrayList<>();

    public SellersEntity() {
    }

    public SellersEntity(
            Long id,
            String name,
            String address,
            String phone,
            String email,
            UsersListsEntity usersLists,
            List<GoodsInvoicesEntity> goodsInvoices
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.usersLists = usersLists;
        this.goodsInvoices = goodsInvoices;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsersListsEntity getUsersLists() {
        return usersLists;
    }

    public void setUsersLists(UsersListsEntity usersLists) {
        this.usersLists = usersLists;
    }

    public List<GoodsInvoicesEntity> getGoodsInvoices() {
        return goodsInvoices;
    }

    public void setGoodsInvoices(List<GoodsInvoicesEntity> goodsInvoices) {
        this.goodsInvoices = goodsInvoices;
    }
}
