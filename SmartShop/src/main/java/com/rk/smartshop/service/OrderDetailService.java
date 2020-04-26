package com.rk.smartshop.service;

import com.rk.smartshop.model.OrderDetail;
import java.util.List;

public interface OrderDetailService {

  List<OrderDetail> getAllOrderDetails();

  OrderDetail getById(Long orderId);

  void delete(Long orderId);

  OrderDetail update(Long orderId, OrderDetail order);

  OrderDetail create(OrderDetail order);
}
