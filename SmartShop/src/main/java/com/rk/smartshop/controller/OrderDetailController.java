package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.OrderDetail;
import com.rk.smartshop.service.OrderDetailService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_VERSION + "/order-detail")
@RequiredArgsConstructor
@Slf4j
public class OrderDetailController {

  private final OrderDetailService orderDetailService;

  @ApiOperation(value = "Save new order.", response = OrderDetail.class)
  @PostMapping
  public OrderDetail createOrder(@RequestBody OrderDetail orderDetail) {

    log.debug("Create order detail{}.", orderDetail);

    return orderDetailService.create(orderDetail);
  }

  @ApiOperation(value = "Update order.", response = OrderDetail.class)
  @PutMapping("/{orderDetailId}")
  public OrderDetail updateOrder(@PathVariable("orderDetailId") Long orderDetailId, @RequestBody OrderDetail orderDetail) {
    log.debug("Create order with id {}.", orderDetailId);

    return orderDetailService.update(orderDetailId, orderDetail);
  }

  @ApiOperation(value = "Delete order by id.", response = ResponseEntity.class)
  @DeleteMapping("/{orderId}")
  public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") Long orderDetailId) {
    log.debug("Delete order by id {}.", orderDetailId);

    orderDetailService.delete(orderDetailId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get order by order id", response = OrderDetail.class)
  @GetMapping("/{orderId}")
  public OrderDetail getOrder(@PathVariable("orderId") Long orderDetailId) {

    return orderDetailService.getById(orderDetailId);
  }

  @ApiOperation(value = "Get all orders", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<OrderDetail> getAllOrderDetails() {

    return orderDetailService.getAllOrderDetails();
  }

}
