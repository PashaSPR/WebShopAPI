package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
//    @Query("SELECT g FROM GoodsEntity g WHERE g.name = :name")
//    GoodsEntity findBayName(String name);
    GoodsEntity findByName(String name);
    List<GoodsEntity> findBySubcategoriesGoods_CategoriesGoods_Id(Long id);
    List<GoodsEntity> findBySubcategoriesGoods_Id(Long id);
    List<GoodsEntity> findByGoodsInvoices_GoodsOrders_OrdersLists_Customers_Id(Long customerId);//  2023/11/05
    GoodsEntity findByIdAndGoodsInvoices_GoodsOrders_OrdersLists_Customers_Id(Long goodsId, Long customerId); //  2023/11/05
    GoodsEntity findByIdAndGoodsInvoices_GoodsOrders_OrdersLists_Customers_IdAndGoodsInvoices_GoodsOrders_OrdersLists_NumberIsNotNull(Long goodsId, Long customerId); // свм 2023/11/05 НЕробоччий пошук
}
