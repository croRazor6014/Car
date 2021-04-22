package org.example.car.service.impl;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.example.car.model.Order;
import org.example.car.repository.OrderRepository;
import org.example.car.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by lovro.vrlec on Apr,2021
 */

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  /**
   * @param specification query Orders.
   * @param pageable      page.
   * @return Page with Order Objects.
   */
  @Override
  public Page<Order> findOrders(final Specification<Order> specification, final Pageable pageable) {
    return orderRepository.findAll(specification,pageable);
  }

  /**
   * @param uuid uuid of Order.
   * @return Optional Order.
   */
  @Override
  public Optional<Order> findOrderByUUID(final UUID uuid) {
    return orderRepository.findOrderByUuidEquals(uuid);
  }

  /**
   * @param id id of Order.
   * @return Optional Order.
   */
  @Override
  public Optional<Order> findOrderByID(final long id) {
    return orderRepository.findById(id);
  }

  /**
   * @param order order to save.
   * @return Order saved Order.
   */
  @Override
  public Order addOrder(final Order order) {
    return null;
  }

  /**
   * @param order order to update.
   * @return Order updated.
   */
  @Override
  public Order updateOrder(final Order order) {
    return null;
  }
}
