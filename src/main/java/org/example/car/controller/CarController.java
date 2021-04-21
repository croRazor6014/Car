package org.example.car.controller;

import static org.example.car.config.RestURIConstants.CAR;
import static org.example.car.config.RestURIConstants.UUID;
import static org.example.car.util.ResponseHelper.responseHttpOk;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.example.car.model.Car;
import org.example.car.model.dto.CarDto;
import org.example.car.model.exception.NonExistentCarException;
import org.example.car.model.jsonviews.View;
import org.example.car.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lovro.vrlec on Apr,2021
 *
 * CarController is Rest API controller for Car object.
 */
@Slf4j
@Tag(name = "Car Controller", description = "Car Controller")
@RestController
@CrossOrigin
@RequestMapping(CAR)
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;
  private final ModelMapper modelMapper;

  /**
   * Rest API "/api/car/{id}" for fetching information about car object.
   *
   * @param uuid Car id
   * @return Car object {@link org.example.car.model.Car}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "Find Car by ID",
      description = "Returns a Car object",
      tags = {"Car"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Car not found.")})
  @GetMapping(value = UUID, produces = "application/json")
  public @ResponseBody
  ResponseEntity<Car> findCarByUUID(
      @Parameter(description = "UUID of the Car. Cannot be empty.", required = true)
      @PathVariable("uuid") final java.util.UUID uuid) throws NonExistentCarException {
    return responseHttpOk(carService.findCarByUUID(uuid).orElseThrow(NonExistentCarException::new));
  }

  /**
   * Rest API "/api/car" for saving new Car objects.
   *
   * @param carDto Car dto
   * @return Car object {@link org.example.car.model.Car}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "adds Car object",
      description = "Returns a Car object",
      tags = {"Car"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Car not found.")})
  @PostMapping( produces = "application/json")
  public @ResponseBody
  ResponseEntity<Car> addCar(
      @RequestBody CarDto carDto) {
    return responseHttpOk(carService.addCar(convertToEntity(carDto)));
  }

  /**
   * Rest API "/api/car" for fetching information about car objects.
   *
   * @param specification query params for Cars
   * @param pageable page
   * @return Car object {@link org.example.car.model.Car}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "Find Cars",
      description = "Returns a page with Car objects",
      tags = {"Car"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Cars not found.")})
  @GetMapping( produces = "application/json")
  public @ResponseBody
  ResponseEntity<Page<Car>> findCars(
      @And( {
                @Spec(path = "name", params = "name", spec = Like.class),
                @Spec(path = "uuid", params = "uuid", spec = EqualIgnoreCase.class)})
          Specification<Car> specification,
      Pageable pageable) {
    return responseHttpOk(carService.findCars(specification,pageable));
  }

  private Car convertToEntity(CarDto componentDTO) {
    return modelMapper.map(componentDTO, Car.class);
  }
}
