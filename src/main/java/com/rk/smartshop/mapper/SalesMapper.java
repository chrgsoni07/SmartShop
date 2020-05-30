package com.rk.smartshop.mapper;

import com.rk.smartshop.model.Sale;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SalesMapper implements RowMapper<Sale> {

  @Override
  public Sale mapRow(ResultSet resultSet, int i) throws SQLException {
    Sale sale = new Sale();
    sale.setId(resultSet.getLong("id"));
    sale.setOrderId(resultSet.getLong("order_id"));
    sale.setSalesAmount(resultSet.getDouble("sales_amount"));
    sale.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime().withNano(0));

    return sale;
  }
}
