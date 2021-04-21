package org.example.car.service;

import java.util.Optional;
import java.util.UUID;

import org.example.car.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by lovro.vrlec on Apr,2021
 */
public interface OrderService {

  /**
   *
   * @param specification query Orders.
   * @param pageable page.
   * @return Page with Order Objects.
   */
  Page<Order> findOrders(Specification<Order> specification, Pageable pageable);

  /**
   *
   * @param uuid uuid of Order.
   * @return Optional Order.
   */
  Optional<Order> findOrderByUUID(UUID uuid);

  /**
   *
   * @param id id of Order.
   * @return Optional Order.
   */
  Optional<Order> findOrderByID(long id);

  /**
   *
   * @param order order to save.
   * @return Order saved Order.
   */
  Order addOrder(Order order);

  /**
   *
   * @param order order to update.
   * @return Order updated.
   */
  Order updateOrder(Order order);
}
