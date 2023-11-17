package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.DeliveriesMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface DeliveriesMethodRepository extends JpaRepository<DeliveriesMethodEntity,Long> {

    DeliveriesMethodEntity findByMethod(String method);
}
