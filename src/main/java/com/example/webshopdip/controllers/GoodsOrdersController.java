//package com.example.webshopdip.controllers;
//
//import com.example.webshopdip.dtos.*;
//import com.example.webshopdip.entities.GoodsEntity;
//import com.example.webshopdip.entities.GoodsInvoicesEntity;
//import com.example.webshopdip.entities.PropertiesGoodsEntity;
//import com.example.webshopdip.entities.PropertiesNameGoodsEntity;
//import com.example.webshopdip.repositories.GoodsInvoicesRepository;
//import com.example.webshopdip.repositories.GoodsRepository;
//import com.example.webshopdip.services.GoodsInvoicesService;
//import com.example.webshopdip.services.GoodsOrdersService;
//import com.example.webshopdip.services.GoodsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/goodsOrders")
//public class GoodsOrdersController {
//
//    @Autowired
//    private GoodsOrdersService goodsOrdersService;
//    @Autowired
//    private GoodsService goodsService;
//    @Autowired
//    private GoodsInvoicesService goodsInvoicesService;
//    @Autowired
//    private GoodsInvoicesRepository goodsInvoicesRepository;
//    @Autowired
//    private GoodsRepository goodsRepository;
//
//    @PostMapping
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody GoodsOrdersDTO goodsOrdersDTO) {
//
//        try {
//            Optional<GoodsEntity> goodsEntityOptional = goodsRepository.findById(goodsOrdersDTO.getGoodsInvoicesDTO().getGoods().getId());
//
//            if (goodsEntityOptional.isPresent()) {
//                GoodsEntity goodsEntity = goodsEntityOptional.get();
//
//                GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.getOne(goodsOrdersDTO.getGoodsInvoicesDTO().getId());
//                goodsInvoicesDTO.setGoods(goodsService.entityToDTO(goodsEntity));
//
//                goodsOrdersDTO.setGoodsInvoicesDTO(goodsInvoicesDTO);
//
////                GoodsOrdersDTO createdDTO = goodsOrdersService.createGoodsOrders(goodsOrdersDTO);
////                return ResponseEntity.ok(createdDTO);
//                return ResponseEntity.ok(goodsOrdersDTO);
//            } else {
//                return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити випадок, коли товар не знайдено
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити помилку
//        }
//    }



package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.exceptions.GoodsOrdersNotFoundException;
import com.example.webshopdip.repositories.*;
import com.example.webshopdip.services.GoodsInvoicesService;
import com.example.webshopdip.services.GoodsOrdersService;
import com.example.webshopdip.services.GoodsService;
import com.example.webshopdip.services.OrdersListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/goodsOrders")
public class GoodsOrdersController {

    @Autowired
    private GoodsOrdersService goodsOrdersService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrdersListsService ordersListsService;
    @Autowired
    private GoodsInvoicesService goodsInvoicesService;
    @Autowired
    private OrderListsController orderListsController;
    @Autowired
    private OrdersListsRepository ordersListsRepository;
    @Autowired
    private GoodsOrdersRepository goodsOrdersRepository;
    @Autowired
    private GoodsInvoicesRepository goodsInvoicesRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private CustomersRepository customersRepository;

    /*
    id customers через ордер ліст з перевіркою
    id goodsInvoices
    quantity з goodsInvoices(не більше ніж залишок в goodsInvoices)
    price ставить автоматом з goodsInvoices (продавець)
    createGoodsOrders(id goodsInvoices,){
   перевіряє чи є запис з конкретним id customers та порожнім номером(number) замовлення,тобто порожнім записом,
   якщо є то id цього запису додаємо orderListId
   якщо немає то створюємо createOrdersList та беремо id новоствореного запису і додаємо запис orderListId
    orderListsController.createOrdersLists(1);
    }
    * */
    @PostMapping
    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody GoodsOrdersToSaveDTO goodsOrdersToSaveDTO) {

        try {
            Optional<GoodsInvoicesEntity> goodsInvoicesEntityOptional = goodsInvoicesRepository.findById(goodsOrdersToSaveDTO.getGoodsInvoicesId());
//            System.out.println("goodsInvoicesEntityOptional: " + goodsInvoicesEntityOptional);
//            System.out.println("goodsOrdersDTO: " + goodsOrdersDTO);
            if (goodsInvoicesEntityOptional.isPresent()) {
                GoodsInvoicesEntity goodsInvoicesEntity = goodsInvoicesEntityOptional.get();
                goodsOrdersToSaveDTO.setGoodsInvoicesId(goodsInvoicesService.entityToDTO(goodsInvoicesEntity).getId());
                GoodsOrdersDTO createOrders = goodsOrdersService.createGoodsOrders(goodsOrdersToSaveDTO);

                return ResponseEntity.ok(createOrders);
            } else {
                return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити випадок, коли товар не знайдено
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити помилку
        }
    }
//    @PostMapping("/addToCart")
//    public ResponseEntity<GoodsOrdersDTO> addToCart(@RequestBody GoodsOrdersToSaveDTO goodsOrdersToSaveDTO){
////чи є в OrdersLists запис де поле Number пусте, а поле customerId==id покупця
////        якщо є такий запис то ordersListsId(Postman)= id цього запису
//// якщо немає то створюємо запис
//
//        return createGoodsOrders(goodsOrdersToSaveDTO);
//    }
//    @PostMapping("/createGoodsOrders")
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(
//            @RequestParam Long customersId,
//            @RequestParam Long goodsInvoicesId) {
//        try {
//            GoodsOrdersDTO createdGoodsOrder = goodsOrdersService.createGoodsOrders(customersId, goodsInvoicesId);
//            return ResponseEntity.ok(createdGoodsOrder);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
//        }
//    }


//    @PostMapping("/createGoodsOrders")
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(
//            @RequestParam Long customers_Id,
//            @RequestParam Long goodsInvoices_Id,
//            @RequestParam Integer quantity,
//            @RequestParam Float price) {
//        //ПЕРЕДЕЛЕГУВАТИ ЦЕ СЕРВІСУ
//        /**
//         public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody Map<String, Object> request) {
//         Long customers_Id = (Long) request.get("customers_Id");
//         Long goodsInvoices_Id = (Long) request.get("goodsInvoices_Id");
//         Integer quantity = (Integer) request.get("quantity");
//         Float price = (Float) request.get("price");
//         */
//        // Перевірте, чи існує запис з порожнім номером (number) та вказаним id клієнта
//        Long customerId = customers_Id;
//        Long orderListId;
//        ResponseEntity<Long> response = orderListsController.getOrderListId(customerId);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            orderListId = response.getBody();
//        } else {
//            // Якщо такого запису немає, створіть новий запис замовлення
//            OrdersListsDTO newOrderList = new OrdersListsDTO();
//            CustomersEntity customers = customersRepository.findById(customers_Id).orElse(null);
//            newOrderList.setCustomers(customers);
//            ResponseEntity<OrdersListsDTO> orderListResponse = orderListsController.createOrdersLists(newOrderList);
//            if (orderListResponse.getStatusCode() == HttpStatus.OK) {
//                orderListId = orderListResponse.getBody().getId();
//            } else {
//                // Обробити помилку створення запису замовлення
//                return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
//            }
//        }
//
//        // Тепер ви маєте `orderListId` і можете створити запис `GoodsOrdersEntity`
//        GoodsOrdersEntity goodsOrders = new GoodsOrdersEntity();
//        OrdersListsEntity ordersListsEntity=ordersListsRepository.findById(orderListId).orElse(null);
//        goodsOrders.setOrdersLists(ordersListsEntity);
//        GoodsInvoicesEntity goodsInvoicesEntity=goodsInvoicesRepository.findById(goodsInvoices_Id).orElse(null);
//        goodsOrders.setGoodsInvoices(goodsInvoicesEntity);
//        goodsOrders.setQuantity(quantity);
//        goodsOrders.setPrice(price);
//
//        // Додайте запис `GoodsOrdersEntity` та поверніть його
//        GoodsOrdersDTO createdGoodsOrder = OrderListsController.createGoodsOrder(goodsOrdersService.entityToDTO(goodsOrders));
//        return ResponseEntity.ok(createdGoodsOrder);
//    }


//    @PostMapping()
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody GoodsOrdersDTO goodsOrdersDTO) {
//        try {
//            GoodsOrdersDTO createdGoodsOrder = goodsOrdersService.createGoodsOrders(goodsOrdersDTO);
//            return ResponseEntity.ok(createdGoodsOrder);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
//        }
//    }
@PatchMapping("/{id}")
public ResponseEntity<GoodsOrdersDTO> updateGoodsOrdersQuantity(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
    try {
        Integer newQuantity = requestBody.get("quantity");
        GoodsOrdersDTO updatedGoodsOrder = goodsOrdersService.updateGoodsOrdersQuantity(id, newQuantity);

        // Додайте вашу логіку оновлення кількості тут
        // Наприклад, знайдіть товар за id і оновіть його кількість

        return ResponseEntity.ok(updatedGoodsOrder);//"Кількість товару успішно оновлено"
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
    }
}

//    @PatchMapping("/{id}")
//public ResponseEntity<GoodsOrdersDTO> updateGoodsOrdersQuantity(@PathVariable Long id, @RequestParam int quantity) {
//    try {
//        GoodsOrdersDTO updatedGoodsOrder = goodsOrdersService.updateGoodsOrdersQuantity(id, quantity);
//        return ResponseEntity.ok(updatedGoodsOrder);
//    } catch (GoodsOrdersNotFoundException e) {
//        return ResponseEntity.notFound().build();
//    }
//}

    @GetMapping//виправлено 18.10.23 18:10
    public ResponseEntity<List<GoodsOrdersDTO>> getAll(HttpServletRequest request) {
        List<GoodsOrdersDTO> goodsOrdersEntities = goodsOrdersService.getAll(request);
//        Iterable<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();
        return new ResponseEntity<>(goodsOrdersEntities, HttpStatus.OK);
    }

    @GetMapping("/getAllByCustomer")//22.10 23:30
    public ResponseEntity<Iterable<GoodsOrdersDTO>> getAllByCustomer(@RequestParam Long id) {
        Iterable<GoodsOrdersEntity> goodsOrdersByCustomer = goodsOrdersService.getAllByCustomer(id);
        Iterable<GoodsOrdersDTO> goodsOrdersDTOByCustomer = goodsOrdersService.convertToDTO(goodsOrdersByCustomer);
        return ResponseEntity.ok(goodsOrdersDTOByCustomer);
    }


    @GetMapping("/getOne")//18.10 після 21 00
    public ResponseEntity<GoodsOrdersDTO> getOneGoodsOrders(@RequestParam Long id) {//, HttpServletRequest request
        try {
            GoodsOrdersDTO dto = goodsOrdersService.getOne(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // or handle the error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoodsOrders(@PathVariable Long id) {
        try {

            goodsOrdersService.delete(id);

            return ResponseEntity.ok("Delete order");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}