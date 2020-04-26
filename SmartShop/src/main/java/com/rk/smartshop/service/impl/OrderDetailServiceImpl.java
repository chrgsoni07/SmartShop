package com.rk.smartshop.service.impl;

import com.rk.smartshop.exception.ResourceNotFoundException;
import com.rk.smartshop.model.OrderDetail;
import com.rk.smartshop.repository.OrderDetailRepository;
import com.rk.smartshop.service.OrderDetailService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

  private static final Logger log = LoggerFactory.getLogger(OrderDetailServiceImpl.class);
  private final OrderDetailRepository orderDetailRepository;

  @Override
  public List<OrderDetail> getAllOrderDetails() {
    log.info("get all order details");

    return orderDetailRepository.getAll();
  }

  @Override
  public OrderDetail getById(Long orderDetailId) {
    log.info("get order detail by id {}", orderDetailId);

    Optional<OrderDetail> orderDetailsOption = orderDetailRepository.getById(orderDetailId);

    return orderDetailsOption.orElseThrow(() ->
        new ResourceNotFoundException("Order detail not found with id "+ orderDetailId));
  }

  @Override
  public void delete(Long orderDetailId) {
    log.info("delete order detail by id {}" , orderDetailId);

    getById(orderDetailId);

    orderDetailRepository.delete(orderDetailId);
  }

  @Override
  public OrderDetail update(Long orderDetailId, OrderDetail order) {
    log.info("update order detail by id {}, data {}", orderDetailId, order);

    orderDetailRepository.update(order, orderDetailId);

    return getById(orderDetailId);
  }

  @Override
  public OrderDetail create(OrderDetail orderDetail) {
    log.info("create order detail by data {}", orderDetail);

    Long id = orderDetailRepository.create(orderDetail);
    orderDetail.setOrderDetailId(id);

    return orderDetail;
  }
}
