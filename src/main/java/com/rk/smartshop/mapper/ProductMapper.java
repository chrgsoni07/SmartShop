package com.rk.smartshop.mapper;

import com.rk.smartshop.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements RowMapper<Product> {

  @Override
  public Product mapRow(ResultSet resultSet, int i) throws SQLException {
    Product product = new Product();

    product.setId(resultSet.getLong("id"));
    product.setName(resultSet.getString("name"));
    product.setDescription(resultSet.getString("description"));
    product.setImage(resultSet.getString("image"));
    product.setCategoryId(resultSet.getLong("category_id"));
    product.setQuantity(resultSet.getInt("quantity"));
    product.setBrand(resultSet.getString("brand"));
    product.setModel(resultSet.getString("model"));
    product.setConfiguration(resultSet.getString("configuration"));
    product.setPrice(resultSet.getDouble("price"));
    product.setFeature(resultSet.getString("featured"));
    product.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime().withNano(0));

    return product;
  }
}
