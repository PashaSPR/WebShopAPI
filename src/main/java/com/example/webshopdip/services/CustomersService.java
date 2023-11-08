package com.example.webshopdip.services;

import com.example.webshopdip.entities.CustomersEntity;
import com.example.webshopdip.exceptions.CustomersNotFoundException;
import com.example.webshopdip.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;
    public CustomersEntity createCustomers(CustomersEntity customersEntity){
        return customersRepository.save(customersEntity);
    }
    public CustomersEntity getOne(Long id) throws CustomersNotFoundException {
        Optional<CustomersEntity> optional = customersRepository.findById(id);
        if(optional.isEmpty()){
            throw new CustomersNotFoundException("Користувача не знайдено");
        }
        return optional.get();
    }
    public void deleteCustomers(Long id) {
        customersRepository.deleteById(id);
    }

}