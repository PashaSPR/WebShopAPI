package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.GoodsOrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodsOrdersRepository extends JpaRepository<GoodsOrdersEntity, Long> {
    boolean existsByGoodsInvoicesIdAndOrdersListsId(Long goodsInvoicesId, Long ordersListsId);

    Optional<GoodsOrdersEntity> findByGoodsInvoicesIdAndOrdersListsId(Long goodsInvoicesId, Long ordersListsId);
}
