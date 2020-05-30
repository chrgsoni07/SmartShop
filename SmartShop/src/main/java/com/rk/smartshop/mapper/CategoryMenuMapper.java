package com.rk.smartshop.mapper;

import com.rk.smartshop.model.CategoryLevel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMenuMapper implements RowMapper<CategoryLevel> {

  @Override
  public CategoryLevel mapRow(ResultSet resultSet, int i) throws SQLException {
    CategoryLevel categoryMenuDTO = CategoryLevel.builder().build();

    categoryMenuDTO.setLvl1(resultSet.getString("lvl1"));
    categoryMenuDTO.setLvl2(resultSet.getString("lvl2"));
    categoryMenuDTO.setLvl3(resultSet.getString("lvl3"));
    categoryMenuDTO.setLvl4(resultSet.getString("lvl4"));

    return categoryMenuDTO;
  }
}
