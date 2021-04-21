package org.example.car.service;

import java.util.Optional;
import java.util.UUID;

import org.example.car.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by lovro.vrlec on Apr,2021
 */
public interface CarService {

  /**
   *
   * @param specification query cars.
   * @param pageable page.
   * @return Page with Car Objects.
   */
  Page<Car> findCars(Specification<Car> specification, Pageable pageable);

  /**
   *
   * @param uuid uuid of Car.
   * @return Optional Car.
   */
  Optional<Car> findCarByUUID(UUID uuid);

  /**
   *
   * @param id id of car.
   * @return Optional Car.
   */
  Optional<Car> findCarByID(long id);

  /**
   *
   * @param car car to add.
   * @return Car saved Car.
   */
  Car addCar(Car car);

  /**
   *
   * @param car car to update.
   * @return Car updated.
   */
  Car updateCar(Car car);
}
