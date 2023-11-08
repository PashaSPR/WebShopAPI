package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.EvaluationsGoodEntity;
import com.example.webshopdip.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EvaluationsGoodRepository extends JpaRepository<EvaluationsGoodEntity, Long> {
    List<EvaluationsGoodEntity> findByGoodsId(Long id);
}