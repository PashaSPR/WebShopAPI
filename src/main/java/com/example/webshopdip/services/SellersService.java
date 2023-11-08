package com.example.webshopdip.services;

import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.dtos.SellersDTO;
import com.example.webshopdip.dtos.SubcategoriesGoodsDTO;
import com.example.webshopdip.entities.CustomersEntity;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.SellersEntity;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import com.example.webshopdip.exceptions.CustomersNotFoundException;
import com.example.webshopdip.exceptions.SellersNotFoundException;
import com.example.webshopdip.repositories.SellersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellersService {
    @Autowired
    private SellersRepository sellersRepository;
    public SellersEntity createSellers(SellersEntity sellersEntity){
        return sellersRepository.save(sellersEntity);
    }

    public ResponseEntity<?> getOne(Long id) {
        try {
            Optional<SellersEntity> optional = sellersRepository.findById(id);
            if (optional.isEmpty()) {
                throw new SellersNotFoundException("Користувача не знайдено");
            }
            SellersEntity seller = optional.get();
            return new ResponseEntity<>(entityToDTO(seller), HttpStatus.OK);
        } catch (SellersNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Помилка у виконанні запиту", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAll() {
        try {
            Iterable<SellersEntity> sellersEntities = sellersRepository.findAll(Sort.by(Sort.Order.asc("id")));
            return new ResponseEntity<>(sellersEntities, HttpStatus.OK);
        } catch (Exception e) {
            // Обробка помилки
            return new ResponseEntity<>("Помилка у виконанні запиту", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public void deleteSellers(Long id) {
        sellersRepository.deleteById(id);
    }

    public SellersDTO entityToDTO(SellersEntity entity) {
        SellersDTO dto = new SellersDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

}
