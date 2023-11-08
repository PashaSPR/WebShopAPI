package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.GoodsOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsOrdersRepository extends JpaRepository<GoodsOrdersEntity, Long> {
}
