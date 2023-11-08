package com.example.webshopdip.controllers;

import com.example.webshopdip.services.SellersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sellers")
@CrossOrigin
public class SellersController {

    @Autowired
    private SellersService sellersService;


    @GetMapping("/getOne")
    public ResponseEntity<?> getOneGood(@RequestParam Long id, HttpServletRequest request) {
        return sellersService.getOne(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return sellersService.getAll();
    }
}
