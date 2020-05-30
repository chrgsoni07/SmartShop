package com.rk.smartshop.mapper;

import com.rk.smartshop.model.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper  implements RowMapper<Order> {


  @Override
  public Order mapRow(ResultSet resultSet, int i) throws SQLException {
    Order order = new Order();

    order.setId(resultSet.getLong("id"));
    order.setUserId(resultSet.getLong("user_id"));
    order.setShippingAddress(resultSet.getString("shipping_address"));
    order.setShippingStatus(resultSet.getString("shipping_status"));
    order.setShippingDate(resultSet.getTimestamp("shipping_date").toLocalDateTime().withNano(0));
    order.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime().withNano(0));

    return order;
  }
}
