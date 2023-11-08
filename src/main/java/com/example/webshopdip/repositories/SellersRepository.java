package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.SellersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellersRepository extends JpaRepository<SellersEntity, Long> {
    SellersEntity findByName (String name);
}
