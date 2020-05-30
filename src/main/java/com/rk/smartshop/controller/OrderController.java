package com.rk.smartshop.controller;

import static com.rk.smartshop.util.Constant.API_VERSION;

import com.rk.smartshop.model.Order;
import com.rk.smartshop.service.OrderService;
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
@RequestMapping(API_VERSION + "/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
  private final OrderService orderService;

  @ApiOperation(value = "Save new order.", response = Order.class)
  @PostMapping
  public Order createOrder(@RequestBody Order order) {

    log.debug("Create order {}.", order);

    return orderService.create(order);
  }

  @ApiOperation(value = "Update order.", response = Order.class)
  @PutMapping("/{orderId}")
  public Order updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
    log.debug("Create order with id {}.", orderId);

    return orderService.update(orderId, order);
  }

  @ApiOperation(value = "Delete order by id.", response = ResponseEntity.class)
  @DeleteMapping("/{orderId}")
  public ResponseEntity<?> deleteOrder(@PathVariable(value = "orderId") Long orderId) {
    log.debug("Delete order by id {}.", orderId);

    orderService.delete(orderId);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Get order by order id", response = Order.class)
  @GetMapping("/{orderId}")
  public Order getOrder(@PathVariable("orderId") Long orderId) {

    return orderService.getById(orderId);
  }

  @ApiOperation(value = "Get all orders", response = ResponseEntity.class)
  @GetMapping("/all")
  public List<Order> getAllOrders() {

    return orderService.getAllOrders();
  }

}
