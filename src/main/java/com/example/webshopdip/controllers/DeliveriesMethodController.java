package com.example.webshopdip.controllers;

import com.example.webshopdip.entities.DeliveriesMethodEntity;
import com.example.webshopdip.repositories.DeliveriesMethodRepository;
import com.example.webshopdip.services.DeliveriesMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/deliveryMethod")///deliveriesMethod
public class DeliveriesMethodController {
    @Autowired
    DeliveriesMethodRepository deliveriesMethodRepository;
    @Autowired
    private DeliveriesMethodService deliveriesMethodService;

//    @PostMapping
//    public ResponseEntity<DeliveriesMethodEntity> createDeliveriesMethod(@RequestBody DeliveriesMethodEntity deliveriesMethodEntity) {
//        try {
//            DeliveriesMethodEntity createdDeliveriesMethod = deliveriesMethodService.createDeliveriesMethod(deliveriesMethodEntity);
//            return ResponseEntity.ok(createdDeliveriesMethod);
//        } catch (Exception ex) {
//            return ResponseEntity.badRequest().body(new DeliveriesMethodEntity());
//        }
//    }
    @PostMapping
    public ResponseEntity<DeliveriesMethodEntity> createDeliveriesMethod(@RequestParam String method) {
        try {
            DeliveriesMethodEntity entity = new DeliveriesMethodEntity();
            entity.setMethod(method);
            deliveriesMethodRepository.save(entity);
            return ResponseEntity.ok(entity);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new DeliveriesMethodEntity());
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<DeliveriesMethodEntity>> getAll() {
        Iterable<DeliveriesMethodEntity> deliveriesMethodEntities = deliveriesMethodRepository.findAll();
        for (DeliveriesMethodEntity entity :deliveriesMethodEntities){
            entity.getId();
            entity.getMethod();
            entity.getOrdersLists();
        }
        return ResponseEntity.ok(deliveriesMethodEntities);
    }

    @GetMapping("/getOne")
    public ResponseEntity<DeliveriesMethodEntity> getOneDeliveriesMethod(Long id) {
        try {
            Optional<DeliveriesMethodEntity> deliveriesMethodEntityOptional = deliveriesMethodRepository.findById(id);
            if(deliveriesMethodEntityOptional.isEmpty()){
                throw new Exception("Deliveries Method not found");
            }
            return ResponseEntity.ok(deliveriesMethodEntityOptional.get());

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new DeliveriesMethodEntity());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteDeliveryMethod(@PathVariable Long id){
        try{
            deliveriesMethodRepository.deleteById(id);
            return ResponseEntity.ok("Delete Delivery Method "+id);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
