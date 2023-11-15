package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.OrdersListsDTO;
import com.example.webshopdip.dtos.SubcategoriesGoodsDTO;
import com.example.webshopdip.entities.OrdersListsEntity;
import com.example.webshopdip.entities.PaymentsTypeEntity;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import com.example.webshopdip.exceptions.OrdersListsNotFoundException;
import com.example.webshopdip.repositories.PaymentsTypeRepository;
import com.example.webshopdip.repositories.SubcategoriesGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paymentsType")
public class PaymentsTypeControllers {//СЕРВІС І DTO ВІДСУТНІ
    @Autowired
    private PaymentsTypeRepository paymentsTypeRepository;

    @PostMapping
    public ResponseEntity<PaymentsTypeEntity> createPaymentsType(@RequestBody PaymentsTypeEntity paymentsTypeEntity) {
        try {
            PaymentsTypeEntity createdPaymentsType = paymentsTypeRepository.save(paymentsTypeEntity);
            return ResponseEntity.ok(createdPaymentsType);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new PaymentsTypeEntity());
        }
    }
//    @PostMapping//навіщо масив ордерів
//    public ResponseEntity<PaymentsTypeEntity> createPaymentsType(@RequestParam String p){
//        try {
//            PaymentsTypeEntity entity=new PaymentsTypeEntity();
//            entity.setPayment_type(p);
//            paymentsTypeRepository.save(entity);
//            return ResponseEntity.ok(entity);
//        }
//        catch(Exception ex){
//            return ResponseEntity.badRequest().body(new PaymentsTypeEntity());
//        }
//    }
//    @PostMapping
//    public ResponseEntity<PaymentsTypeEntity> createPaymentsType(@RequestBody PaymentsTypeEntity paymentsTypeEntity){
//        try {
//            PaymentsTypeEntity entity=new PaymentsTypeEntity();
//            entity.setPayment_type(paymentsTypeEntity.getPayment_type());
//            paymentsTypeRepository.save(entity);
//            return ResponseEntity.ok(entity);
//        }
//        catch(Exception ex){
//            return ResponseEntity.badRequest().body(new PaymentsTypeEntity());
//        }
//    }

    @GetMapping
    public ResponseEntity<Iterable<PaymentsTypeEntity>> getAll() {
        Iterable<PaymentsTypeEntity>  paymentsTypeEntities= paymentsTypeRepository.findAll();
        for (PaymentsTypeEntity paymentsType:paymentsTypeEntities){
            paymentsType.getId();
            paymentsType.getPayment_type();
            paymentsType.getOrdersLists();
        }
        return new ResponseEntity<>(paymentsTypeEntities, HttpStatus.OK);
    }
    @GetMapping("/getOne")
    public ResponseEntity<PaymentsTypeEntity> getOnePaymentsType(@RequestParam Long id) {
        try {
            Optional<PaymentsTypeEntity> paymentsTypeEntity= paymentsTypeRepository.findById(id);
            if (paymentsTypeEntity.isEmpty()) {
                throw new Exception("Payments Type not found");
            }
            //return optional.get();
            return ResponseEntity.ok(paymentsTypeEntity.get());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new PaymentsTypeEntity());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentsType(@PathVariable Long id) {
        try {

            paymentsTypeRepository.deleteById(id);

            return ResponseEntity.ok("Delete paymentsType");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
