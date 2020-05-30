package com.rk.smartshop.mapper;

import com.rk.smartshop.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RowMapper<Category> {

  @Override
  public Category mapRow(ResultSet resultSet, int i) throws SQLException {
    Category category = new Category();
    category.setId(resultSet.getLong("id"));
    category.setName(resultSet.getString("name"));
    category.setParent(resultSet.getInt("parent"));
    
    return category;
  }
}
