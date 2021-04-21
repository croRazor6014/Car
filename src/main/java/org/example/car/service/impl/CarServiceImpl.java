package org.example.car.service.impl;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.example.car.model.Car;
import org.example.car.repository.CarRepository;
import org.example.car.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  /**
   * @param specification query cars.
   * @param pageable      page.
   * @return Page with Car Objects.
   */
  @Override
  public Page<Car> findCars(final Specification<Car> specification, final Pageable pageable) {
    return carRepository.findAll(specification,pageable);
  }

  /**
   * @param uuid uuid of car.
   * @return Optional Car.
   */
  @Override
  public Optional<Car> findCarByUUID(final UUID uuid) {
    return carRepository.findCarByUuidEquals(uuid);
  }

  /**
   * @param id id of car.
   * @return Optional Car.
   */
  @Override
  public Optional<Car> findCarByID(final long id) {
    return carRepository.findById(id);
  }

  /**
   * @param car car to add.
   * @return Car saved Car.
   */
  @Override
  public Car addCar(final Car car) {
    car.setId(0L);
    car.setUuid(UUID.randomUUID());
    return carRepository.save(car);
  }

  /**
   * @param car car to update.
   * @return Car updated.
   */
  @Override
  public Car updateCar(final Car car) {
    return carRepository.save(car);
  }
}
