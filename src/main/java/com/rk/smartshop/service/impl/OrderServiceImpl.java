package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.Order;
import com.rk.smartshop.repository.OrderRepository;
import com.rk.smartshop.service.OrderService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
  private final OrderRepository orderRepository;

  @Override
  public List<Order> getAllOrders() {
    log.info("get all orders");
    return orderRepository.getAll();
  }

  @Override
  public Order getById(Long orderId) {
    log.info("get orders by id {} ", orderId);

    Optional<Order> ordersOptional = orderRepository.getById(orderId);

    return ordersOptional.orElseThrow(() -> new ResourceNotFoundException("Orders not found with id "+orderId));
  }

  @Override
  public void delete(Long orderId) {
    log.info("delete order by id {} ", orderId);

    getById(orderId);

    orderRepository.delete(orderId);
  }

  @Override
  public Order update(Long orderId, Order order) {
    log.info("update order by id {}, and data {}", orderId, order);

   orderRepository.update(order, orderId);

   return getById(orderId);
  }

  @Override
  public Order create(Order order) {

    log.info("create order with data {}", order);

    Long id =  orderRepository.create(order);
    order.setId(id);

    return order;
  }
}
