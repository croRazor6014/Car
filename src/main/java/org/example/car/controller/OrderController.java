package org.example.car.controller;

import static org.example.car.config.RestURIConstants.ORDER;
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
import org.example.car.model.Order;
import org.example.car.model.dto.CarDto;
import org.example.car.model.dto.OrderDto;
import org.example.car.model.exception.NonExistentCarException;
import org.example.car.model.exception.NonExistentOrderException;
import org.example.car.model.jsonviews.View;
import org.example.car.service.CarService;
import org.example.car.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lovro.vrlec on Apr,2021
 *
 * OrderController is Rest API controller for Order object.
 */

@Slf4j
@Tag(name = "Order Controller", description = "Order Controller")
@RestController
@CrossOrigin
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final ModelMapper modelMapper;

  /**
   * Rest API "/api/order/{uuid}" for fetching information about order object.
   *
   * @param uuid Order id
   * @return Order object {@link org.example.car.model.Order}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "Find Order by UUID",
      description = "Returns a Order object",
      tags = {"Order"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Order not found.")})
  @GetMapping(value = UUID, produces = "application/json")
  public @ResponseBody
  ResponseEntity<Order> findOrderByUUID(
      @Parameter(description = "UUID of the Order. Cannot be empty.", required = true)
      @PathVariable("uuid") final java.util.UUID uuid) throws NonExistentOrderException {
    return responseHttpOk(orderService.findOrderByUUID(uuid).orElseThrow(NonExistentOrderException::new));
  }

  /**
   * Rest API "/api/order/{uuid}" for saving updated Order objects.
   *
   * @param orderDto Order dto
   * @return Order object {@link org.example.car.model.Order
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "updates Order object",
      description = "Returns a Order object",
      tags = {"Order"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Order not found.")})
  @PutMapping(value = UUID, produces = "application/json")
  public @ResponseBody
  ResponseEntity<Order> updateOrder(
      @Parameter(description = "UUID of the Order. Cannot be empty.", required = true)
      @PathVariable("uuid") final java.util.UUID uuid,
      @RequestBody OrderDto orderDto) throws NonExistentOrderException {
    Order dbOrder = orderService.findOrderByUUID(uuid).orElseThrow(NonExistentOrderException::new);
    orderDto.setUuid(uuid);
    orderDto.setId(dbOrder.getId());
    return responseHttpOk(orderService.updateOrder(convertToEntity(orderDto)));
  }

  /**
   * Rest API "/api/order" for saving new Order objects.
   *
   * @param orderDto Order dto
   * @return Order object {@link org.example.car.model.Order}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "adds Order object",
      description = "Returns a Order object",
      tags = {"Order"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Order not found.")})
  @PostMapping(produces = "application/json")
  public @ResponseBody
  ResponseEntity<Order> addOrder(
      @RequestBody OrderDto orderDto) {
    return responseHttpOk(orderService.addOrder(convertToEntity(orderDto)));
  }

  /**
   * Rest API "/api/order" for fetching information about car objects.
   *
   * @param specification query params for Order
   * @param pageable page
   * @return Order object {@link org.example.car.model.Order}
   */
  @JsonView(View.Basic.class)
  @Operation(
      summary = "Find Order",
      description = "Returns a page with Order objects",
      tags = {"Order"})
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "successful operation"),
          @ApiResponse(responseCode = "404", description = "Order not found.")})
  @GetMapping(produces = "application/json")
  public @ResponseBody
  ResponseEntity<Page<Order>> findOrders(
      @And( {
                @Spec(path = "user.name", params = "nameUser", spec = Like.class),
                @Spec(path = "car.name", params = "nameCar", spec = Like.class)})
          Specification<Order> specification,
      Pageable pageable) {
    return responseHttpOk(orderService.findOrders(specification,pageable));
  }


  /**
   *
   * @param orderDto dto
   * @return Order from OrderDto
   */
  private Order convertToEntity(OrderDto orderDto) {
    return modelMapper.map(orderDto, Order.class);
  }
}
