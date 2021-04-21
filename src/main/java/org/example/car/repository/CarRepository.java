package org.example.car.repository;

import org.example.car.model.Car;
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
public interface CarRepository extends RevisionRepository<Car, Long, Long>,
    JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
}
