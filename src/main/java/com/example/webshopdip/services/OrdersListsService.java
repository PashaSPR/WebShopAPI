//package com.example.webshopdip.services;
//
//import com.example.webshopdip.dtos.GoodsInvoicesDTO;
//import com.example.webshopdip.dtos.GoodsOrdersDTO;
//import com.example.webshopdip.dtos.OrdersListsDTO;
//import com.example.webshopdip.entities.*;
//import com.example.webshopdip.exceptions.CategoriesGoodsNotFoundException;
//import com.example.webshopdip.exceptions.OrdersListsAlreadyExistException;
//import com.example.webshopdip.repositories.CustomersRepository;
//import com.example.webshopdip.repositories.GoodsOrdersRepository;
//import com.example.webshopdip.repositories.OrdersListsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class OrdersListsService {
//    @Autowired
//    private OrdersListsRepository ordersListsRepository;
//    @Autowired
//    private CustomersRepository customersRepository;
//    @Autowired
//    private GoodsOrdersRepository goodsOrdersRepository;
//
//    //    public OrdersListsDTO createOrdersLists(OrdersListsDTO dto) throws OrdersListsAlreadyExistException {
////        if (ordersListsRepository.findByNumber(dto.getNumber()) != null) {//dto.getNumber()) != null
////            throw new OrdersListsAlreadyExistException("Такий номер вже існує");
////        }
////
////        OrdersListsEntity entity = new OrdersListsEntity();
////        entity.setNumber(dto.getNumber());
////        entity.setCustomers(dto.getCustomers(entity.getCustomers()));
////        entity.setPaymentsType(dto.getPaymentsType());
////        entity.setDeliveriesMethod(dto.getDeliveriesMethod());
////        entity.setAddress_delivery(dto.getAddress_delivery(entity.getAddress_delivery()));
////        entity.setGoodsOrders(dto.getGoodsOrders(entity.getGoodsOrders()));
////
////        OrdersListsEntity savedEntity = ordersListsRepository.save(entity);
////
////        return entityToDTO(savedEntity);
////    }
//    public OrdersListsDTO addGoodsToOrder(Long customerId) {
//
//        CustomersEntity customers = customersRepository.findById(customerId).orElse(null);
//        OrdersListsEntity ordersLists = new OrdersListsEntity();
//        ordersLists.setCustomers(customers);
//        ordersListsRepository.save(ordersLists);
//        return  entityToDTO(ordersLists);
//    }
//
//    public OrdersListsDTO entityToDTO(OrdersListsEntity entity) {
//        OrdersListsDTO dto = new OrdersListsDTO();
//        dto.setId(entity.getId());
//        dto.setNumber(entity.getNumber());
//        dto.setCustomers(entity.getCustomers());
//        dto.setPaymentsType(entity.getPaymentsType());
//        dto.setAddress_delivery(entity.getAddress_delivery());
//        dto.setGoodsOrders(entity.getGoodsOrders());
//        return dto;
//    }
//
//    public OrdersListsEntity getOne(Long id) throws CategoriesGoodsNotFoundException {
//        Optional<OrdersListsEntity> optional = ordersListsRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new CategoriesGoodsNotFoundException("Категорію товару не знайдено");
//        }
//        return optional.get();
//    }
//
//
//}
package com.example.webshopdip.services;

import com.example.webshopdip.dtos.GoodsInvoicesDTO;
import com.example.webshopdip.dtos.GoodsOrdersDTO;
import com.example.webshopdip.dtos.OrdersListsDTO;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.exceptions.CategoriesGoodsNotFoundException;
import com.example.webshopdip.exceptions.OrdersListsAlreadyExistException;
import com.example.webshopdip.exceptions.OrdersListsNotFoundException;
import com.example.webshopdip.repositories.CustomersRepository;
import com.example.webshopdip.repositories.GoodsOrdersRepository;
import com.example.webshopdip.repositories.OrdersListsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
//@Service
//public class OrdersListsService {
//
//    @Autowired
//    private OrdersListsRepository ordersListsRepository;
//
//    @Autowired
//    private GoodsOrdersRepository goodsOrdersRepository;
//
//    public void processOrder(OrdersListsDTO ordersListsDTO) {
//        // Отримайте дані з об'єкту DTO
//        String orderNumber = ordersListsDTO.getNumber();
//        CustomersEntity customers = ordersListsDTO.getCustomers();
//        PaymentsTypeEntity paymentsType = ordersListsDTO.getPaymentsType();
//        DeliveriesMethodEntity deliveriesMethod = ordersListsDTO.getDeliveriesMethod();
//        String addressDelivery = ordersListsDTO.getAddress_delivery();
//        List<GoodsOrdersDTO> goodsOrdersDTOList = ordersListsDTO.getGoodsOrders();
//
//        // Спробуйте знайти запис OrdersLists з номером замовлення
//        OrdersListsEntity ordersLists = ordersListsRepository.findByNumber(orderNumber);
//
//        if (ordersLists == null) {
//            // Якщо такого запису немає, створіть новий
//            ordersLists = new OrdersListsEntity();
//        }
//
//        // Оновіть дані у об'єкті OrdersListsEntity
//        ordersLists.setNumber(orderNumber);
//        ordersLists.setCustomers(customers);
//        ordersLists.setPaymentsType(paymentsType);
//        ordersLists.setDeliveriesMethod(deliveriesMethod);
//        ordersLists.setAddress_delivery(addressDelivery);
//
//        // Збережіть або оновіть запис OrdersLists
//        ordersListsRepository.save(ordersLists);
//
//        // Додайте або оновіть товари в замовленні
//        for (GoodsOrdersDTO goodsOrdersDTO : goodsOrdersDTOList) {
//            GoodsInvoicesDTO goodsInvoicesDTO = goodsOrdersDTO.getGoodsInvoicesDTO();
//            Float price = goodsInvoicesDTO.getPrice();
//            Integer quantity = goodsInvoicesDTO.getQuantity();
//
//            GoodsOrdersEntity goodsOrdersEntity = new GoodsOrdersEntity();
//            goodsOrdersEntity.setOrdersLists(ordersLists);
//            goodsOrdersEntity.setGoodsInvoicesId(goodsInvoicesDTO.getId());
//            goodsOrdersEntity.setPrice(price);
//            goodsOrdersEntity.setQuantity(quantity);
//
//            goodsOrdersRepository.save(goodsOrdersEntity);
//        }
//    }
//}

@Service
public class OrdersListsService {
    @Autowired
    private OrdersListsRepository ordersListsRepository;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private GoodsOrdersRepository goodsOrdersRepository;


    public OrdersListsDTO createOrdersLists(OrdersListsDTO dto) throws OrdersListsAlreadyExistException {
        if (ordersListsRepository.findByNumber(dto.getNumber()) != null) {//dto.getNumber()) != null
            throw new OrdersListsAlreadyExistException("Такий номер вже існує");
        }
        //CustomersEntity customers = customersRepository.findById(dto.getCustomers().getId()).orElse(null);
        //знайти запис number конкретного покупця в базі даних
        //якщо номера немає створити запис в таблицю orderList:number,customerId
        //інакше дописати (редагувати) поля:
        //entity.setGoodsOrders(dto.getGoodsOrders()); має бути колекцією
        //можливо замінити на paymentsTypeId і т.д.
        //або ж переделеговувати на котроллер або сервіс створення в кожній таблиці

        OrdersListsEntity entity = new OrdersListsEntity();
        entity.setNumber(dto.getNumber());
        entity.setCustomers(dto.getCustomers());
        entity.setPaymentsType(dto.getPaymentsType());
        entity.setDeliveriesMethod(dto.getDeliveriesMethod());
        entity.setAddress_delivery(dto.getAddress_delivery());
        entity.setGoodsOrders(dto.getGoodsOrders());

        ordersListsRepository.save(entity);//OrdersListsEntity savedEntity =

        return entityToDTO(entity);
    }
    //    public OrdersListsDTO getOrdersListByCustomer(Long customerId) {
    public OrdersListsDTO getOrdersListByCustomer(Long customerId) {
        //
        CustomersEntity customers = customersRepository.findById(customerId).orElse(null);
        OrdersListsEntity ordersLists = new OrdersListsEntity();
        ordersLists.setCustomers(customers);
        //ordersListsRepository.save(ordersLists);
        return  entityToDTO(ordersLists);//Якого воно зберігає в репозиторій якщо GET
    }
    public OrdersListsDTO entityToDTO(OrdersListsEntity entity) {
        OrdersListsDTO dto = new OrdersListsDTO();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setCustomers(entity.getCustomers());
        dto.setPaymentsType(entity.getPaymentsType());
        dto.setAddress_delivery(entity.getAddress_delivery());
        dto.setGoodsOrders(entity.getGoodsOrders());
        return dto;
    }

    public OrdersListsEntity getOne(Long id) throws CategoriesGoodsNotFoundException, OrdersListsNotFoundException {
        Optional<OrdersListsEntity> optional = ordersListsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new OrdersListsNotFoundException("Замовлення товару не знайдено");
        }
        return optional.get();
    }


}
