package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.EvaluationsGoodDTO;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.exceptions.CustomersNotFoundException;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.repositories.*;
import com.example.webshopdip.services.EvaluationsGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/evaluations")
@CrossOrigin
public class EvaluationsGoodController {

    @Autowired
    private EvaluationsGoodService evaluationsGoodService;

    @Autowired
    private EvaluationsGoodRepository evaluationsGoodRepository;

    @Autowired
    private OrdersListsRepository ordersListsRepository;

    @Autowired
    private GoodsInvoicesRepository goodsInvoicesRepository;

    @Autowired
    private GoodsOrdersRepository goodsOrdersRepository;
    @Autowired
    private GoodsRepository goodsRepository;


//    @PostMapping
//    public ResponseEntity createEvaluationsGood(@RequestBody EvaluationsGoodDTO evaluationsGoodDTO) throws CustomersNotFoundException, GoodsNotFoundException {
//
////        Long customerId = evaluationsGoodDTO.getCustomerId();
////        Long goodsId = evaluationsGoodDTO.getGoodsId();
////        List<OrdersListsEntity> ordersListsEntities = ordersListsRepository.findAll();
////        List<GoodsInvoicesEntity> goodsInvoicesEntities = goodsInvoicesRepository.findByGoodsId(goodsId);
////        List<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();
////        for (OrdersListsEntity ordersLists : ordersListsEntities) {
////            if (ordersLists.getCustomers().getId().equals(customerId)) {
////                for (GoodsOrdersEntity goodsOrders : goodsOrdersEntities) {
////                    if (goodsOrders.getOrdersLists().getId().equals(ordersLists.getId())) {
////                        for (GoodsInvoicesEntity goodsInvoices : goodsInvoicesEntities) {
////                            if (goodsInvoices.getGoods().getId().equals(goodsId)) {
////                                try {
////                                    EvaluationsGoodEntity evaluationsGood = evaluationsGoodService.createEvaluationsGoodFromDTO(evaluationsGoodDTO);
////                                    return ResponseEntity.ok(evaluationsGood);
////                                } catch (Exception e) {
////                                    return ResponseEntity.badRequest().body(e.getMessage());
////                                }
////                            } else {
////                                return ResponseEntity.badRequest().body("Товару немає в магазині!");
////                            }
////                        }
////                    } else {
////                        return ResponseEntity.badRequest().body("Покупець не купував товар, товар в корзині!");
////                    }
////                }
////            } else {
////                return ResponseEntity.badRequest().body("Покупець не купував товар!");
////            }
////        }
////        return ResponseEntity.badRequest().body("Дані відсутні!");
//
//
//        if(verificationGood(evaluationsGoodDTO).equals("Ok"))
//        {
//            EvaluationsGoodEntity evaluationsGood = evaluationsGoodService.createEvaluationsGoodFromDTO(evaluationsGoodDTO);
//            return ResponseEntity.ok(evaluationsGood);
//        }
//        else
//        {
//            return ResponseEntity.badRequest().body("Дані відсутні!");
//        }
//
////        try {
////            EvaluationsGoodEntity evaluationsGood = evaluationsGoodService.createEvaluationsGoodFromDTO(evaluationsGoodDTO);
////            return ResponseEntity.ok(evaluationsGood);
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
////        }
//   }
//
//    public ResponseEntity verificationGood(@RequestBody EvaluationsGoodDTO evaluationsGoodDTO) {
//
//        Long customerId = evaluationsGoodDTO.getCustomerId();
//        Long goodsId = evaluationsGoodDTO.getGoodsId();
//        List<OrdersListsEntity> ordersListsEntities = ordersListsRepository.findAll();
//        List<GoodsInvoicesEntity> goodsInvoicesEntities = goodsInvoicesRepository.findByGoodsId(goodsId);
//        List<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();
//        for (OrdersListsEntity ordersLists : ordersListsEntities) {
//            if (ordersLists.getCustomers().getId().equals(customerId)) {
//                for (GoodsOrdersEntity goodsOrders : goodsOrdersEntities) {
//                    if (goodsOrders.getOrdersLists().getId().equals(ordersLists.getId())) {
//                        for (GoodsInvoicesEntity goodsInvoices : goodsInvoicesEntities) {
//                            if (goodsInvoices.getGoods().getId().equals(goodsId)) {
//                                try {
////                                    EvaluationsGoodEntity evaluationsGood = evaluationsGoodService.createEvaluationsGoodFromDTO(evaluationsGoodDTO);
//                                    return ResponseEntity.ok("");
//                                } catch (Exception e) {
//                                    return ResponseEntity.badRequest().body(e.getMessage());
//                                }
//                            } else {
//                                return ResponseEntity.badRequest().body("Товару немає в магазині!");
//                            }
//                        }
//                    } else {
//                        return ResponseEntity.badRequest().body("Покупець не купував товар, товар в корзині!");
//                    }
//                }
//            } else {
//                return ResponseEntity.badRequest().body("Покупець не купував товар!");
//            }
//        }
//        return ResponseEntity.badRequest().body("Дані відсутні!");
//
//
////        try {
////            EvaluationsGoodEntity evaluationsGood = evaluationsGoodService.createEvaluationsGoodFromDTO(evaluationsGoodDTO);
////            return ResponseEntity.ok(evaluationsGood);
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
////        }
//    }

    //01.11.2023
    @PostMapping
    public ResponseEntity createEvaluationsGood(@RequestBody EvaluationsGoodDTO evaluationsGoodDTO) throws CustomersNotFoundException, GoodsNotFoundException {
//                String verificationResult = isGoodPurchased(evaluationsGoodDTO);
//        System.out.println("-------------------------------------- ");
//        System.out.println("GoodsId " + evaluationsGoodDTO.getGoodsId());
//        System.out.println("CustomerId " + evaluationsGoodDTO.getCustomerId());
//        System.out.println("Evaluation " + evaluationsGoodDTO.getEvaluation());
////        System.out.println("(evaluationsGoodDTO).equals(\"Ok\")" + isGoodPurchased(evaluationsGoodDTO).equals("Ok"));
//        System.out.println("-------------------------------------- ");

        if (isGoodPurchased(evaluationsGoodDTO).getStatusCode()== HttpStatus.OK) {
//            System.out.println("Equals Ok");
            EvaluationsGoodEntity evaluationsGood = evaluationsGoodService.createEvaluationsGoodFromDTO(evaluationsGoodDTO);
            return ResponseEntity.ok(evaluationsGood);
        } else {
            return ResponseEntity.badRequest().body(isGoodPurchased(evaluationsGoodDTO));
        }
    }

    @GetMapping("/isGoodPurchased")
    public ResponseEntity isGoodPurchased(EvaluationsGoodDTO evaluationsGoodDTO) {
        Long customerId = evaluationsGoodDTO.getCustomerId();
        Long goodsId = evaluationsGoodDTO.getGoodsId();

//        List<OrdersListsEntity> ordersListsEntities = ordersListsRepository.findByCustomers_Id(customerId);
//        List<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();
//        List<GoodsInvoicesEntity> goodsInvoicesEntities = goodsInvoicesRepository.findByGoodsId(goodsId);
//
//        List<GoodsEntity> goodsEntities = goodsRepository.findByGoodsInvoices_GoodsOrders_OrdersLists_Customers_Id(customerId);

        GoodsEntity goodsEntity = goodsRepository.findByIdAndGoodsInvoices_GoodsOrders_OrdersLists_Customers_Id(goodsId, customerId);
//        GoodsEntity goodsEntity = goodsRepository.findByIdAndGoodsInvoices_GoodsOrders_OrdersLists_Customers_IdAndGoodsInvoices_GoodsOrders_OrdersLists_NumberIsNotNull(goodsId, customerId);

        System.out.println("goodsEntity: " + goodsEntity.getName() + " id: " + goodsEntity.getId());
//        System.out.println("goodsEntityNumber: " + goodsEntityNumber.getName() + " id: " + goodsEntityNumber.getId());
        if(goodsEntity == null)
            System.out.println("goodsEntityNumber == null");
        else System.out.println("goodsEntityNumber: " + goodsEntity.getName() + " id: " + goodsEntity.getId());

        if(goodsEntity == null){
            System.out.println("Дані відсутні");
            return ResponseEntity.badRequest().body("");
        }
        else return ResponseEntity.ok(true);

//
//        for (OrdersListsEntity ordersLists : ordersListsEntities) {
//            System.out.println("CustomerId for orderList " + ordersLists.getCustomers().getId());
//            System.out.println("CustomerId " + customerId);
//            if (ordersLists.getCustomers().getId().equals(customerId)) {
//                for (GoodsOrdersEntity goodsOrders : goodsOrdersEntities) {
//                    System.out.println("OrdersLists for goodsOrders " + goodsOrders.getOrdersLists().getId());
//                    System.out.println("OrderListId " + ordersLists.getId());
//                    if (goodsOrders.getOrdersLists().getId().equals(ordersLists.getId())) {
//                        for (GoodsInvoicesEntity goodsInvoices : goodsInvoicesEntities) {
//                            System.out.println("goodsInvoices find by goodsId  " + goodsInvoices.getGoods().getId());
//                            System.out.println(("GoodsId " + goodsId));
//                            if (goodsInvoices.getGoods().getId().equals(goodsId)) {
//                                System.out.println("ResponseEntity.ok");
//                                return ResponseEntity.ok(true);
//                            } else {
//                                return ResponseEntity.badRequest().body("Товару немає в магазині!");
//                            }
//                        }
//                    } else {
//
//                        return ResponseEntity.badRequest().body("Покупець не купував товар, товар в корзині");
//                    }
//                }
//            }
//        }

//        System.out.println("Дані відсутні");
//        return ResponseEntity.badRequest().body("");
    }


    @GetMapping
    public String showEvaluations(Model model) {
        List<EvaluationsGoodEntity> evaluationsGoodEntities = evaluationsGoodService.getAllEvaluations();
        model.addAttribute("evaluations", evaluationsGoodEntities);
        return "evaluations/list"; // Передача імені шаблону для показу списку відгуків
    }
}