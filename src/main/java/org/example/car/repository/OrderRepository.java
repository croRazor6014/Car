package org.example.car.repository;

import java.util.Optional;
import java.util.UUID;

import org.example.car.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lovro.vrlec on Apr,2021
 */
@Repository
@Transactional
public interface OrderRepository extends RevisionRepository<Order, Long, Long>,
    JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

  Optional<Order> findOrderByUuidEquals(UUID uuid);
}
