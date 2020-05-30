package com.rk.smartshop.service;

import com.rk.smartshop.model.Order;
import java.util.List;

public interface OrderService {

  List<Order> getAllOrders();

  Order getById(Long orderId);

  void delete(Long orderId);

  Order update(Long orderId, Order order);

  Order create(Order order);
}
