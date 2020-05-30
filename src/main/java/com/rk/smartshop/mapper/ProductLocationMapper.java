package com.rk.smartshop.mapper;

import com.rk.smartshop.model.ProductLocation;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductLocationMapper  implements RowMapper<ProductLocation> {

  @Override
  public ProductLocation mapRow(ResultSet resultSet, int i) throws SQLException {
    ProductLocation prodLocal = new ProductLocation();

    prodLocal.setId(resultSet.getLong("id"));
    prodLocal.setProductId(resultSet.getLong("product_id"));
    prodLocal.setLocationId(resultSet.getLong("location_id"));

    return prodLocal;
  }
}