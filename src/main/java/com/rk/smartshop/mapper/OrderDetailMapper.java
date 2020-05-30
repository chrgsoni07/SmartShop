package com.rk.smartshop.mapper;

import com.rk.smartshop.model.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper implements RowMapper<OrderDetail> {

  @Override
  public OrderDetail mapRow(ResultSet resultSet, int i) throws SQLException {

    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setOrderDetailId(resultSet.getLong("id"));
    orderDetail.setOrderId(resultSet.getLong("order_id"));
    orderDetail.setProductId(resultSet.getLong("product_id"));
    orderDetail.setQuantity(resultSet.getInt("quantity"));

    return orderDetail;
  }
}
